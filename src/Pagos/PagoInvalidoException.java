package Pagos;

/**
 * Se lanza cuando una forma de pago no existe o sus datos son inválidos
 * (cuotas negativas, entidad financiera vacía, cuota inicial mayor al valor, etc.).
 * La interfaz gráfica la captura y muestra el mensaje al usuario.
 */
public class PagoInvalidoException extends RuntimeException {

    public PagoInvalidoException(String mensaje) {
        super(mensaje);
    }
}
