package mx.edu.utng.factory;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;

/**
 * Created by qas on 30/08/16.
 */
public class Triangulo implements Figura{

    @Override
    public void dibujar(Canvas canvas) {
        Paint paint = new Paint();
        paint.setColor(Color.rgb(178, 72, 140));
        paint.setStyle(Paint.Style.FILL);

        Path path = new Path();

        float mitadAncho = canvas.getWidth()/2;
        float mitadAlto = canvas.getHeight()/2;

        path.moveTo(mitadAncho, mitadAlto*0.5f);
        path.lineTo(mitadAncho*0.5f, mitadAlto*1.5f);
        path.lineTo(mitadAncho*1.5f, mitadAlto*1.5f);
        path.lineTo(mitadAncho, mitadAlto*0.5f);
        path.close();
        canvas.drawPath(path, paint);


    }
}
