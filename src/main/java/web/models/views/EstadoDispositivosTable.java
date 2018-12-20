package web.models.views;

import ar.edu.utn.frba.dds.dispositivo.Dispositivo;

public class EstadoDispositivosTable {
    private Dispositivo dispositivo;
    private String estado;
    private double consumo;

    public EstadoDispositivosTable(Dispositivo d, String estado, double consumo) {
        this.dispositivo = d;
        this.estado = estado;
        this.consumo = consumo;
    }

    public EstadoDispositivosTable() {
    }

    public Dispositivo getDispositivo() {
        return dispositivo;
    }

    public String getEstado() {
        return estado;
    }

    public void setDispositivo(Dispositivo d) {
        this.dispositivo = d;
    }

    public void setEstado(String e) {
        this.estado = e;
    }

    public double getConsumo() {
        return consumo;
    }

    public void setConsumo(double consumo) {
        this.consumo = consumo;
    }
}
