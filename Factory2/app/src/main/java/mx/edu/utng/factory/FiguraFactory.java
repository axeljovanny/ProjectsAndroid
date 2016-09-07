package mx.edu.utng.factory;

/**
 * Created by qas on 30/08/16.
 */
public class FiguraFactory {

    private Figura figura;

    public Figura crearFigura(String tipo){
        if(tipo==null){
            return null;
        }
        if(tipo.equalsIgnoreCase("CIRCULO")){
            return  new Circulo();
        } else if(tipo.equalsIgnoreCase("ESTRELLA")){
            return  new Estrella();
        }else if(tipo.equalsIgnoreCase("TRIANGULO")){
            return  new Triangulo();
        }
        return  null;
    }
}
