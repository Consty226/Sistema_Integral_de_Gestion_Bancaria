package Test;

import Clientes.*;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;

class PersonaTest {
    @Test
    void getDNI() {
        Persona persona = new Persona("Jose", "Calle8", Arrays.asList("1234561244"), 46351897);

        assertEquals(46351897,persona.getDNI());
    }

    @Test
    void setDNI() {
        Persona persona = new Persona("Jose", "Calle8", Arrays.asList("1234561244"), 46351987);
        persona.setDNI(22356987);
        assertEquals(22356987,persona.getDNI());
    }
}