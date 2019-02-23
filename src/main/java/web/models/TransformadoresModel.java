package web.models;

import ar.edu.utn.frba.dds.Transformador;
import ar.edu.utn.frba.dds.dao.DispositivoDao;
import org.json.JSONArray;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;



public class TransformadoresModel {

    private static DispositivoDao ddao = new DispositivoDao();

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
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            Date d = null;
            try {
                d = sdf.parse("01/01/2019");
            } catch (ParseException e) {
                e.printStackTrace();
            }
            tJson.put("consumo", transformador.getConsumoTotal(d, new Date()));
            data.put(tJson);
        });

        transformadoresJson.put("data", data);



//        this.transformadoresString = gson.toJson(transformadores);
//        this.transformadoresJson = new ObjectMapper().readValue(gson.toJson(transformadores), JsonNode.class);
    }

}
