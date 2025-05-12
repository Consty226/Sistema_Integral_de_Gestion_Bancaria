package Productos.Cuentas;

import Productos.Producto;

public abstract class Cuenta extends Producto {
    public String numeroCuenta;
    public String titular;
    public double saldo;

    //Constructor
    public Cuenta(String numeroCuenta, String titular, double saldoInicial) {
        this.numeroCuenta = numeroCuenta;
        this.titular = titular;
        this.saldo = saldoInicial;
    }

    //Depostiar
    public void depositar(double monto) {
        if (monto > 0) {
            saldo += monto;
        }
    }

    //Extraer
    public abstract boolean extraer(double monto);

    //Transferir
    public boolean transferir(Cuenta destino, double monto) {
        if (this.extraer(monto)) {
            destino.depositar(monto);
            return true;
        }
        return false;
    }

    //Setters y Getters

    public String getNumeroCuenta() {
        return numeroCuenta;
    }

    public String getTitular() {
        return titular;
    }

    public double getSaldo() {
        return saldo;
    }
}