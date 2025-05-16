package Clientes;

import java.util.List;

public class Persona extends Cliente{
    private String dni;

    public Persona(String nombre, String direccion, List<String> telefonos, String dni, String claveCaja) {
        super(nombre, direccion, telefonos, claveCaja);
        this.dni = dni;
    }
// Se modificó los "int" de dni por String
    public String getDNI() {
        return dni;
    }

    public void setDNI(String dni) {
        this.dni = dni;
    }
}
