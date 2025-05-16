package Clientes;

import java.util.List;

public class Empresa extends Cliente {
    private String cuit;


    public Empresa(String nombre, String direccion, List<String> telefonos, String cuit) {
        super(nombre, direccion, telefonos);
        this.cuit = cuit;
    }

    public String getCuit() {
        return cuit;
    }

    public void setCuit(String cuit) {
        this.cuit = cuit;
    }
}

