package ar.edu.utn.frba.dds.dao;

import ar.edu.utn.frba.dds.Categoria;

import java.util.List;

public class CategoriaDao extends BaseDao {

    public CategoriaDao() {
    }

    public Categoria getCategoria(int id) {
        return getById(Categoria.class,id);
    }

    public List<Categoria> list() { return list(Categoria.class); }

    private Categoria categoriaExists(Categoria e) {
        return categoriaExists(e.getNombre());
    }

    private Categoria categoriaExists(String nombre) {
        return getByPropertyValue(Categoria.class, "nombre", nombre);
    }

    public void addCategoriaIfNotExists(Categoria c){
        Categoria c2 = categoriaExists(c);
        if (c2 != null){
            update(c2);
        }else{
            save(c);
        }
    }
}
