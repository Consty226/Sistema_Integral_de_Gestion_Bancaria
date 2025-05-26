package Clientes;

import CajaDeSeguridad.CajaDeSeguridad;
import java.util.List;

public class Empresa extends Cliente {
    private String cuit;

    public Empresa(String nombre, String direccion, List<String> telefonos, String cuit, String claveCaja) {
        setNombre(nombre);
        setDireccion(direccion);

        for (String telefono : telefonos) {
            agregarTelefono(telefono);
        }

        this.cuit = cuit;

        CajaDeSeguridad caja = new CajaDeSeguridad(this, claveCaja);
        setCajasDeSeguridad(caja);
    }

    public String getCuit() {
        return cuit;
    }

    public void setCuit(String cuit) {
        this.cuit = cuit;
    }

    @Override
    public String tipoCliente() {
        return "Empresa";
    }
}
