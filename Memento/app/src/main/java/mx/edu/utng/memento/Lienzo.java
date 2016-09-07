package mx.edu.utng.memento;

import android.content.Context;
import android.graphics.Canvas;
import android.view.View;

/**
 * Created by qas on 7/09/16.
 */
public class Lienzo extends View {
    private Automovil automovil;

    public Lienzo(Context context, Automovil automovil){
       super(context);
        this.automovil = automovil;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if(automovil!=null){
            automovil.dibujar(canvas, automovil.getColor());
        }
    }

    public Automovil getAutomovil() {
        return automovil;
    }

    public void setAutomovil(Automovil automovil) {
        this.automovil = automovil;
    }
}
