package Test;

import Clientes.*;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;

class EmpresaTest {

    @Test
    void getCuit() {
        Empresa empresa = new Empresa("El Casal","Calle 8",Arrays.asList("1234561244"),46351897);

        assertEquals(46351897,empresa.getCuit());
    }

    @Test
    void setCuit() {
        Empresa empresa = new Empresa("El Casal","Calle 8",Arrays.asList("1234561244"),46351897);

        empresa.setCuit(22351782);
        assertEquals(22351782,empresa.getCuit());
    }
}