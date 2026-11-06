package Pagos;

import concesionarioo.*;


import javax.swing.*;
import java.text.DecimalFormat;
import java.time.LocalDate;

/**
 * Clase que representa un pago por modalidad de LEASING.
 * Hereda de la clase abstracta Pago e implementa sus métodos.
 */
public class PagoLeasing extends Pago {

    private String entidadFinanciera;
    private int cuotas;
    private double cuotaInicial;
    private int duracionMeses;
    private final DecimalFormat df = new DecimalFormat("#,###.00");

    public PagoLeasing(Vehiculo vehiculo, Cliente cliente, Asesor asesor,
                       String entidadFinanciera, int cuotas, double cuotaInicial, int duracionMeses) {
        super(cliente, asesor,vehiculo);
        this.entidadFinanciera = entidadFinanciera;
        this.cuotas = cuotas;
        this.cuotaInicial = cuotaInicial;
        this.duracionMeses = duracionMeses;
    }

    @Override
    public void procesarPago() {
        JOptionPane.showMessageDialog(null,
                "Procesando pago por LEASING para el cliente " + cliente.getNombre() +
                        "\nEntidad financiera: " + entidadFinanciera +
                        "\nCuotas: " + cuotas +
                        "\nDuración del contrato: " + duracionMeses + " meses");
    }

    @Override
    public void generarFactura() {
        double valorVehiculo = vehiculo.getCosto();
        double valorCuota = (valorVehiculo - cuotaInicial) / cuotas;
        double opcionCompra = valorVehiculo * 0.10;

        JOptionPane.showMessageDialog(null,
                "================= FACTURA LEASING =================\n" +
                        "Cliente:                           " + cliente.getNombre() + " " + cliente.getApellido() + "\n" +
                        "Vehículo:                          " + vehiculo.getMarca() + " " + vehiculo.getModelo() + "\n" +
                        "Costo total:                       $" + df.format(valorVehiculo) + "\n" +
                        "Entidad financiera:            " + entidadFinanciera + "\n" +
                        "Duración contrato:              " + duracionMeses + " meses\n" +
                        "Cuota inicial:                    $" + df.format(cuotaInicial) + "\n" +
                        "Valor por cuota:                  $" + df.format(valorCuota) + "\n" +
                        "Opción de compra (10%):       $" + df.format(opcionCompra) + "\n" +
                        "Fecha de emisión:          " + LocalDate.now() + "\n" +
                        "Asesor:                            " + asesor.getNombre()+
                        "=======================================================");
    }

    // ================== Getters & Setters ==================

    public String getEntidadFinanciera() {
        return entidadFinanciera;
    }

    public void setEntidadFinanciera(String entidadFinanciera) {
        this.entidadFinanciera = entidadFinanciera;
    }

    public int getCuotas() {
        return cuotas;
    }

    public void setCuotas(int cuotas) {
        this.cuotas = cuotas;
    }

    public double getCuotaInicial() {
        return cuotaInicial;
    }

    public void setCuotaInicial(double cuotaInicial) {
        this.cuotaInicial = cuotaInicial;
    }

    public int getDuracionMeses() {
        return duracionMeses;
    }

    public void setDuracionMeses(int duracionMeses) {
        this.duracionMeses = duracionMeses;
    }
}
