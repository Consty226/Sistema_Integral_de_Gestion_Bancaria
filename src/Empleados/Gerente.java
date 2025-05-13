package Empleados;

import Banco.Banco;

public class Gerente extends Empleado {
    public Gerente(String nombre, String apellido, int edad, double salario, Banco entidadBancaria) {
        super(nombre, apellido, edad, salario, entidadBancaria);
    }

    // Gestion de Cajas de Seguridad
    public void crearCajaSeguridad(String clave){ {
        if(this.estaAtendiendo()){
            Banco.Productos.CAJA_DE_SEGURIDAD.crear(clave);
        }
    }
}
