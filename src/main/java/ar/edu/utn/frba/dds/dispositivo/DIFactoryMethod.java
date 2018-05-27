package ar.edu.utn.frba.dds.dispositivo;

public class DIFactoryMethod {


    DispositivoInteligente dispositivo = null;

    public DispositivoInteligente crearDispositivo(String nombre, int consumoHora, String estado) {
        switch (nombre) {
            case "Aire acondicionado":
                dispositivo = new DispositivoInteligenteAAcondicionado(nombre, consumoHora, estado);
                break;
            case "televisor":
                dispositivo = new DispositivoInteligenteTV(nombre, consumoHora, estado);
                break;
            case "Heladera":
                dispositivo = new DispositivoInteligenteHeladera(nombre, consumoHora, estado);
                break;
            case "Luz":
                dispositivo = new DispositivoInteligenteLuz(nombre, consumoHora, estado);
                break;
        }
        return dispositivo;
    }
}