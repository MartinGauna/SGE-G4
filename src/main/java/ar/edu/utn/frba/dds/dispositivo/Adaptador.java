package ar.edu.utn.frba.dds.dispositivo;

import ar.edu.utn.frba.dds.Categoria;
import ar.edu.utn.frba.dds.Consumo;
import ar.edu.utn.frba.dds.Transformador;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
@Table
@Entity
public class Adaptador {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    int id;

    @OneToOne(fetch = FetchType.LAZY, targetEntity = Estandard.class)
    @JoinColumn(name = "idDispositivo", referencedColumnName = "id", foreignKey = @ForeignKey(name = "FK_ESTANDAR"))
    private Estandard dispositivo;
    @OneToMany(cascade = {CascadeType.ALL})
    @JoinColumn(name = "idConsumo", referencedColumnName = "id")
    private List<Consumo> consumo;
    @NotNull
    private Boolean modoAhorro;

    //Constructor
    public Adaptador(Estandard dispositivo) {

        this.consumo = new ArrayList<Consumo>();
        this.dispositivo = dispositivo;
        dispositivo.convertir(this);
    }

    public Adaptador() {

    }

    public int getId() {
        return id;
    }

    //AgregarConsumo
    public void addConsumo(int watts, Date fechaInicio, Date fechaFinal) {
        //Create consumo Object
        Consumo consumoI = new Consumo(dispositivo, watts, fechaInicio, fechaFinal);

        //Add to Consumo list
        consumo.add(consumoI);

    }

    public int getConsumoTotal(Date fechaInicio, Date fechaFinal) {
        int total = 0;
        for(int i=0; i< consumo.size(); i++) {
            if((consumo.get(i).getFechaInicio().after(fechaInicio) || (consumo.get(i).getFechaFinal()).before(fechaFinal)))
            {total += consumo.get(i).getWatts();}
}
        return total;
    }

    public Boolean isModoAhorro() {
        return this.modoAhorro;
    }

    public void setModoAhorro(Boolean modoAhorro) {
        this.modoAhorro = modoAhorro;
    }
}
