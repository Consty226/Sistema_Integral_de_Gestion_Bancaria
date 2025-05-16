package Empleados;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CajeroTest {

    // Este test verifica que los datos del cajero se guarden correctamente
    @Test
    void testDatosCajero() {
        // Creo un nuevo cajero con nombre "Carlos" y legajo 123
        Cajero cajero = new Cajero("Carlos", 123);

        // Verifico que el legajo se haya guardado bien
        assertEquals(123, cajero.getLegajo());

        // Verifico que el nombre sea el que pasé al constructor
        assertEquals("Carlos", cajero.getNombre());

        // El rol del cajero debe ser "Cajero" por defecto
        assertEquals("Cajero", cajero.getRol());
    }
}
