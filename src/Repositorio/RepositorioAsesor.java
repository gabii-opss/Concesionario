package Repositorio;
import concesionarioo.Asesor;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
public class RepositorioAsesor implements iRepoAsesor {
    private final List<Asesor> asesores = new ArrayList<>();
    private final Random random = new Random();

    @Override
    public void agregar(Asesor asesor) {
        if (asesor != null && !asesores.contains(asesor)) {
            asesores.add(asesor);
        }
    }

    @Override
    public List<Asesor> obtenerTodos() {
        return new ArrayList<>(asesores); // Retorna copia para proteger la lista interna
    }

    @Override
    public Asesor obtenerAleatorio() {
        if (asesores.isEmpty()) {
            return null;
        }
        return asesores.get(random.nextInt(asesores.size()));
    }

    @Override
    public Asesor buscarPorId(String nombre) {
        return asesores.stream()
                .filter(a -> a.getNombre().equalsIgnoreCase(nombre))
                .findFirst()
                .orElse(null);
    }

    // Métodos adicionales útiles
    public int contarAsesores() {
        return asesores.size();
    }

    public List<Asesor> obtenerAsesoresActivos() {
        return asesores.stream()
                .filter(a -> a.estaActivo())
                .collect(java.util.stream.Collectors.toList());
    }


}