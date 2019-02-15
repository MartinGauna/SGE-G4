package ar.edu.utn.frba.dds.helpers;

import ar.edu.utn.frba.dds.Cliente;
import ar.edu.utn.frba.dds.dao.ClientDao;
import org.omg.CORBA.Request;

import java.util.Timer;
import java.util.TimerTask;
import static web.helper.SessionHelper.setSession;

public class BackgroundProcesses {

    Cliente currentClient = new Cliente();
    ClientDao cdao = new ClientDao();

    public void automatizacionAhorroAutomatico(spark.Request request, long miliseconds) {
    String userSession = request.session().attribute("user");
    Integer userID = Integer.parseInt(userSession.substring(0, userSession.indexOf("-")));

    TimerTask task = new TimerTask() {
        @Override
        public void run() {
            currentClient = cdao.getCliente(userID);

            currentClient.ahorroAutomatico();
        }
    };

    Timer timer = new Timer();

    // schedules the task to be run in an interval
    timer.schedule(task,0,miliseconds);
    }
}