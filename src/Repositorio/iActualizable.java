package Repositorio;

public interface iActualizable<T, ID> {
    boolean actualizar(ID id, T entidad);
}
