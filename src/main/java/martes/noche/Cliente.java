package martes.noche;

import martes.noche.categoria.Categoria;

import java.time.LocalDate;
import java.util.List;

public class Cliente extends Usuario {

    private String tipoDoc;
    private int numeroDoc;
    private int telefono;
    private Categoria categoria;
    private List<Dispositivo> dispositivos;

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

    public Cliente(String tipoDoc, int numeroDoc, int telefono, Categoria categoria, LocalDate fechaAltaServicio) {
        this.tipoDoc = tipoDoc;
        this.numeroDoc = numeroDoc;
        this.telefono = telefono;
        this.categoria = categoria;
        this.setFechaAlta(fechaAltaServicio) ;
    }

	public Cliente() {
		// TODO Auto-generated constructor stub
	}


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


    public List<Dispositivo> getDispositivos() {
        return this.dispositivos;
    }
    public void setDispositivos(List<Dispositivo> dispositivos) {
        this.dispositivos = dispositivos;
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
}
