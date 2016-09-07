package mx.edu.utng.factory;

/**
 * Created by qas on 31/08/16.
 */
public class FiguraFactory {
    private Figura figura;
    public Figura crearFigura(String tipo){
        if(tipo!=null) {
            if (tipo.equalsIgnoreCase("circulo")) {
                figura = new Circulo();
            } else if (tipo.equalsIgnoreCase("rectangulo")) {
                figura = new Rectangulo();
            } else if (tipo.equalsIgnoreCase("triangulo")) {
                figura = new Triangulo();
            }else if (tipo.equalsIgnoreCase("trapecio")) {
                figura = new Trapecio();
            }else if (tipo.equalsIgnoreCase("estrella")) {
                figura = new Estrella();
            } else{
                return  null;
            }
        }
        return figura;
    }

}
