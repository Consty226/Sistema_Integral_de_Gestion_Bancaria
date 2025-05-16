package Tarjetas;

import java.util.Random;

public class TarjetaCredito {

    private final String numeroTarjeta;
    private final double limiteCredito;
    private double saldoDisponible;
    
//    Genera un número de tarjeta de crédito aleatorio de 16 dígitos.
//    @return Un String que representa el número de tarjeta generado.
    public static String generarNumeroTarjeta() {
        Random random = new Random();
        StringBuilder sb = new StringBuilder(16);
        for (int i = 0; i < 16; i++) {
            sb.append(random.nextInt(10)); // Genera un dígito aleatorio (0-9)
        }
        return sb.toString();
    }

    
//     El saldo disponible inicial es igual al límite de crédito.
//     @param numeroTarjeta El número único de la tarjeta de crédito.
//     @param limiteCredito El límite máximo de crédito permitido. Debe ser positivo.
//     @throws IllegalArgumentException si el número de tarjeta es nulo/vacío o el límite es negativo.
    public TarjetaCredito(Cliente titular, String numeroTarjeta, double limiteCredito) {
        // Agregacion con Cliente.
        this.titular = titular;
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

    // --- Getters ---

    public String getNumeroTarjeta() {
        return numeroTarjeta;
    }

    public double getLimiteCredito() {
        return limiteCredito;
    }

    public double getSaldoDisponible() {
        return saldoDisponible;
    }

    // --- Operaciones ---

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

    public boolean registrarPago(double monto) {
        if (monto <= 0) {
            System.out.println("Error: El monto del pago debe ser positivo.");
            return false;
        }
        this.saldoDisponible += monto;
        System.out.println("Pago de $" + String.format("%.2f", monto) + " registrado. Nuevo saldo disponible: $" + String.format("%.2f", this.saldoDisponible));
        return true;
    }

    // --- toString ---

    @Override
    public String toString() {
        return "TarjetaCredito {" +
                "numero='" + numeroTarjeta + '\'' +
                ", limite=" + String.format("%.2f", limiteCredito) +
                ", saldoDisponible=" + String.format("%.2f", saldoDisponible) +
                '}';
    }

    // --- equals y hashCode (para Set) ---

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TarjetaCredito that = (TarjetaCredito) o;
        return numeroTarjeta.equals(that.numeroTarjeta);
    }

    @Override
    public int hashCode() {
        return numeroTarjeta.hashCode();
    }
}
