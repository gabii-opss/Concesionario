package Servicio;

import concesionarioo.Cliente;
import concesionarioo.Asesor;

public interface iServicioNotificacion {
    void notificarBienvenida(Cliente cliente, Asesor asesor);
    void notificarPreventa(Cliente cliente, Asesor asesor);
    void notificarCompraExitosa(Cliente cliente, String vehiculo);
    void mostrarFactura(DatosFactura factura);
}