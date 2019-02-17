package ar.edu.utn.frba.dds.actuador;

import ar.edu.utn.frba.dds.dispositivo.DispositivoInteligente;


import javax.persistence.*;

@Entity
@Table
public class Actuador {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    public DispositivoInteligente getDispositivo() {
        return disp;
    }

    public void setDispositivo(DispositivoInteligente disp) {
        this.disp = disp;
    }

    @OneToOne(fetch = FetchType.LAZY, targetEntity = DispositivoInteligente.class)
    @JoinColumn(name = "idDispositivo", referencedColumnName = "id", foreignKey = @ForeignKey(name = "FK_ACTUADOR"))
    private DispositivoInteligente disp = null;

    public Actuador(DispositivoInteligente dispositivoInteligente) {
        disp = dispositivoInteligente;
    }

    public void prenderDispositivo(DispositivoInteligente d){
        d.encender();
    }
    public void apagarDispositivo(DispositivoInteligente d){
        d.apagar();
    }

    public void cambiarModoAAhorro(DispositivoInteligente d){
        d.cambiarAmodoAhorro();
    }
    public void subir(DispositivoInteligente d, int cantidad){
        d.subir(cantidad);
    }
    public void bajar(DispositivoInteligente d, int cantidad){
        d.bajar(cantidad);
    }

    public int getId() {
        return id;
    }

}
