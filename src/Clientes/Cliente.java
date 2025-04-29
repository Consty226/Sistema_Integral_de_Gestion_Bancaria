package Clientes;

import java.util.List;

public abstract class Cliente {
    protected String nombre;
    protected String direccion;
    protected List<String> telefonos;
    protected List<Cuenta> cuentas;
    protected List<TarjetaCredito> tarjetas;
    protected List<Prestamo> prestamos;
    protected List<CajaDeSeguridad> cajasDeSeguridad;

    public Cliente(String nombre, String direccion, List<String> telefonos){
        this.nombre = nombre;
        this.direccion = direccion;
        this.telefonos = telefonos;
    }

    // ---- Getters y Setters Relaciones ----

    // Cuentas
    public void vincularCuenta(Cuenta cuenta){
        this.cuentas.add(cuenta);
    }

    public void desvincularCuenta(Cuenta cuenta){
        if (this.cuentas.size() > 1) {
            this.cuentas.remove(cuenta);
        } else {
            System.out.println("El cliente debe poseer al menos una Cuenta.");
        }
    }

    public List<Cuenta> getCuentas(){
        return this.cuentas;
    }

    // Tarjetas
    public void vincularTarjeta(TarjetaCredito tarjeta){
        this.tarjetas.add(tarjeta);
    }

    public void desvincularTarjeta(TarjetaCredito tarjeta){
        if (this.tarjetas.size() > 1){
            this.tarjetas.remove(tarjeta);
        } else {
            System.out.println("El cliente debe poseer al menos una Tarjeta.");
        }
    }

    public List<TarjetaCredito> getTarjetas(){
        return this.tarjetas;
    }

    // Prestamos

    public void vincularPrestamo(Prestamo prestamo){
        this.prestamos.add(prestamo);
    }

    // Deberia implementarse un metodo para pagos de prestamos o tomar un prestamo especifico?

    public List<Prestamo> getPrestamos(){
        return this.prestamos;
    }

    // Cajas de Seguridad

    public void vincularCajaSeguridad(CajaDeSeguridad caja){
        this.cajasDeSeguridad.add(caja);
    }

    public void desvincularCajaSeguridad(CajaDeSeguridad caja){
        this.cajasDeSeguridad.remove(caja);
    }

    public List<CajaDeSeguridad> getCajasDeSeguridad(){
        return this.cajasDeSeguridad;
    }

    // ---- Getters y Setters ----

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

    public void agregarTelefono(String telefono){
        this.telefonos.add(telefono);
    }

    public void desvincularTelefono(String telefono){
        this.telefonos.remove(telefono);
    }

    public List<String> getTelefonos() {
        return this.telefonos;
    }
}
