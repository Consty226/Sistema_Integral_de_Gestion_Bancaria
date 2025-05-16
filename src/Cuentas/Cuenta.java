package Cuentas;

public abstract class Cuenta {
    public String numeroCuenta;
    public double saldo;

    //Constructor
    public Cuenta(String numeroCuenta, String titular, double saldoInicial) {
        this.numeroCuenta = numeroCuenta;
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

    public double getSaldo() {
        return saldo;
    }
}