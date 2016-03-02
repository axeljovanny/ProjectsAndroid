package mx.edu.utng.database2.model;

import java.util.Date;

/**
 * Created by qas on 23/02/16.
 */
public class Cliente {
    private int idCliente;
    private String nombre;
    private String apellidos;


    public Cliente(int idCliente, String nombre, String apellidos) {
        this.idCliente = idCliente;
        this.nombre = nombre;
        this.apellidos = apellidos;
       }

    public Cliente(){
        this(0, "","");
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    @Override
    public String toString() {
        return "Cliente{" +
                "idCliente=" + idCliente +
                ", nombre='" + nombre + '\'' +
                ", apellidos='" + apellidos + '\'' +
                '}';
    }
}
