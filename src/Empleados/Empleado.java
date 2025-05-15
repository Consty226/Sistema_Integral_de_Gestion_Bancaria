package Empleados;

import Banco.Banco;
import Clientes.Cliente;

import java.util.List;

public class Empleado {
    private String nombre;
    private String apellido;
    private int edad;
    private double salario;
    protected final Banco entidadBancaria;
    protected Cliente clienteEnAtencion;

    public Empleado(String nombre, String apellido, int edad, double salario, Banco entidadBancaria) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.edad = edad;
        this.salario = salario;
        this.entidadBancaria = entidadBancaria;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }

    public Banco getEntidadBancaria() {
        return entidadBancaria;
    }

    public Cliente getClienteEnAtencion() {
        return clienteEnAtencion;
    }

    public void atenderCliente(Cliente clienteEnAtencion) {
        if (this.clienteEnAtencion != null) {
            System.out.println("Ya hay un cliente en atención, terminando la atención.");
            this.terminarAtencion();
        }
        if (this.entidadBancaria.esClienteDelBanco(clienteEnAtencion)) {
            System.out.println("No es un cliente registrado del banco.");
            System.out.println("Cree o se registre el cliente para continuar.");
        } else {
            this.clienteEnAtencion = clienteEnAtencion;
        }
    }

    public void terminarAtencion() {
        this.clienteEnAtencion = null;
    }

    public boolean estaAtendiendo() {
        return this.clienteEnAtencion != null;
    }

    public void crearYAtenderCliente(String nombre, String direccion, List<String> telefonos, int dniCuit, String tipo) {
        Cliente cliente = this.entidadBancaria.crearCliente(nombre, direccion, telefonos, dniCuit, tipo);
        if (cliente != null) {
            this.atenderCliente(cliente);
        }
    }

    public void agregarYAtenderCliente(Cliente cliente) {
        this.entidadBancaria.agregarCliente(cliente);
        this.atenderCliente(cliente);
    }

}
