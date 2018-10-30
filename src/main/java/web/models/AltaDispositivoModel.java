package web.models;

import ar.edu.utn.frba.dds.dispositivo.*;

import java.util.ArrayList;
import java.util.List;


public class AltaDispositivoModel {

    private List<Dispositivo> dispositivosDisponibles;

    public AltaDispositivoModel() {
    }

    public List<Dispositivo> getDispositivos() {

        dispositivosDisponibles = new ArrayList<>();
        dispositivosDisponibles.add(new DispositivoInteligenteAAcondicionado("Aire Acondicionado",0.0,"",false));
        dispositivosDisponibles.add(new DispositivoInteligenteHeladera("Heladera",0.0,"",false));
        dispositivosDisponibles.add(new DispositivoInteligenteLavarropas("Lavarropas",0.0,"",false));
        dispositivosDisponibles.add(new DispositivoInteligenteLuz("Luz",0.0,"",false));
        dispositivosDisponibles.add(new DispositivoInteligenteMicro("Microondas",0.0,""));
        dispositivosDisponibles.add(new DispositivoInteligentePC("PC",0.0,"",false));
        dispositivosDisponibles.add(new DispositivoInteligentePlancha("Plancha",0.0,""));
        dispositivosDisponibles.add(new DispositivoInteligenteTV("TV",0.0,"",false));
        dispositivosDisponibles.add(new DispositivoInteligenteVentilador("Ventilador",0.0,"",false));
        return dispositivosDisponibles;
    }


}

