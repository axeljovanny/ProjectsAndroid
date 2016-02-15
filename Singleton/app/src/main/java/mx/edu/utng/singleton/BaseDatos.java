package mx.edu.utng.singleton;

/**
 * Created by qas on 5/02/16.
 */
public class BaseDatos {
    private static BaseDatos bd;
    private String nombre;
    private String host;
    private String puerto;

    private BaseDatos() {
        this.nombre = "BD_Unica";
        this.host = "127.0.0.1";
        this.puerto = "5432";
    }

    public static BaseDatos getBd() {
        if(bd==null) {
            bd = new BaseDatos();
        }
        return bd;
    }

    public String getHost() {
        return host;
    }

    public String getNombre() {
        return nombre;
    }

    public String getPuerto() {
        return puerto;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setPuerto(String puerto) {
        this.puerto = puerto;
    }
}
