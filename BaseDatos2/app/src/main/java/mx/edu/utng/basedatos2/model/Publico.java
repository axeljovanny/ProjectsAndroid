package mx.edu.utng.basedatos2.model;

/**
 * Created by qas on 29/02/16.
 */
public class Publico {
    private int idPublico;
    private String descripcion;

    public Publico(int idPublico, String descripcion) {
        this.idPublico = idPublico;
        this.descripcion = descripcion;
    }

    public Publico() {
        this(0, "");
    }

    public int getIdPublico() {
        return idPublico;
    }

    public void setIdPublico(int idPublico) {
        this.idPublico = idPublico;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    public String toString() {
        return "Publico{" +
                "idPublico=" + idPublico +
                ", descripcion='" + descripcion + '\'' +
                '}';
    }
}
