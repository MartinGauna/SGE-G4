package web.models;

import ar.edu.utn.frba.dds.Consumo;
import ar.edu.utn.frba.dds.dispositivo.Dispositivo;
import ar.edu.utn.frba.dds.regla.Regla;

import java.util.ArrayList;
import java.util.List;

public class EstadoHogarModel {

    private List<Consumo> consumos;
    private Consumo consumoUltimo;
    private List<Dispositivo> dispositivos;
    private List<Regla> reglas;


    /*=== GETTERS AND SETTERS ===*/
    public List<Consumo> getConsumos() {
        return consumos;
    }

    public void setConsumos(List<Consumo> consumos) {
        this.consumos = consumos;
    }

    public Consumo getConsumoUltimo() {
        return consumoUltimo;
    }

    public void setConsumoUltimo(Consumo consumoUltimo) {
        this.consumoUltimo = consumoUltimo;
    }

    public List<Regla> getReglas() {
        return reglas;
    }

    public void setReglas(List<Regla> reglas) {
        this.reglas = reglas;
    }

    public List<Dispositivo> getDispositivos() {
        return dispositivos;
    }

    public void setDispositivos(List<Dispositivo> dispositivos) {
        this.dispositivos = dispositivos;
    }




    public EstadoHogarModel() {
        this.consumos = new ArrayList<Consumo>();
        consumoUltimo = new Consumo();
        dispositivos = new ArrayList<Dispositivo>();
        reglas =  new ArrayList<Regla>();
    }
}
