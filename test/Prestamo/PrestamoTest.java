package Prestamo;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PrestamoTest {

    @Test
    void realizarPagoReduceSaldo() {
        Prestamo prestamo = new Prestamo(10000, 0.15);
        prestamo.realizarPago(3000);
        assertEquals(7000, prestamo.getSaldoRestante());
    }

    @Test
    void realizarPagoMayorAlSaldo() {
        Prestamo prestamo = new Prestamo(5000, 0.1);
        prestamo.realizarPago(6000);  // excede el saldo
        assertEquals(0, prestamo.getSaldoRestante());
    }

    @Test
    void realizarPagoInvalido() {
        Prestamo prestamo = new Prestamo(4000, 0.1);
        prestamo.realizarPago(-1000);  // monto negativo, no debe afectar
        assertEquals(4000, prestamo.getSaldoRestante());
    }

    @Test
    void getMontoOtorgado() {
        Prestamo prestamo = new Prestamo(8000, 0.2);
        assertEquals(8000, prestamo.getMontoOtorgado());
    }

    @Test
    void getTasaInteres() {
        Prestamo prestamo = new Prestamo(5000, 0.25);
        assertEquals(0.25, prestamo.getTasaInteres());
    }

    @Test
    void toStringDevuelveInformacionCorrecta() {
        Prestamo prestamo = new Prestamo(10000, 0.1);
        String resultado = prestamo.toString();
        assertTrue(resultado.contains("montoOtorgado=10000"));
        assertTrue(resultado.contains("saldoRestante=10000"));
        assertTrue(resultado.contains("tasaInteres=0.1"));
    }
}
