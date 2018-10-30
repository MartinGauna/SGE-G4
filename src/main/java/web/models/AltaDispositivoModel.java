package web.models;

import ar.edu.utn.frba.dds.dispositivo.*;
import web.models.views.DispositivosTable;

import java.util.List;


public class AltaDispositivoModel {

    private DispositivosTable dispositivosDisponibles;

    public AltaDispositivoModel() {
        dispositivosDisponibles = new DispositivosTable();
    }

    public List<Dispositivo> getDispositivos() {
        return this.dispositivosDisponibles.getDispositivosDisponibles();
    }


}

