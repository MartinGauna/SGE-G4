package martes.noche.json;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.json.JSONObject;

import martes.noche.App;
import martes.noche.Cliente;
import martes.noche.Dispositivo;
import martes.noche.categoria.Categoria;

public class Json {
	
	public Json() {
		
	}
	
    public Cliente loadClientJSON(String path) throws IOException {
    	
        File file = new File(path);
        String content = FileUtils.readFileToString(file, "utf-8");
        JSONObject dispJsonObject = new JSONObject(content);
        Cliente cliente = new Cliente();
        
        cliente.setNombreCompleto(dispJsonObject.getString("nombre"));
        cliente.setDomicilio(dispJsonObject.getString("domicilio"));
        cliente.setUsername(dispJsonObject.getString("usuario"));
        cliente.setPassword(dispJsonObject.getString("password"));
        cliente.setTipoDoc(dispJsonObject.getString("tipoDoc"));
        cliente.setNumeroDoc(dispJsonObject.getInt("numeroDoc"));
        cliente.setTelefono(dispJsonObject.getInt("telefono"));
        String catName = dispJsonObject.getString("categoria");
        Categoria cat = App.getInstance().getCategoriaByName(catName);
        if(cat != null) {
        	cliente.setCategoria(cat);
        } else {
        	/// TODO: Ver que hacer cuando no encuentra la categoria
        	cat = App.getInstance().getCategoriaByName("R1");
        }
        String altaServicio = dispJsonObject.getString("fechaAltaServicio");
        
        return cliente;
        
    }
    
    public Dispositivo loadDispositivoJSON(String path) throws IOException {

        File file = new File(path);
        String content = FileUtils.readFileToString(file, "utf-8");
        JSONObject dispJsonObject = new JSONObject(content);
        Dispositivo disp = new Dispositivo();
        
        disp.setNombre(dispJsonObject.getString("nombre"));
        disp.setConsumoHora(dispJsonObject.getInt("consumoHora"));
        disp.setEstado(dispJsonObject.getString("estado"));
        
        return disp;
    }
    
    

}
