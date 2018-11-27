package ar.edu.utn.frba.dds;

import ar.edu.utn.frba.dds.dispositivo.Adaptador;
import ar.edu.utn.frba.dds.dispositivo.DispositivoInteligente;
import ar.edu.utn.frba.dds.dispositivo.Estandard;
import ar.edu.utn.frba.dds.dispositivo.Dispositivo;
import ar.edu.utn.frba.dds.helpers.AdapterSimplex;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Optional;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table
@DiscriminatorValue("")
public class Cliente extends Usuario {

    public static final int puntosPorEstandard = 10;
    public static final int puntosPorInteligente = 15;

    @NotNull
    private String tipoDoc;
    @NotNull
    @Column(unique = true)
    private int numeroDoc;
    @NotNull
    private int telefono;

    private int puntaje;

    private boolean ahorroAutomatico;


    @JoinColumn(name = "idCategoria", referencedColumnName = "id", foreignKey = @ForeignKey(name = "FK_CATEGORIA"))
    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Categoria.class)
    public Categoria categoria;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Transformador.class)
    @JoinColumn(name = "idTransformador", referencedColumnName = "id", foreignKey = @ForeignKey(name = "FK_TRANSFORMADOR"))
    private Transformador transformador;

    @Transient
    private List<Dispositivo> dispositivos;

    @Transient
    private AdapterSimplex adapterSimplex = new AdapterSimplex();
//    public enum TipoDocumento {
//        DNI("DNI"),
//        CI("CI"),
//        LE("LE"),
//        LC("LC");
//
//        public final String val;
//
//        private TipoDocumento(String val) {
//            this.val = val;
//        }
//    }

    public Cliente(String nombre, String apellido, String domicilio, String user, String password, LocalDate fechaAlta,
                   String tipoDoc, int numeroDoc, int telefono, Categoria categoria, LocalDate fechaAltaServicio, boolean ahorroAutomatico) {
        super(nombre, apellido, domicilio, user, password, fechaAlta);
        this.tipoDoc = tipoDoc;
        this.numeroDoc = numeroDoc;
        this.telefono = telefono;
        this.categoria = categoria;
        this.setFechaAlta(fechaAltaServicio);
        this.dispositivos = new ArrayList<Dispositivo>();
        this.puntaje = 0;
        this.ahorroAutomatico = ahorroAutomatico;
    }

    public Cliente() {
    }

    //===================== Getters & Setters

    // Tipo de Documento
    public String getTipoDoc() {
        return this.tipoDoc;
    }

    public void setTipoDoc(String tipoDoc) {
        this.tipoDoc = tipoDoc;
    }

    public Transformador getTransformador() {
        return transformador;
    }

    public void setTransformador(Transformador transformador) {
        this.transformador = transformador;
        transformador.addCliente(this);
    }

    public void ahorroAutomatico() {
        if (ahorroAutomatico) {
            List<DispositivoInteligente> dispositivosParaApagar = adapterSimplex.getDispositivosParaApagar(this.getDispositivosInteligentes());
            if (dispositivosParaApagar != null) {
                for (DispositivoInteligente disp : dispositivosParaApagar) {
                    disp.apagar();
                }
            }
            ;
        }
    }

    // Numero de Documento
    public int getNumeroDoc() {
        return this.numeroDoc;
    }

    public void setNumeroDoc(int numeroDoc) {
        this.numeroDoc = numeroDoc;
    }

    // Telefono
    public double getTelefono() {
        return this.telefono;
    }

    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }

    // Categoría
    public Categoria getCategoria() {
        return this.categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    // Dispositivos
    public List<Dispositivo> getDispositivos() {
        return this.dispositivos;
    }

    public List<DispositivoInteligente> getDispositivosInteligentes() {
        List<DispositivoInteligente> out = new ArrayList<DispositivoInteligente>();
        if(this.dispositivos != null) {
            for (Dispositivo d : this.dispositivos) {
                if (d instanceof DispositivoInteligente) {
                    DispositivoInteligente di = (DispositivoInteligente) d;
                    out.add(di);
                }
            }
        }
        return out;
    }

    public List<Estandard> getDispositivosEstandars() {
        List<Estandard> out = new ArrayList<Estandard>();
        if(this.dispositivos != null) {
            for (Dispositivo d : this.dispositivos) {
                if (d instanceof Estandard) {
                    Estandard de = (Estandard) d;
                    out.add(de);
                }
            }
        }
        return out;
    }

    public void setDispositivos(List<Dispositivo> dispositivos) {
        this.dispositivos = dispositivos;
    }

    //puntaje
    public int getPuntaje() {
        return puntaje;
    }

    public void setPuntaje(int puntaje) {
        this.puntaje = puntaje;
    }


    public void addDispositivo(Dispositivo disp) {
        if (this.dispositivos == null) {
            this.dispositivos = new ArrayList<Dispositivo>();
        }
        disp.setCliente(this);

        this.dispositivos.add(disp);
        if (isInteligente(disp)) this.setPuntaje(this.getPuntaje() + puntosPorInteligente);
        else this.setPuntaje(this.getPuntaje() + puntosPorEstandard);
    }

    public void addAdaptador(Estandard disp) {
        Adaptador adapt = new Adaptador(disp);
        disp.setAdaptador(adapt);
        this.dispositivos.add(disp);
        this.addDispositivo(disp);
    }

//===================== Methods

    public long cantDispOff() {
        return this.getDispositivos().stream().filter(disp -> !disp.getEstado().equals("activo")).count();
    }

    public long cantDispOn() {
        return this.getDispositivos().stream().filter(disp -> disp.getEstado().equals("activo")).count();
    }

    public long infCantDisp() {
        return this.getDispositivos().stream().count();
    }

    public boolean dispositivoOn(String name) {

        Optional<Dispositivo> disp = dispositivos.stream().filter(d -> d.getNombre().equals(name)).findFirst();

        return disp.get().getEstado().equals("activo");
    }

    public void cambiarTransformador(Transformador newtra) {
        if(this.getTransformador() != null) {
            //primero remuevo el cliente si estaba en otro transformador
            this.getTransformador().getClientes().remove(this);
        }
        //seteo el nuevo transformador
        this.setTransformador(newtra);
    }

    @Override
    public String toString() {
        return "Cliente: \n" +
                "\tNombre: " + getNombre() + "\n" +
                "\tApellido: " + getApellido() + "\n" +
                "\tDomicilio: " + getDomicilio() + "\n" +
                "\tUser: " + getUsername() + "\n" +
                "\tPassword: " + getPassword() + "\n" +
                "\tFecha de Alta: " + getFechaAlta() + "\n" +
                "\tTipo de Documento: " + getTipoDoc() + "\n" +
                "\tNumero de documento: " + getNumeroDoc() + "\n" +
                "\tTelefono: " + getTelefono() + "\n" +
                "\tCategoría: " + getCategoria() + "\n" +
                "\tDispositivos: " + getDispositivos() + "\n";
    }

    public Boolean isInteligente(Dispositivo disp) {
        return !(disp instanceof Estandard);
    }
}
