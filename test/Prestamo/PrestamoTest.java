package Prestamo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class PrestamoTest {

    private Prestamo prestamo;

    // Este metodo se ejecuta antes de cada test para crear un nuevo préstamo
    @BeforeEach
    void setUp() {
        // Creo un préstamo de $10.000 con una tasa de interés del 5%
        prestamo = new Prestamo(10000.0, 0.05);
    }

    // Verifico que el préstamo se cree correctamente con los valores esperados
    @Test
    void testCreacionPrestamo() {
        assertEquals(10000.0, prestamo.getMontoOtorgado()); // El monto otorgado debería ser 10000
        assertEquals(10000.0, prestamo.getSaldoRestante()); // El saldo restante al principio es igual al monto total
        assertEquals(0.05, prestamo.getTasaInteres());      // La tasa de interés debería ser 0.05
    }

    // Simulo un pago parcial y reviso que el saldo se actualice correctamente
    @Test
    void testPagoParcial() {
        prestamo.realizarPago(3000.0); // Pago $3000
        assertEquals(7000.0, prestamo.getSaldoRestante(), 0.01); // El saldo debería bajar a $7000
    }

    // Simulo un pago total del préstamo
    @Test
    void testPagoTotal() {
        prestamo.realizarPago(10000.0); //Pago todo el monto del prestamo
        assertEquals(0.0, prestamo.getSaldoRestante(), 0.01); // El saldo debería quedar en 0
    }

    // Intento pagar más de lo que debo y verifico que no pase de 0
    @Test
    void testPagoSuperiorAlSaldo() {
        prestamo.realizarPago(15000.0); // Pago más de lo que debo
        assertEquals(0.0, prestamo.getSaldoRestante(), 0.01); // El saldo no debería volverse negativo
    }

    // Pruebo qué pasa si intento pagar 0
    @Test
    void testPagoInvalidoMontoCero() {
        prestamo.realizarPago(0.0); // Pago 0
        assertEquals(10000.0, prestamo.getSaldoRestante(), 0.01); // No debería cambiar el saldo
    }

    // Pruebo con un monto negativo, que tampoco debería afectar el saldo
    @Test
    void testPagoInvalidoMontoNegativo() {
        prestamo.realizarPago(-500.0); // Pago negativo (inválido)
        assertEquals(10000.0, prestamo.getSaldoRestante(), 0.01); // El saldo debería mantenerse igual
    }
}
