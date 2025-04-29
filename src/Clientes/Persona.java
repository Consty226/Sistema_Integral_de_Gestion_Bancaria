package Clientes;

import java.util.List;

public class Persona extends Cliente{
    private int dni;

    public Persona(String nombre, String direccion, List<String> telefonos, int dni) {
        super(nombre, direccion, telefonos);
        this.dni = dni;
    }

    public int getDNI() {
        return dni;
    }

    public void setDNI(int dni) {
        this.dni = dni;
    }
}
