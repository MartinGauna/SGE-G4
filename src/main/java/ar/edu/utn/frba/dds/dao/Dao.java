package ar.edu.utn.frba.dds.dao;

import java.util.List;

/**
 * Created by gdpepp on 27/10/18.
 */
public interface Dao {

    <T> void delete(Class<?> clazz, T id);

    void delete(Object obj);

    <T> T getById(Class<T> clazz, Object id);

    <T> T getById(Class<T> clazz, Object id, boolean lock);

    void persist(Object obj);
    void merge(Object obj);

    <T> T getByPropertyValue(Class<T> clazz, String propertyName,
                             Object propertyValue);

    <T> List<T> getListByPropertyValue(Class<T> clazz, String propertyName,
                                       Object propertyValue);

    <T> List<T> listOrdered(Class<T> clazz, String orderField);

    <T> List<T> list(Class<T> clazz);

    void lock(Object entity);
}
