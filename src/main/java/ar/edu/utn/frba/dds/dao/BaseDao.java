package ar.edu.utn.frba.dds.dao;

import ar.edu.utn.frba.dds.exception.DuplicateValueException;
import ar.edu.utn.frba.dds.factory.EntityFactory;
import ar.edu.utn.frba.dds.models.QueryModel;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.hibernate.Session;
import org.hibernate.exception.ConstraintViolationException;
import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.jpa.Search;

import javax.persistence.*;
import java.util.List;


public class BaseDao implements Dao {

    private EntityManager manager;
    private String connectionName = null;

    public BaseDao(){
    }

    public <T> void delete(Class<?> clazz, T id) {
        delete(getById(clazz,id));
    }

    @Override
    public void delete(Object obj) {
        obj = getManager().merge(obj);
        manager.remove(obj);
    }

    @Override
    public <T> T getById(Class<T> clazz, Object id) {
        return getById(clazz, id, false);
    }

    @Override
    public <T> T getById(Class<T> clazz, Object id, boolean lock) {
//TODO uno de los puntos donde salta el error de BD [issues/57]
        //        try {
            if (lock) {
                return getManager().find(clazz, id, LockModeType.PESSIMISTIC_WRITE);
            } else {
                return getManager().find(clazz, id);
            }
//        } catch (EOFException e) {
//
//        }

    }

    @Override
    public void persist(Object object) {
        getManager().persist(object);
    }

    @Override
    public void merge(Object object) {
        getManager().merge(object);
    }

    @Override
    public <T> T getByPropertyValue(Class<T> clazz, String propertyName, Object propertyValue) {
        String dotlessPropertyName = propertyName.replace(".", "");
        List<T> result = findByQuery(new QueryModel().setQuery(
                "SELECT o FROM " + clazz.getName() + " o WHERE " + propertyName
                        + "=:" + dotlessPropertyName)
                .setParamNames(new String[] { dotlessPropertyName })
                .setParamValues(new Object[] { propertyValue }));
        return getResult(result);

    }

    @Override
    public <T> List<T> getListByPropertyValue(Class<T> clazz, String propertyName,
                                              Object propertyValue) {
        String dotlessPropertyName = propertyName.replace(".", "");
        List<T> result = findByQuery(new QueryModel().setQuery(
                "SELECT o FROM " + clazz.getName() + " o WHERE " + propertyName
                        + "=:" + dotlessPropertyName)
                .setParamNames(new String[] { dotlessPropertyName })
                .setParamValues(new Object[] { propertyValue }));

        return result;

    }
    @Override
    public <T> List<T> listOrdered(Class<T> clazz, String orderField) {
        return findByQuery(new QueryModel().setQuery("from " + clazz.getName() + " order by "
                + orderField));
    }

    @Override
    public <T> List<T> list(Class<T> clazz) {
        return findByQuery(new QueryModel().setQuery("from " + clazz.getName()));
    }

    @Override
    public void lock(Object entity) {
        if (entity != null) {
            getEntityManager().lock(entity, LockModeType.PESSIMISTIC_WRITE);
        }
    }

    @SuppressWarnings("unchecked")
    protected <T> List<T> findByQuery(QueryModel details) {
        Query q = null;

        try {
            getManager().clear();
            //mavivo
            details.setCacheable(false);
            getManager().getEntityManagerFactory().getCache().evictAll();
            manager.setProperty("javax.persistence.cache.retrieveMode", CacheRetrieveMode.BYPASS);
            manager.setProperty("javax.persistence.cache.storeMode", CacheStoreMode.BYPASS);

            if (details.getQueryName() != null) {
                q = getManager().createNamedQuery(details.getQueryName());
            } else if (details.getQuery() != null) {
                q = getManager().createQuery(details.getQuery());
            } else {
                throw new IllegalArgumentException("Either query or query name must be set");
            }

            for (int i = 0; i < details.getParamNames().length; i++) {
                q.setParameter(details.getParamNames()[i], details.getParamValues()[i]);
            }
            if (details.getStart() > -1) {
                q.setFirstResult(details.getStart());
            }
            if (details.getCount() > -1) {
                q.setMaxResults(details.getCount());
            }

            //mavivo
            q.setHint("javax.persistence.cache.retrieveMode", CacheRetrieveMode.BYPASS); // skip the L2 cache
            getManager().getEntityManagerFactory().getCache().evictAll();

            return q.getResultList();
        }
        catch (NullPointerException e)
        {
            findByQuery(details);
            return q.getResultList();
        }
    }

