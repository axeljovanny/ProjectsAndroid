package mx.edu.utng.factory;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.View;

/**
 * Created by qas on 12/02/16.
 */
public class Cuadrado extends View implements Figura{

    public Cuadrado(Context context) {
        super(context);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawRGB(255,255,255);
        int ancho = canvas.getWidth();
        int alto = canvas.getHeight();
        Paint pincel = new Paint();
        pincel.setARGB(255, 255, 0,255);
        pincel.setStyle(Paint.Style.FILL);
        canvas.drawRect(ancho/4, alto/4, 200, 200, pincel);

    }

    @Override
    public void dibujar() {

    }
}
