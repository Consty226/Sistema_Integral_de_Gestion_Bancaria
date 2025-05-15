package Test;

import Clientes.*;
import Productos.Cuentas.CuentaCorriente;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.*;

class CuentaCorrienteTest {
    private CuentaCorriente cuentaCorriente;
    private Cliente cliente;

    @Test
    void testConstructor() {
        cliente = new Persona("Jose","Calle8", Arrays.asList("1234561244"),4635197);
        cuentaCorriente = new CuentaCorriente(cliente,"1234",1000.0,10);
        assertNotNull(cuentaCorriente);
        assertEquals(cliente,cuentaCorriente.getTitular());
        assertEquals("1234",cuentaCorriente.getNumeroCuenta());
        assertEquals(1000.0,cuentaCorriente.getSaldo());
        assertEquals(10,cuentaCorriente.getLimiteDescubierto());
    }

    @Test
    void testExtraer() {
        cliente = new Persona("Jose","Calle8", Arrays.asList("1234561244"),4635197);
        cuentaCorriente = new CuentaCorriente(cliente,"1234",1000.0,10);
        assertTrue(cuentaCorriente.extraer(500.0));
        assertEquals(500.0, cuentaCorriente.getSaldo());
    }

    @Test
    void getLimiteDescubierto() {
        cliente = new Persona("Jose","Calle8", Arrays.asList("1234561244"),4635197);
        cuentaCorriente = new CuentaCorriente(cliente,"1234",1000.0,10);
        assertEquals(10,cuentaCorriente.getLimiteDescubierto());
    }

    @Test
    void setLimiteDescubierto() {
        cliente = new Persona("Jose","Calle8", Arrays.asList("1234561244"),4635197);
        cuentaCorriente = new CuentaCorriente(cliente,"1234",1000.0,10);
        cuentaCorriente.setLimiteDescubierto(20);
        assertEquals(20,cuentaCorriente.getLimiteDescubierto());
    }
}