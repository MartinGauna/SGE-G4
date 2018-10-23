package ar.edu.utn.frba.dds.dispositivo.estadosDispositivo;

import java.util.NoSuchElementException;

public enum Context {
    string_Activo("Activo"),
    string_Apagado("Apagado"),
    string_Ahorrro("ahorro");

    private String state;

    /**
     * @param text
     */
    Context(String state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return state;
    }
}
