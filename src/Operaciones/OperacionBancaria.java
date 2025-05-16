package Operaciones;

import java.time.LocalDateTime;

public class OperacionBancaria {
    private String tipo; // Depósito, Extracción, Transferencia, etc.
    private double monto;
    private String descripcion; // Detalles opcionales
    private String cuentaOrigen;
    private String cuentaDestino;
    private String empleadoResponsable;
    private LocalDateTime fechaHora;

    // Constructor
    public OperacionBancaria(String tipo, double monto, String descripcion,
                             String cuentaOrigen, String cuentaDestino, String empleadoResponsable) {
        this.tipo = tipo;
        this.monto = monto;
        this.descripcion = descripcion;
        this.cuentaOrigen = cuentaOrigen;
        this.cuentaDestino = cuentaDestino;
        this.empleadoResponsable = empleadoResponsable;
        this.fechaHora = LocalDateTime.now(); // Captura fecha y hora actuales
    }

    // Getters
    public String getTipo() {
        return tipo;
    }

    public double getMonto() {
        return monto;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getCuentaOrigen() {
        return cuentaOrigen;
    }

    public String getCuentaDestino() {
        return cuentaDestino;
    }

    public String getEmpleadoResponsable() {
        return empleadoResponsable;
    }

    public LocalDateTime getFechaHora() {
        return fechaHora;
    }

    @Override
    public String toString() {
        return "[" + fechaHora + "] " + tipo +
                " | Monto: $" + monto +
                " | Empleado: " + empleadoResponsable +
                " | Origen: " + (cuentaOrigen != null ? cuentaOrigen : "-") +
                " | Destino: " + (cuentaDestino != null ? cuentaDestino : "-") +
                " | " + descripcion;
    }
}
