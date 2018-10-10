package ar.edu.utn.frba.dds;

import ar.edu.utn.frba.dds.dispositivo.Dispositivo;
import ar.edu.utn.frba.dds.dispositivo.DispositivoInteligente;
import ar.edu.utn.frba.dds.dispositivo.Estandard;

import java.util.Date;
import java.util.List;

public class GeneradorReportes {

    GeneradorReportes instance = null;

    GeneradorReportes getInstance() {
        if(instance == null) {
            instance = new GeneradorReportes();
        }
        return instance;
    }

    String generarReporteConsumoHogar(Cliente cliente, Date fechaInicio, Date fechaFin) {
        String reporte;

        int consumoTotal = 0;

        List<Dispositivo> dispositivos = cliente.getDispositivos();

        for(int i = 0; i < dispositivos.size(); i++){
            Dispositivo dispositivo = dispositivos.get(i);
            consumoTotal += dispositivo.getConsumoTotal(fechaInicio, fechaFin);
        }

        reporte = "El horgar de: " + cliente.getApellido();

        if(consumoTotal == 0) {
            reporte = reporte + " no registro consumos.";
        } else {
            reporte = reporte + " registro un consumo de: " + consumoTotal + " watts.";
        }

        return reporte;
    }

    String generarReporteConsumoInteligente(Cliente cliente, Date fechaInicio, Date fechaFin) {
        String reporte;
        int consumoTotal = 0;
        int consumoPromedio = 0;
        List<DispositivoInteligente> dispositivoInteligentes = cliente.getDispositivosInteligentes();

        for(int i = 0; i < dispositivoInteligentes.size(); i++) {
            DispositivoInteligente disp = dispositivoInteligentes.get(i);
            consumoTotal += disp.getConsumoTotal(fechaInicio, fechaFin);
        }

        if(consumoTotal > 0){
            consumoPromedio = consumoTotal / dispositivoInteligentes.size();
        }

        reporte = "El hogar de: " + cliente.getApellido() + " tuvo un consumo promedio de: ";
        reporte = reporte + consumoPromedio + " watts en el periodo de: " + fechaInicio + " a: " + fechaFin;

        return reporte;
    }

    String generarReporteConsumoEstandard(Cliente cliente, Date fechaInicio, Date fechaFin) {
        String reporte;
        int consumoTotal = 0;
        int consumoPromedio = 0;
        List<Estandard> dispositivoEstandards = cliente.getDispositivosEstandars();

        for(int i = 0; i < dispositivoEstandards.size(); i++) {
            Estandard disp = dispositivoEstandards.get(i);
            consumoTotal += disp.getConsumoTotal(fechaInicio, fechaFin);
        }

        if(consumoTotal > 0){
            consumoPromedio = consumoTotal / dispositivoEstandards.size();
        }

        reporte = "El hogar de: " + cliente.getApellido() + " tuvo un consumo promedio de: ";
        reporte = reporte + consumoPromedio + " watts en el periodo de: " + fechaInicio + " a: " + fechaFin;

        return reporte;
    }

    String generarReporteTransformador(Transformador transformador, Date fechaInicio, Date fechaFin){
        String reporte;
        reporte = "El consumo del transformador fue: " + transformador.getConsumoTotal(fechaInicio, fechaFin) + " watts.";
        return reporte;
    }

}
