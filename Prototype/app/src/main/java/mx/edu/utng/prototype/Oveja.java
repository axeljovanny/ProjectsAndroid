package mx.edu.utng.prototype;

/**
 * Created by qas on 19/02/16.
 */
public class Oveja implements Clonable{
    private String raza;
    private String color;
    private float peso;

    public Oveja(){
        raza = "";
        color = "";
        peso = 0.0f;
    }

    public Oveja(String raza, String color, float peso) {
        this.raza = raza;
        this.color = color;
        this.peso = peso;
    }

    @Override
    public Clonable clonar() {
        Oveja clon = new Oveja();
        clon.setRaza(raza);
        clon.setColor(color);
        clon.setPeso(peso);
        return clon;
    }

    public String getRaza() {
        return raza;
    }

    public void setRaza(String raza) {
        this.raza = raza;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public float getPeso() {
        return peso;
    }

    public void setPeso(float peso) {
        this.peso = peso;
    }
}
