package mx.edu.utng.abstractas;

/**
 * Created by qas on 22/01/16.
 */
public class Triangulo extends Figura {
    private float base;
    private float altura;
    @Override
    public float calcularArea() {
        return (base*altura)/2;
    }
}
