package ar.edu.utn.frba.dds.jsonParser;

import ar.edu.utn.frba.dds.Administrador;
import ar.edu.utn.frba.dds.Categoria;
import ar.edu.utn.frba.dds.Cliente;
import ar.edu.utn.frba.dds.dispositivo.DispositivoInteligente;
import ar.edu.utn.frba.dds.dispositivo.Estandard;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.lang.reflect.Type;
import java.util.List;


public  class JsonParser {
	
	public JsonParser() {}

	// Cliente
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
}
