package Repositorio;
import java.util.List;
public interface iRepositorioBase<T> {
    void agregar(T entidad);
    List<T> obtenerTodos();
}
