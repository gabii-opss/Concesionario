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

    public Compra registrarCompra(Cliente cliente, Vehiculo vehiculo,
                                  Asesor asesor,String tipoPago,
                                  DatosAdicionales datosAdicionales, int idCompra) {
        // Validar disponibilidad del vehículo



    // Crear compra

    Compra compra = new Compra(idCompra, vehiculo,cliente, asesor,
            tipoPago, LocalDate.now());

    // Guardar compra
        repoCompra.agregar(compra);


    //cliente.realizarCompra(vehiculo, tipoPago.name());
        asesor.registrarVenta();

    // Calcular y mostrar factura
    DatosFactura factura = calculadoraPago.calcularFactura(compra, datosAdicionales);
        notificacionService.mostrarFactura(factura);

    // Notificar éxito
    String infoVehiculo = vehiculo.getMarca() + " " + vehiculo.getModelo();
        notificacionService.notificarCompraExitosa(cliente, infoVehiculo);

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