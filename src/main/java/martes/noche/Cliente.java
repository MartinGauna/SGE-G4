package martes.noche;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public class Cliente extends Usuario {

    private String tipoDoc;
    private int numeroDoc;
    private int telefono;
    private Categoria categoria;
    private List<Dispositivo> dispositivos;
    private int puntaje;

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
                   String tipoDoc, int numeroDoc, int telefono, Categoria categoria, LocalDate fechaAltaServicio) {
        super(nombre, apellido, domicilio, user,password,fechaAlta);
        this.tipoDoc = tipoDoc;
        this.numeroDoc = numeroDoc;
        this.telefono = telefono;
        this.categoria = categoria;
        this.setFechaAlta(fechaAltaServicio) ;
        this.puntaje = 0;
    }
//===================== Getters & Setters
	// Tipo de Documento
	public String getTipoDoc() {
        return this.tipoDoc;
    }
    public void setTipoDoc(String tipoDoc) {
        this.tipoDoc = tipoDoc;
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
    public void setDispositivos(List<Dispositivo> dispositivos) {
        this.dispositivos = dispositivos;
    }
    
    public void addDispositivo(Dispositivo disp) {
    	this.dispositivos.add(disp);
    	if(isInteligente(disp)) {
    		puntaje += 15;
    	}
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

    public boolean dispositivoOn(String name){

        Optional<Dispositivo> disp = dispositivos.stream().filter(d -> d.getNombre().equals(name)).findFirst();

        return disp.get().getEstado().equals("activo");
    }


    @Override
    public String toString() {
        return  "Cliente: \n"+
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
    	return disp.getClass().getName().equals("DispositivoInteligente");
    }
}
