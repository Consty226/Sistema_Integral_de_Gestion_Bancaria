package Empleados;

import Banco.Banco;
import Clientes.Cliente;
import Productos.Cuentas.Cuenta;

public class Cajero extends Empleado {

    public Cajero(String nombre, String apellido, int edad, double salario, Banco entidadBancaria) {
        super(nombre, apellido, edad, salario, entidadBancaria);
    }

    // Operaciones de Deposito
    public void depositarEnCuentaPorNumero(double monto, String numeroCuenta) {
        this.entidadBancaria.depositarEnCuentaPorNumero(numeroCuenta, monto);
    }

    public void depositarEnCuentaPorInstancia(double monto, Cuenta cuenta) {
        this.entidadBancaria.depositarEnCuentaPorInstancia(cuenta, monto);
    }

    // Operaciones de Retiro
    public void retirarDeCuentaPorNumero(double monto, String numeroCuenta) {
        this.entidadBancaria.retirarDeCuentaPorNumero(numeroCuenta, monto);
    }

    public void retirarDeCuentaPorInstancia(double monto, Cuenta cuenta) {
        this.entidadBancaria.retirarDeCuentaPorInstancia(cuenta, monto);
    }

    // Operaciones de Transferencia (deberia utilizar overloading aca y en banco?????)
    public void transferirPorNumeros(double monto, String numeroCuentaOrigen, String numeroCuentaDestino) {
        if(this.estaAtendiendo()){
            this.entidadBancaria.transferirPorNumero(this.clienteEnAtencion, numeroCuentaOrigen, numeroCuentaDestino, monto);
        }else {
            System.out.println("No hay un cliente en atención");
        }
    }

    public void transferirPorInstancias(double monto, Cuenta cuentaOrigen, Cuenta cuentaDestino) {
        if(this.estaAtendiendo()){
            this.entidadBancaria.transferirPorInstancia(this.clienteEnAtencion, cuentaOrigen, cuentaDestino, monto);
        } else {
            System.out.println("No hay un cliente en atención");
        }
    }


}
