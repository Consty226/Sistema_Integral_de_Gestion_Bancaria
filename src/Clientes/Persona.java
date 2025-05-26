package Clientes;

import CajaDeSeguridad.CajaDeSeguridad;
import java.util.List;

public class Persona extends Cliente {
    private String dni;

    public Persona(String nombre, String direccion, List<String> telefonos, String dni, String claveCaja) {
        setNombre(nombre);
        setDireccion(direccion);

        for (String telefono : telefonos) {
            agregarTelefono(telefono);
        }

        this.dni = dni;

        // Composición: la caja se crea dentro del cliente
        CajaDeSeguridad caja = new CajaDeSeguridad(this, claveCaja);
        setCajasDeSeguridad(caja);
    }

    public String getDNI() {
        return dni;
    }

    public void setDNI(String dni) {
        this.dni = dni;
    }

    @Override
    public String tipoCliente() {
        return "Persona";
    }
}
