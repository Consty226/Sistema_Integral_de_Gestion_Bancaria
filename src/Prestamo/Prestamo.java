package Prestamo;

public class Prestamo {
    private double montoOtorgado;
    private double saldoRestante;
    private double tasaInteres;

    // Constructor
    public Prestamo(double montoOtorgado, double tasaInteres) {
        this.montoOtorgado = montoOtorgado;
        this.tasaInteres = tasaInteres;
        this.saldoRestante = montoOtorgado;
    }

    // Metodo para realizar un pago
    public void realizarPago(double monto) {
        if (monto <= 0) {
            System.out.println("El monto debe ser mayor que cero.");
            return;
        }

        if (monto > saldoRestante) {
            System.out.println("El monto excede el saldo restante. Se aplicará solo hasta cubrir el saldo.");
            saldoRestante = 0;
        } else {
            saldoRestante -= monto;
        }
    }

    // Getters
    public double getMontoOtorgado() {
        return montoOtorgado;
    }

    public double getSaldoRestante() {
        return saldoRestante;
    }

    public double getTasaInteres() {
        return tasaInteres;
    }

    // Para mostrar los datos del préstamo
    @Override
    public String toString() {
        return "Prestamo{" +
                "montoOtorgado=" + montoOtorgado +
                ", saldoRestante=" + saldoRestante +
                ", tasaInteres=" + tasaInteres +
                '}';
    }
}

