package Productos.Prestamo;

import Clientes.Cliente;
import Productos.Producto;

public class Prestamo extends Producto {
    private final double montoOtorgado;
    private final double tasaDeInteres;
    private double saldoRestante;

    public Prestamo(Cliente titular, double montoOtorgado, double tasaDeInteres) {
        super(titular);
        this.montoOtorgado = montoOtorgado * (1 + tasaDeInteres / 100);
        this.tasaDeInteres = tasaDeInteres;
        this.saldoRestante = this.montoOtorgado;
    }

    public double getMontoOtorgado() {
        return montoOtorgado;
    }

    public double getSaldoRestante() {
        return saldoRestante;
    }

    public void recibirPago(double monto) {
        this.saldoRestante -= monto;
    }

    public double getTasaDeInteres() {
        return tasaDeInteres;
    }

}
