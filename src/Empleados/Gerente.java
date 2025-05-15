package Empleados;

import Banco.Banco;
import Productos.CajaDeSeguridad.CajaDeSeguridad;
import Productos.Producto;

public class Gerente extends Empleado {
    public Gerente(String nombre, String apellido, int edad, double salario, Banco entidadBancaria) {
        super(nombre, apellido, edad, salario, entidadBancaria);
    }

    // Gestion de Cajas de Seguridad
    public void crearCajaSeguridad(String clave){
        if(this.estaAtendiendo()){
            this.entidadBancaria.crearCajaSeguridad(this.clienteEnAtencion, clave);
        } else {
            System.out.println("No hay un cliente en atención.");
        }
    }

    public CajaDeSeguridad obtenerCajaSeguridad(String clave){
        return this.entidadBancaria.getCajaSeguridad(this.clienteEnAtencion, clave);
    }

    // Gestion de Prestamos
    public void crearPrestamo(double monto, int plazos){
        if(this.estaAtendiendo()){
            this.entidadBancaria.crearPrestamo(this.clienteEnAtencion, monto, plazos);
        } else {
            System.out.println("No hay un cliente en atención.");
        }
    }

    public Producto obtenerPrestamo(String clave){
        return this.entidadBancaria.getPrestamo(this.clienteEnAtencion);
    }
}
