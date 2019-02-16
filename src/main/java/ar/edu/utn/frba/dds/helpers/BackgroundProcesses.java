package ar.edu.utn.frba.dds.helpers;

import ar.edu.utn.frba.dds.Cliente;
import ar.edu.utn.frba.dds.dao.ClientDao;
//import com.sun.security.ntlm.Client;
import org.omg.CORBA.Request;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import static web.helper.SessionHelper.setSession;

public class BackgroundProcesses {

    List<Cliente>  allClients;
    ClientDao cdao = new ClientDao();

    public void automatizacionAhorroAutomatico(long miliseconds) {

    TimerTask task = new TimerTask() {
        @Override
        public void run() {
            allClients = cdao.list();
            for(int i = 0;i < allClients.size();i++)
            {
                allClients.get(i).ahorroAutomatico();
            }

        }
    };

    Timer timer = new Timer();

    // schedules the task to be run in an interval
    timer.schedule(task,0,miliseconds);
    }
}