package Productos.Cuentas;

import Clientes.Cliente;

public class CuentaCorriente extends Cuenta {
    private int limiteDescubierto;

    public CuentaCorriente(Cliente titular, String numeroCuenta, double saldoInicial, int limiteDescubierto) {
        super(titular, numeroCuenta, saldoInicial);
        this.limiteDescubierto = limiteDescubierto;
    }

    @Override
    public boolean extraer(double monto) {
        if (monto <= saldo + limiteDescubierto) {
            saldo -= monto;
            return true;
        }
        return false;
    }

    public int getLimiteDescubierto() {
        return limiteDescubierto;
    }

    public void setLimiteDescubierto(int limiteDescubierto) {
        this.limiteDescubierto = limiteDescubierto;
    }
}
