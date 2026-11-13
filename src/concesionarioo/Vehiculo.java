/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package concesionarioo;

/**
 *
 * @author gabri
 */
public class Vehiculo {
    private String marca;
    private int costo;
    private String modelo;
    private String pais_ensamblaje;
    private String Color;
    private int cantidad;

    public Vehiculo() {
    }

    public Vehiculo(String marca, int costo, String modelo, String pais_ensamblaje, int cantidad) {
        this.marca = marca;
        this.costo = costo;
        this.modelo = modelo;
        this.cantidad=cantidad;
        this.pais_ensamblaje = pais_ensamblaje;
    
    }

    public String getColor() {
        return Color;
    }

    public void setColor(String Color) {
        this.Color = Color;
    }

    public String getIdentificador() {
        return marca + "-" + modelo;
    }



    public String getMarca() {
        return marca;
    }

    public int getCosto() {
        return costo;
    }

    public String getModelo() {
        return modelo;
    }

    public String getPais_ensamblaje() {
        return pais_ensamblaje;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public void setCosto(int costo) {
        this.costo = costo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public void setPais_ensamblaje(String pais_ensamblaje) {
        this.pais_ensamblaje = pais_ensamblaje;
    }
    public int getCantidad() {
        return cantidad;
    }
    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
    public int quitar_vehiculo() {
        return cantidad--;
    }


    @Override
    public String toString() {
        return  "Marca: " + marca + ", \nCosto: " + costo + ", \nModelo=" + modelo + ", \nPais de ensamblaje=" + pais_ensamblaje ;
    }
    
    
    
    
    
}
