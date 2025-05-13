package Banco;

import Clientes.Cliente;
import Clientes.Empresa;
import Clientes.Persona;
import Empleados.Empleado;
import Productos.Cuentas.Cuenta;
import Productos.Producto;
import Productos.ProductoFactory;

import java.util.ArrayList;
import java.util.List;

public abstract class Banco {
    private String nombre;
    private String direccion;
    private final List<Cliente> clientes = new ArrayList<Cliente>();
    private final List<Producto> productos = new ArrayList<Producto>();
    private final List<Empleado> empleados = new ArrayList<Empleado>();

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
    public void contratarEmpleado(Empleado empleado) {
        this.empleados.add(empleado);
    }

    public void contratarEmpleado(String nombre, String apellido, int edad, double salario) {
        Empleado empleado = new Empleado(nombre, apellido, edad, salario, this);
        this.empleados.add(empleado);
    }

    // Getter de empleados.
    public List<Empleado> getEmpleados() {
        return this.empleados;
    }

    public Empleado getEmpleado(String nombre) {
        return this.empleados.stream()
                .filter(empleado -> empleado.getNombre().equalsIgnoreCase(nombre))
                .findFirst()
                .orElse(null);
    }

    public void despedirEmpleado(Empleado empleado) {
        this.empleados.remove(empleado);
    }

    public void despedirEmpleado(String nombre) {
        this.empleados.remove(this.getEmpleado(nombre));
    }

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

    public void agregarCliente(Cliente cliente) {
        this.clientes.add(cliente);
    }

    public void agregarCliente(String nombre, String direccion, List<String> telefonos, int dniCuit, String tipo) {
        switch (tipo.toLowerCase()) {
            case "persona":
                this.clientes.add(new Persona(nombre, direccion, telefonos, dniCuit));
                break;
            case "empresa":
                this.clientes.add(new Empresa(nombre, direccion, telefonos, dniCuit));
                break;
            default:
                System.out.println("Tipo de cliente no reconocido.");
        }
    }

    public void eliminarCliente(Cliente cliente) {
        this.clientes.remove(cliente);
    }

    public void eliminarCliente(String nombre) {
        this.clientes.remove(this.getCliente(nombre));
    }


    // Gestion Cuentas
    private Cuenta getCuenta(String numeroCuenta) {
        return (Cuenta) this.productos.stream()
                .filter(producto -> producto instanceof Cuenta)
                .filter(producto -> ((Cuenta) producto).getNumeroCuenta().equals(numeroCuenta))
                .findFirst()
                .orElse(null);
    }

    private boolean esCuentaDelBanco(Cuenta cuenta) {
        return this.productos.contains(cuenta);
    }

    // Deposito en Cuenta
    public void depositarEnCuenta(String numeroCuenta, double monto) {
        Cuenta cuenta = this.getCuenta(numeroCuenta);
        if (cuenta != null){
            cuenta.depositar(monto);
        }else {
            System.out.println("No se encontro la cuenta " + numeroCuenta);
        }
    }

    public void depositarEnCuenta(Cuenta cuenta, double monto) {
        if (this.esCuentaDelBanco(cuenta)){
            cuenta.depositar(monto);
        }else {
            System.out.println("La cuenta " + cuenta.getNumeroCuenta() + " no pertenece al banco");
        }
    }

    // Retiro en Cuenta
    private void retiroEnCuenta(Cuenta cuenta, double monto) {
        boolean exitoExtraccion = cuenta.extraer(monto);
        if (exitoExtraccion) {
            System.out.println("Extraccion exitosa");
        } else {
            System.out.println("No se pudo realizar la extraccion");
        }
    }
    public void retirarDeCuenta(String numeroCuenta, double monto) {
        Cuenta cuenta = this.getCuenta(numeroCuenta);
        if (cuenta != null){
            this.retiroEnCuenta(cuenta, monto);
        }else {
            System.out.println("No se encontro la cuenta " + numeroCuenta);
        }
    }

    public void retirarDeCuenta(Cuenta cuenta, double monto) {
        if (this.esCuentaDelBanco(cuenta)){
            this.retiroEnCuenta(cuenta, monto);
        }else {
            System.out.println("La cuenta " + cuenta.getNumeroCuenta() + " no pertenece al banco");
        }
    }

    // Transferencia entre Cuentas

    private void transferir(Cuenta cuentaOrigen, Cuenta cuentaDestino, double monto) {
        boolean exitoTransferencia = cuentaOrigen.transferir(cuentaDestino, monto);
        if (exitoTransferencia) {
            System.out.println("Transferencia exitosa");
        } else {
            System.out.println("No se pudo realizar la transferencia");
        }
    }
    public void realizarTransferencia(
            Cliente cliente,
            String numeroCuentaOrigen,
            String numeroCuentaDestino,
            double monto) {

        Cuenta cuentaOrigen = this.getCuenta(numeroCuentaOrigen);
        Cuenta cuentaDestino = this.getCuenta(numeroCuentaDestino);

        if(cuentaOrigen == null) {
            System.out.println("No se encontro la cuenta de origen N°" + numeroCuentaOrigen);
        } else if (!cuentaOrigen.getTitular().equals(cliente)) {
            System.out.println("La cuenta " + numeroCuentaOrigen + " no pertenece al cliente " + cliente.getNombre());
        } else if (cuentaDestino == null) {
            System.out.println("No se encontro la cuenta de destino N°" + numeroCuentaDestino);
        }else{
            this.transferir(cuentaOrigen, cuentaDestino, monto);
        }
    }

    public void realizarTransferencia(
            Cliente cliente,
            Cuenta cuentaOrigen,
            Cuenta cuentaDestino,
            double monto) {

        boolean cuentaOrigenPerteneceBanco = this.esCuentaDelBanco(cuentaOrigen);
        boolean cuentaDestinoPerteneceBanco = this.esCuentaDelBanco(cuentaDestino);
        if (!cuentaOrigenPerteneceBanco) {
            System.out.println("La cuenta " + cuentaOrigen.getNumeroCuenta() + " no pertenece al banco");
        } else if (!cuentaDestinoPerteneceBanco) {
            System.out.println("La cuenta " + cuentaDestino.getNumeroCuenta() + " no pertenece al banco");
        } else if (cuentaOrigen.getTitular().equals(cliente)) {
            System.out.println("La cuenta " + cuentaOrigen.getNumeroCuenta() + " no pertenece al cliente " + cliente.getNombre());
        } else {
            this.transferir(cuentaOrigen, cuentaDestino, monto);
        }
    }


}
