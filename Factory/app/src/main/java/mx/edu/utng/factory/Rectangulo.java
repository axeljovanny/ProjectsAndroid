package mx.edu.utng.factory;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.View;

/**
 * Created by qas on 12/02/16.
 */
public class Rectangulo implements Figura {

    @Override
    public void dibujar(Canvas canvas) {
        canvas.drawRGB(255,255,255);
        int ancho = canvas.getWidth();
        int alto = canvas.getHeight();
        int x = (int)(Math.random()*ancho);
        int y = (int)(Math.random()*alto);

        Paint pincel = new Paint();
        pincel.setARGB(255, 0, 255,0);
        pincel.setStyle(Paint.Style.FILL);
        canvas.drawRect(x, y, x+300,y+200, pincel);
    }
}
