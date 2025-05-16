package Servicio;

import Clientes.Persona;
import Cuentas.CajaDeAhorro;
import Cuentas.Cuenta;
import Empleados.*;
import Prestamo.Prestamo;
import Tarjetas.TarjetaCredito;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

class BancoServicioTest {

    private BancoServicio banco;
    private Persona cliente;
    private Cajero cajero;
    private Gerente gerente;
    private Cuenta cuenta;
    private TarjetaCredito tarjeta;
    private Prestamo prestamo;

    @BeforeEach
    void setUp() {
        banco = new BancoServicio();

        cliente = new Persona("José", "Av. Siempre Viva", Collections.singletonList("123456"), "12345678", "clave123");
        cuenta = new CajaDeAhorro("CA001", 1000.0);
        cliente.setCuentas(cuenta);

        cajero = new Cajero("Carlos", 101);
        gerente = new Gerente("Lucía", 201);

        tarjeta = new TarjetaCredito(cliente, "1111222233334444", 3000.0);
        prestamo = new Prestamo(5000.0, 0.1);
    }

    // Test para realizar un depósito válido
    @Test
    void testRealizarDepositoValido() {
        banco.realizarDeposito(cuenta, 500.0, cajero);
        assertEquals(1500.0, cuenta.getSaldo(), 0.01);
        assertEquals(1, banco.getHistorial().size());
    }

    // Test para extracción válida
    @Test
    void testRealizarExtraccionValida() {
        banco.realizarExtraccion(cliente, cuenta, 500.0, cajero);
        assertEquals(500.0, cuenta.getSaldo(), 0.01);
        assertEquals(1, banco.getHistorial().size());
    }

    // Test para transferencia válida
    @Test
    void testRealizarTransferenciaValida() {
        Cuenta destino = new CajaDeAhorro("CA002", 500.0);
        cliente.setCuentas(destino);
        banco.realizarTransferencia(cliente, cuenta, destino, 300.0, cajero);

        assertEquals(700.0, cuenta.getSaldo(), 0.01);
        assertEquals(800.0, destino.getSaldo(), 0.01);
        assertEquals(1, banco.getHistorial().size());
    }

    // Test para registrar consumo de tarjeta válido
    @Test
    void testRegistrarConsumoTarjetaValido() {
        banco.registrarConsumoTarjeta(tarjeta, 1000.0, cajero, cliente);
        assertEquals(2000.0, tarjeta.getSaldoDisponible(), 0.01);
        assertEquals(1, banco.getHistorial().size());
    }

    // Test para registrar pago de tarjeta
    @Test
    void testRegistrarPagoTarjeta() {
        tarjeta.registrarConsumo(1000.0);
        banco.registrarPagoTarjeta(tarjeta, 500.0, cajero, cliente);
        assertEquals(2500.0, tarjeta.getSaldoDisponible(), 0.01);
        assertEquals(1, banco.getHistorial().size());
    }

    // Test para pago de préstamo con gerente
    @Test
    void testRegistrarPagoPrestamo() {
        banco.registrarPagoPrestamo(prestamo, 1000.0, gerente, cliente);
        assertEquals(4000.0, prestamo.getSaldoRestante(), 0.01);
        assertEquals(1, banco.getHistorial().size());
    }

    // Test para guardar elemento en caja de seguridad
    @Test
    void testRegistrarElementoCaja() {
        banco.registrarElementoCaja(cliente, "Pasaporte", gerente);
        assertTrue(cliente.getCajaDeSeguridad().getContenido().contains("Pasaporte"));
        assertEquals(1, banco.getHistorial().size());
    }

    // Test para remover elemento de la caja de seguridad
    @Test
    void testRemoverElementoCaja() {
        cliente.getCajaDeSeguridad().setContenido("Contrato");
        banco.removerElementoCaja(cliente, "Contrato", gerente);
        assertTrue(cliente.getCajaDeSeguridad().getContenido().contains("Contrato")); // porque se usa setContenido en remover
        assertEquals(1, banco.getHistorial().size());
    }
}
