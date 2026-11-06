/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package concesionarioo;

import javax.swing.JOptionPane;

/**
 *
 * @author gabri
 */
public class Asesor extends Persona  {

    private String atencion;
    private int cantidad_ventas;
    private int cantidad_preventas;
    


    public Asesor(String nombre, String apellido, String tipoid, String id, int cantidad_preventas, int cantidad_ventas, String atencion) {
        super(nombre, apellido, tipoid, id);
        this.cantidad_preventas = cantidad_preventas;
        this.atencion = atencion;
        this.cantidad_ventas = cantidad_ventas;
    }
    public Asesor(String id, String nombre, String atencion) {
        super(nombre, "", "CC", id); // puedes dejar el tipoid fijo si no lo usas
        this.atencion = atencion;
        this.cantidad_ventas = 0;
        this.cantidad_preventas = 0;
    }

    public int getCantidad_preventas() {
        return cantidad_preventas;
    }

    public void setCantidad_preventas(int cantidad_preventas) {
        this.cantidad_preventas = cantidad_preventas;
    }

    public String getAtencion() {
        return atencion;
    }

    public int getCantidad_ventas() {
        return cantidad_ventas;
    }

    public void setAtencion(String atencion) {
        this.atencion = atencion;
    }

    public void setCantidad_ventas(int cantidad_ventas) {
        this.cantidad_ventas = cantidad_ventas;
    }
    public void registrarVenta() {
        this.cantidad_ventas++;
    }
    public void registrarPreventa() {
        this.cantidad_preventas++;
    }
    
    public void atender_cliente(Cliente cliente){
        JOptionPane.showMessageDialog(null," 'Hola " +
                cliente.getNombre() + ", bienvenid@ a nuestro sistema de compra. Sere tu asistente mi nombre es  [" +nombre+ "]  \n                      "
                        + "        PULSA OK PARA INICIAR EL PROCESOR DE COMPRA ");
        
    }
     public void atender_cliente_preventa(Cliente cliente){
        JOptionPane.showMessageDialog(null," 'Hola " +
                cliente.getNombre() + ", bienvenid@   mi nombre es  [" +nombre+ "]  \n                      "
                + "HE GUARDADO TU INFO CON EXITO <3 ");
        
    }
    public int getTotalInteracciones() {
        return cantidad_ventas + cantidad_preventas;
    }
    public boolean estaActivo() {
        return getTotalInteracciones() > 0;
    }



    @Override
    public void mostrarInfo() {
        JOptionPane.showMessageDialog(null,
                "Asesor: " + getNombre() + " " + getApellido() +
                        "\nTipo ID: " + getTipoid() +
                        "\nID: " + getId() +
                        "\nAtención: " + atencion +
                        "\nVentas: " + cantidad_ventas +
                        "\nPreventas: " + cantidad_preventas);
    }
    



    

    
    
}
