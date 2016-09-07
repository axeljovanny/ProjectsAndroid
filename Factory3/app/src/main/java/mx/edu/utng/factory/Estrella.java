package mx.edu.utng.factory;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;

/**
 * Created by qas on 30/08/16.
 */
public class Estrella implements Figura {


    @Override
    public void dibujar(Canvas canvas) {
        Paint paint = new Paint();
        paint.setColor(Color.argb(255, 240, 178, 72));
        paint.setStyle(Paint.Style.FILL);

        Path path = new Path();

        float mitad = canvas.getWidth()/2;
        float min = Math.min(canvas.getWidth(),
                canvas.getHeight());
        float half = min/2;

        mitad = mitad - half;

        path.moveTo(mitad+half*0.5f, half*0.84f);
        path.lineTo(mitad+half*1.5f, half*0.84f);
        path.lineTo(mitad+half*0.68f, half*1.45f);
        path.lineTo(mitad+half*1.0f, half*0.5f);
        path.lineTo(mitad+half*1.32f, half*1.45f);
        path.lineTo(mitad+half*0.5f, half*0.84f);

        path.close();
        canvas.drawPath(path, paint);





    }
}
