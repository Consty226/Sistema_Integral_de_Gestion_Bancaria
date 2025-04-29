import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Transaccion {
        private LocalDateTime fecha;
        private String descripcion;
        private double monto;
        private String tipo; // "CONSUMO" o "PAGO"

        public Transaccion(String descripcion, double monto, String tipo) {
            this.fecha = LocalDateTime.now();
            this.descripcion = descripcion;
            this.monto = monto;
            this.tipo = tipo;
        }

        // Getters para los atributos
        public LocalDateTime getFecha() {
            return fecha;
        }

        public String getDescripcion() {
            return descripcion;
        }

        public double getMonto() {
            return monto;
        }

        public String getTipo() {
            return tipo;
        }

        @Override
        public String toString() {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            return fecha.format(formatter) + " - " + tipo + " - " + descripcion + ": $" + Math.abs(monto);
        }
}
