package Clientes;

import CajaDeSeguridad.CajaDeSeguridad;
import Cuentas.Cuenta;
import Prestamo.Prestamo;
import Tarjetas.TarjetaCredito;

import java.util.*;

public abstract class Cliente {
    protected String nombre;
    protected String direccion;
    protected Set<String> telefonos;
    protected List<Cuenta> cuentas;
    protected Set<TarjetaCredito> tarjetas;
    protected List<Prestamo> prestamos;
    protected List<CajaDeSeguridad> cajasDeSeguridad;

    public Cliente(String nombre, String direccion, List<String> telefonos) {
        this.nombre = nombre;
        this.direccion = direccion;
        this.telefonos = new HashSet<>(telefonos); // evita duplicados si vienen desde una lista

        this.cuentas = new ArrayList<>();
        this.tarjetas = new HashSet<>();
        this.prestamos = new ArrayList<>();
        this.cajasDeSeguridad = new ArrayList<>();
    }

    // ---- Cuentas ----

    public void vincularCuenta(Cuenta cuenta) {
        this.cuentas.add(cuenta);
    }

    public void desvincularCuenta(Cuenta cuenta) {
        if (this.cuentas.size() > 1) {
            this.cuentas.remove(cuenta);
        } else {
            System.out.println("El cliente debe poseer al menos una Cuenta.");
        }
    }

    public List<Cuenta> getCuentas() {
        return this.cuentas;
    }

    // ---- Tarjetas ----

    public void vincularTarjeta(TarjetaCredito tarjeta) {
        if (!this.tarjetas.add(tarjeta)) {
            System.out.println("La tarjeta ya está registrada para este cliente.");
        }
    }

    public void desvincularTarjeta(TarjetaCredito tarjeta) {
        if (this.tarjetas.size() > 1) {
            this.tarjetas.remove(tarjeta);
        } else {
            System.out.println("El cliente debe poseer al menos una Tarjeta.");
        }
    }

    public Set<TarjetaCredito> getTarjetas() {
        return this.tarjetas;
    }

    // ---- Préstamos ----

    public void vincularPrestamo(Prestamo prestamo) {
        this.prestamos.add(prestamo);
    }

    public List<Prestamo> getPrestamos() {
        return this.prestamos;
    }

    // ---- Cajas de Seguridad ----

    public void vincularCajaSeguridad(CajaDeSeguridad caja) {
        this.cajasDeSeguridad.add(caja);
    }

    public void desvincularCajaSeguridad(CajaDeSeguridad caja) {
        this.cajasDeSeguridad.remove(caja);
    }

    public List<CajaDeSeguridad> getCajasDeSeguridad() {
        return this.cajasDeSeguridad;
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
}
