package ar.edu.utn.frba.dds.adaptadorFabricante;

public class JSon implements IAdapter{

    @Override
    public void enviarMensaje() {
        System.out.println("Envio JSON");
    }

    @Override
    public void recibirMensaje() {
        System.out.println("Recibo JSON");
    }
}
