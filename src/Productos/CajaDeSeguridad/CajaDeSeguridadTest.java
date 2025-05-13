package Productos.CajaDeSeguridad;

import Clientes.Cliente;
import Clientes.Persona;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class CajaDeSeguridadTest {
    CajaDeSeguridad caja;

    @BeforeEach
    void setUp() {
        Cliente cliente = new Persona("Javier", "Calle 123", List.of("[\"123456789\"]"), 12345678);
        this.caja = new CajaDeSeguridad(cliente, "1234");
    }

    @Test
    void probarDepositoPositivo() {
        this.caja.depositarDinero(100.0);
        assertEquals(100.0, caja.getMontoActual());
    }
    // esto prueba para que si deposita 100 lo sume al monto actual

    @Test
    void probarDepositoNegativo() {
        this.caja.depositarDinero(-50.0);
        assertEquals(0.0, caja.getMontoActual());
    }
    //este es para que ingrese un numero negativo no sume nada

    @Test
    void probarRetiroConSaldo() {
        this.caja.depositarDinero(200.0);
        this.caja.retirarDinero(50.0);
        assertEquals(150.0, caja.getMontoActual());
    }
    // este para restarle al saldo lo que retire

    @Test
    void probarRetiroSinSaldo() {
        this.caja.retirarDinero(100.0);
        assertEquals(0.0, caja.getMontoActual());
    }
    //este es para que si intenta retirar algo que no tiene , no lo haga

    @Test
    void probarRetiroNegativo() {
        this.caja.depositarDinero(100.0);
        this.caja.retirarDinero(-20.0);
        assertEquals(100.0, caja.getMontoActual());
    }
    //esto es para que si intenta retirar en negativo , no le afecte al saldo que tiene depositado

}
