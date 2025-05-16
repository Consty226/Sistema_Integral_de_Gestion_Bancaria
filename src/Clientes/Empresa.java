package Clientes;

import java.util.List;

public class Empresa extends Cliente {
    private int cuit;


    public Empresa(String nombre, String direccion, List<String> telefonos, int cuit) {
        super(nombre, direccion, telefonos);
        this.cuit = cuit;
    }
}