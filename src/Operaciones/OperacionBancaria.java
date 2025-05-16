package Operaciones;

import java.time.LocalDateTime;

import Clientes.Cliente;
import Cuentas.Cuenta;
import Empleados.Empleado;

public class OperacionBancaria {
    private String tipo; // Depósito, Extracción, Transferencia, etc.
    private double monto;
    private String descripcion; // Detalles opcionales
    private Cuenta cuentaOrigen;
    private Cuenta cuentaDestino;
    private Empleado empleadoResponsable;
    private LocalDateTime fechaHora;
    private Cliente cliente;

    // Constructor
    public OperacionBancaria(
            String tipo,
            double monto,
            String descripcion,
            Cuenta cuentaOrigen,
            Cuenta cuentaDestino,
            Empleado empleadoResponsable,
            Cliente cliente
    ) {
        this.tipo = tipo;
        this.monto = monto;
        this.descripcion = descripcion;
        this.cuentaOrigen = cuentaOrigen;
        this.cuentaDestino = cuentaDestino;
        this.empleadoResponsable = empleadoResponsable;
        this.fechaHora = LocalDateTime.now(); // Captura fecha y hora actuales
        this.cliente = cliente;
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

    public Cuenta getCuentaOrigen() {
        return cuentaOrigen;
    }

    public Cuenta getCuentaDestino() {
        return cuentaDestino;
    }

    public Empleado getEmpleadoResponsable() {
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
                " | Cliente: " + (cliente != null ? cliente: "-") +
                " | " + descripcion;
    }
}
