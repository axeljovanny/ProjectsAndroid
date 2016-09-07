package mx.edu.utng.singleton;

/**
 * Created by qas on 29/08/16.
 */
public class Universidad {

    private static Universidad unicaInstancia;
    private String nombre;

    private Universidad(){
        this.nombre = "";
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public static Universidad getUnicaInstancia() {
        if(unicaInstancia==null){
            unicaInstancia = new Universidad();
        }
        return unicaInstancia;
    }
}
