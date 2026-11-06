package Servicio;

import concesionarioo.*;
import Repositorio.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class VehiculoService {
    private final iRepoVehiculo repoVehiculo;


    public VehiculoService(iRepoVehiculo repoVehiculo) {
        this.repoVehiculo = repoVehiculo;
    }

    public void registrarVehiculo(String marca, String modelo,
                                  int costo, String paisEnsamblaje, int cantidad) {
        Vehiculo vehiculo = new Vehiculo(marca,costo, modelo,  paisEnsamblaje, cantidad);
        repoVehiculo.agregar(vehiculo);
    }

    public void registrarVehiculo(Vehiculo vehiculo) {
        repoVehiculo.agregar(vehiculo);
    }

    public List<Vehiculo> obtenerCatalogo() {
        return repoVehiculo.obtenerCatalogo();
    }

    public Vehiculo obtenerVehiculoAleatorio() {
        List<Vehiculo> vehiculos = this.obtenerCatalogo();
        if (vehiculos.isEmpty()) return null;
        return vehiculos.get(new Random().nextInt(vehiculos.size()));
    }    public Vehiculo buscarPorIdentificador(String identificador) {
        return repoVehiculo.buscarPorId(identificador);
    }

    public void precargarVehiculos() {
        registrarVehiculo("Chevrolet", "Onix", 45000000, "Brasil",50);
        registrarVehiculo("Renault", "Logan", 52000000, "Colombia",10);
        registrarVehiculo("Mazda", "CX-5", 125000000, "Japon",45);
        registrarVehiculo("Toyota", "Corolla", 95000000, "Japon",30);
        registrarVehiculo("Nissan", "Versa", 68000000, "Mexico",25);
        registrarVehiculo("Ford", "Escape", 145000000, "Estados Unidos",11);
        registrarVehiculo("Hyundai", "Tucson", 115000000, "Corea del Sur",15);
        registrarVehiculo("Kia", "Sportage", 118000000, "Corea del Sur",23);
        registrarVehiculo("Volkswagen", "Jetta", 89000000, "Alemania",5);
        registrarVehiculo("Honda", "CR-V", 135000000, "Japon",44);
        registrarVehiculo("Suzuki", "Swift", 58000000, "Japon",62);
        registrarVehiculo("Mazda", "3", 85000000, "Japon",10);
        registrarVehiculo("Chevrolet", "Spark", 42000000, "Colombia",2);
        registrarVehiculo("Renault", "Sandero", 48000000, "Brasil",42);
        registrarVehiculo("Toyota", "RAV4", 155000000, "Japon",8);
    }
}