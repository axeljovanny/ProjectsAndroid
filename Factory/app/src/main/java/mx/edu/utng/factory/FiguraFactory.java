package mx.edu.utng.factory;

import android.content.Context;
import android.graphics.Canvas;

/**
 * Created by qas on 12/02/16.
 */
public class FiguraFactory {
    private Figura figura;


    public Figura crearFigura(String tipo){
        if(tipo==null){
            return null;
        }
        if(tipo.equalsIgnoreCase("CIRCULO")){
            return new Circulo();
        }else  if(tipo.equalsIgnoreCase("RECTANGULO")){
            return new Rectangulo();
        }else if(tipo.equalsIgnoreCase("CUADRADO")){
            return new Cuadrado();
        }else if(tipo.equalsIgnoreCase("ESTRELLA")){
            return new Estrella();
        }else if(tipo.equalsIgnoreCase("TRIANGULO")){
            return new Triangulo();
        }
        return null;
    }
}
