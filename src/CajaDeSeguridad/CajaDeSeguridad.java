package CajaDeSeguridad;
public class CajaDeSeguridad {

    private int numero;
    private String descripcionContenido;
    private String claveDeAcceso;
    private double montoActual;

    //Se actualizaron los atributos para cumplir con la consigna “Cada caja de
// seguridad posee un número y descripción del contenido. Su existencia está ligada al cliente.”

    public CajaDeSeguridad(int numero, String descripcionContenido, String claveDeAcceso){
        this.numero = numero;
        this.descripcionContenido = descripcionContenido;
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

    public int getNumero() {
        return numero;
    }

    public String getDescripcionContenido() {
        return descripcionContenido;
    }

    public void setDescripcionContenido(String descripcionContenido) {
        this.descripcionContenido = descripcionContenido;
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
