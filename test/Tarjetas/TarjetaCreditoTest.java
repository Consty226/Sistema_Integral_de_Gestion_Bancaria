package Tarjetas;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TarjetaCreditoTest {

    @Test
    void generarNumeroTarjetaDebeTener16Digitos() {
        String numero = TarjetaCredito.generarNumeroTarjeta();
        assertNotNull(numero);
        assertEquals(16, numero.length());
        assertTrue(numero.matches("\\d{16}"));
    }

    @Test
    void registrarConsumoExitoso() {
        TarjetaCredito tarjeta = new TarjetaCredito("1111222233334444", 1000);
        boolean resultado = tarjeta.registrarConsumo(300);
        assertTrue(resultado);
        assertEquals(700, tarjeta.getSaldoDisponible());
    }

    @Test
    void registrarConsumoFallaPorSaldoInsuficiente() {
        TarjetaCredito tarjeta = new TarjetaCredito("1111222233334444", 500);
        boolean resultado = tarjeta.registrarConsumo(600);
        assertFalse(resultado);
        assertEquals(500, tarjeta.getSaldoDisponible());
    }

    @Test
    void registrarPagoExitoso() {
        TarjetaCredito tarjeta = new TarjetaCredito("1111222233334444", 1000);
        tarjeta.registrarConsumo(400);
        boolean resultado = tarjeta.registrarPago(200);
        assertTrue(resultado);
        assertEquals(800, tarjeta.getSaldoDisponible());
    }

    @Test
    void registrarPagoInvalido() {
        TarjetaCredito tarjeta = new TarjetaCredito("1111222233334444", 1000);
        boolean resultado = tarjeta.registrarPago(0);
        assertFalse(resultado);
        assertEquals(1000, tarjeta.getSaldoDisponible());
    }

    @Test
    void testEqualsYHashCode() {
        TarjetaCredito t1 = new TarjetaCredito("9999888877776666", 5000);
        TarjetaCredito t2 = new TarjetaCredito("9999888877776666", 3000); // mismo número

        assertEquals(t1, t2);
        assertEquals(t1.hashCode(), t2.hashCode());
    }

    @Test
    void testToStringContieneDatos() {
        TarjetaCredito tarjeta = new TarjetaCredito("4444333322221111", 2000);
        String texto = tarjeta.toString();
        assertNotNull(texto);
        assertTrue(texto.contains("numero='4444333322221111"));
        assertTrue(texto.contains("limite="));
        assertTrue(texto.contains("saldoDisponible="));
    }
}
