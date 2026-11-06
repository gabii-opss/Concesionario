package Pagos;

import concesionarioo.*;
import Repositorio.*;
import java.util.List;

public abstract class Pago {
    Cliente cliente;
    Asesor asesor;
    Vehiculo vehiculo;

    public Pago(Cliente cliente, Asesor asesor, Vehiculo vehiculo) {
        this.asesor = asesor;
        this.vehiculo = vehiculo;
        this.cliente = cliente;
    }

    public abstract void procesarPago();

    public abstract void generarFactura();
}
