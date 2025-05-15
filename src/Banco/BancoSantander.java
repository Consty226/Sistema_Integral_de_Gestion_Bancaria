package Banco;

import Clientes.Cliente;
import Productos.CajaDeSeguridad.CajaDeSeguridad;
import Productos.Cuentas.CajaDeAhorro;
import Productos.Cuentas.CuentaCorriente;
import Productos.Prestamo.Prestamo;
import Productos.ProductoFactory;
import Productos.Tarjetas.TarjetaCredito;

public class BancoSantander extends BancoGenerico {

    public BancoSantander(String nombre, String direccion) {
        super(nombre, direccion);
    }

    public enum Productos implements ProductoFactory {
        TARJETA {
            @Override
            public TarjetaCredito crearTarjeta(Cliente titular, String numeroTarjeta, double limiteCredito) {
                return new TarjetaCredito(titular, numeroTarjeta, limiteCredito);
            }
        },
        CUENTA_CORRIENTE {
            @Override
            public CuentaCorriente crearCuentaCorriente(Cliente titular, String numeroCuenta, double saldoInicial, int limiteDescubierto) {
                return new CuentaCorriente(titular, numeroCuenta, saldoInicial, limiteDescubierto);
            }
        },
        CAJA_AHORRO {
            @Override
            public CajaDeAhorro crearCajaAhorro(Cliente titular, String numeroCuenta, double saldoInicial) {
                return new CajaDeAhorro(titular,numeroCuenta, saldoInicial);
            }
        },
        PRESTAMO {
            public Prestamo crearPrestamo(Cliente cliente, double montoOtorgado, double tasaDeInteres) {
                return new Prestamo(cliente, montoOtorgado, tasaDeInteres);
            }
        },

        CAJA_DE_SEGURIDAD {
            public CajaDeSeguridad crearCajaSeguridad(Cliente titular, String claveDeAcceso) {
                return new CajaDeSeguridad(titular, claveDeAcceso);
            }
        };
    }

    @Override
    public ProductoFactory getFactory(ProductoType tipo) {
        try{
            return Productos.valueOf(tipo.name());
        } catch (IllegalArgumentException e) {
            System.out.println("Producto " + tipo.name() + " no soportado");
            return null;
        }
    }



}

