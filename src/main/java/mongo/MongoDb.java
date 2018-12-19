package mongo;


import ar.edu.utn.frba.dds.Reporte;
import ar.edu.utn.frba.dds.ReporteTransformador;
import ar.edu.utn.frba.dds.dao.ReporteDao;
import com.mongodb.DBCursor;
import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.client.*;
import org.bson.Document;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;


public class MongoDb {

    public static MongoDb getInstance() {
        if(instance == null) {
            instance = new MongoDb();
            instance.connectDb();
        }
        return instance;
    }

    static MongoDb instance = null;

    MongoDatabase database;

    static String collectionName = "reportes";

    public void connectDb() {

        // Creating Credentials
        MongoCredential credential;
        credential = MongoCredential.createCredential("sge", "heroku_6bwbb2f3",
                "Dds2018".toCharArray());
        System.out.println("Connected to the database successfully");

        ServerAddress serverAddress = new ServerAddress("ds141661.mlab.com", 41661);

        MongoClient mongo = new MongoClient(serverAddress, Arrays.asList(credential));
        // Accessing the database
        this.database = mongo.getDatabase("heroku_6bwbb2f3");
        System.out.println("Credentials ::" + credential);
    }

    public boolean collectionExists(String collectionName) {

        final MongoIterable<String> iterable = database.listCollectionNames();
        try (final MongoCursor<String> it = iterable.iterator()) {
            while (it.hasNext()) {
                if (it.next().equalsIgnoreCase(collectionName)) {
                    return true;
                }
            }
        }

        return false;
    }

    public void guardarReporte(Reporte reporte) {
        if (!collectionExists(collectionName)) {
            database.createCollection(collectionName);
        }
        MongoCollection<Document> collection = database.getCollection(collectionName);
        Document doc = new Document()
        .append("fuente", "cliente")
        .append("generadorId", reporte.clienteId)
        .append("descripcion", reporte.reporte)
        .append("fechaInicio", reporte.fechaInicio)
        .append("fechaFin", reporte.fechaFin)
        .append("type", reporte.type);
        collection.insertOne(doc);
    }

    public void guardarReporteTrafo(ReporteTransformador reporte){
        if (!collectionExists(collectionName)) {
            database.createCollection(collectionName);
        }
        MongoCollection<Document> collection = database.getCollection(collectionName);
        Document doc = new Document()
        .append("fuente", "transformador")
        .append("generadorId", reporte.transformadorId)
        .append("descripcion", reporte.reporte)
        .append("fechaInicio", reporte.fechaInicio)
        .append("fechaFin", reporte.fechaFin)
        .append("type", 0);
        collection.insertOne(doc);
    }

    public List<Reporte> getReportesClientes(){
        MongoCollection<Document> col = database.getCollection(collectionName);
        List<Reporte> listReport = new ArrayList<Reporte>();
        try (MongoCursor<Document> cur = col.find().iterator()) {
            while (cur.hasNext()) {

                Document doc = cur.next();

                List list = new ArrayList(doc.values());
                if (list.get(1).equals("cliente")){
                    Date fechaInicio = (Date) list.get(4);
                    Date fechaFin = (Date) list.get(5);
                    Reporte rep = new Reporte((int)list.get(2), fechaInicio, fechaFin
                            ,(String)list.get(3),(int) list.get(6));
                    listReport.add(rep);
                }
            }
        }
        return listReport;
    }

    public List<ReporteTransformador> getReportesTranformadores(){
        MongoCollection<Document> col = database.getCollection(collectionName);
        List<ReporteTransformador> listReport = new ArrayList<ReporteTransformador>();
        try (MongoCursor<Document> cur = col.find().iterator()) {
            while (cur.hasNext()) {

                Document doc = cur.next();

                List list = new ArrayList(doc.values());
                if (list.get(1).equals("transformador")){
                    Date fechaInicio = (Date) list.get(4);
                    Date fechaFin = (Date) list.get(5);
                    ReporteTransformador rep = new ReporteTransformador((int)list.get(2), fechaInicio, fechaFin
                            ,(String)list.get(3));
                    listReport.add(rep);
                }
            }
        }
        return listReport;
    }
}

