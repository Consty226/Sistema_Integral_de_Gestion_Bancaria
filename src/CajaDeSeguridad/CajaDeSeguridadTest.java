<<<<<<<< HEAD:test/Test/CajaDeSeguridadTest.java
package Test;

import Clientes.*;
import Productos.CajaDeSeguridad.CajaDeSeguridad;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class CajaDeSeguridadTest {
    CajaDeSeguridad caja;

    @Test
    void probarDepositoPositivo() {
        Cliente cliente = new Persona("Javier", "Calle 123", List.of("[\"123456789\"]"), 12345678);
        this.caja = new CajaDeSeguridad(cliente, "1234");
        this.caja.depositarDinero(100.0);
========
package CajaDeSeguridad;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
// este import te asegura que de el resultado correcto

public class CajaDeSeguridadTest {

    @Test
    void probarDepositoPositivo() {
        CajaDeSeguridad caja = new CajaDeSeguridad("1234");
        caja.depositarDinero(100.0);
>>>>>>>> parent of 9d37b93 (Banco (#10)):src/CajaDeSeguridad/CajaDeSeguridadTest.java
        assertEquals(100.0, caja.getMontoActual());
    }
    // esto prueba para que si deposita 100 lo sume al monto actual

    @Test
    void probarDepositoNegativo() {
<<<<<<<< HEAD:test/Test/CajaDeSeguridadTest.java
        Cliente cliente = new Persona("Javier", "Calle 123", List.of("[\"123456789\"]"), 12345678);
        this.caja = new CajaDeSeguridad(cliente, "1234");
        this.caja.depositarDinero(-50.0);
========
        CajaDeSeguridad caja = new CajaDeSeguridad("1234");
        caja.depositarDinero(-50.0);
>>>>>>>> parent of 9d37b93 (Banco (#10)):src/CajaDeSeguridad/CajaDeSeguridadTest.java
        assertEquals(0.0, caja.getMontoActual());
    }
    //este es para que ingrese un numero negativo no sume nada

    @Test
    void probarRetiroConSaldo() {
<<<<<<<< HEAD:test/Test/CajaDeSeguridadTest.java
        Cliente cliente = new Persona("Javier", "Calle 123", List.of("[\"123456789\"]"), 12345678);
        this.caja = new CajaDeSeguridad(cliente, "1234");
        this.caja.depositarDinero(200.0);
        this.caja.retirarDinero(50.0);
========
        CajaDeSeguridad caja = new CajaDeSeguridad("1234");
        caja.depositarDinero(200.0);
        caja.retirarDinero(50.0);
>>>>>>>> parent of 9d37b93 (Banco (#10)):src/CajaDeSeguridad/CajaDeSeguridadTest.java
        assertEquals(150.0, caja.getMontoActual());
    }
    // este para restarle al saldo lo que retire

    @Test
    void probarRetiroSinSaldo() {
<<<<<<<< HEAD:test/Test/CajaDeSeguridadTest.java
        Cliente cliente = new Persona("Javier", "Calle 123", List.of("[\"123456789\"]"), 12345678);
        this.caja = new CajaDeSeguridad(cliente, "1234");
        this.caja.retirarDinero(100.0);
========
        CajaDeSeguridad caja = new CajaDeSeguridad("1234");
        caja.retirarDinero(100.0);
>>>>>>>> parent of 9d37b93 (Banco (#10)):src/CajaDeSeguridad/CajaDeSeguridadTest.java
        assertEquals(0.0, caja.getMontoActual());
    }
    //este es para que si intenta retirar algo que no tiene , no lo haga

    @Test
    void probarRetiroNegativo() {
<<<<<<<< HEAD:test/Test/CajaDeSeguridadTest.java
        Cliente cliente = new Persona("Javier", "Calle 123", List.of("[\"123456789\"]"), 12345678);
        this.caja = new CajaDeSeguridad(cliente, "1234");
        this.caja.depositarDinero(100.0);
        this.caja.retirarDinero(-20.0);
========
        CajaDeSeguridad caja = new CajaDeSeguridad("1234");
        caja.depositarDinero(100.0);
        caja.retirarDinero(-20.0);
>>>>>>>> parent of 9d37b93 (Banco (#10)):src/CajaDeSeguridad/CajaDeSeguridadTest.java
        assertEquals(100.0, caja.getMontoActual());
    }
    //esto es para que si intenta retirar en negativo , no le afecte al saldo que tiene depositado

    @Test

}
