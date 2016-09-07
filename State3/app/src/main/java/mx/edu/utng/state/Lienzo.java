package mx.edu.utng.state;

import android.content.Context;
import android.graphics.Canvas;
import android.view.View;

/**
 * Created by qas on 6/09/16.
 */
public class Lienzo extends View{
    private ControlRemoto estado;
    private TV tv;

    public Lienzo(Context context, ControlRemoto estado, TV tv){
        super(context);
        this.estado = estado;
        this.tv = tv;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if(estado!=null&&tv!=null){
            tv.setCanvas(canvas);
            estado.presionarSwitch(tv, canvas);
        }
    }

    public ControlRemoto getEstado() {
        return estado;
    }

    public void setEstado(ControlRemoto estado) {
        this.estado = estado;
    }
}
