package Pagos;

public class RegistroPago<T extends Pago> {
    public void registrarPago(T pago) {
        pago.procesarPago();
        pago.generarFactura();
    }
}
