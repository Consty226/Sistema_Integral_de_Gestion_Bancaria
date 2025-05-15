package Productos;

import Clientes.Cliente;
import Productos.CajaDeSeguridad.CajaDeSeguridad;
import Productos.Cuentas.CajaDeAhorro;
import Productos.Cuentas.CuentaCorriente;
import Productos.Prestamo.Prestamo;
import Productos.Tarjetas.TarjetaCredito;

public interface ProductoFactory {
    default TarjetaCredito crearTarjeta(Cliente titular, String numeroTarjeta, double limiteCredito){
        System.out.println("Creacion de tarjeta no soportada");
        return null;
    };
    default CuentaCorriente crearCuentaCorriente(Cliente titular, String numeroCuenta, double saldoInicial, int limiteDescubierto){
        System.out.println("Creacion de cuenta no soportada");
        return null;
    };
    default CajaDeAhorro crearCajaAhorro(Cliente titular, String numeroCuenta, double saldoInicial){
        System.out.println("Creacion de caja de ahorro no soportada");
        return null;
    };
    default Prestamo crearPrestamo(Cliente cliente, double montoOtorgado, double tasaInteres){
        System.out.println("Creacion de prestamo no soportada");
        return null;
    };
    default CajaDeSeguridad crearCajaSeguridad(Cliente titular, String claveAcceso){
        System.out.println("Creacion de caja de seguridad no soportada");
        return null;
    };
}