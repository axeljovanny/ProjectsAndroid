package mx.edu.utng.factory;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

/**
 * Created by qas on 30/08/16.
 */
public class Circulo implements Figura{

    @Override
    public void dibujar(Canvas canvas) {
        Paint paint = new Paint();
        paint.setColor(Color.RED);


        paint.setStyle(Paint.Style.FILL);
        canvas.drawCircle((canvas.getWidth()/2),
                (canvas.getHeight()/2), 200, paint);
    }
}
