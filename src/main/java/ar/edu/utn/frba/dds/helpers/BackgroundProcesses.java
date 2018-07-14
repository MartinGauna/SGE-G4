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
        }
    };

    Timer timer = new Timer();
    long delay = 0;
    long intervalPeriod = miliseconds; //daily 24 * 60 * 60 * 1000;

    // schedules the task to be run in an interval
    timer.scheduleAtFixedRate(task,delay,intervalPeriod);
    }
}