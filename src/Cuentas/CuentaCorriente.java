package Cuentas;

public class CuentaCorriente extends Cuenta {
    private int limiteDescubierto;

    public CuentaCorriente(String numeroCuenta, double saldoInicial, int limiteDescubierto) {
        super(numeroCuenta, saldoInicial);
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
