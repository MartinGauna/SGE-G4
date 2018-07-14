package ar.edu.utn.frba.dds.helpers;

import ar.edu.utn.frba.dds.Cliente;

import java.util.Timer;
import java.util.TimerTask;

public class BackgroundProcesses {

    public void automatizacionAhorroAutomatico(Cliente cliente,long miliseconds) {

    TimerTask task = new TimerTask() {
        @Override
        public void run() {
            cliente.ahorroAutomatico();
            System.out.println("Automatic process ran.");
        }
    };

    Timer timer = new Timer();

    // schedules the task to be run in an interval
    timer.schedule(task,0,miliseconds);
    }
}