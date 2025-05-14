package Cuentas;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CuentaCorrienteTest {

    @Test
    void extraccionDentroDelLimiteDeDescubierto() {
        CuentaCorriente cuenta = new CuentaCorriente("CC-001", "José", 100, 500);
        assertTrue(cuenta.extraer(400)); // usa parte del descubierto
        assertEquals(-300, cuenta.getSaldo());
    }

    @Test
    void extraccionExcedeLimiteDeDescubierto() {
        CuentaCorriente cuenta = new CuentaCorriente("CC-002", "José", 100, 500);
        assertFalse(cuenta.extraer(700)); // no alcanza ni con el descubierto
        assertEquals(100, cuenta.getSaldo());
    }
}
