package Empleados;

public class Gerente extends Empleado {
    public Gerente(String nombre, int legajo) {
        super(nombre, legajo);
    }

    @Override
    public String getRol() {
        return "Gerente";
    }

    // Métodos específicos como aprobar préstamos, asignar cajas de seguridad
}
