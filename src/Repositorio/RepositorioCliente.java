package Repositorio;

import concesionarioo.Cliente;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


public class RepositorioCliente implements iRepoCliente {
    private final List<Cliente> clientes = new ArrayList<>();

    @Override
    public void agregar(Cliente cliente) {
        if (cliente != null && buscarPorId(cliente.getId()) == null) {
            clientes.add(cliente);
        }
    }

    @Override
    public Cliente buscarPorId(String id) {
        return clientes.stream()
                .filter(c -> c.getId().equalsIgnoreCase(id))
                .findFirst()
                .orElse(null);
    }

    @Override
    public List<Cliente> obtenerTodos() {
        return new ArrayList<>(clientes); // Retorna copia para proteger la lista interna
    }
    @Override
    public List<Cliente> buscarPorEstado(String estado) {
        return clientes.stream()
                .filter(c -> c.getEstado() != null &&
                        c.getEstado().equalsIgnoreCase(estado))
                .collect(Collectors.toList());
    }




    // Métodos adicionales útiles
    public int contarClientes() {
        return clientes.size();
    }

    public List<Cliente> buscarPorNombre(String nombre) {
        return clientes.stream()
                .filter(c -> c.getNombre().toLowerCase().contains(nombre.toLowerCase()) ||
                        c.getApellido().toLowerCase().contains(nombre.toLowerCase()))
                .collect(Collectors.toList());
    }

    public List<Cliente> obtenerClientesQueCompraron() {
        return clientes.stream()
                .filter(c -> c.haComprado())
                .collect(Collectors.toList());
    }

    public List<Cliente> obtenerClientesPotenciales() {
        return clientes.stream()
                .filter(c -> c.esPotencialComprador())
                .collect(Collectors.toList());
    }

    public boolean existeCliente(String id) {
        return buscarPorId(id) != null;
    }
}