public class TarjetaCredito {
    private int numeroUnico;
    private double saldoDispoible;
    private double consumo;
    private int pagos;
    private double limite;
    private int transacciones;

    public int getTransacciones(){
        return transacciones;
    }

    public void setTransacciones(int transacciones) {
        this.transacciones = transacciones;
    }

    public int getPagos() {
        return pagos;
    }

    public void setPagos(int pagos) {
        this.pagos = pagos;
    }

    public double getLimite() {
        return limite;
    }

    public void setLimite(double limite) {
        this.limite = limite;
    }

    public double getConsumo() {
        return consumo;
    }

    public void setConsumo(double consumo) {
        this.consumo = consumo;
    }

    public double getSaldoDispoible() {
        return saldoDispoible;
    }

    public void setSaldoDispoible(double saldoDispoible) {
        this.saldoDispoible = saldoDispoible;
    }

    public void setNumeroUnico(int numeroUnico) {
        this.numeroUnico = numeroUnico;
    }

    public TarjetaCredito(int numeroUnico, double saldoDispoible, double consumo, int pagos, double limite, int transacciones) {
        this.numeroUnico = numeroUnico;
        this.saldoDispoible = saldoDispoible;
        this.consumo = consumo;
        this.pagos = pagos;
        this.limite = limite;
        this.transacciones = transacciones;
    }

    //metodo de consumo
    public void registrarConsumo(String descripcion, double monto) {
        if (saldoDispoible + monto <= limite) {
            Transaccion consumo = new Transaccion(descripcion, monto, "CONSUMO");
            transacciones.add(consumo);
            saldoDispoible += monto;
            System.out.println("Consumo de $" + monto + " registrado: " + descripcion);
        } else {
            System.out.println("Límite de crédito excedido. No se puede registrar el consumo.");
        }
    }

    public void registrarPago(String descripcion, double monto) {
        Transaccion pago = new Transaccion(descripcion, -monto, "PAGO"); // Monto negativo para indicar pago
        transacciones.add(pago);
        saldoDispoible -= monto;
        System.out.println("Pago de $" + monto + " registrado: " + descripcion);
    }

    public void mostrarEstadoCuenta() {
        System.out.println("\n--- Estado de Cuenta Tarjeta " + numeroUnico + " ---");
        System.out.println("Límite de Crédito: $" + limite);
        System.out.println("Saldo Actual: $" + saldoDispoible);
        System.out.println("\n--- Transacciones ---");
        for (Transaccion transaccion : transacciones) {
            System.out.println(transaccion); // Asumimos que la clase Transaccion tiene un toString() útil
        }
        System.out.println("-----------------------");
    }

}
