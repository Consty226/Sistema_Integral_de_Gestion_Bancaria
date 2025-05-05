package Cuentas;

public class CajaDeAhorro extends Cuenta {

    public CajaDeAhorro(String numeroCuenta, String titular, double saldoInicial) {
        super(numeroCuenta, titular, saldoInicial);
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
