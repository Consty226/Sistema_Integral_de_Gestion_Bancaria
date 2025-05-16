package Tarjetas;

import Clientes.Persona;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class TarjetaCreditoTest {

    private TarjetaCredito tarjeta;
    private Persona cliente;

    // Antes de cada test, creo un cliente y una tarjeta de crédito con $5000 de límite
    @BeforeEach
    void setUp() {
        cliente = new Persona("José Pérez", "Av. Falsa 123", Arrays.asList("123456789"), "12345678", "clave123");
        tarjeta = new TarjetaCredito(cliente, "1234567890123456", 5000.0);
    }

    // Verifico que la tarjeta se cree correctamente con el saldo igual al límite
    @Test
    void testCreacionTarjeta() {
        assertEquals("1234567890123456", tarjeta.getNumeroTarjeta());
        assertEquals(5000.0, tarjeta.getLimiteCredito(), 0.01);
        assertEquals(5000.0, tarjeta.getSaldoDisponible(), 0.01);
    }

    // Verifico que se pueda registrar un consumo válido y se descuente del saldo
    @Test
    void testConsumoValido() {
        boolean resultado = tarjeta.registrarConsumo(1500.0);
        assertTrue(resultado); // El consumo fue aceptado
        assertEquals(3500.0, tarjeta.getSaldoDisponible(), 0.01); // El saldo bajó correctamente
    }

    // Intento consumir más de lo que tengo disponible, debería fallar
    @Test
    void testConsumoMayorAlSaldo() {
        boolean resultado = tarjeta.registrarConsumo(6000.0);
        assertFalse(resultado); // No debería permitir el consumo
        assertEquals(5000.0, tarjeta.getSaldoDisponible(), 0.01); // El saldo no debe cambiar
    }

    // Intento consumir un monto negativo, tampoco debería dejarme
    @Test
    void testConsumoNegativo() {
        boolean resultado = tarjeta.registrarConsumo(-100.0);
        assertFalse(resultado);
        assertEquals(5000.0, tarjeta.getSaldoDisponible(), 0.01);
    }

    // Registro un pago válido y verifico que el saldo disponible aumente
    @Test
    void testPagoValido() {
        tarjeta.registrarConsumo(2000.0); // Gasto primero
        boolean resultado = tarjeta.registrarPago(1000.0); // Pago después
        assertTrue(resultado);
        assertEquals(4000.0, tarjeta.getSaldoDisponible(), 0.01);
    }

    // Intento pagar con un monto inválido (negativo), no debería cambiar el saldo
    @Test
    void testPagoNegativo() {
        boolean resultado = tarjeta.registrarPago(-500.0);
        assertFalse(resultado);
        assertEquals(5000.0, tarjeta.getSaldoDisponible(), 0.01);
    }

    // Verifico que el método equals funcione correctamente
    @Test
    void testEquals() {
        TarjetaCredito otraTarjeta = new TarjetaCredito(cliente, "1234567890123456", 7000.0);
        assertEquals(tarjeta, otraTarjeta); // Son iguales porque tienen el mismo número de tarjeta
    }
}
