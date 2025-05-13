package Productos.CajaDeSeguridad;
import Clientes.Cliente;
import Productos.Producto;

public class CajaDeSeguridad extends Producto {

    private String claveDeAcceso;
    private double montoActual;

    public CajaDeSeguridad(Cliente titular, String claveDeAcceso){
        super(titular);
        this.claveDeAcceso = claveDeAcceso;
        this.montoActual = 0;
        System.out.println("----------------------------------");
        System.out.println("Caja de seguridad creada");
        System.out.println("----------------------------------");
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

}
