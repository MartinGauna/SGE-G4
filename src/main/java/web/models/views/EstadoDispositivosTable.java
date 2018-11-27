package web.models.views;

import ar.edu.utn.frba.dds.dispositivo.Dispositivo;

public class EstadoDispositivosTable {
    private Dispositivo dispositivo;
    private String estado;

    public EstadoDispositivosTable(Dispositivo d, String estado) {
        this.dispositivo = d;
        this.estado = estado;
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

}
