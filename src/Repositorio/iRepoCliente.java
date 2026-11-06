package Repositorio;

import concesionarioo.*;
import java.util.List;
public interface iRepoCliente extends iRepositorioBase<Cliente>,
        iBuscablePorId<Cliente, String> {
    // Hereda: agregar, obtenerTodos, buscarPorId
    List<Cliente> buscarPorEstado(String estado);


}