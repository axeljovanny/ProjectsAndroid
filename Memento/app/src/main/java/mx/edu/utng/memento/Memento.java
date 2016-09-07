package mx.edu.utng.memento;

import android.graphics.Color;

/**
 * Created by qas on 7/09/16.
 */
public class Memento {
    private int color;

    public Memento(int color){
        this.color = color;
    }

     public int getColor() {
        return color;
    }
}
