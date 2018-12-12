package web;

import ar.edu.utn.frba.dds.*;
import ar.edu.utn.frba.dds.actuador.ActuadorAAcondicionado;
import ar.edu.utn.frba.dds.actuador.ActuadorHeladera;
import ar.edu.utn.frba.dds.actuador.ActuadorTV;
import ar.edu.utn.frba.dds.dao.*;
import ar.edu.utn.frba.dds.dispositivo.DispositivoInteligenteAAcondicionado;
import ar.edu.utn.frba.dds.dispositivo.DispositivoInteligenteHeladera;
import ar.edu.utn.frba.dds.dispositivo.DispositivoInteligenteTV;
import ar.edu.utn.frba.dds.dispositivo.Estandard;
import ar.edu.utn.frba.dds.jsonParser.JsonParser;
import ar.edu.utn.frba.dds.sensor.Sensor;

import javax.persistence.EntityTransaction;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static spark.Spark.staticFileLocation;

public class LoadData {

    private static BaseDao bdao = new BaseDao();
    private static AdminDao adao = new AdminDao();
    private static ClientDao cldao = new ClientDao();
    private static CategoriaDao cadao = new CategoriaDao();
    private static ZonaDao zdao = new ZonaDao();
    private static TransformadorDao tdao = new TransformadorDao();

    public static void Load() throws ParseException {
        List<Object> toPersist = new ArrayList<>();
        List<Categoria> categorias = null;
        List<Cliente> clientes = null;
        List<Administrador> admins = null;
        List<Estandard> dispositivos = null;
        List<Zona> zonas = null;
        List<Transformador> trafos = null;


        JsonParser jsonParser = new JsonParser();

        //Lista de Categorias
        try {
            categorias = jsonParser.loadCategoriasJSON();
            toPersist.addAll(categorias);
        } catch (
                IOException e) {
            e.printStackTrace();
        }

        // Lista de Dispositivos Estandard
        try {
            dispositivos = jsonParser.loadDispositivosEstandardJSON();
            dispositivos.forEach(disp -> System.out.println(disp.getClass().toString()));
        } catch (IOException e) {
            e.printStackTrace();
        }

        //Lista de zonas
        try {
            zonas = jsonParser.loadZonaJSON();
            toPersist.addAll(zonas);
        } catch (IOException e) {
            e.printStackTrace();
        }

        //Lista de transformadores
        try {
            trafos = jsonParser.loadTransformadorJSON();

            //asignamos zonas a los diferentes transformadores
            Transformador t1 = trafos.get(0);
            t1.setZona(zonas.get(0));
            Transformador t2 = trafos.get(1);
            t2.setZona(zonas.get(1));
            Transformador t3 = trafos.get(2);
            t3.setZona(zonas.get(1));

            toPersist.addAll(trafos);
        } catch (IOException e) {
            e.printStackTrace();
        }

        //lista de Administradores
        try {
            admins = jsonParser.loadAdministradoresJSON();
            toPersist.addAll(admins);
        } catch (IOException e) {
            e.printStackTrace();
        }

        //Lista de Clientes
        try {
            clientes = jsonParser.loadClientesJSON();

            //asignacion de categorias y transformadores
            Cliente c1 = clientes.get(0);

            Transformador t = trafos.get(0);

            c1.setTransformador(t);
            c1.setCategoria(categorias.get(0));

            Cliente c2 = clientes.get(1);
            c2.setTransformador(trafos.get(0));
            c2.setCategoria(categorias.get(1));

            Cliente c3 = clientes.get(2);
            c3.setTransformador(trafos.get(0));
            c3.setCategoria(categorias.get(2));

            Cliente c4 = clientes.get(3);
            c4.setTransformador(trafos.get(1));
            c4.setCategoria(categorias.get(1));

            Cliente c5 = clientes.get(4);
            c5.setTransformador(trafos.get(1));
            c5.setCategoria(categorias.get(2));

            toPersist.addAll(clientes);

        } catch (IOException e) {
            e.printStackTrace();
        }

        //creacion de dispositivos inteligentes


        //HELADERA
        DispositivoInteligenteHeladera dispositivo = new DispositivoInteligenteHeladera("heladera", 15.5, "activo", true);
        Cliente c1 = clientes.get(0);
        c1.addDispositivo(dispositivo);

        ActuadorHeladera actuador = new ActuadorHeladera(dispositivo);
        //dispositivo.setActuador(actuador);

        //consumos del dispositivo
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Date d = sdf.parse("12/12/2018");
        Date e = sdf.parse("18/12/2018");
        Consumo cons1 = new Consumo(dispositivo, 1200, d, e);
        Date f = sdf.parse("19/12/2018");
        Date g = sdf.parse("21/12/2018");
        Consumo cons2 = new Consumo(dispositivo, 2100, f, g);
        dispositivo.addConsumo(cons1);
        dispositivo.addConsumo(cons2);

        if(!dispositivo.getSensores().isEmpty()) {
            for (Sensor s : dispositivo.getSensores()) {
                toPersist.add(s.getMagnitud());
            }
        }

        List<Object> listToPersist = new ArrayList<>();
        listToPersist.add(actuador);
        listToPersist.add(dispositivo);
        listToPersist.add(cons1);
        listToPersist.add(cons2);
        listToPersist.add(dispositivo);
        toPersist.addAll(listToPersist);

        toPersist.add(c1);


        //TV
        DispositivoInteligenteTV tv = new DispositivoInteligenteTV("tv", 15.5, "activo", true);
        Cliente c2 = clientes.get(3);
        c2.addDispositivo(tv);

        ActuadorTV a2 = new ActuadorTV(tv);
        //dispositivo.setActuador(actuador);

        //consumos del dispositivo
        Date h = sdf.parse("01/12/2018");
        Consumo cons3 = new Consumo(tv, 2000, h, d);
        Consumo cons4 = new Consumo(tv, 700, f, g);
        tv.addConsumo(cons3);
        tv.addConsumo(cons4);

        if(!tv.getSensores().isEmpty()) {
            for (Sensor s : tv.getSensores()) {
                toPersist.add(s.getMagnitud());
            }
        }
            List<Object> list2 = new ArrayList<>();
        list2.add(a2);
        list2.add(tv);
        list2.add(cons3);
        list2.add(cons4);
        list2.add(tv);

        toPersist.addAll(list2);

        toPersist.add(c2);


        //Aire
        DispositivoInteligenteAAcondicionado aire = new DispositivoInteligenteAAcondicionado("heladera", 15.5, "activo", true);
        Cliente c3 = clientes.get(2);
        c3.addDispositivo(aire);

        ActuadorAAcondicionado a3 = new ActuadorAAcondicionado(aire);
        //dispositivo.setActuador(actuador);

        //consumos del dispositivo
        Consumo cons5 = new Consumo(aire, 1200, d, e);
        Consumo cons6 = new Consumo(aire, 2100, f, g);
        aire.addConsumo(cons5);
        aire.addConsumo(cons6);

        if(!aire.getSensores().isEmpty()) {
            for (Sensor s : aire.getSensores()) {
                toPersist.add(s.getMagnitud());
            }
        }

        List<Object> list3 = new ArrayList<>();
        list3.add(a3);
        list3.add(aire);
        list3.add(cons5);
        list3.add(cons6);
        list3.add(aire);

        toPersist.addAll(list3);

        toPersist.add(c3);

        bdao.persistList(toPersist);
    }
}
