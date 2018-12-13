package web.models;

import ar.edu.utn.frba.dds.Consumo;
import ar.edu.utn.frba.dds.dispositivo.Dispositivo;
import ar.edu.utn.frba.dds.regla.Regla;
import web.models.views.EstadoDispositivosTable;
import web.models.views.MedicionesTable;
import web.models.views.ReglaTable;

import java.util.ArrayList;
import java.util.List;

public class EstadoHogarModel {

    // consumo
    private List<Consumo> consumos;
    private int consumoUltimo;
    private List<Dispositivo> dispositivos;

    //dispositivos
    private List<EstadoDispositivosTable> tableDispositivos;

    //mediciones
    private List<MedicionesTable> tableMediciones;

    //Reglas
    private List<ReglaTable> tableReglas;

    /*=== GETTERS AND SETTERS ===*/
    public List<Consumo> getConsumos() {
        return consumos;
    }

    public void setConsumos(List<Consumo> consumos) {
        this.consumos = consumos;
    }

    public int getConsumoUltimo() {
        return consumoUltimo;
    }

    public void setConsumoUltimo(int consumoUltimo) {
        this.consumoUltimo = consumoUltimo;
    }

    public List<ReglaTable> getReglas() {
        return tableReglas;
    }

    public void setReglas(List<ReglaTable> reglas) {
        this.tableReglas = reglas;
    }

    public List<Dispositivo> getDispositivos() {
        return dispositivos;
    }

    public void setDispositivos(List<Dispositivo> dispositivos) {
        this.dispositivos = dispositivos;
    }

    public List<EstadoDispositivosTable> getTableDispositivos() {
        return tableDispositivos;
    }

    public void setTableDispositivos(List<EstadoDispositivosTable> tableDispositivos) {
        this.tableDispositivos = tableDispositivos;
    }

    public List<MedicionesTable> getTableMediciones() {
        return tableMediciones;
    }

    public void setTableMediciones(List<MedicionesTable> tableMediciones) {
        this.tableMediciones = tableMediciones;
    }

    public EstadoHogarModel() {
        this.consumos = new ArrayList<>();
        consumoUltimo = 0;
        dispositivos = new ArrayList<>();
        tableReglas =  new ArrayList<>();
        tableDispositivos = new ArrayList<>();
        tableMediciones = new ArrayList<>();
    }
}