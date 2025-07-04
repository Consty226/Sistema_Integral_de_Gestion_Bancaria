package Empleados;

public abstract class Empleado {
    private String nombre;
    private int legajo;

    public Empleado(String nombre, int legajo) {
        this.nombre = nombre;
        this.legajo = legajo;
    }

    public String getNombre() {
        return nombre;
    }

    public int getLegajo() {
        return legajo;
    }

    public abstract String getRol();

    public String toString() {
        return "Empleado {" +
                "Nombre='" + this.getNombre() + '\'' +
                ", Rol=" + this.getRol() +
                '}';
    }
}
