package ar.edu.utn.frba.dds.dispositivo;

import ar.edu.utn.frba.dds.Cliente;
import ar.edu.utn.frba.dds.dispositivo.InstanciasDispositivo.*;
import ar.edu.utn.frba.dds.exception.IncompleteFormException;

import javax.swing.text.TabableView;

public class DIFactory {

    Dispositivo d = null;

    public Dispositivo crearDispositivoFromPOST(String tipo, String disp, String nombre, double consumo, String estado, Cliente cliente, boolean bajoConsumo) {

        try {
            if (tipo.equals("int")) {
                switch (disp) {
                    case "Aire":
                        d = new DispositivoInteligenteAAcondicionado(nombre, consumo, estado, bajoConsumo);
                        break;
                    case "Heladera":
                        d = new DispositivoInteligenteHeladera(nombre, consumo, estado, bajoConsumo);
                        break;
                    case "Lavarropas":
                        d = new DispositivoInteligenteLavarropas(nombre, consumo, estado, bajoConsumo);
                        break;
                    case "Luz":
                        d = new DispositivoInteligenteLuz(nombre, consumo, estado, bajoConsumo);
                        break;
                    case "Microondas":
                        d = new DispositivoInteligenteMicro(nombre, consumo, estado);
                        break;
                    case "PC":
                        d = new DispositivoInteligentePC(nombre, consumo, estado, bajoConsumo);
                        break;
                    case "Plancha":
                        d = new DispositivoInteligentePlancha(nombre, consumo, estado);
                        break;
                    case "TV":
                        d = new DispositivoInteligenteTV(nombre, consumo, estado, bajoConsumo);
                        break;
                    case "Ventilador":
                        d = new DispositivoInteligenteVentilador(nombre, consumo, estado, bajoConsumo);
                        break;
                    default:
                        d = null;
                }


            } else if (tipo.equals("est")) {
                d = new Estandard(nombre,(int)consumo,estado);
            }

        } catch (Exception ex) {
            throw new IncompleteFormException("Complete todos los campos");
        }

        if(d != null) {
            cliente.addDispositivo(d);
        }

        return d;
    }



    // TODO revisar si esto sigue siendo necesario. Solo se usa en ClienteTest

    public DispositivoInteligente crearDispositivo(String nombre, double consumoHora, String estado, boolean esBajoConsumo) {

        DispositivoInteligente d = null;

        switch (nombre) {
            case "Aire acondicionado 2200":
                d = new DispositivoInteligenteAAcondicionado2200(nombre, estado);
                break;
            case "Aire acondicionado 3500":
                d = new DispositivoInteligenteAAcondicionado3500(nombre, estado);
                break;
            case "Heladera Con Freezer":
                d = new DispositivoInteligenteHeladeraConFreezer(nombre, estado);
                break;
            case "Heladera Sin Freezer":
                d = new DispositivoInteligenteHeladeraSinFreezer(nombre, estado);
                break;
            case "Lavarropas 5kg":
                d = new DispositivoInteligenteLavarropas5(nombre, estado);
                break;
            case "Luz 11W":
                d = new DispositivoInteligenteLuz11W(nombre, estado);
                break;
            case "Luz 15W":
                d = new DispositivoInteligenteLuz15W(nombre, estado);
                break;
            case "Luz 20W":
                d = new DispositivoInteligenteLuz20W(nombre, estado);
                break;
            case "Luz 40W":
                d = new DispositivoInteligenteLuz40W(nombre, estado);
                break;
            case "Luz 100W":
                d = new DispositivoInteligenteLuz100W(nombre, estado);
                break;
            case "Microondas":
                d = new DispositivoInteligenteMicro(nombre, consumoHora, estado);
                break;
            case "PC Escritorio":
                d = new DispositivoInteligentePCEscritorio(nombre, estado);
                break;
            case "Plancha":
                d = new DispositivoInteligentePlancha(nombre, consumoHora, estado);
                break;
            case "Televisor 24":
                d = new DispositivoInteligenteTV24(nombre, estado);
                break;
            case "Televisor 32":
                d = new DispositivoInteligenteTV32(nombre, estado);
                break;
            case "Televisor 40":
                d = new DispositivoInteligenteTV40(nombre, estado);
                break;
            case "Ventilador Techo":
                d = new DispositivoInteligenteVentiladorTecho(nombre, estado);
                break;

        }
        return d;
    }

}
