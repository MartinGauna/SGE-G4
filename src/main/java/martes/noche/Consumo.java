package martes.noche;

import java.util.Date;

public class Consumo {

    private Dispositivo dispositivo;
    private int watts;
    private Date fechaInicio;
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
