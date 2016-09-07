package mx.edu.utng.state;

import android.graphics.Canvas;

/**
 * Created by qas on 6/09/16.
 */
public class TV {
    private ControlRemoto estado;
    private Canvas canvas;

    public TV(ControlRemoto estado){
        this.estado = estado;
    }

    public void presionarBoton(){
        estado.presionarSwitch(this, canvas);
    }

    public Canvas getCanvas() {
        return canvas;
    }

    public void setCanvas(Canvas canvas) {
        this.canvas = canvas;
    }

    public ControlRemoto getEstado() {
        return estado;
    }

    public void setEstado(ControlRemoto estado) {
        this.estado = estado;
    }

}
