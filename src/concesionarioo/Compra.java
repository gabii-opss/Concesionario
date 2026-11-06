/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package concesionarioo;

import java.time.LocalDate;

/**
 *
 * @author gabri
 */
public class Compra {
    private int idCompra;
    private Vehiculo vehiculo;
    private Cliente cliente;
    LocalDate fecha;
    private Asesor asesor;
    private String tipoPago;

    public Compra() {
    }

    public Compra(int idCompra, Vehiculo vehiculo, Cliente cliente, Asesor asesor,
                  String tipoPago, LocalDate fecha) {


        this.idCompra = idCompra;
        this.vehiculo = vehiculo;
        this.cliente = cliente;
        this.asesor = asesor;
        this.tipoPago = tipoPago.toUpperCase();
        this.fecha = fecha;
    }


    public int getIdcompra() {
        return idCompra;
    }

    public void setIdcompra(int idcompra) {
        this.idCompra = idcompra;
    }

    public int getIdCompra() {
        return idCompra;
    }

    public Vehiculo getVehiculo() {
        return vehiculo;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public Asesor getAsesor() {
        return asesor;
    }
    public String getTipoPago() {
        return tipoPago;
    }
    public void setTipoPago(String tipoPago) {
        this.tipoPago = tipoPago;
    }
    public LocalDate getFecha() {
        return fecha;
    }
    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;

    }

    public void setIdCompra(int idCompra) {
        this.idCompra = idCompra;
    }

    public void setVehiculo(Vehiculo vehiculo) {
        this.vehiculo = vehiculo;
    }
    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
    public void setAsesor(Asesor asesor) {

        this.asesor = asesor;
    }


    @Override
    public String toString() {
        return "Compra{" + "idprestamo=" +  ", carro=" + vehiculo + ", cliente=" + cliente + ", fechap=" + fecha + ", fechad=" + '}';
    }
    
    
}
