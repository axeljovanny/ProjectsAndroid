package mx.edu.utng.memento;

import java.util.ArrayList;

/**
 * Created by qas on 7/09/16.
 */
public class CareTaker {
    private ArrayList<Memento> mementos = new ArrayList<Memento>();

    public void addMemento(Memento memento){
        mementos.add(memento);
    }

    public Memento getMemento(int index){
        return  mementos.get(index);
    }

    public ArrayList<Memento> getMementos() {
        return mementos;
    }
}
