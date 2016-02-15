package mx.edu.utng.fragmentos;

/**
 * Created by qas on 9/02/16.
 */
public class Correo {
    private String remitente;
    private String asunto;
    private String texto;

    public Correo(String remitente, String asunto, String texto) {
        this.remitente = remitente;
        this.asunto = asunto;
        this.texto = texto;
    }

    public String getRemitente() {
        return remitente;
    }

    public void setRemitente(String remitente) {
        this.remitente = remitente;
    }

    public String getAsunto() {
        return asunto;
    }

    public void setAsunto(String asunto) {
        this.asunto = asunto;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }
}
