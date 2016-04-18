package mx.edu.utng.gestbaby.pack;

import java.io.Serializable;
import java.util.List;

public class Semana implements Serializable {
    private static final long serialVersionUID = 15;
    String consejos;
    String estadoBebe;
    String estadoMadre;
    List<String> imagenes;
    String peso;
    String pregunta;
    int semana;
    String tamano;

    public int getSemana() {
        return this.semana;
    }

    public void setSemana(int semana) {
        this.semana = semana;
    }

    public String getTamano() {
        return this.tamano;
    }

    public void setTamano(String tamano) {
        this.tamano = tamano;
    }

    public String getPeso() {
        return this.peso;
    }

    public void setPeso(String peso) {
        this.peso = peso;
    }

    public String getEstadoBebe() {
        return this.estadoBebe;
    }

    public void setEstadoBebe(String estadoBebe) {
        this.estadoBebe = estadoBebe;
    }

    public String getEstadoMadre() {
        return this.estadoMadre;
    }

    public void setEstadoMadre(String estadoMadre) {
        this.estadoMadre = estadoMadre;
    }

    public String getConsejos() {
        return this.consejos;
    }

    public void setConejos(String consejos) {
        this.consejos = consejos;
    }

    public String getPregunta() {
        return this.pregunta;
    }

    public void setPregunta(String pregunta) {
        this.pregunta = pregunta;
    }

    public List<String> getImagenes() {
        return this.imagenes;
    }

    public void setImagenes(List<String> imagenes) {
        this.imagenes = imagenes;
    }

    @Override
    public String toString() {
        return "Semana{" +
                "consejos='" + consejos + '\'' +
                ", estadoBebe='" + estadoBebe + '\'' +
                ", estadoMadre='" + estadoMadre + '\'' +
                ", imagenes=" + imagenes +
                ", peso='" + peso + '\'' +
                ", pregunta='" + pregunta + '\'' +
                ", semana=" + semana +
                ", tamano='" + tamano + '\'' +
                '}';
    }
}
