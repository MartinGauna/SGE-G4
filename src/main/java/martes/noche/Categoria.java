package martes.noche;

public abstract class Categoria {
    private int cargo_fijo;
    private int cargo_variable;

    public int getCargo_fijo(){return cargo_fijo;}

    public void setCargo_fijo(int cargo_fijo) { this.cargo_fijo = cargo_fijo; }

    public int getCargo_variable() { return cargo_variable;}

    public void setCargo_variable(int cargo_variable) { this.cargo_variable = cargo_variable; }

}