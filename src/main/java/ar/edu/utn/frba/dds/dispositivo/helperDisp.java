package ar.edu.utn.frba.dds.dispositivo;

public class helperDisp {

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String nombre;

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public boolean isBajoConsumo() {
        return bajoConsumo;
    }

    public void setBajoConsumo(boolean bajoConsumo) {
        this.bajoConsumo = bajoConsumo;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public double getConsumoHora() {
        return consumoHora;
    }

    public void setConsumoHora(double consumoHora) {
        this.consumoHora = consumoHora;
    }

    public double consumoHora;

    public String estado;

    public boolean bajoConsumo;

    public String tipo;

}
