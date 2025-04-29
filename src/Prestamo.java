public class Prestamo {
    private double montoPrestamo;
    private double saldoRestante;
    private int tasaInteres;

    public Prestamo(double montoPrestamo, double saldoRestante, int tasaInteres) {
        this.montoPrestamo = montoPrestamo;
        this.saldoRestante = saldoRestante;
        this.tasaInteres = tasaInteres;
    }

    public double getMontoPrestamo() {
        return montoPrestamo;
    }

    public void setMontoPrestamo(double montoPrestamo) {
        this.montoPrestamo = montoPrestamo;
    }

    public double getSaldoRestante() {
        return saldoRestante;
    }

    public void setSaldoRestante(double saldoRestante) {
        this.saldoRestante = saldoRestante;
    }

    public int getTasaInteres() {
        return tasaInteres;
    }

    public void setTasaInteres(int tasaInteres) {
        this.tasaInteres = tasaInteres;
    }
}
