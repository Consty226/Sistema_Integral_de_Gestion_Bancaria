import java.util.ArrayList;
import java.util.List;
import java.util.UUID; // Para generar números únicos

public class TarjetaDeCredito {
    private String numeroUnico;
    private double limiteCredito;
    private double saldoRestante;
    private double tasaInteresAnual;
    private List<String> historialTransacciones;

    // Constructor para crear una nueva tarjeta
    public TarjetaDeCredito(double limiteCredito, double tasaInteresAnual) {
        this.numeroUnico = generarNumeroUnico(); // Generamos un número único al crear la tarjeta
        this.limiteCredito = limiteCredito;
        this.saldoRestante = limiteCredito; // Al principio, el saldo restante es igual al límite
        this.tasaInteresAnual = tasaInteresAnual;
        this.historialTransacciones = new ArrayList<>();
        System.out.println("Tarjeta creada con número: " + this.numeroUnico);
    }

    // Método para generar un número único para la tarjeta
    private String generarNumeroUnico() {
        return UUID.randomUUID().toString(); // UUID genera un identificador único universal
    }

    // Getters para acceder a la información de la tarjeta (solo lectura por ahora)
    public String getNumeroUnico() {
        return numeroUnico;
    }

    public double getSaldoRestante() {
        return saldoRestante;
    }

    public double getTasaInteresAnual() {
        return tasaInteresAnual;
    }

    // Método para registrar un consumo
    public void registrarConsumo(String descripcion, double monto) {
        if (monto <= saldoRestante) {
            saldoRestante -= monto;
            String transaccion = "CONSUMO: " + descripcion + " - $" + monto;
            historialTransacciones.add(transaccion);
            System.out.println("Consumo registrado en la tarjeta " + numeroUnico + ": " + transaccion);
            System.out.println("Nuevo saldo restante: $" + saldoRestante);
        } else {
            System.out.println("Fondos insuficientes en la tarjeta " + numeroUnico + " para el consumo de $" + monto);
        }
    }

    // Método para registrar un pago
    public void registrarPago(double monto) {
        saldoRestante += monto;
        String transaccion = "PAGO: $" + monto;
        historialTransacciones.add(transaccion);
        System.out.println("Pago registrado en la tarjeta " + numeroUnico + ": " + transaccion);
        System.out.println("Nuevo saldo restante: $" + saldoRestante);
    }

    // Método para mostrar el historial de transacciones
    public void mostrarHistorial() {
        System.out.println("\n--- Historial de Transacciones de la Tarjeta " + numeroUnico + " ---");
        if (historialTransacciones.isEmpty()) {
            System.out.println("No hay transacciones registradas.");
        } else {
            for (String transaccion : historialTransacciones) {
                System.out.println(transaccion);
            }
        }
        System.out.println("--------------------------------------------------");
    }
}