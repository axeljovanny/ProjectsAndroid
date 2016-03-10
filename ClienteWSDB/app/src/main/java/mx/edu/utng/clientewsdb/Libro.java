package mx.edu.utng.clientewsdb;

import org.ksoap2.serialization.KvmSerializable;
import org.ksoap2.serialization.PropertyInfo;

import java.util.Hashtable;

/**
 * Created by qas on 9/03/16.
 */
public class Libro implements KvmSerializable{

    private int idLibro;
    private String titulo;
    private String autor;
    private String editorial;
    private float precio;
    private int categoria;

    public Libro(int idLibro, String titulo, String autor, String editorial, float precio, int categoria) {
        this.idLibro = idLibro;
        this.titulo = titulo;
        this.autor = autor;
        this.editorial = editorial;
        this.precio = precio;
        this.categoria = categoria;
    }

    public Libro(){
        this(0,"","","",0,0);
    }

    @Override
    public Object getProperty(int i) {
        switch (i){
            case 0:
                return idLibro;
            case 1:
                return titulo;
            case 2:
                return autor;
            case 3:
                return editorial;
            case 4:
                return precio;
            case 5:
                return categoria;
        }
        return null;
    }

    @Override
    public int getPropertyCount() {
        return 6;
    }

    @Override
    public void setProperty(int i, Object o) {
        switch (i){
            case 0:
                idLibro = Integer.parseInt(o.toString());
                break;
            case 1:
                titulo = o.toString();
                break;
            case 2:
                autor = o.toString();
                break;
            case 3:
                editorial = o.toString();
                break;
            case 4:
                precio = Float.parseFloat(o.toString());
                break;
            case 5:
                categoria = Integer.parseInt(o.toString());
                break;
        }
    }

    @Override
    public void getPropertyInfo(int i, Hashtable hashtable, PropertyInfo propertyInfo) {
        switch (i){
            case 0:
                propertyInfo.type = PropertyInfo.INTEGER_CLASS;
                propertyInfo.name = "idLibro";
                break;
            case 1:
                propertyInfo.type = PropertyInfo.STRING_CLASS;
                propertyInfo.name = "titulo";
                break;
            case 2:
                propertyInfo.type = PropertyInfo.STRING_CLASS;
                propertyInfo.name = "autor";
                break;
            case 3:
                propertyInfo.type = PropertyInfo.STRING_CLASS;
                propertyInfo.name = "editorial";
                break;
            case 4:
                propertyInfo.type = Float.class;
                propertyInfo.name = "precio";
                break;
            case 5:
                propertyInfo.type = PropertyInfo.INTEGER_CLASS;
                propertyInfo.name = "categoria";
                break;

        }
    }
}
