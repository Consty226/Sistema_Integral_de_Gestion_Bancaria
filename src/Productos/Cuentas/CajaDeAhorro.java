package Productos.Cuentas;

import Clientes.Cliente;

public class CajaDeAhorro extends Cuenta {

    public CajaDeAhorro(Cliente titular, String numeroCuenta, double saldoInicial) {
        super(titular, numeroCuenta, saldoInicial);
    }

    @Override
    public boolean extraer(double monto) {
        if (monto <= saldo) {
            saldo -= monto;
            return true;
        }
        return false;
    }
}
