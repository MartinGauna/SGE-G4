package martes.noche.jsonParser;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;

import java.util.List;
import java.lang.reflect.Type;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import martes.noche.Cliente;
import martes.noche.Dispositivo;
import martes.noche.Categoria;


import com.google.gson.reflect.TypeToken;


public  class JsonParser {
	
	public JsonParser() {}
	
    public  Cliente loadClientJSON(String path) throws IOException {

        Cliente cliente;
        try(Reader reader = new InputStreamReader(JsonParser.class.getResourceAsStream(path), "UTF-8")){
            Gson gson = new GsonBuilder().create();
            cliente = gson.fromJson(reader, Cliente.class);
            if(cliente.getCategoria() == null) {
                // TODO chequear si hace falta poner una categoria por default
                // asignar categoría R1 por default
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
            // TODO chequear si hace falta poner una categoria por default
//            if(clientes.getCategoria() == null) {
//                // asignar categoría R1 por default
//            }
        }
        return clientes;
    }
    
    public Dispositivo loadDispositivoJSON(String path) throws IOException {
        Dispositivo dispositivo;
        try(Reader reader = new InputStreamReader(JsonParser.class.getResourceAsStream(path), "UTF-8")){
            Gson gson = new GsonBuilder().create();
            dispositivo = gson.fromJson(reader, Dispositivo.class);
        }
        return dispositivo;
    }

    public List<Dispositivo> loadDispositivosJSON(String path) throws IOException {
        Type DispositivoListType = new TypeToken<List<Dispositivo>>() {}.getType();
        List<Dispositivo> dispositivos;
        try(Reader reader = new InputStreamReader(JsonParser.class.getResourceAsStream(path), "UTF-8")){
            Gson gson = new GsonBuilder().create();
            dispositivos = gson.fromJson(reader, DispositivoListType);
        }
        return dispositivos;
    }


    public List<Categoria> loadCategoriasJSON() throws IOException{
        Type CategoriasListType = new TypeToken<List<Categoria>>() {}.getType();
        List<Categoria> categorias;
        try(Reader reader = new InputStreamReader(JsonParser.class.getResourceAsStream("/categorias.json"), "UTF-8")){
            Gson gson = new GsonBuilder().create();
            categorias = gson.fromJson(reader, CategoriasListType);
        }
        return categorias;
    }
    

}
