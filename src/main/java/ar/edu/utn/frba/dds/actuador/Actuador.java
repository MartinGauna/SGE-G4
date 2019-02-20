package ar.edu.utn.frba.dds.actuador;

import ar.edu.utn.frba.dds.dao.BaseDao;
import ar.edu.utn.frba.dds.dispositivo.DispositivoInteligente;


import javax.persistence.*;

@Entity
@Table
public class Actuador {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Transient
    private BaseDao bdao = new BaseDao();
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

    public Actuador (){}

    public void prenderDispositivo(){
        disp.encender();
        bdao.update(disp);
    }
    public void apagarDispositivo(){
        disp.apagar();
        bdao.update(disp);
    }

    public void cambiarModoAAhorro(){
        disp.cambiarAmodoAhorro();
        bdao.update(disp);
    }
    public void subir(int cantidad){
        disp.subir(cantidad);
        bdao.update(disp);
    }
    public void bajar(int cantidad){
        disp.bajar(cantidad);
        bdao.update(disp);
    }

    public int getId() {
        return id;
    }

}
