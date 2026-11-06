package Repositorio;

public interface iBuscablePorId<T, ID> {
    T buscarPorId(ID id);
}