package mx.edu.utng.composite;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by qas on 11/03/16.
 */
public class Megazord implements PowerRanger{
    private String nombre;

    List<PowerRanger> powerRangers;

    public Megazord(String nombre, List<PowerRanger> powerRangers) {
        this.nombre = nombre;
        this.powerRangers = powerRangers;
    }

    @Override
    public boolean fight() {
        return true;
    }

    @Override
    public void add(PowerRanger ranger) {
        powerRangers.add(ranger);
    }

    @Override
    public void remove(PowerRanger ranger) {
        powerRangers.remove(ranger);
    }

    @Override
    public PowerRanger getPowerRanger(int i) {
        return powerRangers.get(i);
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return "Megazord{" +
                "nombre='" + nombre + '\'' +
                ", powerRangers=" + powerRangers +
                '}';
    }
}
