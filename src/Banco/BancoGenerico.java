package Banco;

import Clientes.Cliente;
import Clientes.Empresa;
import Clientes.Persona;
import Empleados.Empleado;
import Productos.CajaDeSeguridad.CajaDeSeguridad;
import Productos.Cuentas.CajaDeAhorro;
import Productos.Cuentas.Cuenta;
import Productos.Cuentas.CuentaCorriente;
import Productos.ProductoFactory;
import Productos.Tarjetas.TarjetaCredito;

import java.util.List;

public abstract class BancoGenerico extends Banco {


    public BancoGenerico(String nombre, String direccion) {
        super(nombre, direccion);
    }

    // Contratacion de empleados.
    public void contratarEmpleado(Empleado empleado) {
        this.empleados.add(empleado);
    }

    public void contratarEmpleado(String nombre, String apellido, int edad, double salario) {
        Empleado empleado = new Empleado(nombre, apellido, edad, salario, this);
        this.empleados.add(empleado);
    }

    public void despedirEmpleado(Empleado empleado) {
        this.empleados.remove(empleado);
    }

    public void despedirEmpleado(String nombre) {
        this.empleados.remove(this.getEmpleado(nombre));
    }

    // Gestion de clientes.


    public void agregarCliente(Cliente cliente) {
        if (this.esClienteDelBanco(cliente)) {
            return;
        }
        System.out.println("Se agrego el cliente " + cliente.getNombre());
        this.clientes.add(cliente);

        System.out.println("Creando Caja de Ahorro");
        this.crearCuenta(cliente, ProductoType.CAJA_AHORRO, "1234", 10000, 1000);

        System.out.println("Creando Tarjeta");
        this.crearTarjetaCredito(cliente, "1234", 10000);

    }

    public Cliente crearCliente(String nombre, String direccion, List<String> telefonos, int dniCuit, String tipo) {
        switch (tipo.toLowerCase()) {
            case "persona":
                Persona persona = new Persona(nombre, direccion, telefonos, dniCuit);
                this.agregarCliente(persona);
                return persona;
            case "empresa":
                Empresa empresa = new Empresa(nombre, direccion, telefonos, dniCuit);
                this.agregarCliente(empresa);
                return empresa;
            default:
                System.out.println("Tipo de cliente no reconocido.");
                return null;
        }
    }


    // Gestion Cuentas
    public void crearCuenta(Cliente titular, ProductoType tipoCuenta, String numeroCuenta, double saldoInicial, int limiteDescubierto) {

        ProductoFactory factory = this.getFactory(tipoCuenta);

        switch (tipoCuenta) {
            case CUENTA_CORRIENTE:
                CuentaCorriente cuentaCorriente = factory.crearCuentaCorriente(titular, numeroCuenta, saldoInicial, limiteDescubierto);
                this.productos.add(cuentaCorriente);
                break;
            case CAJA_AHORRO:
                CajaDeAhorro cajaAhorro = factory.crearCajaAhorro(titular, numeroCuenta, saldoInicial);
                this.productos.add(cajaAhorro);
                break;
            default:
                System.out.println("Producto " + tipoCuenta.name() + " no soportado");
                break;
        }

    }

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
    public void retirarDeCuenta(Cliente titular, Cuenta cuenta, double monto) {
        if (!this.esCuentaDelBanco(cuenta)){
            System.out.println("La cuenta " + cuenta.getNumeroCuenta() + " no pertenece al banco");
            return;
        }
        if (!cuenta.getTitular().equals(titular)){
            System.out.println("El titular de la cuenta no coincide con el cliente proporcionado");
            return;
        }
        boolean exitoExtraccion = cuenta.extraer(monto);
        if (exitoExtraccion) {
            System.out.println("Extraccion exitosa");
        } else {
            System.out.println("No se pudo realizar la extraccion");
        }
    }

    public void retirarDeCuenta(Cliente titular, String numeroCuenta, double monto) {
        Cuenta cuenta = this.getCuenta(numeroCuenta);
        if (cuenta == null){
            System.out.println("No se encontro la cuenta " + numeroCuenta);
            return;
        }
        if (!cuenta.getTitular().equals(titular)){
            System.out.println("El titular de la cuenta no coincide con el cliente proporcionado");
            return;
        }

        boolean exitoExtraccion = cuenta.extraer(monto);
        if (exitoExtraccion) {
            System.out.println("Extraccion exitosa");
        } else {
            System.out.println("No se pudo realizar la extraccion");
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

    // Gestion de Cajas de Seguridad
    public void crearCajaSeguridad(Cliente titular, String clave) {
        ProductoFactory factory = this.getFactory(ProductoType.CAJA_SEGURIDAD);
        if (factory == null) {
            System.out.println("No se pudo crear la caja de seguridad.");
            return;
        }
        CajaDeSeguridad cajaSeguridad = factory.crearCajaSeguridad(titular, clave);
        this.productos.add(cajaSeguridad);
    }

    public void eliminarCajaSeguridad(Cliente titular, String clave) {
        this.productos.remove(this.getCajaSeguridad(titular, clave));
    }


    // Gestion Tarjetas de Credito
    public void crearTarjetaCredito(Cliente titular, String numeroTarjeta, double limiteCredito) {
        if (this.esClienteDelBanco(titular)) {
            System.out.println("El cliente " + titular.getNombre() + " no pertenece al banco");
            return;
        }
        ProductoFactory factory = this.getFactory(ProductoType.TARJETA);
        if (factory == null) {
            System.out.println("No se pudo crear la tarjeta de credito.");
            return;
        }
        TarjetaCredito tarjetaCredito = factory.crearTarjeta(titular, numeroTarjeta, limiteCredito);
        this.productos.add(tarjetaCredito);
    }

    public void eliminarTarjetaCredito(Cliente titular, String numeroTarjeta) {
        this.productos.remove(this.getTarjetaCredito(titular, numeroTarjeta));
    }
}
