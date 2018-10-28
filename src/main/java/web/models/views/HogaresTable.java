package web.models.views;

import ar.edu.utn.frba.dds.Cliente;

public class HogaresTable {

    private Cliente cliente;
    private long consumoTotal;

    public HogaresTable(Cliente cliente, long consumoTotal) {
        this.cliente = cliente;
        this.consumoTotal = consumoTotal;
    }

    public HogaresTable() {
    }

    public Cliente getCliente() {
        return cliente;
    }

    public long getConsumoTotal() {
        return consumoTotal;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public void setConsumoTotal(long consumoTotal) {
        this.consumoTotal = consumoTotal;
    }

}
