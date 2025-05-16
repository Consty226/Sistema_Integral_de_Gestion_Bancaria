package Main;

import Clientes.Persona;
import Cuentas.CajaDeAhorro;
import Cuentas.CuentaCorriente;
import Empleados.Cajero;
import Empleados.Gerente;
import Prestamo.Prestamo;
import Servicio.BancoServicio;
import Tarjetas.TarjetaCredito;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        // --- 1. Crear empleado (cajero y gerente) ---
        Cajero cajero1 = new Cajero("Carlos", 101);
        Gerente gerente1 = new Gerente("Laura", 201);

        // --- 2. Crear cliente ---
        Persona cliente1 = new Persona("José", "Av. Libertador 123", Arrays.asList("1122334455"), "12345678");

        // --- 3. Crear cuentas y vincularlas ---
        CajaDeAhorro cuentaAhorro = new CajaDeAhorro("CA-001", cliente1.getNombre(), 5000);
        CuentaCorriente cuentaCorriente = new CuentaCorriente("CC-001", cliente1.getNombre(), 2000, 1000);

        cliente1.vincularCuenta(cuentaAhorro);
        cliente1.vincularCuenta(cuentaCorriente);

        // --- 4. Crear tarjeta y vincular ---
        TarjetaCredito tarjeta = new TarjetaCredito(TarjetaCredito.generarNumeroTarjeta(), 10000);
        cliente1.vincularTarjeta(tarjeta);

        // --- 5. Crear préstamo y vincular ---
        Prestamo prestamo = new Prestamo(20000, 0.2);
        cliente1.vincularPrestamo(prestamo);

        // --- 6. Crear servicio bancario ---
        BancoServicio banco = new BancoServicio();

        // --- 7. Realizar operaciones ---
        banco.realizarDeposito(cuentaAhorro, 3000, cajero1);
        banco.realizarExtraccion(cuentaAhorro, 1000, cajero1);
        banco.realizarTransferencia(cuentaAhorro, cuentaCorriente, 2000, cajero1);
        banco.registrarConsumoTarjeta(tarjeta, 1500, cajero1);
        banco.registrarPagoTarjeta(tarjeta, 500, cajero1);
        banco.registrarPagoPrestamo(prestamo, 3000, gerente1);

        // --- 8. Mostrar historial de operaciones ---
        banco.mostrarHistorial();
    }
}
