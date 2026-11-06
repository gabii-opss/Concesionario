package Pagos;

import concesionarioo.*;
import Repositorio.*;
import javax.swing.*;
import java.time.LocalDate;

public class PagoContado extends Pago {
    public PagoContado(Cliente cliente, Asesor asesor, Vehiculo vehiculo) {
        super(cliente, asesor, vehiculo);
    }

    @Override
    public void procesarPago() {
        JOptionPane.showMessageDialog(null, "Pago al contado realizado exitosamente.");
    }

    @Override
    public void generarFactura() {
        JOptionPane.showMessageDialog(null,
                "FACTURA CONTADO\nCliente: " + cliente.getNombre() +
                        "\nVehículo: " + vehiculo.getModelo() +
                        "Fecha de emisión: " + LocalDate.now() + "\n" +

                        "\nCosto: $" + vehiculo.getCosto()+
                        "\nAsesor: " + asesor.getNombre());


    }
}
