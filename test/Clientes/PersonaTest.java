package Clientes;

import CajaDeSeguridad.CajaDeSeguridad;
import Cuentas.CajaDeAhorro;
import Cuentas.Cuenta;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class PersonaTest {

    private Persona persona;

    // Antes de cada test creo una persona con datos de prueba
    @BeforeEach
    void setUp() {
        List<String> telefonos = Arrays.asList("123", "456");
        persona = new Persona("José Pérez", "Av. Siempre Viva 742", telefonos, "12345678", "clave123");
    }

    // Verifico que se almacenen correctamente los datos personales
    @Test
    void testDatosBasicos() {
        assertEquals("José Pérez", persona.getNombre());
        assertEquals("Av. Siempre Viva 742", persona.getDireccion());
        assertEquals("12345678", persona.getDNI());
    }

    // Verifico que los teléfonos no se repitan y se puedan eliminar
    @Test
    void testTelefonos() {
        Set<String> telefonos = persona.getTelefonos();
        assertEquals(2, telefonos.size());

        persona.agregarTelefono("789");
        assertTrue(persona.getTelefonos().contains("789"));

        persona.agregarTelefono("123"); // número repetido
        assertEquals(3, persona.getTelefonos().size()); // no se suma el duplicado

        persona.desvincularTelefono("456");
        assertFalse(persona.getTelefonos().contains("456"));
    }

    // Verifico que pueda vincular y eliminar cuentas
    @Test
    void testCuentasVinculadas() {
        Cuenta cuenta = new CajaDeAhorro("CA123", 1000.0);
        persona.setCuentas(cuenta);

        assertTrue(persona.esCuentaDelCliente(cuenta));
        assertEquals(1, persona.getCuentas().size());

        persona.eliminarCuenta(cuenta);
        assertFalse(persona.getCuentas().contains(cuenta));
    }

    // Verifico que la caja de seguridad esté vinculada correctamente
    @Test
    void testCajaDeSeguridad() {
        CajaDeSeguridad caja = persona.getCajaDeSeguridad();
        assertNotNull(caja);

        caja.setContenido("Pasaporte");
        assertTrue(caja.getContenido().contains("Pasaporte"));
    }
}
