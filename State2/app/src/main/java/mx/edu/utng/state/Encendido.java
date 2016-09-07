package mx.edu.utng.state;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

/**
 * Created by qas on 5/09/16.
 */
public class Encendido extends ControlRemoto {

    @Override
    public void oprimirEncendido(TV tv, Canvas canvas) {
        tv.setEstado(new Apagado());
        Paint paint = new Paint();
        paint.setStyle(Paint.Style.FILL);

        float mitadX = canvas.getWidth()/2;
        float mitadY = canvas.getHeight()/2;

        paint.setColor(Color.BLACK);
        canvas.drawRect(mitadX*0.1f, mitadY*0.2f,
                mitadX*1.8f, mitadY*1.05f, paint);
        paint.setColor(Color.GRAY);
        canvas.drawRect(mitadX * 0.15f, mitadY * 0.25f,
                mitadX * 1.75f, mitadY, paint);
    }
}
