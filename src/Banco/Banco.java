package Banco;

import Clientes.Cliente;
import Empleados.Empleado;
import Productos.CajaDeSeguridad.CajaDeSeguridad;
import Productos.Cuentas.Cuenta;
import Productos.Prestamo.Prestamo;
import Productos.Producto;
import Productos.ProductoFactory;
import Productos.Tarjetas.TarjetaCredito;

import java.util.ArrayList;
import java.util.List;

public abstract class Banco {
    private String nombre;
    private String direccion;
    protected final List<Cliente> clientes = new ArrayList<Cliente>();
    protected final List<Producto> productos = new ArrayList<Producto>();
    protected final List<Empleado> empleados = new ArrayList<Empleado>();

    public abstract ProductoFactory getFactory(ProductoType tipo);

    public enum ProductoType {
        TARJETA, CUENTA_CORRIENTE, CAJA_AHORRO, PRESTAMO, CAJA_SEGURIDAD
    }

    public Banco(String nombre, String direccion) {
        this.nombre = nombre;
        this.direccion = direccion;
    }

    // Getters y Setters
    public String getNombre() {return this.nombre;}

    public String getDireccion() {
        return this.direccion;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public List<Producto> getProductos() {
        return this.productos;
    }


    // Contratacion de empleados.
    public abstract void contratarEmpleado(Empleado empleado);

    public abstract void contratarEmpleado(String nombre, String apellido, int edad, double salario);

    // Getter de empleados.
    public List<Empleado> getEmpleados(){
        return this.empleados;
    };

    public Empleado getEmpleado(String nombre){
        return this.empleados.stream()
                .filter(empleado -> empleado.getNombre().equalsIgnoreCase(nombre))
                .findFirst()
                .orElse(null);
    };

    public abstract void despedirEmpleado(Empleado empleado);

    public abstract void despedirEmpleado(String nombre);

    // Gestion de clientes.

    public List<Cliente> getClientes() {
        return this.clientes;
    }

    public boolean esClienteDelBanco(Cliente cliente) {
        return this.clientes.contains(cliente);
    }

    public boolean esClienteDelBanco(String nombre) {
        return this.clientes.stream()
                .anyMatch(cliente -> cliente.getNombre().equalsIgnoreCase(nombre));
    }

    public Cliente getCliente(String nombre) {
        return this.clientes.stream()
                .filter(cliente -> cliente.getNombre().equalsIgnoreCase(nombre))
                .findFirst()
                .orElse(null);
    }

    public abstract void agregarCliente(Cliente cliente);

    public abstract Cliente crearCliente(String nombre, String direccion, List<String> telefonos, int dniCuit, String tipo);

    public void eliminarCliente(Cliente cliente) {
        this.clientes.remove(cliente);
    }

    public void eliminarCliente(String nombre) {
        this.clientes.remove(this.getCliente(nombre));
    }


    // Gestion Cuentas
    public abstract void crearCuenta(Cliente titular, ProductoType tipoCuenta, String numeroCuenta, double saldoInicial, int limiteDescubierto);

    private boolean esCuentaDelBanco(Cuenta cuenta) {
        return this.productos.contains(cuenta);
    }

    private Cuenta getCuenta(String numeroCuenta) {
        return (Cuenta) this.productos.stream()
                .filter(producto -> producto instanceof Cuenta)
                .filter(producto -> ((Cuenta) producto).getNumeroCuenta().equals(numeroCuenta))
                .findFirst()
                .orElse(null);
    }

    // Deposito en Cuenta
    public abstract void depositarEnCuenta(String numeroCuenta, double monto);

    public abstract void depositarEnCuenta(Cuenta cuenta, double monto);

    // Retiro en Cuenta
    private void retiroEnCuenta(Cuenta cuenta, double monto) {
        boolean exitoExtraccion = cuenta.extraer(monto);
        if (exitoExtraccion) {
            System.out.println("Extraccion exitosa");
        } else {
            System.out.println("No se pudo realizar la extraccion");
        }
    }

    public abstract void retirarDeCuenta(Cliente titular, String numeroCuenta, double monto);

    public abstract void retirarDeCuenta(Cliente titular, Cuenta cuenta, double monto);

    // Transferencia entre Cuentas

    private void transferir(Cuenta cuentaOrigen, Cuenta cuentaDestino, double monto) {
        boolean exitoTransferencia = cuentaOrigen.transferir(cuentaDestino, monto);
        if (exitoTransferencia) {
            System.out.println("Transferencia exitosa");
        } else {
            System.out.println("No se pudo realizar la transferencia");
        }
    }
    public abstract void realizarTransferencia(Cliente cliente, String numeroCuentaOrigen, String numeroCuentaDestino, double monto);

    public abstract void realizarTransferencia(Cliente cliente, Cuenta cuentaOrigen, Cuenta cuentaDestino, double monto);

    // Gestion de Cajas de Seguridad
    public abstract void crearCajaSeguridad(Cliente titular, String clave);

    public CajaDeSeguridad getCajaSeguridad(Cliente titular, String clave) {
        CajaDeSeguridad cajaSeguridad = (CajaDeSeguridad) this.productos.stream()
                .filter(producto -> producto instanceof CajaDeSeguridad)
                .filter(producto -> producto.getTitular().equals(titular))
                .filter(producto -> ((CajaDeSeguridad) producto).getClaveDeAcceso().equals(clave))
                .findFirst()
                .orElse(null);
        if (cajaSeguridad == null) {
            System.out.println("No se encontro la caja de seguridad, compruebe la clave o el titular");
        }
        return cajaSeguridad;
    }

    public abstract void eliminarCajaSeguridad(Cliente titular, String clave);

    // Gestion Tarjetas de Credito
    public abstract void crearTarjetaCredito(Cliente titular, String numeroTarjeta, double limiteCredito);

    public TarjetaCredito getTarjetaCredito(Cliente titular, String numeroTarjeta) {
        return (TarjetaCredito) this.productos.stream()
                .filter(producto -> producto instanceof TarjetaCredito)
                .filter(producto -> producto.getTitular().equals(titular))
                .filter(producto -> ((TarjetaCredito) producto).getNumeroTarjeta().equals(numeroTarjeta))
                .findFirst()
                .orElse(null);
    }

    public abstract void eliminarTarjetaCredito(Cliente titular, String numeroTarjeta);

    // Gestion de Prestamos
    public void crearPrestamo(Cliente titular, double montoOtorgado, int plazoEnMeses) {
        if (this.esClienteDelBanco(titular)) {
            System.out.println("El cliente " + titular.getNombre() + " no pertenece al banco, no se puede otorgar el prestamo.");
            return;
        }
        ProductoFactory factory = this.getFactory(ProductoType.PRESTAMO);
        if (factory == null) {
            System.out.println("No se pudo crear el prestamo.");
        } else {
            Prestamo prestamo = factory.crearPrestamo(titular, montoOtorgado, plazoEnMeses);
            this.productos.add(prestamo);
        }
    }

    public Prestamo getPrestamo(Cliente titular) {
        // toma el primer prestamo activo filtrado.
        if (this.esClienteDelBanco(titular)) {
            System.out.println("El cliente " + titular.getNombre() + " no pertenece al banco, no se puede otorgar el prestamo.");
            return null;
        }
        Prestamo prestamo = (Prestamo) this.productos.stream()
                .filter(producto -> producto instanceof Prestamo)
                .filter(producto -> ((Prestamo) producto).getTitular().equals(titular))
                .filter(producto -> ((Prestamo) producto).getSaldoRestante() > 0)
                .findFirst()
                .orElse(null);

        if (prestamo == null) {
            System.out.println("No se encontro prestamo activo para el cliente " + titular.getNombre());
        }
        return prestamo;
    }


}
