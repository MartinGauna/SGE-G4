package martes.noche.categoria;

public abstract class Categoria {

    private double cargoFijo;
    private double cargoVariable;

    public Categoria(double  cargoFijo, double  cargoVariable) {
        this.cargoFijo = cargoFijo;
        this.cargoVariable = cargoVariable;
    }

    public double  getCargoFijo() {
        return cargoFijo;
    }

    public void setCargoFijo(float cargoFijo) {
        this.cargoFijo = cargoFijo;
    }

    public double  getCargoVariable() {
        return cargoVariable;
    }

    public void setCargoVariable(float cargoVariable) {
        this.cargoVariable = cargoVariable;
    }

    public double calcularValorAprox(int kwh) {
        return getCargoFijo() + kwh * getCargoVariable();
    }
}
