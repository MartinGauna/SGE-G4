package ar.edu.utn.frba.dds.dispositivo;

public class DIFactoryMethod {


    DispositivoInteligente dispositivo = null;

    public DispositivoInteligente crearDispositivo(String nombre, double consumoHora, String estado) {
        switch (nombre) {
            case "Aire acondicionado":
                dispositivo = new DispositivoInteligenteAAcondicionado(nombre, consumoHora, estado);
                break;
            case "Heladera":
                dispositivo = new DispositivoInteligenteHeladera(nombre, consumoHora, estado);
                break;
            case "Lavarropas":
                dispositivo = new DispositivoInteligenteLavarropas(nombre, consumoHora, estado);
                break;
            case "Microondas":
                dispositivo = new DispositivoInteligenteMicro(nombre, consumoHora, estado);
                break;
            case "PC":
                dispositivo = new DispositivoInteligentePC(nombre, consumoHora, estado);
                break;
            case "Plancha":
                dispositivo = new DispositivoInteligentePlancha(nombre, consumoHora, estado);
                break;
            case "Televisor":
                dispositivo = new DispositivoInteligenteTV(nombre, consumoHora, estado);
                break;
            case "Luz":
                dispositivo = new DispositivoInteligenteLuz(nombre, consumoHora, estado);
                break;
            case "Ventilador":
                dispositivo = new DispositivoInteligenteTV(nombre, consumoHora, estado);
                break;

        }
        return dispositivo;
    }
}
