package Cuentas;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CajaDeAhorroTest {

    private CajaDeAhorro cuenta;

    // Antes de cada test, creo una caja de ahorro con un saldo inicial de $5000
    @BeforeEach
    void setUp() {
        cuenta = new CajaDeAhorro("CA123", 5000.0);
    }

    // Verifico que la cuenta se crea con el saldo correcto
    @Test
    void testCreacionCajaDeAhorro() {
        assertEquals("CA123", cuenta.getNumeroCuenta());
        assertEquals(5000.0, cuenta.getSaldo(), 0.01);
    }

    // Pruebo una extracción válida (el monto es menor que el saldo)
    @Test
    void testExtraccionValida() {
        boolean resultado = cuenta.extraer(2000.0);
        assertTrue(resultado); // Debería poder extraer
        assertEquals(3000.0, cuenta.getSaldo(), 0.01); // El saldo baja correctamente
    }

    // Intento extraer un monto igual al saldo
    @Test
    void testExtraccionExacta() {
        boolean resultado = cuenta.extraer(5000.0);
        assertTrue(resultado);
        assertEquals(0.0, cuenta.getSaldo(), 0.01); // Saldo queda en cero
    }

    // Intento extraer más de lo que hay, no debería permitirlo
    @Test
    void testExtraccionExcesiva() {
        boolean resultado = cuenta.extraer(6000.0);
        assertFalse(resultado); // No se puede extraer más de lo que hay
        assertEquals(5000.0, cuenta.getSaldo(), 0.01); // Saldo se mantiene igual
    }
}
