package mx.edu.utng.state;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

/**
 * Created by qas on 6/09/16.
 */
public class Encendido extends ControlRemoto {

    @Override
    public void presionarSwitch(TV tv, Canvas canvas) {
        tv.setEstado(new Apagado());
        Paint paint = new Paint();
        paint.setStyle(Paint.Style.FILL);

        float mitadX = canvas.getWidth();
        float mitadY = canvas.getHeight();

        paint.setColor(Color.BLACK);
        canvas.drawRect(mitadX*0.1f, mitadY*0.2f,
                mitadX*0.9f, mitadY*0.5f, paint);

        paint.setColor(Color.GRAY);
        canvas.drawRect(mitadX * 0.15f, mitadY * 0.25f,
                mitadX * 0.85f, mitadY*0.45f, paint);

    }
}
