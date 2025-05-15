package Productos.Cuentas;

import Clientes.Cliente;
import Productos.Producto;

public abstract class Cuenta extends Producto {
    public String numeroCuenta;
    public double saldo;

    //Constructor
    public Cuenta(Cliente titular, String numeroCuenta, double saldoInicial) {
        super(titular);
        this.numeroCuenta = numeroCuenta;
        this.saldo = saldoInicial;
    }

    //Depostiar
    public void depositar(double monto) {
        if (monto > 0) {
            saldo += monto;
        } else{
            System.out.println("El monto debe ser mayor a 0.");
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

    public double getSaldo() {
        return saldo;
    }
}