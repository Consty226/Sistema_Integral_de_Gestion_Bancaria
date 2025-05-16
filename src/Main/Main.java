package Main;

import CajaDeSeguridad.CajaDeSeguridad;
import Clientes.Persona;
import Cuentas.CajaDeAhorro;
import Cuentas.CuentaCorriente;
import Empleados.Cajero;
import Empleados.Gerente;
import Prestamo.Prestamo;
import Servicio.BancoServicio;
import Tarjetas.TarjetaCredito;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        // --- 1. Crear empleado (cajero y gerente) ---
        Cajero cajero1 = new Cajero("Carlos", 101);
        Gerente gerente1 = new Gerente("Laura", 201);

        // --- 2. Crear cliente ---
        Persona cliente1 = new Persona("José", "Av. Libertador 123", List.of("1122334455"), "12345678", "1234");

        // --- 3. Crear cuentas y vincularlas ---
        CajaDeAhorro cuentaAhorro = new CajaDeAhorro("CA-001", 5000);
        CuentaCorriente cuentaCorriente = new CuentaCorriente("CC-001", 2000, 1000);
        cliente1.setCuentas(cuentaAhorro);
        cliente1.setCuentas(cuentaCorriente);

        // --- 4. Crear tarjeta y vincular ---
        TarjetaCredito tarjeta = new TarjetaCredito(cliente1, TarjetaCredito.generarNumeroTarjeta(), 10000);

        // --- 5. Crear préstamo y vincular ---
        Prestamo prestamo = new Prestamo(20000, 0.2);

        // --- 6. Crear servicio bancario ---
        BancoServicio banco = new BancoServicio();

        // --- 7. Realizar operaciones ---
        banco.realizarDeposito(cuentaAhorro, 3000, cajero1);
        banco.realizarExtraccion(cliente1,cuentaAhorro, 1000, cajero1);
        banco.realizarTransferencia(cliente1,cuentaAhorro, cuentaCorriente, 2000, cajero1);
        banco.registrarConsumoTarjeta(tarjeta, 1500, cajero1, cliente1);
        banco.registrarPagoTarjeta(tarjeta, 500, cajero1, cliente1);
        banco.registrarPagoPrestamo(prestamo, 3000, gerente1, cliente1);
        banco.registrarElementoCaja(cliente1, "Aros", gerente1);
        banco.registrarElementoCaja(cliente1, "Medalla", gerente1);
        banco.removerElementoCaja(cliente1, "Aros", cajero1); // Comprobar permiso de empleado
        banco.removerElementoCaja(cliente1, "Aros", gerente1);

        // --- 8. Mostrar historial de operaciones ---
        banco.mostrarHistorial();
    }
}
