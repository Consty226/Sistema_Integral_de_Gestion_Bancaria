package Cuentas;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CuentaTest {

    @Test
    void depositar() {
        CajaDeAhorro cuenta = new CajaDeAhorro("CA-001", "José", 1000);
        cuenta.depositar(500);
        assertEquals(1500, cuenta.getSaldo());
    }


    @Test
    void extraer() {
        CajaDeAhorro cuenta = new CajaDeAhorro("CA-002", "José", 1000);
        boolean resultado = cuenta.extraer(600);
        assertTrue(resultado);
        assertEquals(400, cuenta.getSaldo());
    }

    @Test
    void extraerFallaPorSaldoInsuficiente() {
        CajaDeAhorro cuenta = new CajaDeAhorro("CA-003", "José", 300);
        boolean resultado = cuenta.extraer(400);
        assertFalse(resultado);
        assertEquals(300, cuenta.getSaldo());
    }

    @Test
    void transferir() {
        CajaDeAhorro origen = new CajaDeAhorro("CA-004", "José", 1000);
        CajaDeAhorro destino = new CajaDeAhorro("CA-005", "Ana", 500);

        boolean exito = origen.transferir(destino, 400);

        assertTrue(exito);
        assertEquals(600, origen.getSaldo());
        assertEquals(900, destino.getSaldo());
    }


    @Test
    void getNumeroCuenta() {
        Cuenta cuenta = new CajaDeAhorro("CA-006", "José", 0);
        assertEquals("CA-006", cuenta.getNumeroCuenta());
    }

    @Test
    void getTitular() {
        Cuenta cuenta = new CajaDeAhorro("CA-007", "José", 0);
        assertEquals("José", cuenta.getTitular());
    }

    @Test
    void getSaldo() {
        Cuenta cuenta = new CajaDeAhorro("CA-008", "José", 1234.56);
        assertEquals(1234.56, cuenta.getSaldo());
    }

    @Test
    void cuentaCorrientePermiteDescubierto() {
        CuentaCorriente cuenta = new CuentaCorriente("CC-001", "José", 100, 500);
        boolean exito = cuenta.extraer(400);
        assertTrue(exito);
        assertEquals(-300, cuenta.getSaldo());
    }

}