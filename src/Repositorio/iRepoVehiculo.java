package Repositorio;

import concesionarioo.*;
import java.util.List;
import java.util.Map;

public interface iRepoVehiculo extends iRepositorioBase<Vehiculo>,
        iBuscablePorId<Vehiculo, String> {
    // Hereda: agregar, obtenerTodos, buscarPorId
    List<Vehiculo>  obtenerCatalogo();

}