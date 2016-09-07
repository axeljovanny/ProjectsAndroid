package mx.edu.utng.prototype;

/**
 * Created by qas on 2/09/16.
 */
public class Documento implements Clonable{

    private String nombre;
    private float tamanio;
    private String extension;

    public Documento(){
        this.nombre = "";
        this.tamanio = 0.0f;
        this.extension = "";
    }

    public Documento(String nombre, float tamanio, String extension) {
        this.nombre = nombre;
        this.tamanio = tamanio;
        this.extension = extension;
    }

    @Override
    public Clonable clonar() {
        Documento clon = new Documento(nombre, tamanio, extension);
        return clon;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public float getTamanio() {
        return tamanio;
    }

    public void setTamanio(float tamanio) {
        this.tamanio = tamanio;
    }

    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }
}
