package Servicio;

import Operaciones.OperacionBancaria;
import Cuentas.Cuenta;
import Empleados.Empleado;
import Prestamo.Prestamo;
import Tarjetas.TarjetaCredito;

import java.util.ArrayList;
import java.util.List;

public class BancoServicio {
    private List<OperacionBancaria> historial;

    public BancoServicio() {
        this.historial = new ArrayList<>();
    }

    // --- DEPÓSITO ---
    public void realizarDeposito(Cuenta cuenta, double monto, Empleado empleado) {
        if (monto <= 0) {
            System.out.println("El monto debe ser mayor que cero.");
            return;
        }

        cuenta.depositar(monto);

        historial.add(new OperacionBancaria(
                "Depósito",
                monto,
                "Depósito en cuenta: " + cuenta.getNumeroCuenta(),
                cuenta.getNumeroCuenta(),
                null,
                empleado.getNombre()
        ));

        System.out.println("Depósito realizado correctamente.");
    }

    // --- TRANSFERENCIA ---
    public void realizarTransferencia(Cuenta origen, Cuenta destino, double monto, Empleado empleado) {
        if (origen.transferir(destino, monto)) {
            historial.add(new OperacionBancaria(
                    "Transferencia",
                    monto,
                    "Transferencia entre cuentas",
                    origen.getNumeroCuenta(),
                    destino.getNumeroCuenta(),
                    empleado.getNombre()
            ));
            System.out.println("Transferencia realizada correctamente.");
        } else {
            System.out.println("La transferencia no pudo realizarse.");
        }
    }

    // --- HISTORIAL ---
    public List<OperacionBancaria> getHistorial() {
        return historial;
    }

    public void mostrarHistorial() {
        System.out.println("Historial de operaciones:");
        for (OperacionBancaria op : historial) {
            System.out.println(op);
        }
    }

    // --- EXTRACCIÓN ---
    public void realizarExtraccion(Cuenta cuenta, double monto, Empleado empleado) {
        if (monto <= 0) {
            System.out.println("El monto debe ser mayor que cero.");
            return;
        }

        if (cuenta.extraer(monto)) {
            historial.add(new OperacionBancaria(
                    "Extracción",
                    monto,
                    "Extracción de cuenta: " + cuenta.getNumeroCuenta(),
                    cuenta.getNumeroCuenta(),
                    null,
                    empleado.getNombre()
            ));
            System.out.println("Extracción realizada correctamente.");
        } else {
            System.out.println("No se pudo realizar la extracción. Verifique el saldo o el límite.");
        }
    }
    // --- CONSUMO DE TARJETA ---
    public void registrarConsumoTarjeta(TarjetaCredito tarjeta, double monto, Empleado empleado) {
        if (tarjeta.registrarConsumo(monto)) {
            historial.add(new OperacionBancaria(
                    "Consumo con Tarjeta",
                    monto,
                    "Consumo realizado con tarjeta: " + tarjeta.getNumeroTarjeta(),
                    null,
                    null,
                    empleado.getNombre()
            ));
        } else {
            System.out.println("El consumo no pudo registrarse.");
        }
    }
    // --- PAGO DE TARJETA ---
    public void registrarPagoTarjeta(TarjetaCredito tarjeta, double monto, Empleado empleado) {
        if (tarjeta.registrarPago(monto)) {
            historial.add(new OperacionBancaria(
                    "Pago de Tarjeta",
                    monto,
                    "Pago registrado a tarjeta: " + tarjeta.getNumeroTarjeta(),
                    null,
                    null,
                    empleado.getNombre()
            ));
        } else {
            System.out.println("El pago no pudo registrarse.");
        }
    }
    // --- PAGO DE PRÉSTAMO ---
    public void registrarPagoPrestamo(Prestamo prestamo, double monto, Empleado empleado) {
        double saldoAnterior = prestamo.getSaldoRestante();
        prestamo.realizarPago(monto);
        double saldoFinal = prestamo.getSaldoRestante();

        if (saldoFinal < saldoAnterior) {
            historial.add(new OperacionBancaria(
                    "Pago de Préstamo",
                    monto,
                    "Pago aplicado a préstamo con saldo anterior: $" + saldoAnterior,
                    null,
                    null,
                    empleado.getNombre()
            ));
            System.out.println("Pago registrado correctamente.");
        } else {
            System.out.println("No se aplicó ningún pago.");
        }
    }
}

