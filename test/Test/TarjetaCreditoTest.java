package Test;

import Clientes.*;
import Productos.Tarjetas.TarjetaCredito;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class TarjetaCreditoTest {

    @Test
    void testConstructor() {
        Cliente cliente = new Persona("Jose","Calle8", Arrays.asList("1234561244"),4635197);
        String numeroTarjeta = TarjetaCredito.generarNumeroTarjeta();
        TarjetaCredito tarjetaCredito = new TarjetaCredito(cliente,numeroTarjeta,1000.0);

        assertNotNull(tarjetaCredito);
        assertEquals(numeroTarjeta, tarjetaCredito.getNumeroTarjeta());
        assertEquals(1000.0, tarjetaCredito.getLimiteCredito());
        assertEquals(1000.0, tarjetaCredito.getSaldoDisponible());
    }

    @Test
    void testRegistrarConsumo() {
        Cliente cliente = new Persona("Jose","Calle8", Arrays.asList("1234561244"),4635197);
        String numeroTarjeta = TarjetaCredito.generarNumeroTarjeta();
        TarjetaCredito tarjetaCredito = new TarjetaCredito(cliente, numeroTarjeta, 1000.0);

        assertTrue(tarjetaCredito.registrarConsumo(300.0));
        assertEquals(700.0, tarjetaCredito.getSaldoDisponible());

        assertFalse(tarjetaCredito.registrarConsumo(800.0)); // Insufficient funds
        assertEquals(700.0, tarjetaCredito.getSaldoDisponible());
    }

    @Test
    void testRegistrarPago() {
        Cliente cliente = new Persona("Jose","Calle8", Arrays.asList("1234561244"),4635197);
        String numeroTarjeta = TarjetaCredito.generarNumeroTarjeta();
        TarjetaCredito tarjetaCredito = new TarjetaCredito(cliente, numeroTarjeta, 1000.0);

        tarjetaCredito.registrarConsumo(300.0);
        assertEquals(700.0, tarjetaCredito.getSaldoDisponible());

        assertTrue(tarjetaCredito.registrarPago(200.0));
        assertEquals(900.0, tarjetaCredito.getSaldoDisponible());
    }

    @Test
    void testToString() {
        Cliente cliente = new Persona("Jose","Calle8", Arrays.asList("1234561244"),4635197);
        String numeroTarjeta = TarjetaCredito.generarNumeroTarjeta();
        TarjetaCredito tarjetaCredito = new TarjetaCredito(cliente, numeroTarjeta, 1000.0);

        String expectedString = "Productos.Tarjetas.TarjetaCredito {" +
                "numero='" + numeroTarjeta + '\'' +
                ", limite=1000,00" +
                ", saldoDisponible=1000,00" +
                '}';
        assertEquals(expectedString, tarjetaCredito.toString());
    }

    @Test
    void testInvalidConstructor() {
        Cliente cliente = new Persona("Jose","Calle8", Arrays.asList("1234561244"),4635197);
        String numeroTarjeta = TarjetaCredito.generarNumeroTarjeta();

        // Test with null number
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new TarjetaCredito(cliente, null, 1000.0);
        });
        assertEquals("El número de tarjeta no puede ser nulo o vacío.", exception.getMessage());

        // Test with empty number
        exception = assertThrows(IllegalArgumentException.class, () -> {
            new TarjetaCredito(cliente, "", 1000.0);
        });
        assertEquals("El número de tarjeta no puede ser nulo o vacío.", exception.getMessage());

        // Test with negative limit
        exception = assertThrows(IllegalArgumentException.class, () -> {
            new TarjetaCredito(cliente, numeroTarjeta, -100.0);
        });
        assertEquals("El límite de crédito no puede ser negativo.", exception.getMessage());
    }
}
