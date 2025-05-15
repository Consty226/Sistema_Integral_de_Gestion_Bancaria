package Test;

import Clientes.*;
import Productos.Prestamo.Prestamo;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class PrestamoTest {
    private Prestamo prestamo;
    private Cliente cliente;

    @Test
    void testConstructor() {
        cliente = new Persona("Jose","Calle8", Arrays.asList("1234561244"),4635197);
        prestamo = new Prestamo(cliente, 1000.0, 5.0);
        assertNotNull(prestamo);
        assertEquals(1050.0, prestamo.getMontoOtorgado());
        assertEquals(5.0, prestamo.getTasaDeInteres());
        assertEquals(1050.0, prestamo.getSaldoRestante());
    }

    @Test
    void recibirPago() {
        cliente = new Persona("Jose","Calle8", Arrays.asList("1234561244"),4635197);
        prestamo = new Prestamo(cliente, 1000.0, 5.0);
        prestamo.recibirPago(300.0);
        assertEquals(750.0, prestamo.getSaldoRestante());
    }

    @Test
    void testToString() {
        cliente = new Persona("Jose","Calle8", Arrays.asList("1234561244"),4635197);
        prestamo = new Prestamo(cliente, 1000.0, 5.0);
        String expectedString = "Prestamo {montoOtorgado=1050,00, tasaDeInteres=5.0, saldoRestante=1050,00}";
        assertEquals(expectedString, prestamo.toString());
    }
}