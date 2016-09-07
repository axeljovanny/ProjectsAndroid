package mx.edu.utng.singleton;

/**
 * Created by qas on 30/08/16.
 */
public class Carrera {
    private String nombre;
    private int numAlumnos;
    private static Carrera carrera;

    private Carrera(){
        this.nombre = "";
        this.numAlumnos = 0;
    }

    public String getNombre() {
        return nombre;
    }

    public int getNumAlumnos() {
        return numAlumnos;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setNumAlumnos(int numAlumnos) {
        this.numAlumnos = numAlumnos;
    }

    public static Carrera getCarrera(){
        if(carrera==null){
            carrera = new Carrera();
        }
        return carrera;
    }
}
