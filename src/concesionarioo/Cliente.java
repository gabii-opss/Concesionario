/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package concesionarioo;

import javax.swing.*;

/**
 *
 * @author gabri
 */
public class Cliente extends Persona {
    private String telefono;
    private String correo;
    private String forma_pago;
    private Vehiculo carro_comprados;
    private Vehiculo carro_deseado;
    public Asesor asesor_asignado;
    public String estado;


    public Cliente(String telefono, String correo, Asesor asesor, String estado,
                   String nombre, String apellido, String tipoid, String id) {
        super(nombre, apellido, tipoid, id);



        this.telefono = telefono;
        this.correo = correo;
        this.asesor_asignado = asesor;
        this.estado = estado;
    }



    public boolean esPotencialComprador() {
        return estado != null &&
                (estado.equalsIgnoreCase("PREVENTA") ||
                        estado.equalsIgnoreCase("PREVENTA_CON_COMPRA"));
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Vehiculo getCarro_deseado() {
        return carro_deseado;
    }

    public Asesor getAsesor_asignado() {
        return asesor_asignado;
    }

    public void setAsesor_asignado(Asesor asesor_asignado) {
        this.asesor_asignado = asesor_asignado;
    }
    

    public void setCarro_deseado(Vehiculo carro_deseado) {
        this.carro_deseado = carro_deseado;
    }
    

    public String getTelefono() {
        return telefono;
    }

    public String getCorreo() {
        return correo;
    }

    public String getForma_pago() {
        return forma_pago;
    }

    public Vehiculo getCarro_comprados() {
        return carro_comprados;
    }



    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public void setForma_pago(String forma_pago) {
        this.forma_pago = forma_pago;
    }

    public void setCarro_comprados(Vehiculo carro_comprados) {
        this.carro_comprados = carro_comprados;
    }

    public boolean haComprado() {
        return carro_comprados != null;
    }


    @Override
    public void mostrarInfo() {
        JOptionPane.showMessageDialog(null,
                "👤 CLIENTE:\n" +
                        "Nombre: " + getNombre() + " " + getApellido() + "\n" +
                        "ID: " + getId() + "\n" +
                        "Teléfono: " + telefono + "\n" +
                        "Correo: " + correo + "\n" +
                        "Estado: " + (estado != null ? estado : "No definido") + "\n" +
                        "Asesor Asignado: " +
                        (asesor_asignado != null ? asesor_asignado.getNombre() : "No asignado") + "\n" +
                        "Vehículo Deseado: " +
                        (carro_deseado != null ? carro_deseado.getModelo() : "Ninguno") + "\n" +
                        "Vehículo Comprado: " +
                        (carro_comprados != null ? carro_comprados.getModelo() : "Ninguno"),
                "Información del Cliente",
                JOptionPane.INFORMATION_MESSAGE
        );
    }

    @Override
    public String toString() {
        return "Cliente: " + getNombre() + " " + getApellido() +
                " | ID: " + getId() +
                " | Estado: " + estado +
                " | Asesor: " + (asesor_asignado != null ? asesor_asignado.getNombre() : "No asignado");
    }

}
