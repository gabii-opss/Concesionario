package Servicio;

import concesionarioo.Asesor;
import concesionarioo.Cliente;
import Servicio.DatosFactura;

import javax.swing.JOptionPane;
import java.time.format.DateTimeFormatter;

public class ServicioNotificacionSwing implements iServicioNotificacion {

    @Override
    public void notificarBienvenida(Cliente cliente, Asesor asesor) {
        JOptionPane.showMessageDialog(
                null,
                "CLIENTE REGISTRADO\n" +
                        "\nNombre: " + cliente.getNombre() + " " + cliente.getApellido() +
                        "\nAsesor asignado: " + asesor.getNombre(),
                "Registro Exitoso",
                JOptionPane.INFORMATION_MESSAGE
        );
    }

    @Override
    public void notificarPreventa(Cliente cliente, Asesor asesor) {
        JOptionPane.showMessageDialog(
                null,
                "PREVENTA REALIZADA\n" +
                        "\nCliente: " + cliente.getNombre() + " " + cliente.getApellido() +
                        "\nAsesor encargado: " + asesor.getNombre(),
                "Preventa Registrada",
                JOptionPane.WARNING_MESSAGE
        );
    }

    @Override
    public void notificarCompraExitosa(Cliente cliente, String vehiculo) {
        JOptionPane.showMessageDialog(
                null,
                "COMPRA EXITOSA\n" +
                        "\nCliente: " + cliente.getNombre() + " " + cliente.getApellido() +
                        "\nVehículo comprado: " + vehiculo,
                "Compra Realizada",
                JOptionPane.INFORMATION_MESSAGE
        );
    }

    @Override
    public void mostrarFactura(DatosFactura factura) {

        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        String mensaje =
                "***** FACTURA DE " + factura.getTipoFactura().toUpperCase() + " *****\n\n" +
                        "N.º de compra: " + factura.getIdFactura() + "\n" +
                        "Cliente: " + factura.getNombreCliente() + "\n" +
                        "Asesor: " + factura.getNombreAsesor() + "\n" +
                        "Vehículo: " + factura.getInfoVehiculo() + "\n" +
                        "Fecha: " + factura.getFecha().format(formato) + "\n" +
                        "\nMonto Total: $" + factura.getMontoTotal();

        // Si hay financiación, la agregamos
        if (factura.getEntidadFinanciera() != null) {
            mensaje +=
                    "\n\n--- PLAN DE FINANCIACIÓN ---" +
                            "\nEntidad Financiera: " + factura.getEntidadFinanciera() +
                            "\nCuota Inicial: $" + factura.getCuotaInicial() +
                            "\nNúmero de Cuotas: " + factura.getNumeroCuotas() +
                            "\nValor por Cuota: $" + factura.getValorCuota();
        }

        JOptionPane.showMessageDialog(
                null,
                mensaje,
                "Factura de Compra",
                JOptionPane.PLAIN_MESSAGE
        );
    }
}
