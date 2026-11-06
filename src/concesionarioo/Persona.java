/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package concesionarioo;

/**
 *
 * @author gabri
 */
public abstract class Persona {
    protected String nombre;
    protected String apellido;
    protected String tipoid;
    protected String id;

    public Persona(String nombre, String apellido, String tipoid, String id) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.tipoid = tipoid;
        this.id = id;
    }

    public abstract void mostrarInfo();
    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }



    public String getTipoid() {
        return tipoid;
    }

    public String getId() {
        return id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }



    public void setTipoid(String tipoid) {
        this.tipoid = tipoid;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Persona{" +
                "nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", tipoid='" + tipoid + '\'' +
                ", id='" + id + '\'' +
                '}';
    }// método abstracto que cada subclase implementa
}


