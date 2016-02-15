package mx.edu.utng.abstractas;

/**
 * Created by qas on 22/01/16.
 */
public class Cuadrado extends Figura {
    private float lado;


    @Override
    public float calcularArea(){
        return lado*lado;
    }

    public float getLado() {
        return lado;
    }

    public void setLado(float lado) {
        this.lado = lado;
    }
}
