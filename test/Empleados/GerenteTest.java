package Empleados;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class GerenteTest {

    // Este test verifica que los datos del gerente se asignen correctamente
    @Test
    void testDatosGerente() {
        // Creo un nuevo gerente con nombre "Lucía" y legajo 456
        Gerente gerente = new Gerente("Lucía", 456);

        // Verifico que el legajo sea el que pasé en el constructor
        assertEquals(456, gerente.getLegajo());

        // Verifico que el nombre también sea el correcto
        assertEquals("Lucía", gerente.getNombre());

        // El rol del gerente debería ser siempre "Gerente"
        assertEquals("Gerente", gerente.getRol());
    }
}
