package web.models;

import ar.edu.utn.frba.dds.Transformador;
import com.google.gson.JsonArray;

import java.util.ArrayList;
import java.util.List;
import com.google.gson.*;
import org.json.JSONArray;
import org.json.JSONObject;



public class TransformadoresModel {

    private List<Transformador> transformadoresList;

    private JSONObject transformadoresJson;


    public List<Transformador> getTransformadoresList() {
        return transformadoresList;
    }

    public void setTransformadoresList(List<Transformador> transformadoresList) {
        this.transformadoresList = transformadoresList;
    }

    public JSONObject getTransformadoresJson() {
        return transformadoresJson;
    }

    public TransformadoresModel() {
        this.transformadoresList = new ArrayList<Transformador>();
    }

    public TransformadoresModel(List<Transformador> transformadores) {
        transformadoresJson = new JSONObject();
        JSONArray data = new JSONArray();


        this.transformadoresList = transformadores;
        transformadores.forEach(transformador -> {
            JSONObject tJson = new JSONObject();
            tJson.put("id", transformador.getId());
            tJson.put("lat", transformador.getLatitud());
            tJson.put("lng", transformador.getLongitud());
            tJson.put("consumo", transformador.getConsumoTotal());
            data.put(tJson);
        });

        transformadoresJson.put("data", data);



//        this.transformadoresString = gson.toJson(transformadores);
//        this.transformadoresJson = new ObjectMapper().readValue(gson.toJson(transformadores), JsonNode.class);
    }
}
