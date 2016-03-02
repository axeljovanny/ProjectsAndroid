package mx.edu.utng.basedatos2.model;

import java.util.Date;

/**
 * Created by qas on 22/02/16.
 */
public class Cuestionario {
    private int idCuestionario;
    private String nombre;
    private Date fechaCreacion;
    private boolean activo;
    private int dirigido;

    public Cuestionario(int idCuestionario, String nombre, Date fechaCreacion, boolean activo, int dirigido) {
        this.idCuestionario = idCuestionario;
        this.nombre = nombre;
        this.fechaCreacion = fechaCreacion;
        this.activo = activo;
        this.dirigido = dirigido;
    }

    public Cuestionario(){
        this(0,"Sin nombre", new Date(), false, 0);
    }

    public int getIdCuestionario() {
        return idCuestionario;
    }

    public void setIdCuestionario(int idCuestionario) {
        this.idCuestionario = idCuestionario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public int getDirigido() {
        return dirigido;
    }

    public void setDirigido(int dirigido) {
        this.dirigido = dirigido;
    }

    @Override
    public String toString() {
        return "Cuestionario{" +
                "idCuestionario=" + idCuestionario +
                ", nombre='" + nombre + '\'' +
                ", fechaCreacion=" + fechaCreacion +
                ", activo=" + activo +
                ", dirigido=" + dirigido +
                '}';
    }
}
