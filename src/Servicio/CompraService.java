package Servicio;

import concesionarioo.*;
import Repositorio.*;
import java.time.LocalDate;
import java.util.List;


public class CompraService {
    private final iRepoCompra repoCompra;
    private final iCalculadoraPago calculadoraPago;
    private final iServicioNotificacion notificacionService;

    public CompraService(iRepoCompra repoCompra,
                         iCalculadoraPago calculadoraPago,
                         iServicioNotificacion notificacionService) {
        this.repoCompra = repoCompra;
        this.calculadoraPago = calculadoraPago;
        this.notificacionService = notificacionService;
    }

    /** Registra la compra asignando automáticamente el siguiente id disponible. */
    public Compra registrarCompra(Cliente cliente, Vehiculo vehiculo,
                                  Asesor asesor, String tipoPago,
                                  DatosAdicionales datosAdicionales) {
        return registrarCompra(cliente, vehiculo, asesor, tipoPago, datosAdicionales,
                repoCompra.obtenerTodos().size() + 1);
    }

    /**
     * Registra una compra: valida los datos, calcula la factura con la
     * estrategia de pago elegida y, solo si todo es válido, guarda la compra.
     *
     * @throws IllegalArgumentException si falta cliente, vehículo, asesor o tipo de pago
     * @throws IllegalStateException    si el vehículo no tiene unidades disponibles
     * @throws Pagos.PagoInvalidoException si la forma de pago o sus datos no son válidos
     */
    public Compra registrarCompra(Cliente cliente, Vehiculo vehiculo,
                                  Asesor asesor, String tipoPago,
                                  DatosAdicionales datosAdicionales, int idCompra) {
        if (cliente == null || vehiculo == null || asesor == null) {
            throw new IllegalArgumentException("Cliente, vehículo y asesor son obligatorios.");
        }
        if (tipoPago == null || tipoPago.isBlank()) {
            throw new IllegalArgumentException("Debe seleccionar una forma de pago.");
        }
        if (vehiculo.getCantidad() <= 0) {
            throw new IllegalStateException("No hay unidades disponibles de " + vehiculo.getIdentificador() + ".");
        }

        Compra compra = new Compra(idCompra, vehiculo, cliente, asesor, tipoPago, LocalDate.now());

        // Se calcula primero: si los datos de pago son inválidos se lanza la
        // excepción y no se guarda nada ni se descuenta inventario.
        DatosFactura factura = calculadoraPago.calcularFactura(compra, datosAdicionales);

        repoCompra.agregar(compra);
        vehiculo.quitar_vehiculo();
        asesor.registrarVenta();
        cliente.setCarro_comprados(vehiculo);
        cliente.setEstado("COMPRA");

        notificacionService.mostrarFactura(factura);
        notificacionService.notificarCompraExitosa(cliente, vehiculo.getMarca() + " " + vehiculo.getModelo());
        return compra;
    }

    public List<Compra> obtenerTodas() {
    return repoCompra.obtenerTodos();
}

public List<Compra> obtenerPorCliente(String idCliente) {
    return repoCompra.obtenerPorCliente(idCliente);
}

public List<Compra> obtenerPorAsesor(String nombreAsesor) {
    return repoCompra.obtenerPorAsesor(nombreAsesor);
}

public List<Compra> obtenerPorTipoPago(String tipoPago) {
    return repoCompra.obtenerPorTipoPago(tipoPago);
}

public Compra buscarPorId(int id) {
    return repoCompra.buscarPorId(id);
}
}