package mx.edu.utng.factory;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.View;
import android.widget.Toast;

/**
 * Created by qas on 12/02/16.
 */
public class Circulo extends View implements Figura {

    public Circulo(Context context) {
        super(context);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawRGB(255,255,255);
        int ancho = canvas.getWidth();
        int alto = canvas.getHeight();
        Paint pincel = new Paint();
        pincel.setARGB(255, 255, 0,0);
        pincel.setStyle(Paint.Style.FILL);
        canvas.drawCircle(ancho/2,alto/2, 100, pincel);

    }

    @Override
    public void dibujar() {
        Toast.makeText(getContext(), "Circulo",
                Toast.LENGTH_SHORT).show();
    }
}
