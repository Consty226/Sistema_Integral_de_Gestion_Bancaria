package Productos;

import Clientes.Cliente;

public abstract class Producto {
    private final Cliente titular;

    public Producto(Cliente titular) {
        this.titular = titular;
    }

    public Cliente getTitular() {
        return titular;
    }
}
