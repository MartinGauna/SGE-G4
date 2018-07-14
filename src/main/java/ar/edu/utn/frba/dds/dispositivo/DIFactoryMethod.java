package ar.edu.utn.frba.dds.dispositivo;

public class DIFactoryMethod {


    DispositivoInteligente dispositivo = null;

    public DispositivoInteligente crearDispositivo(String nombre, double consumoHora, String estado,boolean esBajoConsumo) {
        switch (nombre) {
            case "Aire acondicionado 2200":
                dispositivo = new DispositivoInteligenteAAcondicionado2200(nombre, estado);
                break;
            case "Aire acondicionado 3500":
                dispositivo = new DispositivoInteligenteAAcondicionado3500(nombre, estado);
                break;
            case "Heladera Con Freezer":
                dispositivo = new DispositivoInteligenteHeladeraConFreezer(nombre, estado);
                break;
            case "Heladera Sin Freezer":
                dispositivo = new DispositivoInteligenteHeladeraSinFreezer(nombre, estado);
                break;
            case "Lavarropas 5kg":
                dispositivo = new DispositivoInteligenteLavarropas5(nombre,  estado);
                break;
            case "Luz 11W":
                dispositivo = new DispositivoInteligenteLuz11W(nombre, estado);
                break;
            case "Luz 15W":
                dispositivo = new DispositivoInteligenteLuz15W(nombre, estado);
                break;
            case "Luz 20W":
                dispositivo = new DispositivoInteligenteLuz20W(nombre, estado);
                break;
            case "Luz 40W":
                dispositivo = new DispositivoInteligenteLuz40W(nombre, estado);
                break;
            case "Luz 60W":
                dispositivo = new DispositivoInteligenteLuz60W(nombre, estado);
                break;
            case "Luz 100W":
                dispositivo = new DispositivoInteligenteLuz100W(nombre, estado);
                break;
            case "Microondas":
                dispositivo = new DispositivoInteligenteMicro(nombre, consumoHora, estado);
                break;
            case "PC Escritorio":
                dispositivo = new DispositivoInteligentePCEscritorio(nombre, estado);
                break;
            case "Plancha":
                dispositivo = new DispositivoInteligentePlancha(nombre, consumoHora, estado);
                break;
            case "Televisor 24":
                dispositivo = new DispositivoInteligenteTV24(nombre, estado);
                break;
            case "Televisor 32":
                dispositivo = new DispositivoInteligenteTV32(nombre, estado);
                break;
            case "Televisor 40":
                dispositivo = new DispositivoInteligenteTV40(nombre, estado);
                break;
            case "Ventilador Techo":
                dispositivo = new DispositivoInteligenteVentiladorTecho(nombre, estado);
                break;

        }
        return dispositivo;
    }
}
