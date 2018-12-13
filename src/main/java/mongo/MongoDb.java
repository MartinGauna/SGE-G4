package mongo;


import ar.edu.utn.frba.dds.Reporte;
import ar.edu.utn.frba.dds.ReporteTransformador;
import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoIterable;
import org.bson.Document;

import java.util.Arrays;


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
        .append("id", reporte.id)
        .append("fuente", "cliente")
        .append("generadorId", reporte.clienteId)
        .append("descripcion", reporte.reporte)
        .append("fechaInicio", reporte.fechaInicio)
        .append("fechaFin", reporte.fechaFin);
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
        .append("fechaFin", reporte.fechaFin);
        collection.insertOne(doc);
    }
}

