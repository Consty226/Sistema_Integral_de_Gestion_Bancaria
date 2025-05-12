package Banco;

import java.util.List;
import java.util.ArrayList;

import Clientes.Cliente;
import Empleados.Empleado;
import Productos.Producto;
import Productos.CajaDeSeguridad.CajaDeSeguridad;
import Productos.Cuentas.CajaDeAhorro;
import Productos.Cuentas.CuentaCorriente;
import Productos.Prestamo.Prestamo;
import Productos.Tarjetas.TarjetaCredito;



public class Banco {
    public enum Productos {
        TARJETA {
            public Producto crear(String numeroTarjeta, double limiteCredito) {
                return new TarjetaCredito(numeroTarjeta, limiteCredito);
            }
        },
        CUENTA_CORRIENTE {
            public Producto crear(String numeroCuenta, String titular, double saldoInicial, int limiteDescubierto) {
                return new CuentaCorriente(numeroCuenta, titular, saldoInicial, limiteDescubierto);
            }
        },
        CAJA_DE_AHORRO {
            public Producto crear(String numeroCuenta, String titular, double saldoInicial) {
                return new CajaDeAhorro(numeroCuenta, titular, saldoInicial);
            }
        },
        PRESTAMO {
            public Producto crear() {
                return new Prestamo();
            }
        },
        CAJA_DE_SEGURIDAD {
            public Producto crear(String claveDeAcceso) {
                return new CajaDeSeguridad(claveDeAcceso);
            }
        };
    }

    private String nombre;
    private String direccion;
    private final List<Producto> productos = new ArrayList<Producto>();
    private final List<Empleado> empleados = new ArrayList<Empleado>();
    private final List<Cliente> clientes = new ArrayList<Cliente>();

    public Banco(String nombre, String direccion) {
        this.nombre = nombre;
        this.direccion = direccion;
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

    public Empleado getEmpleadoPorNombre(String nombre) {
        return this.empleados.stream()
                .filter(empleado -> empleado.getNombre().equalsIgnoreCase(nombre))
                .findFirst()
                .orElse(null);
    }

    // Gestion de clientes.
    public List<Cliente> getClientes() {
        return this.clientes;
    }

    public boolean esClienteDelBanco(Cliente cliente) {
        return this.clientes.contains(cliente);
    }

    public Cliente getClientePorNombre(String nombre) {
        return this.clientes.stream()
                .filter(cliente -> cliente.getNombre().equalsIgnoreCase(nombre))
                .findFirst()
                .orElse(null);
    }


    // Getters y Setters.
    public String getNombre() {
        return this.nombre;
    }

    public String getDireccion() {
        return this.direccion;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
}
