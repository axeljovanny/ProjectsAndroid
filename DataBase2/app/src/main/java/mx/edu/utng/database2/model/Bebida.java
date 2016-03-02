package mx.edu.utng.database2.model;

import java.util.Date;

/**
 * Created by qas on 23/02/16.
 */
public class Bebida {
    private int idBebida;
    private String nombre;
    private float precio;
    private Date fechaCaducidad;
    private boolean disponible;
    private int cliente;

    public Bebida(int idBebida, String nombre, float precio, Date fechaCaducidad, boolean disponible, int cliente) {
        this.idBebida = idBebida;
        this.nombre = nombre;
        this.precio = precio;
        this.fechaCaducidad = fechaCaducidad;
        this.disponible = disponible;
        this.cliente = cliente;
    }

    public Bebida(){
        this(0, "",0.0F,new Date(), false, 0);
    }

    public int getIdBebida() {
        return idBebida;
    }

    public void setIdBebida(int idBebida) {
        this.idBebida = idBebida;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    public Date getFechaCaducidad() {
        return fechaCaducidad;
    }

    public void setFechaCaducidad(Date fechaCaducidad) {
        this.fechaCaducidad = fechaCaducidad;
    }

    public boolean isDisponible() {
        return disponible;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }

    public int getCliente() {
        return cliente;
    }

    public void setCliente(int cliente) {
        this.cliente = cliente;
    }

    @Override
    public String toString() {
        return "Bebida{" +
                "idBebida=" + idBebida +
                ", nombre='" + nombre + '\'' +
                ", precio=" + precio +
                ", fechaCaducidad=" + fechaCaducidad +
                ", disponible=" + disponible +
                ", cliente=" + cliente  +
                '}';
    }
}
