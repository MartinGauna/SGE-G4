package martes.noche.categoria;

public class Categoria {
	
	private String name;
    private double cargoFijo;
    private double cargoVariable;
    private int consumoMin;
    private int consumoMax;

    public Categoria(String name, double  cargoFijo, double  cargoVariable, int consumoMin, int consumoMax ) {
        this.setName(name);
    	this.cargoFijo = cargoFijo;
        this.cargoVariable = cargoVariable;
        this.setConsumoMin(consumoMin);
        this.setConsumoMax(consumoMax);
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getConsumoMin() {
		return consumoMin;
	}

	public void setConsumoMin(int consumoMin) {
		this.consumoMin = consumoMin;
	}

	public int getConsumoMax() {
		return consumoMax;
	}

	public void setConsumoMax(int consumoMax) {
		this.consumoMax = consumoMax;
	}
}
