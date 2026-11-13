package Servicio;

import Pagos.EstrategiaPago;
import Pagos.FabricaEstrategiaPago;
import concesionarioo.Compra;

/**
 * Contexto del patrón Strategy.
 *
 * Arma la factura base de una compra y delega el cálculo específico a la
 * {@link EstrategiaPago} que entrega la fábrica. Ya no contiene ningún
 * switch por tipo de pago: agregar una forma de pago no obliga a modificar
 * esta clase.
 */
public class CalculadoraPago implements iCalculadoraPago {

    private final FabricaEstrategiaPago fabrica;

    public CalculadoraPago() {
        this(new FabricaEstrategiaPago());
    }

    public CalculadoraPago(FabricaEstrategiaPago fabrica) {
        this.fabrica = fabrica;
    }

    @Override
    public DatosFactura calcularFactura(Compra compra, DatosAdicionales datos) {
        EstrategiaPago estrategia = fabrica.obtener(compra.getTipoPago());
        DatosFactura factura = crearFacturaBase(compra, estrategia.getNombre());
        estrategia.aplicar(factura, datos);
        return factura;
    }

    private DatosFactura crearFacturaBase(Compra compra, String tipoPago) {
        String nombreCompleto = compra.getCliente().getNombre() + " "
                + compra.getCliente().getApellido();
        String infoVehiculo = compra.getVehiculo().getMarca() + " "
                + compra.getVehiculo().getModelo();

        return new DatosFactura(
                tipoPago,
                nombreCompleto,
                compra.getAsesor().getNombre(),
                infoVehiculo,
                compra.getFecha(),
                compra.getVehiculo().getCosto(),
                compra.getIdCompra());
    }
}
