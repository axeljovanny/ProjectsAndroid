package mx.edu.utng.factory;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.View;

/**
 * Created by qas on 12/02/16.
 */
public class Rectangulo extends View implements Figura {

    public Rectangulo(Context context) {
        super(context);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawRGB(255,255,255);
        int ancho = canvas.getWidth();
        int alto = canvas.getHeight();
        Paint pincel = new Paint();
        pincel.setARGB(255, 0, 255,0);
        pincel.setStyle(Paint.Style.FILL);
        canvas.drawRect(ancho/4, alto/4, 300, 200, pincel);
    }

    @Override
    public void dibujar() {

    }
}
