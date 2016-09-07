package mx.edu.utng.factory;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

/**
 * Created by qas on 31/08/16.
 */
public class Cuadrado implements Figura {

    @Override
    public void dibujar(Canvas canvas) {
        Paint paint = new Paint();
        paint.setColor(Color.argb(128, 50, 200, 100));
        paint.setStyle(Paint.Style.FILL);

        float mitadX = canvas.getWidth()/2;
        float mitadY = canvas.getHeight()/2;

        canvas.drawRect(mitadX*0.5f,mitadY*0.5f,
                mitadX*1.5f,mitadY*1.5f, paint);
    }
}