    protected int executeQuery(String query, String[] names, Object[] args) {
        if (names == null) {
            names = new String[] {};
        }

        if (args == null) {
            args = new Object[] {};
        }

        Query q = manager.createQuery(query);
        for (int i = 0; i < names.length; i++) {
            q.setParameter(names[i], args[i]);
        }
        return q.executeUpdate();
    }

    protected Object getDelegate() {
        return getManager().getDelegate();
    }

    protected int executeNamedQuery(String name) {
        return getManager().createNamedQuery(name).executeUpdate();
    }

    protected <T> T getResult(List<T> result) {
        if (!result.isEmpty()) {
            return result.get(0);
        }

        return null;
    }

    protected EntityManager getEntityManager() {
        return manager;
    }

    protected FullTextEntityManager getFullTextEntityManager() {
        FullTextEntityManager fullTextEntityManager = Search
                .getFullTextEntityManager(getEntityManager());
        return fullTextEntityManager;
    }

    protected String escapeKeywords(String keywords) {
        keywords = keywords.trim();
        if (keywords.isEmpty()) {
            return keywords;
        }

        if (keywords.charAt(0) == '*') {
            if (keywords.length() == 1) {
                return "";
            } else {
                keywords = keywords.substring(1).trim();
            }
        }

        keywords = QueryParser.escape(keywords);

        return keywords;
    }

    protected void commit(){
        getManager().getTransaction().commit();
    }

    protected void rollback(){
        getManager().getTransaction().rollback();
    }

    public void close(){
        getManager().close();
    }

    protected void begin(){getManager().getTransaction().begin();}

    public void save(Object o) {
        try {
            begin();
            persist(o);
            commit();
        } catch (Exception ex) {
            rollback();
            handlerException(ex);
        }
        finally {
            close();
        }
    }

    private void handlerException(Exception ex){
        if (ex.getClass().equals(RollbackException.class)){
            if (ex.getCause().equals(PersistenceException.class)){
                handlerPersistException((PersistenceException)ex.getCause());
            }
            if(ex.getCause().getClass().equals(PersistenceException.class)){
                handlerPersistException((PersistenceException)ex.getCause());
            }
            throw new RollbackException();
        }
        if (ex.getClass().equals(PersistenceException.class)){
            handlerPersistException((PersistenceException) ex);
        }

    }

    private void handlerPersistException(PersistenceException ex){
        if (ex.getCause() instanceof ConstraintViolationException){
            throw new DuplicateValueException();
        }
    }

    public void update(Object o) {
        try {
            begin();
            merge(o);
            commit();
        } catch (Exception ex) {
            rollback();
        }
        finally {
            close();
        }
    }
    //Rename
    public void deleteElement(Object o){
        try {
            begin();
            delete(o);
            commit();
        }catch (Exception ex){
            rollback();
            handlerException(ex);
        }
        finally {
            close();
        }
    }

    protected EntityManager getManager() {
        if (this.manager == null || !this.manager.isOpen()){
            this.manager = EntityFactory.getEntityManager();
        }
        return manager;
    }

    public void persistList(List<Object> ol) {
        try {
            begin();
            ol.forEach(o -> persist(o));
            commit();
        } catch (Exception ex) {
            rollback();
        }
        finally {
            close();
        }
    }

    public void deleteList(List<Object> ol) {
        try {
            begin();
            ol.forEach(o -> delete(o));
            commit();
        } catch (Exception ex) {
            rollback();
        }
        finally {
            close();
        }
    }

}
