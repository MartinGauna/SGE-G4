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


    private static final BackgroundProcesses instance = new BackgroundProcesses();
    public boolean prendido;
    public int intervalo;
    public Timer timer = new Timer();
    public TimerTask task = new TimerTask() {
        @Override
        public void run() {
            List<Cliente>  allClients;
            ClientDao cdao = new ClientDao();

            allClients = cdao.list();
            for(int i = 0;i < allClients.size();i++)
            {
                allClients.get(i).ahorroAutomatico();
            }

        }
    };
    //private constructor to avoid client applications to use constructor
    private BackgroundProcesses(){}

    public static BackgroundProcesses getInstance(){
        return instance;
    }

    //timer.schedule(task,0,miliseconds);

}