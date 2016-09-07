package mx.edu.utng.factory;

/**
 * Created by qas on 31/08/16.
 */
public class FiguraFactory {
    private Figura figura;

    public Figura crearFigura(String tipo){
        if(tipo!=null){
            if(tipo.equalsIgnoreCase("circulo")){
                figura = new Circulo();
            }else if(tipo.equalsIgnoreCase("cuadrado")){
                figura = new Cuadrado();
            }else {
                return null;
            }
        }
        return figura;
    }
}
