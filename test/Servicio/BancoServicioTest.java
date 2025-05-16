package Servicio;

import Cuentas.CajaDeAhorro;
import Cuentas.CuentaCorriente;
import Empleados.Cajero;
import Empleados.Gerente;
import Operaciones.OperacionBancaria;
import Prestamo.Prestamo;
import Tarjetas.TarjetaCredito;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class BancoServicioTest {

    @Test
    void realizarDeposito() {
        BancoServicio banco = new BancoServicio();
        Cajero cajero = new Cajero("Carlos", 1);
        CajaDeAhorro cuenta = new CajaDeAhorro("CA-001", "José", 1000);

        banco.realizarDeposito(cuenta, 500, cajero);

        assertEquals(1500, cuenta.getSaldo());
        assertEquals(1, banco.getHistorial().size());

        OperacionBancaria op = banco.getHistorial().get(0);
        assertEquals("Depósito", op.getTipo());
        assertEquals(500, op.getMonto());
    }

    @Test
    void realizarTransferencia() {
        BancoServicio banco = new BancoServicio();
        Cajero cajero = new Cajero("Carlos", 1);
        CajaDeAhorro origen = new CajaDeAhorro("CA-001", "José", 1000);
        CajaDeAhorro destino = new CajaDeAhorro("CA-002", "Ana", 500);

        banco.realizarTransferencia(origen, destino, 300, cajero);

        assertEquals(700, origen.getSaldo());
        assertEquals(800, destino.getSaldo());
        assertEquals(1, banco.getHistorial().size());
        assertEquals("Transferencia", banco.getHistorial().get(0).getTipo());
    }

    @Test
    void realizarExtraccion() {
        BancoServicio banco = new BancoServicio();
        Cajero cajero = new Cajero("Carlos", 1);
        CajaDeAhorro cuenta = new CajaDeAhorro("CA-003", "José", 1000);

        banco.realizarExtraccion(cuenta, 400, cajero);

        assertEquals(600, cuenta.getSaldo());
        assertEquals(1, banco.getHistorial().size());
        assertEquals("Extracción", banco.getHistorial().get(0).getTipo());
    }

    @Test
    void registrarConsumoTarjeta() {
        BancoServicio banco = new BancoServicio();
        Cajero cajero = new Cajero("Carlos", 1);
        TarjetaCredito tarjeta = new TarjetaCredito("1111222233334444", 1000);

        banco.registrarConsumoTarjeta(tarjeta, 300, cajero);

        assertEquals(700, tarjeta.getSaldoDisponible());
        assertEquals(1, banco.getHistorial().size());
        assertEquals("Consumo con Tarjeta", banco.getHistorial().get(0).getTipo());
    }

    @Test
    void registrarPagoTarjeta() {
        BancoServicio banco = new BancoServicio();
        Cajero cajero = new Cajero("Carlos", 1);
        TarjetaCredito tarjeta = new TarjetaCredito("1111222233334444", 1000);
        tarjeta.registrarConsumo(600); // para bajar saldo

        banco.registrarPagoTarjeta(tarjeta, 200, cajero);

        assertEquals(600, tarjeta.getSaldoDisponible());
        assertEquals(1, banco.getHistorial().size());
        assertEquals("Pago de Tarjeta", banco.getHistorial().get(0).getTipo());
    }

    @Test
    void registrarPagoPrestamo() {
        BancoServicio banco = new BancoServicio();
        Gerente gerente = new Gerente("Laura", 2);
        Prestamo prestamo = new Prestamo(5000, 0.15);

        banco.registrarPagoPrestamo(prestamo, 2000, gerente);

        assertEquals(3000, prestamo.getSaldoRestante());
        assertEquals(1, banco.getHistorial().size());
        assertEquals("Pago de Préstamo", banco.getHistorial().get(0).getTipo());
    }

    @Test
    void getHistorial() {
        BancoServicio banco = new BancoServicio();
        assertNotNull(banco.getHistorial());
        assertTrue(banco.getHistorial().isEmpty());
    }

    @Test
    void mostrarHistorial() {
        BancoServicio banco = new BancoServicio();
        Cajero cajero = new Cajero("Carlos", 1);
        CajaDeAhorro cuenta = new CajaDeAhorro("CA-004", "José", 1000);
        banco.realizarDeposito(cuenta, 200, cajero);

        assertDoesNotThrow(() -> banco.mostrarHistorial());
    }
}
