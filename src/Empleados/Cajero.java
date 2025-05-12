package Empleados;

import Banco.Banco;
import Clientes.Cliente;

public class Cajero extends Empleado {

    public Cajero(String nombre, String apellido, int edad, double salario, Banco entidadBancaria) {
        super(nombre, apellido, edad, salario, entidadBancaria);
    }

    public void depositarEnCuenta(double monto, String numeroCuenta) {
        this.clienteEnAtencion.depositarEnCuenta(monto, numeroCuenta);
    }

}
