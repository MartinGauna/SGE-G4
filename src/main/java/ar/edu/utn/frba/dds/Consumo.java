package ar.edu.utn.frba.dds;

import ar.edu.utn.frba.dds.dispositivo.Dispositivo;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;


@Entity
@Table
public class Consumo {


	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@ManyToOne(fetch = FetchType.LAZY, targetEntity = Transformador.class)
	@JoinColumn(name = "idTransformador", referencedColumnName = "id", foreignKey = @ForeignKey(name = "FK_TRANSFORMADOR"))
    private Dispositivo dispositivo;
    @NotNull
	private int watts;
    @NotNull
    private Date fechaInicio;
    @NotNull
    private Date fechaFinal;

    public Consumo(Dispositivo dispositivo, int watts, Date fechaInicio, Date fechaFinal) {
        this.setDispositivo(dispositivo);
        this.setWatts(watts);
        this.setFechaInicio(fechaInicio);
        this.setFechaFinal(fechaFinal);
    }

	public Date getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public Date getFechaFinal() {
		return fechaFinal;
	}

	public void setFechaFinal(Date fechaFinal) {
		this.fechaFinal = fechaFinal;
	}

	public int getWatts() {
		return watts;
	}

	public void setWatts(int watts) {
		this.watts = watts;
	}

	public Dispositivo getDispositivo() {
		return dispositivo;
	}

	public void setDispositivo(Dispositivo dispositivo) {
		this.dispositivo = dispositivo;
	}

}
