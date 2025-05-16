package Servicio;

import CajaDeSeguridad.CajaDeSeguridad;
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
        System.out.println("//////////////////////////");
        System.out.println("Servicio de banco iniciado");
        System.out.println("//////////////////////////");
    }

    // --- DEPÓSITO ---
    public void realizarDeposito(Cuenta cuenta, double monto, Empleado empleado) {
        if(!empleado.getRol().equals("Cajero")){
            System.out.println("El empleado no tiene permiso para realizar esta operacion");
            return;
        }
        if (monto <= 0) {
            System.out.println("El monto debe ser mayor que cero.");
            return;
        }

        cuenta.depositar(monto);

        historial.add(new OperacionBancaria(
                "Depósito",
                monto,
                "Depósito en cuenta: " + cuenta.getNumeroCuenta(),
                cuenta,
                null,
                empleado,
                null
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
                    cuenta,
                    null,
                    empleado,
                    titular
            ));
            System.out.println("Extracción realizada correctamente.");
        } else {
            System.out.println("No se pudo realizar la extracción. Verifique el saldo o el límite.");
        }
    }
    // --- CONSUMO DE TARJETA ---
    public void registrarConsumoTarjeta(TarjetaCredito tarjeta, double monto, Empleado empleado, Cliente cliente) {
        if (!tarjeta.getTitular().equals(cliente)){
            System.out.println("La tarjeta no pertenece al cliente. No puede realizar un consumo.");
            return;
        }
        if (tarjeta.registrarConsumo(monto)) {
            historial.add(new OperacionBancaria(
                    "Consumo con Tarjeta",
                    monto,
                    "Consumo realizado con tarjeta: " + tarjeta.getNumeroTarjeta(),
                    null,
                    null,
                    empleado,
                    cliente
            ));
        } else {
            System.out.println("El consumo no pudo registrarse.");
        }
    }
    // --- PAGO DE TARJETA ---
    public void registrarPagoTarjeta(TarjetaCredito tarjeta, double monto, Empleado empleado, Cliente cliente) {
        if (tarjeta.registrarPago(monto)) {
            historial.add(new OperacionBancaria(
                    "Pago de Tarjeta",
                    monto,
                    "Pago registrado a tarjeta: " + tarjeta.getNumeroTarjeta(),
                    null,
                    null,
                    empleado,
                    cliente
            ));
        } else {
            System.out.println("El pago no pudo registrarse.");
        }
    }
    // --- PAGO DE PRÉSTAMO ---
    public void registrarPagoPrestamo(Prestamo prestamo, double monto, Empleado empleado, Cliente cliente) {
        if(!empleado.getRol().equals("Gerente")){
            System.out.println("El empleado no tiene permiso para realizar esta operacion");
            return;
        }
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
                    empleado,
                    cliente
            ));
            System.out.println("Pago registrado correctamente.");
        } else {
            System.out.println("No se aplicó ningún pago.");
        }
    }

    public void registrarElementoCaja(Cliente cliente, String elemento, Empleado empleado){
        if(!empleado.getRol().equals("Gerente")){
            System.out.println("El empleado no tiene permiso para realizar esta operacion");
            return;
        }
        cliente.getCajaDeSeguridad().setContenido(elemento);

        this.historial.add(
                new OperacionBancaria(
                        "Guardado en Caja de Seguridad",
                        0,
                        "Guardado de elemento en la caja de " + cliente.getNombre(),
                        null,
                        null,
                        empleado,
                        cliente
                )
        );

        System.out.println("Elemento "+ elemento + " se guardó en la caja de seguridad de "+ cliente.getNombre() + " con exito");
    }

    public void removerElementoCaja(Cliente cliente, String elemento, Empleado empleado){
        if(!empleado.getRol().equals("Gerente")){
            System.out.println("El empleado no tiene permiso para realizar esta operacion");
            return;
        }
        cliente.getCajaDeSeguridad().setContenido(elemento);

        this.historial.add(
                new OperacionBancaria(
                        "Retiro en Caja de Seguridad",
                        0,
                        "Remocion de elemento en la caja del cliente " + cliente.getNombre(),
                        null,
                        null,
                        empleado,
                        cliente
                )
        );

        System.out.println("Elemento "+ elemento + " se removio de la caja de seguridad de "+ cliente.getNombre() + " con exito.");
    }
}

