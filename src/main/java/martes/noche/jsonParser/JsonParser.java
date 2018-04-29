package martes.noche.jsonParser;

import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;

import org.apache.commons.io.FileUtils;
import org.json.JSONObject;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

//import martes.noche.App;
import martes.noche.Cliente;
import martes.noche.Dispositivo;
//import martes.noche.categoria.Categoria;

import java.lang.reflect.Type;
import java.util.List;

import com.google.gson.reflect.TypeToken;


public class JsonParser {
	
	public JsonParser() {
		
	}
	
    public Cliente loadClientJSON(String path) throws IOException {

        Cliente cliente;
        try(Reader reader = new InputStreamReader(JsonParser.class.getResourceAsStream(path), "UTF-8")){
            Gson gson = new GsonBuilder().create();
            cliente = gson.fromJson(reader, Cliente.class);
            if(cliente.getCategoria() == null) {
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
    

}
