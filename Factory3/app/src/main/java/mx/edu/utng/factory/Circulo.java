package mx.edu.utng.factory;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

/**
 * Created by qas on 31/08/16.
 */
public class Circulo implements Figura {

    @Override
    public void dibujar(Canvas canvas) {
        Paint paint = new Paint();
        paint.setColor(Color.RED);
        paint.setStyle(Paint.Style.FILL);
        float ancho = canvas.getWidth();
        float alto = canvas.getHeight();
        canvas.drawCircle(ancho/2,alto/2,200, paint);
    }
}

