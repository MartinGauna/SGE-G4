package ar.edu.utn.frba.dds.dispositivo;

import ar.edu.utn.frba.dds.Cliente;
import ar.edu.utn.frba.dds.Consumo;
import ar.edu.utn.frba.dds.actuador.Actuador;
import ar.edu.utn.frba.dds.dispositivo.estadosDispositivo.Context;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
@Entity
@Inheritance(strategy=InheritanceType.JOINED)
@Table
public class DispositivoInteligente extends Dispositivo {

    @OneToMany(cascade = {CascadeType.ALL})
    @JoinColumn(name = "idDispositivo", referencedColumnName = "id")
    List<Consumo> consumo;
    //TODO: Cambiar actuador de interfaz a clase o implementarlo

    @OneToOne(fetch = FetchType.LAZY, targetEntity = Actuador.class)
    @JoinColumn(name = "idDispositivo", referencedColumnName = "id")
    Actuador actuador;

    @Transient
    List<Integer> periodos;

    @NotNull
    public int uso_minimo;
    @NotNull
    public int uso_maximo;

    protected  DispositivoInteligente() {
        super();
    }

    public DispositivoInteligente(String nombre, double consumoHora, String estado, int uso_maximo,int uso_minimo) {
        super(nombre, consumoHora, estado);
        this.uso_minimo = uso_minimo;
        this.uso_maximo = uso_maximo;
        this.consumo = new ArrayList<Consumo>();
        this.actuador = null;
        this.initializePeriods();
    }

    public void addConsumo(int watts, Date fechaInicio, Date fechaFinal) {
        //Create consumo Object
        Consumo consumoI = new Consumo(this, watts, fechaInicio, fechaFinal);

        //Add to Consumo list
        addConsumo(consumoI);
    }

    public void addConsumo(Consumo consumoI) {
         consumo.add(consumoI);
    }

    public List<Consumo> getConsumos() {
        return consumo;
    }

    public void encender() {
        this.estado = Context.string_Activo;
    }

    public void apagar() {
        this.estado = Context.string_Apagado;
    }

    public void cambiarAmodoAhorro() {
        this.estado = Context.string_Ahorrro;
    }

    public Actuador getActuador() {
        return actuador;
    }


    public void setActuador(Actuador actuador) {
        this.actuador = actuador;
    }

    @Override
    public int getConsumoTotal(Date fechaInicio, Date fechaFinal) {
        int total = 0;
        for(int i=0; i< consumo.size(); i++) {
            if((consumo.get(i).getFechaInicio().after(fechaInicio) || (consumo.get(i).getFechaFinal()).before(fechaFinal))) {
            	total += consumo.get(i).getWatts();
            }
        }
        return total;
    }
    @Override
    public int getConsumoTotal() {
        int total = 0;
        for(int i=0; i< consumo.size(); i++) {

                total += consumo.get(i).getWatts();

        }
        return total;
    }

    public void initializePeriods( ) {
    	periodos = new ArrayList<Integer>();
    	for (int i = 0; i < 12; i++) {
    		periodos.add(i, 0);
    	}
    }

    public void cleanPeriod() {
    	Date date = new Date();
    	Calendar c = Calendar.getInstance();
    	c.setTime(date);
    	c.add(Calendar.MONTH, -1);
    	int watts = 0;
    	for(int i = 0; i < consumo.size(); i++) {
    		if(consumo.get(i).getFechaInicio().after(c.getTime())) {
    			watts += consumo.get(i).getWatts();
    		}
    	}
    	periodos.add(c.get(Calendar.MONTH), watts);
    }

    public int getConsumoPeriodo(int mes) {
    	return periodos.get(mes);
    }
    public int getUso_minimo(){return this.uso_minimo;}
    public int getUso_maximo() {return this.uso_maximo;}

}