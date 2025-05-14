package Empleados;

public class Cajero extends Empleado {
    public Cajero(String nombre, int legajo) {
        super(nombre, legajo);
    }

    @Override
    public String getRol() {
        return "Cajero";
    }

    // Métodos específicos como operar depósitos, extracciones, transferencias
}
