package mx.edu.utng.factory;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

/**
 * Created by qas on 31/08/16.
 */
public class Rectangulo implements Figura {
    @Override
    public void dibujar(Canvas canvas) {
        Paint paint = new Paint();
        paint.setColor(Color.argb(128, 220, 100, 50));
        paint.setStyle(Paint.Style.FILL);
        float mitadAncho = canvas.getWidth()/2;
        float mitadAlto = canvas.getHeight()/2;
        canvas.drawRect(mitadAncho*0.5f, mitadAlto*0.5f,
                mitadAncho*1.5f, mitadAlto*1.5f, paint);
    }
}
