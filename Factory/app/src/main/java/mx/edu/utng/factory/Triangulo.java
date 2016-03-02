package mx.edu.utng.factory;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;

/**
 * Created by qas on 19/02/16.
 */
public class Triangulo implements Figura {
    @Override
    public void dibujar(Canvas canvas) {
        Paint pincel = new Paint();
        pincel.setColor(Color.rgb(178, 72, 240));
        pincel.setStyle(Paint.Style.FILL);

        Path path = new Path();

        path.moveTo(canvas.getWidth()/2,
                200);
        path.lineTo(100,
                400);
        path.lineTo(400, 400);
        path.lineTo(canvas.getWidth()/2,
                200);
        path.close();
        canvas.drawPath(path, pincel);
    }
}
