package Repositorio;
import concesionarioo.*;
import java.util.List;
public interface iRepoCompra extends iRepositorioBase<Compra>, iBuscablePorId<Compra, Integer> {
    // Hereda: agregar, obtenerTodos, buscarPorId
    List<Compra> obtenerPorAsesor(String nombreAsesor);
    List<Compra> obtenerPorCliente(String idCliente);
    List<Compra> obtenerPorTipoPago(String tipoPago);
}
