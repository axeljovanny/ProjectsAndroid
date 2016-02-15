package mx.edu.utng.factory;

import android.content.Context;

/**
 * Created by qas on 12/02/16.
 */
public class FiguraFactory {
    private Figura figura;


    public Figura crearFigura(String tipo, Context context){
        if(tipo==null){
            return null;
        }
        if(tipo.equalsIgnoreCase("CIRCULO")){
            return new Circulo(context);
        }else  if(tipo.equalsIgnoreCase("RECTANGULO")){
            return new Rectangulo(context);
        }else if(tipo.equalsIgnoreCase("CUADRADO")){
            return new Cuadrado(context);
        }
        return null;
    }
}
