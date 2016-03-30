package mx.edu.utng.composite;

/**
 * Created by qas on 11/03/16.
 */
public class Zord implements PowerRanger{
    public static final int ROSA = R.color.colorRosa;
    public static final int AMARILLO = R.color.colorAmarillo;
    public static final int AZUL = R.color.colorAzul;
    public static final int NEGRO = R.color.colorNegro;
    public static final int ROJO = R.color.colorRojo;
    public static final int VERDE = R.color.colorVerde;
    public static final int PLATEADO = R.color.colorPlateado;

    private String color;

    public Zord(String color) {
        this.color = color;
    }

    @Override
    public boolean fight() {
        return false;
    }

    @Override
    public void add(PowerRanger ranger) {

    }

    @Override
    public void remove(PowerRanger ranger) {

    }

    @Override
    public PowerRanger getPowerRanger(int i) {
        return null;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public String toString() {
        return  color;
    }
}
