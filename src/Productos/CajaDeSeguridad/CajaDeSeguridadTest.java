package Productos.CajaDeSeguridad;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
// este import te asegura que de el resultado correcto

public class CajaDeSeguridadTest {

    @Test
    void probarDepositoPositivo() {
        CajaDeSeguridad caja = new CajaDeSeguridad("1234");
        caja.depositarDinero(100.0);
        assertEquals(100.0, caja.getMontoActual());
    }
    // esto prueba para que si deposita 100 lo sume al monto actual

    @Test
    void probarDepositoNegativo() {
        CajaDeSeguridad caja = new CajaDeSeguridad("1234");
        caja.depositarDinero(-50.0);
        assertEquals(0.0, caja.getMontoActual());
    }
    //este es para que ingrese un numero negativo no sume nada

    @Test
    void probarRetiroConSaldo() {
        CajaDeSeguridad caja = new CajaDeSeguridad("1234");
        caja.depositarDinero(200.0);
        caja.retirarDinero(50.0);
        assertEquals(150.0, caja.getMontoActual());
    }
    // este para restarle al saldo lo que retire

    @Test
    void probarRetiroSinSaldo() {
        CajaDeSeguridad caja = new CajaDeSeguridad("1234");
        caja.retirarDinero(100.0);
        assertEquals(0.0, caja.getMontoActual());
    }
    //este es para que si intenta retirar algo que no tiene , no lo haga

    @Test
    void probarRetiroNegativo() {
        CajaDeSeguridad caja = new CajaDeSeguridad("1234");
        caja.depositarDinero(100.0);
        caja.retirarDinero(-20.0);
        assertEquals(100.0, caja.getMontoActual());
    }
    //esto es para que si intenta retirar en negativo , no le afecte al saldo que tiene depositado

}
