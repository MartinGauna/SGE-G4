package web.models;

import ar.edu.utn.frba.dds.Transformador;
import com.google.gson.JsonArray;

import java.util.ArrayList;
import java.util.List;
import com.google.gson.*;



public class TransformadoresModel {

    private List<Transformador> transformadoresList;
    private String transformadoresJson;

    public List<Transformador> getTransformadoresList() {
        return transformadoresList;
    }

    public void setTransformadoresList(List<Transformador> transformadoresList) {
        this.transformadoresList = transformadoresList;
    }

    public TransformadoresModel() {
        this.transformadoresList = new ArrayList<Transformador>();
    }

    public TransformadoresModel(List<Transformador> transformadores) {
        Gson gson = new Gson();

        this.transformadoresList = transformadores;
        this.transformadoresJson = gson.toJson(transformadores);
//        this.transformadoresJson = new ObjectMapper().readValue(gson.toJson(transformadores), JsonNode.class);
    }
}
