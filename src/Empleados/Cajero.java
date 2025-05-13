package Empleados;

import Banco.Banco;
import Productos.Cuentas.Cuenta;

public class Cajero extends Empleado {

    public Cajero(String nombre, String apellido, int edad, double salario, Banco entidadBancaria) {
        super(nombre, apellido, edad, salario, entidadBancaria);
    }

    // Operaciones de Deposito
    public void depositarEnCuenta(double monto, String numeroCuenta) {
        this.entidadBancaria.depositarEnCuenta(numeroCuenta, monto);
    }

    public void depositarEnCuenta(double monto, Cuenta cuenta) {
        this.entidadBancaria.depositarEnCuenta(cuenta, monto);
    }

    // Operaciones de Retiro
    public void retirarDeCuenta(double monto, String numeroCuenta) {
        this.entidadBancaria.retirarDeCuenta(numeroCuenta, monto);
    }

    public void retirarDeCuenta(double monto, Cuenta cuenta) {
        this.entidadBancaria.retirarDeCuenta(cuenta, monto);
    }

    // Operaciones de Transferencia
    public void ejecutarTransferencia(double monto, String numeroCuentaOrigen, String numeroCuentaDestino) {
        if(this.estaAtendiendo()){
            this.entidadBancaria.realizarTransferencia(this.clienteEnAtencion, numeroCuentaOrigen, numeroCuentaDestino, monto);
        }else {
            System.out.println("No hay un cliente en atención");
        }
    }

    public void ejecutarTransferencia(double monto, Cuenta cuentaOrigen, Cuenta cuentaDestino) {
        if(this.estaAtendiendo()){
            this.entidadBancaria.realizarTransferencia(this.clienteEnAtencion, cuentaOrigen, cuentaDestino, monto);
        } else {
            System.out.println("No hay un cliente en atención");
        }
    }


}
