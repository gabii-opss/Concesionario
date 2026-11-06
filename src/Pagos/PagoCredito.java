package Pagos;


import concesionarioo.*;
import Repositorio.*;
import javax.swing.*;
import java.time.LocalDate;

public class PagoCredito extends Pago {
    private int cuotas;

    public PagoCredito(Cliente cliente, Asesor asesor, Vehiculo vehiculo, int cuotas) {
        super(cliente, asesor, vehiculo);
        this.cuotas = cuotas;
    }

    @Override
    public void procesarPago() {
        int valorCuota = vehiculo.getCosto() / cuotas;
        JOptionPane.showMessageDialog(null, "Pago a crédito aprobado.\nValor por cuota: $" + valorCuota);
    }

    @Override
    public void generarFactura() {
        JOptionPane.showMessageDialog(null,
                "=====================FACTURA CRÉDITO=======================\nCliente: " + cliente.getNombre() +
                        "\nVehículo:           " + vehiculo.getModelo() +
                        "\nCosto total:       $" + vehiculo.getCosto() +
                        "\nCuotas:       " + cuotas+
                        "Fecha de emisión:         " + LocalDate.now() + "\n" +
                        "\nAsesor:       " + asesor.getNombre()+
                         "======================================================== ");
    }
}
