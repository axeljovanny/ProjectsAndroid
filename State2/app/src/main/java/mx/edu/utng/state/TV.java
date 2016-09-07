package mx.edu.utng.state;

import android.content.Context;
import android.graphics.Canvas;
import android.view.View;

/**
 * Created by qas on 5/09/16.
 */
public class TV {
    private ControlRemoto estado;
    private Canvas canvas;

    public TV(ControlRemoto estado){
        this.estado = estado;
    }

    public ControlRemoto getEstado() {
        return estado;
    }

    public void setEstado(ControlRemoto estado) {
        this.estado = estado;
    }

    public void oprimirBoton(){
        estado.oprimirEncendido(this, canvas);
    }

    public Canvas getCanvas() {
        return canvas;
    }

    public void setCanvas(Canvas canvas) {
        this.canvas = canvas;
    }
}
