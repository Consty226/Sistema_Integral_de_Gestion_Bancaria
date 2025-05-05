import java.util.Random;

public class TarjetaCredito {

    private final String numeroTarjeta;
    private final double limiteCredito;
    private double saldoDisponible;

    /**
     * Genera un número de tarjeta de crédito aleatorio de 16 dígitos.
     * return Un String que representa el número de tarjeta generado.
     */
    public static String generarNumeroTarjeta() {
        Random random = new Random();
        StringBuilder sb = new StringBuilder(16);
        for (int i = 0; i < 16; i++) {
            sb.append(random.nextInt(10)); // Genera un dígito aleatorio (0-9)
        }
        return sb.toString();
    }

    /**
     * El saldo disponible inicial es igual al límite de crédito.
     *  numeroTarjeta El número único de la tarjeta de crédito.
     *  limiteCredito El límite máximo de crédito permitido. Debe ser positivo.
     *  IllegalArgumentException si el número de tarjeta es nulo/vacío o el límite es negativo.
     */
    public TarjetaCredito(String numeroTarjeta, double limiteCredito) {
        if (numeroTarjeta == null || numeroTarjeta.trim().isEmpty()) {
            throw new IllegalArgumentException("El número de tarjeta no puede ser nulo o vacío.");
        }
        if (limiteCredito < 0) {
            throw new IllegalArgumentException("El límite de crédito no puede ser negativo.");
        }
        this.numeroTarjeta = numeroTarjeta;
        this.limiteCredito = limiteCredito;
        this.saldoDisponible = limiteCredito; // Al principio, el saldo disponible es el límite total
    }

    // Getters (Métodos para obtener los valores)

    public String getNumeroTarjeta() {
        return numeroTarjeta;
    }

    public double getLimiteCredito() {
        return limiteCredito;
    }

    public double getSaldoDisponible() {
        return saldoDisponible;
    }

    /**
     * Registra un consumo en la tarjeta si hay saldo disponible suficiente.
     * El monto a consumir (debe ser mayor que 0).
     * return true si el consumo fue exitoso, false si no hay saldo suficiente o el monto es inválido.
     */
    public boolean registrarConsumo(double monto) {
        if (monto <= 0) {
            System.out.println("Error: El monto del consumo debe ser positivo.");
            return false;
        }
        if (monto > this.saldoDisponible) {
            System.out.println("Error: Saldo insuficiente para el consumo de $" + String.format("%.2f", monto) +
                    ". Saldo disponible: $" + String.format("%.2f", this.saldoDisponible));
            return false;
        }

        this.saldoDisponible -= monto;
        System.out.println("Consumo de $" + String.format("%.2f", monto) + " registrado. Saldo restante: $" + String.format("%.2f", this.saldoDisponible));
        return true;
    }

    /**
     * Registra un pago en la tarjeta, aumentando el saldo disponible.
     *  El monto a pagar (debe ser mayor que 0).
     * return true si el pago fue exitoso, false si el monto es inválido.
     */
    public boolean registrarPago(double monto) {
        if (monto <= 0) {
            System.out.println("Error: El monto del pago debe ser positivo.");
            return false;
        }
        this.saldoDisponible += monto;
        System.out.println("Pago de $" + String.format("%.2f", monto) + " registrado. Nuevo saldo disponible: $" + String.format("%.2f", this.saldoDisponible));
        return true;
    }
    /**
     * Devuelve una representación en String del estado actual de la tarjeta.
     * return una cadena con la información de la tarjeta.
     */

    public String toString() {
        return "TarjetaCredito {" +
                "numero='" + numeroTarjeta + '\'' +
                ", limite=" + String.format("%.2f", limiteCredito) +
                ", saldoDisponible=" + String.format("%.2f", saldoDisponible) +
                '}';
    }
}