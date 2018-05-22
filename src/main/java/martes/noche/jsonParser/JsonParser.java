package martes.noche.jsonParser;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;

import java.util.List;
import java.lang.reflect.Type;
import java.util.Optional;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import martes.noche.*;


import com.google.gson.reflect.TypeToken;


public  class JsonParser {
	
	public JsonParser() {}

	// Cliente
    public  Cliente loadClientJSON(String path) throws IOException {
        Cliente cliente;
        try(Reader reader = new InputStreamReader(JsonParser.class.getResourceAsStream(path), "UTF-8")){
            Gson gson = new GsonBuilder().create();
            cliente = gson.fromJson(reader, Cliente.class);
            if(cliente.getCategoria().getNombre() == null) {
                // Categoria por default R1
                cliente.setCategoria(new Categoria("R1"));
            }
        }
        return cliente;
    }
    public List<Cliente> loadClientesJSON(String path) throws IOException {
        Type ClientListType = new TypeToken<List<Cliente>>() {}.getType();
        List<Cliente> clientes;
        try(Reader reader = new InputStreamReader(JsonParser.class.getResourceAsStream(path), "UTF-8")){
            Gson gson = new GsonBuilder().create();
            clientes = gson.fromJson(reader, ClientListType);
            clientes.forEach(cliente -> {
                if(cliente.getCategoria().getNombre() == null){
                    cliente.setCategoria(new Categoria("R1"));
                }
            });
        }
        return clientes;
    }

    // Administrador
    public  Administrador loadAdministradorJSON(String path) throws IOException {
        Administrador administrador;
        try(Reader reader = new InputStreamReader(JsonParser.class.getResourceAsStream(path), "UTF-8")){
            Gson gson = new GsonBuilder().create();
            administrador = gson.fromJson(reader, Administrador.class);
        }
        return administrador;
    }
    public List<Administrador> loadAdministradoresJSON(String path) throws IOException {
        Type AdminListType = new TypeToken<List<Administrador>>() {}.getType();
        List<Administrador> administradores;
        try(Reader reader = new InputStreamReader(JsonParser.class.getResourceAsStream(path), "UTF-8")){
            Gson gson = new GsonBuilder().create();
            administradores = gson.fromJson(reader, AdminListType);
        }
        return administradores;
    }

    // Dispositivo Estandar
    public List<Estandard> loadDispositivosEstandardJSON(String path) throws IOException {
        Type DispositivoListType = new TypeToken<List<Estandard>>() {}.getType();
        List<Estandard> dispositivos;
        try(Reader reader = new InputStreamReader(JsonParser.class.getResourceAsStream(path), "UTF-8")){
            Gson gson = new GsonBuilder().create();
            dispositivos = gson.fromJson(reader, DispositivoListType);
        }
        return dispositivos;
    }
    // Dispositivo Inteligente
    public List<DispositivoInteligente> loadDispositivosInteligentesJSON(String path) throws IOException {
        Type DispositivoListType = new TypeToken<List<DispositivoInteligente>>() {}.getType();
        List<DispositivoInteligente> dispositivos;
        try(Reader reader = new InputStreamReader(JsonParser.class.getResourceAsStream(path), "UTF-8")){
            Gson gson = new GsonBuilder().create();
            dispositivos = gson.fromJson(reader, DispositivoListType);
        }
        return dispositivos;
    }

    // Categorias
    public List<Categoria> loadCategoriasJSON() throws IOException{
        Type CategoriasListType = new TypeToken<List<Categoria>>() {}.getType();
        List<Categoria> categorias;
        try(Reader reader = new InputStreamReader(JsonParser.class.getResourceAsStream("/categorias.json"), "UTF-8")){
            Gson gson = new GsonBuilder().create();
            categorias = gson.fromJson(reader, CategoriasListType);
        }
        return categorias;
    }
    public Categoria loadCategoriaJSON(String id) throws IOException{
        Type CategoriasListType = new TypeToken<List<Categoria>>() {}.getType();
        List<Categoria> categorias;
        try(Reader reader = new InputStreamReader(JsonParser.class.getResourceAsStream("/categorias.json"), "UTF-8")){
            Gson gson = new GsonBuilder().create();
            categorias = gson.fromJson(reader, CategoriasListType);
        }
        return categorias.stream().filter(c->c.getNombre().equals(id)).findFirst().get();
    }
    

}
