package Repositorio;

import concesionarioo.Asesor;
import concesionarioo.Vehiculo;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


public class RepossitorioVehiculo implements iRepoVehiculo {
    private final List<Vehiculo> vehiculos = new ArrayList<>();

    @Override
    public void agregar(Vehiculo vehiculo) {
        if (vehiculo != null) {
            vehiculos.add(vehiculo);
        }
    }
    @Override
    public List<Vehiculo> obtenerTodos() {
        return new ArrayList<>(vehiculos); // Retorna copia para proteger la lista interna
    }

    @Override
    public List<Vehiculo> obtenerCatalogo() {
        return new ArrayList<>(vehiculos); // Retorna copia para proteger la lista interna
    }


    // Métodos adicionales útiles
    public int contarVehiculos() {
        return vehiculos.size();
    }
    @Override
    public Vehiculo buscarPorId(String identificador) {
        return vehiculos.stream()
                .filter(v -> v.getIdentificador().equalsIgnoreCase(identificador))
                .findFirst()
                .orElse(null);
    }




    public List<String> obtenerMarcasDisponibles() {
        return vehiculos.stream()
                .map(Vehiculo::getMarca)
                .distinct()
                .sorted()
                .collect(Collectors.toList());
    }
}
