package Cuentas;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CuentaCorrienteTest {

    private CuentaCorriente cuenta;

    // Antes de cada test creo una cuenta corriente con $2000 y un descubierto de $1000
    @BeforeEach
    void setUp() {
        cuenta = new CuentaCorriente("CC123", 2000.0, 1000);
    }

    // Verifico que la cuenta se crea correctamente con el límite de descubierto
    @Test
    void testCreacionCuentaCorriente() {
        assertEquals("CC123", cuenta.getNumeroCuenta());
        assertEquals(2000.0, cuenta.getSaldo(), 0.01);
        assertEquals(1000, cuenta.getLimiteDescubierto());
    }

    // Pruebo extraer un monto menor al saldo
    @Test
    void testExtraccionDentroDelSaldo() {
        boolean resultado = cuenta.extraer(1500.0);
        assertTrue(resultado);
        assertEquals(500.0, cuenta.getSaldo(), 0.01);
    }

    // Pruebo extraer usando parte del descubierto (saldo + descubierto)
    @Test
    void testExtraccionConDescubierto() {
        boolean resultado = cuenta.extraer(2500.0); // $2000 + $500 del descubierto
        assertTrue(resultado);
        assertEquals(-500.0, cuenta.getSaldo(), 0.01); // El saldo queda en negativo
    }

    // Intento extraer más de lo que permite el saldo + descubierto
    @Test
    void testExtraccionSuperaLimite() {
        boolean resultado = cuenta.extraer(3100.0); // $2000 + $1000 < $3100
        assertFalse(resultado);
        assertEquals(2000.0, cuenta.getSaldo(), 0.01); // No cambia el saldo
    }

    // Cambio el límite de descubierto y verifico que se actualice
    @Test
    void testModificarLimiteDescubierto() {
        cuenta.setLimiteDescubierto(2000);
        assertEquals(2000, cuenta.getLimiteDescubierto());
    }
}
