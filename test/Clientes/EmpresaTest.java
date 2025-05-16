package Clientes;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class EmpresaTest {

    private Empresa empresa;

    // Antes de cada test, creo una empresa con datos ficticios
    @BeforeEach
    void setUp() {
        empresa = new Empresa(
                "Tequeños S.A.",
                "Parque Industrial 404",
                Arrays.asList("123456", "7891011"),
                "30-12345678-9",
                "claveEmpresa"
        );
    }

    // Verifico los datos básicos de la empresa
    @Test
    void testDatosEmpresa() {
        assertEquals("Tequeños S.A.", empresa.getNombre());
        assertEquals("Parque Industrial 404", empresa.getDireccion());
        assertEquals("30-12345678-9", empresa.getCuit());
        assertEquals(2, empresa.getTelefonos().size());
    }

    // Verifico que se pueda modificar el CUIT
    @Test
    void testModificarCuit() {
        empresa.setCuit("30-87654321-0");
        assertEquals("30-87654321-0", empresa.getCuit());
    }
}
