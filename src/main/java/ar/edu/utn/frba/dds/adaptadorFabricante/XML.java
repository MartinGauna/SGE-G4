package ar.edu.utn.frba.dds.adaptadorFabricante;

public class XML implements IAdapter{

    @Override
    public void enviarMensaje() {
        System.out.println("Envio XML");
    }

    @Override
    public void recibirMensaje() {
        System.out.println("XML");
    }

}
