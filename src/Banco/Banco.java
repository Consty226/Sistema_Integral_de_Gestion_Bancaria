package Banco;

import java.util.List;
import java.util.ArrayList;

import Clientes.Cliente;
import Empleados.Empleado;
import Productos.Cuentas.Cuenta;
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


    // Gestion Cuentas
    private Cuenta getCuentaPorNumero(String numeroCuenta) {
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
    public void depositarEnCuentaPorNumero(String numeroCuenta, double monto) {
        Cuenta cuenta = this.getCuentaPorNumero(numeroCuenta);
        if (cuenta != null){
            cuenta.depositar(monto);
        }else {
            System.out.println("No se encontro la cuenta " + numeroCuenta);
        }
    }

    public void depositarEnCuentaPorInstancia(Cuenta cuenta, double monto) {
        if (this.esCuentaDelBanco(cuenta)){
            cuenta.depositar(monto);
        }else {
            System.out.println("La cuenta " + cuenta.getNumeroCuenta() + " no pertenece al banco");
        }
    }

    // Retiro en Cuenta
    private void retirarDeCuenta(Cuenta cuenta, double monto) {
        boolean exitoExtraccion = cuenta.extraer(monto);
        if (exitoExtraccion) {
            System.out.println("Extraccion exitosa");
        } else {
            System.out.println("No se pudo realizar la extraccion");
        }
    }
    public void retirarDeCuentaPorNumero(String numeroCuenta, double monto) {
        Cuenta cuenta = this.getCuentaPorNumero(numeroCuenta);
        if (cuenta != null){
            this.retirarDeCuenta(cuenta, monto);
        }else {
            System.out.println("No se encontro la cuenta " + numeroCuenta);
        }
    }

    public void retirarDeCuentaPorInstancia(Cuenta cuenta, double monto) {
        if (this.esCuentaDelBanco(cuenta)){
            this.retirarDeCuenta(cuenta, monto);
        }else {
            System.out.println("La cuenta " + cuenta.getNumeroCuenta() + " no pertenece al banco");
        }
    }

    // Transferencia entre Cuentas

    private void realizarTransferencia(Cuenta cuentaOrigen, Cuenta cuentaDestino, double monto) {
        boolean exitoTransferencia = cuentaOrigen.transferir(cuentaDestino, monto);
        if (exitoTransferencia) {
            System.out.println("Transferencia exitosa");
        } else {
            System.out.println("No se pudo realizar la transferencia");
        }
    }
    public void transferirPorNumero(
        Cliente cliente,
        String numeroCuentaOrigen,
        String numeroCuentaDestino,
        double monto) {

        Cuenta cuentaOrigen = this.getCuentaPorNumero(numeroCuentaOrigen);
        Cuenta cuentaDestino = this.getCuentaPorNumero(numeroCuentaDestino);

        if(cuentaOrigen == null) {
            System.out.println("No se encontro la cuenta de origen N°" + numeroCuentaOrigen);
        } else if (!cuentaOrigen.getTitular().equals(cliente)) {
            System.out.println("La cuenta " + numeroCuentaOrigen + " no pertenece al cliente " + cliente.getNombre());
        } else if (cuentaDestino == null) {
            System.out.println("No se encontro la cuenta de destino N°" + numeroCuentaDestino);
        }else{
            this.realizarTransferencia(cuentaOrigen, cuentaDestino, monto);
        }
    }

    public void transferirPorInstancia(
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
            this.realizarTransferencia(cuentaOrigen, cuentaDestino, monto);
        }
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

    public List<Producto> getProductos() {
        return this.productos;
    }
}
