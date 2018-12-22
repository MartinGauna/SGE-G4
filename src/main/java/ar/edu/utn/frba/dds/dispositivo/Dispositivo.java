package ar.edu.utn.frba.dds.dispositivo;

import ar.edu.utn.frba.dds.Cliente;
import ar.edu.utn.frba.dds.dispositivo.estadosDispositivo.Context;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Inheritance(strategy=InheritanceType.JOINED)
@Table
public class Dispositivo {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public int id;

    @NotNull
    private String nombre;
    @NotNull
    private double consumoHora;

    public void setEstado(String estado) {
        switch (estado) {
            case "ahorro":
                this.estado = Context.string_Ahorrro;
                break;
            case "apagado":
                this.estado = Context.string_Apagado;
                break;
            case "activo":
                this.estado = Context.string_Activo;
                break;
            default:
                this.estado = Context.string_Activo;
                break;
        }
    }

    @Enumerated(EnumType.STRING)
    @Column
    public Context estado;

    @OneToOne(fetch = FetchType.EAGER , targetEntity = Cliente.class)
    @JoinColumn(name = "idCliente", referencedColumnName = "id", foreignKey = @ForeignKey(name = "FK_CLIENTE"))
    private Cliente cliente;

    // Dispositivo
    public Dispositivo(){
        this.nombre = "";
        this.consumoHora = 0;
        this.estado = Context.string_Activo;
    }
    public Dispositivo(String nombre, double consumoHora, String estado) {
        this.nombre = nombre;
        setConsumoHora(consumoHora);
        switch (estado) {
            case "ahorro":
                this.estado = Context.string_Ahorrro;
                break;
            case "apagado":
                this.estado = Context.string_Apagado;
                break;
            case "activo":
                this.estado = Context.string_Activo;
                break;
            default:
                this.estado = Context.string_Activo;
                break;
        }
    }


    public int getId() {
        return id;
    }

    // Nombre
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    // Consumo por Hora
    public double getConsumoHora() {
        return consumoHora;
    }
    public void setConsumoHora(double consumoHora) {
        this.consumoHora = consumoHora;
    }

    public int getConsumoTotal(Date fechaInicio, Date fechaFinal) {return 0;}
    public int getConsumoTotal() {return 0;}

    // Estado del dispositivo.
    public Context getEstado() {
        return estado;

    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public int getUsoMinimo(){
        if(this instanceof DispositivoInteligenteAAcondicionado){return 90;}
        else if(this instanceof DispositivoInteligenteLuz){return 90;}
        else if(this instanceof DispositivoInteligenteTV){return 90;}
        else if(this instanceof DispositivoInteligenteLavarropas){return 6;}
        else if(this instanceof DispositivoInteligentePC){return 60;}
        else if(this instanceof DispositivoInteligenteMicro){return 3;}
        else if(this instanceof DispositivoInteligentePlancha){return 3;}
        else if(this instanceof DispositivoInteligenteVentilador){return 120;}
        else if(this instanceof DispositivoInteligenteHeladera){return 90;}
        else return 0;
    }
    public int getUsoMaximo(){
        if(this instanceof DispositivoInteligenteAAcondicionado){return 360;}
        else if(this instanceof DispositivoInteligenteLuz){return 360;}
        else if(this instanceof DispositivoInteligenteTV){return 360;}
        else if(this instanceof DispositivoInteligenteLavarropas){return 30;}
        else if(this instanceof DispositivoInteligentePC){return 360;}
        else if(this instanceof DispositivoInteligenteMicro){return 15;}
        else if(this instanceof DispositivoInteligentePlancha){return 30;}
        else if(this instanceof DispositivoInteligenteVentilador){return 360;}
        else if(this instanceof DispositivoInteligenteHeladera){return 360;}
        else return 0;
    }

    @Override
    public String toString() {
        return  "Dispositivo: \n"+
                "\tNombre: " + getNombre() + "\n" +
                "\tConsumo por Hora: " + getConsumoHora() + "\n" +
                "\tEstado: " + getEstado() + "\n";
    }

}
