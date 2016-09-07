package mx.edu.utng.prototype;

import java.util.Date;

/**
 * Created by qas on 5/09/16.
 */
public class TarjetaCredito implements Clonable {
    private int numero;
    private String titular;
    private Date fechaVencimiento;

    public TarjetaCredito(){}

    public TarjetaCredito(int numero, String titular,
                          Date fechaVencimiento) {
        this.numero = numero;
        this.titular = titular;
        this.fechaVencimiento = fechaVencimiento;
    }

    @Override
    public Clonable clonar() {
        TarjetaCredito clon =
                new TarjetaCredito(numero, titular, fechaVencimiento);
        return clon;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getTitular() {
        return titular;
    }

    public void setTitular(String titular) {
        this.titular = titular;
    }

    public Date getFechaVencimiento() {
        return fechaVencimiento;
    }

    public void setFechaVencimiento(Date fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }
}
