package mx.edu.utng.state;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

/**
 * Created by qas on 6/09/16.
 */
public class Encendido extends ControlRemoto{
    @Override
    public void presionarSwitch(TV tv, Canvas canvas) {
        tv.setEstado(new Apagado());
        Paint paint = new Paint();
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.BLACK);
        float mitadX = canvas.getWidth()/2;
        float mitadY = canvas.getHeight()/2;
        canvas.drawRect(mitadX * 0.5f, mitadY * 0.5f,
                mitadX * 1.5f, mitadY * 1.5f, paint);
        paint.setColor(Color.GRAY);
        canvas.drawRect(mitadX * 0.55f, mitadY * 0.55f,
                mitadX * 1.45f, mitadY * 1.45f, paint);
    }
}
