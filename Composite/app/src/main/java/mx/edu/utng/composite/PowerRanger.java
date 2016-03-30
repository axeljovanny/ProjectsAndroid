package mx.edu.utng.composite;

/**
 * Created by qas on 11/03/16.
 */
public interface PowerRanger {
    boolean fight();
    void add(PowerRanger ranger);
    void remove(PowerRanger ranger);
    PowerRanger getPowerRanger(int i);

}
