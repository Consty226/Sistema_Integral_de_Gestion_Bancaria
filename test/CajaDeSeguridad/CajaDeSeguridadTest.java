package CajaDeSeguridad;

import Clientes.Persona;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

class CajaDeSeguridadTest {

    private Persona persona;
    private CajaDeSeguridad caja;

    // Antes de cada test creo una persona y su caja de seguridad
    @BeforeEach
    void setUp() {
        persona = new Persona(
                "José Pérez",
                "Av. Siempre Viva 742",
                Collections.singletonList("123456789"),
                "12345678",
                "clave123"
        );

        caja = new CajaDeSeguridad(persona, "clave123");
    }

    // Verifico que la caja se crea con un número aleatorio no vacío
    @Test
    void testNumeroCajaGenerado() {
        assertNotNull(caja.getNumero());
        assertEquals(16, caja.getNumero().length()); // debe tener 16 dígitos
    }

    // Verifico que la clave de acceso se guarda correctamente
    @Test
    void testClaveDeAcceso() {
        assertEquals("clave123", caja.getClaveDeAcceso());

        caja.setClaveDeAcceso("nuevaClave");
        assertEquals("nuevaClave", caja.getClaveDeAcceso());
    }

    // Verifico que al depositar dinero el monto aumente
    @Test
    void testDepositarDinero() {
        caja.depositarDinero(1000.0);
        assertEquals(1000.0, caja.getMontoActual(), 0.01);
    }

    // Verifico que al retirar dinero válido, el monto disminuya
    @Test
    void testRetirarDineroValido() {
        caja.depositarDinero(1000.0);
        caja.retirarDinero(400.0);
        assertEquals(600.0, caja.getMontoActual(), 0.01);
    }

    // Verifico que no se permita retirar más del monto disponible
    @Test
    void testRetirarDineroExcesivo() {
        caja.depositarDinero(500.0);
        caja.retirarDinero(1000.0); // no debería descontar
        assertEquals(500.0, caja.getMontoActual(), 0.01);
    }

    // Verifico que no se permita depositar valores negativos
    @Test
    void testDepositoNegativo() {
        caja.depositarDinero(-100.0); // no cambia
        assertEquals(0.0, caja.getMontoActual(), 0.01);
    }

    // Verifico que se pueda agregar contenido correctamente
    @Test
    void testAgregarContenido() {
        caja.setContenido("Pasaporte");
        caja.setContenido("Título de propiedad");

        assertTrue(caja.getContenido().contains("Pasaporte"));
        assertEquals(2, caja.getContenido().size());
    }

    // Verifico que se pueda remover contenido
    @Test
    void testRemoverContenido() {
        caja.setContenido("Pasaporte");
        caja.removerContenido("Pasaporte");

        assertFalse(caja.getContenido().contains("Pasaporte"));
    }
}
