package ar.edu.utn.frba.dds;

import ar.edu.utn.frba.dds.dao.ClientDao;
import ar.edu.utn.frba.dds.dao.ReporteDao;
import ar.edu.utn.frba.dds.dispositivo.Dispositivo;
import ar.edu.utn.frba.dds.dispositivo.DispositivoInteligente;
import ar.edu.utn.frba.dds.dispositivo.Estandard;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class GeneradorReportes {

    public static int CONSUMO_HOGAR=0;
    public static int CONSUMO_INTELIGENTE=1;
    public static int CONSUMO_ESTANDAR=2;

    public static GeneradorReportes instance = null;

    public static ReporteDao rdao = new ReporteDao();

    public static GeneradorReportes getInstance() {
        if(instance == null) {
            instance = new GeneradorReportes();
        }
        return instance;
    }

    public void generarReportes(List<Cliente> listCli, List<Transformador> listTrafo) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Date fechaInicio = sdf.parse("15/12/2018");
        Date fechaFin = sdf.parse("17/12/2018");
        Cliente cli;
        for (int i = 0; i < listCli.size(); i++ ){
            cli = listCli.get(i);
            rdao.addReporte(generarReporteConsumoHogar(cli, fechaInicio, fechaFin));
            rdao.addReporte(generarReporteConsumoEstandard(cli, fechaInicio, fechaFin));
            rdao.addReporte(generarReporteConsumoInteligente(cli, fechaInicio, fechaFin));
        }
        Transformador trafo;
        for (int i = 0; i < listTrafo.size(); i++){
            trafo = listTrafo.get(i);
            rdao.addReporteTrafo(generarReporteTransformador(trafo, fechaInicio, fechaFin));
        }
    }

    public Reporte generarReporteConsumoHogar(Cliente cliente, Date fechaInicio, Date fechaFin) {
        String reporte;

        int consumoTotal = 0;

        List<Dispositivo> dispositivos = cliente.getDispositivos();
        if(dispositivos != null) {
            for (int i = 0; i < dispositivos.size(); i++) {
                Dispositivo dispositivo = dispositivos.get(i);
                consumoTotal += dispositivo.getConsumoTotal(fechaInicio, fechaFin);
            }
        }
        reporte = "El horgar de: " + cliente.getApellido();

        if(consumoTotal == 0) {
            reporte = reporte + " no registro consumos.";
        } else {
            reporte = reporte + " registro un consumo de: " + consumoTotal + " watts.";
        }
        Reporte reporte1 = new Reporte(cliente.getId(), fechaInicio, fechaFin, reporte, CONSUMO_HOGAR);
        return reporte1;
    }

    public Reporte generarReporteConsumoInteligente(Cliente cliente, Date fechaInicio, Date fechaFin) {
        String reporte;
        int consumoTotal = 0;
        int consumoPromedio = 0;
        List<DispositivoInteligente> dispositivoInteligentes = cliente.getDispositivosInteligentes();
        if(dispositivoInteligentes != null) {
            for (int i = 0; i < dispositivoInteligentes.size(); i++) {
                DispositivoInteligente disp = dispositivoInteligentes.get(i);
                consumoTotal += disp.getConsumoTotal(fechaInicio, fechaFin);
            }
        }

        if(consumoTotal > 0){
            consumoPromedio = consumoTotal / dispositivoInteligentes.size();
        }

        reporte = "El hogar de: " + cliente.getApellido() + " tuvo un consumo promedio de: ";
        reporte = reporte + consumoPromedio + " watts en el periodo de: " + fechaInicio + " a: " + fechaFin;
        Reporte reporte1 = new Reporte(cliente.getId(), fechaInicio, fechaFin, reporte, CONSUMO_INTELIGENTE);
        return reporte1;
    }

    public Reporte generarReporteConsumoEstandard(Cliente cliente, Date fechaInicio, Date fechaFin) {
        String reporte;
        int consumoTotal = 0;
        int consumoPromedio = 0;
        List<Estandard> dispositivoEstandards = cliente.getDispositivosEstandars();
        if(dispositivoEstandards != null) {
            for (int i = 0; i < dispositivoEstandards.size(); i++) {
                Estandard disp = dispositivoEstandards.get(i);
                consumoTotal += disp.getConsumoTotal(fechaInicio, fechaFin);
            }
        }

        if(consumoTotal > 0){
            consumoPromedio = consumoTotal / dispositivoEstandards.size();
        }

        reporte = "El hogar de: " + cliente.getApellido() + " tuvo un consumo promedio de: ";
        reporte = reporte + consumoPromedio + " watts en el periodo de: " + fechaInicio + " a: " + fechaFin;
        Reporte reporte1 = new Reporte(cliente.getId(), fechaInicio, fechaFin, reporte, CONSUMO_ESTANDAR);
        return reporte1;
    }

    public ReporteTransformador generarReporteTransformador(Transformador transformador, Date fechaInicio, Date fechaFin){
        String reporte;
        reporte = "El consumo del transformador fue: " + transformador.getConsumoTotal(fechaInicio, fechaFin) + " watts.";
        ReporteTransformador reporteTransformador = new ReporteTransformador(transformador.getId(), fechaInicio, fechaFin, reporte);
        return reporteTransformador;
}

    public static ReporteDao getRdao() {
        return rdao;
    }

}
