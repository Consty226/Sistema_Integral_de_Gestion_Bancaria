package Test;

import Clientes.*;
import Productos.Cuentas.CajaDeAhorro;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.*;

class CajaDeAhorroTest {
    private CajaDeAhorro cajaDeAhorro;
    private Cliente cliente;

    @Test
    void testConstructor() {
        cliente = new Persona("Jose","Calle8", Arrays.asList("1234561244"),4635197);
        cajaDeAhorro = new CajaDeAhorro(cliente, "001", 1000.0);
        assertNotNull(cajaDeAhorro);
        assertEquals("001", cajaDeAhorro.getNumeroCuenta());
        assertEquals(1000.0, cajaDeAhorro.getSaldo());
        assertEquals(cliente, cajaDeAhorro.getTitular());
    }

    @Test
    void testExtraer() {
        cliente = new Persona("Jose","Calle8", Arrays.asList("1234561244"),4635197);
        cajaDeAhorro = new CajaDeAhorro(cliente, "001", 1000.0);
        assertTrue(cajaDeAhorro.extraer(500.0));
        assertEquals(500.0, cajaDeAhorro.getSaldo());
    }

    @Test
    void testExtraerFondosInsuficientes() {
        cliente = new Persona("Jose","Calle8", Arrays.asList("1234561244"),4635197);
        cajaDeAhorro = new CajaDeAhorro(cliente, "001", 1000.0);
        assertFalse(cajaDeAhorro.extraer(1500.0));
        assertEquals(1000.0, cajaDeAhorro.getSaldo());
    }
}
