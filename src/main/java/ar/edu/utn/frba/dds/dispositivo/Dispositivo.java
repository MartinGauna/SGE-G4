package ar.edu.utn.frba.dds.dispositivo;

import ar.edu.utn.frba.dds.Cliente;
import ar.edu.utn.frba.dds.Consumo;
import ar.edu.utn.frba.dds.Transformador;
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

    @Override
    public String toString() {
        return  "Dispositivo: \n"+
                "\tNombre: " + getNombre() + "\n" +
                "\tConsumo por Hora: " + getConsumoHora() + "\n" +
                "\tEstado: " + getEstado() + "\n";
    }

}
