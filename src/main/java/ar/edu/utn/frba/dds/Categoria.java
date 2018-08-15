package ar.edu.utn.frba.dds;


import java.io.IOException;
import java.util.List;
import java.util.Optional;

import ar.edu.utn.frba.dds.jsonParser.JsonParser;

import javax.persistence.*;

@Entity
@Table
public class Categoria {

    @Id
    private int id;

	private String nombre;
    private double cargoFijo;
    private double cargoVariable;
    private int consumoMin;
    private int consumoMax;


    public Categoria() throws IOException {
    }

    // Cargo las categorías desde un json
    public Categoria(String id) {
        JsonParser jsonParser = new JsonParser();
        try{
            List<Categoria> categorias = jsonParser.loadCategoriasJSON();
            Optional<Categoria> cat = categorias.stream().filter(c->c.getNombre().equals(id)).findFirst();
            nombre = cat.get().getNombre();
            cargoFijo = cat.get().getCargoFijo();
            cargoVariable = cat.get().getCargoVariable();
            consumoMin = cat.get().getConsumoMin();
            consumoMax = cat.get().getConsumoMax();
        }
        catch(Exception e) {
        }

    }

    public Categoria(String nombre, double  cargoFijo, double  cargoVariable, int consumoMin, int consumoMax ) {
        this.nombre = nombre;
        this.cargoFijo = cargoFijo;
        this.cargoVariable = cargoVariable;
        this.consumoMin = consumoMin;
        this.consumoMax = consumoMax;
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

	public String getNombre() {
		return nombre;
	}
	public void getNombre(String name) {
		this.nombre = name;
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

    public double calcularValorAprox(int kwh) {
        return getCargoFijo() + kwh * getCargoVariable();
    }

    @Override
    public String toString() {
        return  "Categoria: \n"+
                "\tNombre: " + getNombre() + "\n" +
                "\tCargo  Fijo: " + getCargoFijo() + "\n" +
                "\tCargo Variable: " + getCargoVariable() + "\n" +
                "\tConsumo Maximo: " + getConsumoMax() + "\n" +
                "\tConsumo Minimo: " + getConsumoMin() + "\n";
    }
}
