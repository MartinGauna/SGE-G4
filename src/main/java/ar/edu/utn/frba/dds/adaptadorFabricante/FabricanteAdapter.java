package ar.edu.utn.frba.dds.adaptadorFabricante;

public class FabricanteAdapter {

    private IAdapter iAdapter;

    public FabricanteAdapter(String formato){
        switch (formato) {
            case "JSon":
                iAdapter = new JSon();
                break;
            case "XML":
                iAdapter = new XML();
                break;
        }
    }


    public void enviarMensaje(String formato) {
        switch (formato) {
            case "JSon":
                iAdapter.enviarMensaje();
                break;
            case "XML":
                iAdapter.enviarMensaje();
                break;
        }
    }


    public void recibirMensaje(String formato) {
        switch (formato) {
            case "JSon":
                iAdapter.recibirMensaje();
                break;
            case "XML":
                iAdapter.recibirMensaje();
                break;
        }
    }
}
