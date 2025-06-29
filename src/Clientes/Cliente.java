package Clientes;

import CajaDeSeguridad.CajaDeSeguridad;
import Cuentas.Cuenta;

import java.util.*;

public abstract class Cliente {
    private String nombre;
    private String direccion;
    private Set<String> telefonos = new HashSet<>();
    private List<Cuenta> cuentas = new ArrayList<>();
    private CajaDeSeguridad cajaDeSeguridad;

    // Método abstracto obligatorio
    public abstract String tipoCliente();

    // ---- Caja ----
    public CajaDeSeguridad getCajaDeSeguridad() {
        return this.cajaDeSeguridad;
    }

    public void setCajasDeSeguridad(CajaDeSeguridad caja) {
        this.cajaDeSeguridad = caja;
    }

    // ---- Datos personales ----

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public void agregarTelefono(String telefono) {
        if (!this.telefonos.add(telefono)) {
            System.out.println("El número ya está registrado.");
        }
    }

    public void desvincularTelefono(String telefono) {
        this.telefonos.remove(telefono);
    }

    public Set<String> getTelefonos() {
        return this.telefonos;
    }

    public List<Cuenta> getCuentas() {
        return this.cuentas;
    }

    public void setCuentas(Cuenta cuenta) {
        if (this.esCuentaDelCliente(cuenta)) {
            System.out.println("Esta cuenta ya se encuentra vinculada al cliente.");
            return;
        }
        this.cuentas.add(cuenta);
    }

    public boolean esCuentaDelCliente(Cuenta cuenta) {
        return this.cuentas.contains(cuenta);
    }

    public void eliminarCuenta(Cuenta cuenta) {
        this.cuentas.remove(cuenta);
    }

    @Override
    public String toString() {
        return "Cliente {" +
                "Nombre='" + this.getNombre() + '\'' +
                ", Tipo=" + this.getClass().getSimpleName() +
                '}';
    }
}
