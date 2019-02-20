package ar.edu.utn.frba.dds;

import ar.edu.utn.frba.dds.dao.ConsumoDao;
import ar.edu.utn.frba.dds.dispositivo.Dispositivo;
import ar.edu.utn.frba.dds.dispositivo.DispositivoInteligente;
import ar.edu.utn.frba.dds.dispositivo.Estandard;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;


@Entity
@Table
public class Consumo {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@ManyToOne(fetch = FetchType.LAZY, targetEntity = DispositivoInteligente.class)
	@JoinColumn(name = "idDispositivo", referencedColumnName = "id", foreignKey = @ForeignKey(name = "FK_CONSUMO"))
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

    public Consumo() {}

	public int getId() {
		return id;
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

    public static Consumo createConsumo(Dispositivo disp) throws ParseException {

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        Random rand = new Random();
        int valor = rand.nextInt((int) disp.getConsumoHora());
        int inicio = rand.nextInt(31);
        int fin = rand.nextInt(31);

        if (fin < inicio) {
            int aux = inicio;
            inicio = fin;
            fin = aux;
        }


        Date d = sdf.parse(sdf.format(new Date()));
        Date e = sdf.parse(sdf.format(new Date()));
        Consumo cons1 = new Consumo(disp, valor, d, e);

        if(!(disp instanceof Estandard)) {
            ((DispositivoInteligente) disp).addConsumo(cons1);
        }

        return cons1;
    }

}
