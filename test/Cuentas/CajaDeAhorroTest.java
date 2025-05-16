package Cuentas;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CajaDeAhorroTest {

    @Test
    void extraccionExitosa() {
        CajaDeAhorro cuenta = new CajaDeAhorro("CA-001", "José", 1000);
        assertTrue(cuenta.extraer(500));
        assertEquals(500, cuenta.getSaldo());
    }

    @Test
    void extraccionFallidaPorSaldoInsuficiente() {
        CajaDeAhorro cuenta = new CajaDeAhorro("CA-002", "José", 300);
        assertFalse(cuenta.extraer(500));
        assertEquals(300, cuenta.getSaldo());
    }
}
