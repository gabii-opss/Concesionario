package Servicio;

import concesionarioo.Compra;

public interface iCalculadoraPago {
    DatosFactura calcularFactura(Compra compra, DatosAdicionales datosAdicionales);
}