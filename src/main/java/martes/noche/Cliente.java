package martes.noche;

;
import categoria.Categoria;

import java.time.LocalDate;
import java.util.List;

public class Cliente extends Persona {

    private String tipoDoc;
    private int numeroDoc;
    private double telefono;
    private Categoria categoria;
    private LocalDate fechaAltaServicio;
    private List<Dispositivo> dispositivos;

    public Cliente(String tipoDoc, int numeroDoc, int telefono, Categoria categoria, LocalDate fechaAltaServicio) {
        this.tipoDoc = tipoDoc;
        this.numeroDoc = numeroDoc;
        this.telefono = telefono;
        this.categoria = categoria;
        this.fechaAltaServicio = fechaAltaServicio;
    }

    public String getTipoDoc() {
        return tipoDoc;
    }

    public void setTipoDoc(String tipoDoc) {
        this.tipoDoc = tipoDoc;
    }

    public int getNumeroDoc() {
        return numeroDoc;
    }

    public void setNumeroDoc(int numeroDoc) {
        this.numeroDoc = numeroDoc;
    }

    public double getTelefono() {
        return telefono;
    }

    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public LocalDate getFechaAltaServicio() {
        return fechaAltaServicio;
    }

    public void setFechaAltaServicio(LocalDate fechaAltaServicio) {
        this.fechaAltaServicio = fechaAltaServicio;
    }

    public List<Dispositivo> getDispositivos() {
        return dispositivos;
    }
    public void setDispositivos(List<Dispositivo> dispositivos) {
        this.dispositivos = dispositivos;
    }
}
