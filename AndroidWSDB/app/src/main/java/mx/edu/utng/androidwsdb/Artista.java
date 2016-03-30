package mx.edu.utng.androidwsdb;

import org.ksoap2.serialization.KvmSerializable;
import org.ksoap2.serialization.PropertyInfo;

import java.util.Hashtable;

/**
 * Created by qas on 15/03/16.
 */
public class Artista implements KvmSerializable{
    private int idArtista;
    private String nombre;
    private int numeroExitos;


    @Override
    public Object getProperty(int i) {
        switch (i){
            case 0:
                return  idArtista;
            case 1:
                return nombre;
            case 2:
                return numeroExitos;
        }
        return null;
    }

    @Override
    public int getPropertyCount() {
        return 3;
    }

    @Override
    public void setProperty(int i, Object o) {
        switch (i) {
            case 0:
                idArtista = Integer.parseInt(o.toString());
                break;
            case 1:
                nombre = o.toString();
                break;
            case 2:
                numeroExitos = Integer.parseInt(o.toString());
                break;
            default:
                break;
        }
    }

    @Override
    public void getPropertyInfo(int i,
                     Hashtable hashtable,
                     PropertyInfo propertyInfo) {
        switch (i){
            case 0:
                propertyInfo.type =
                        PropertyInfo.INTEGER_CLASS;
                propertyInfo.name = "idArtista";
                break;
            case 1:
                propertyInfo.type =
                        PropertyInfo.STRING_CLASS;
                propertyInfo.name = "nombre";
                break;
            case 2:
                propertyInfo.type =
                        PropertyInfo.INTEGER_CLASS;
                propertyInfo.name = "numeroExitos";
                break;
            default:
                 break;
        }
    }
}
