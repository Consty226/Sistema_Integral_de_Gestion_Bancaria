package Empleados;

import Banco.Banco;
import Productos.Producto;
import Productos.ProductoFactory;

public class Gerente extends Empleado {
    public Gerente(String nombre, String apellido, int edad, double salario, Banco entidadBancaria) {
        super(nombre, apellido, edad, salario, entidadBancaria);
    }

    // Gestion de Cajas de Seguridad
    public void crearCajaSeguridad(String clave){
        if(this.estaAtendiendo()){
            ProductoFactory factory = this.entidadBancaria.getFactory(Banco.ProductoType.CAJA_SEGURIDAD);
            if (factory == null) {
                System.out.println("No se pudo crear la caja de seguridad.");
            } else {
                factory.crearCajaSeguridad(this.clienteEnAtencion, clave);
            }
        } else {
            System.out.println("No hay un cliente en atención.");
        }
    }
}
