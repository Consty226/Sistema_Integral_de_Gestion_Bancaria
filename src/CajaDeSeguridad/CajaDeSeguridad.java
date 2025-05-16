package CajaDeSeguridad;
import Clientes.Cliente;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CajaDeSeguridad {
    private Cliente titular;
    private String numero;
    private String claveDeAcceso;
    private double montoActual;
    private List<String> contenido = new ArrayList<>();


    //Se actualizaron los atributos para cumplir con la consigna “Cada caja de
// seguridad posee un número y descripción del contenido. Su existencia está ligada al cliente.”
    public static String generarNumeroCaja() {
        Random random = new Random();
        StringBuilder sb = new StringBuilder(16);
        for (int i = 0; i < 16; i++) {
            sb.append(random.nextInt(10)); // Genera un dígito aleatorio (0-9)
        }
        return sb.toString();
    }
    public CajaDeSeguridad(Cliente titular, String claveDeAcceso){
        this.numero = CajaDeSeguridad.generarNumeroCaja();
        this.titular = titular;
        this.claveDeAcceso = claveDeAcceso;
        this.montoActual = 0;
    }

    public double getMontoActual(){
        return this.montoActual;
    }
    public void retirarDinero(double cantidadRetirada){

        if (cantidadRetirada > 0 && cantidadRetirada <= montoActual) {
            montoActual -= cantidadRetirada;
            System.out.println("$" + cantidadRetirada + " retirados con éxito.");
        } else if (cantidadRetirada <= 0) {
            System.out.println("La cantidad a retirar debe ser mayor que cero.");
        } else {
            System.out.println("No tienes suficiente saldo para retirar esa cantidad.");
        }
    }

    public String getNumero() {
        return numero;
    }

    public void depositarDinero(double cantidadDepositada){
        if (cantidadDepositada > 0) {
            montoActual += cantidadDepositada;
            System.out.println("$" + cantidadDepositada + " depositados con éxito.");
        } else {
            System.out.println("La cantidad a depositar debe ser mayor que cero.");
        }
    }

    public String getClaveDeAcceso() {
        return claveDeAcceso;
    }

    public void setClaveDeAcceso(String claveDeAcceso) {
        this.claveDeAcceso = claveDeAcceso;
    }

    public List<String> getContenido(){
        return this.contenido;
    }

    public void setContenido(String elemento){
        this.contenido.add(elemento);
    }

    public void removerContenido(String elemento){
        this.contenido.remove(elemento);
    }
}
