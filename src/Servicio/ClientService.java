package Servicio;

import concesionarioo.*;
import Repositorio.*;
import java.time.LocalDate;
import java.util.List;

public class ClientService {
    private final iRepoCliente repoCliente;
    private final iRepoAsesor repoAsesor;
    private final iServicioNotificacion notificacionService;

    public ClientService(iRepoCliente repoCliente,
                          iRepoAsesor repoAsesor,
                          iServicioNotificacion notificacionService) {
        this.repoCliente = repoCliente;
        this.repoAsesor = repoAsesor;
        this.notificacionService = notificacionService;
    }

    public Cliente registrarCliente(String nombre, String apellido, String tipoid,
                                    String id, String telefono, String correo,
                                    String estado) {
        Asesor asesor = repoAsesor.obtenerAleatorio();

        if (asesor == null) {
            throw new IllegalStateException("No hay asesores disponibles");
        }
        Cliente cliente = new Cliente(telefono,correo,asesor,estado,nombre,apellido,tipoid,id);

        repoCliente.agregar(cliente);

        // Registrar preventa si aplica
        if (estado.equalsIgnoreCase("Preventa")) {
            asesor.registrarPreventa();
            notificacionService.notificarPreventa(cliente, asesor);
        } else {
            notificacionService.notificarBienvenida(cliente, asesor);
        }

        return cliente;
    }

    public Cliente buscarCliente(String id) {
        return repoCliente.buscarPorId(id);
    }

    public List<Cliente> obtenerTodos() {
        return repoCliente.obtenerTodos();
    }

    public List<Cliente> obtenerClientesPreventa() {
        return repoCliente.buscarPorEstado("PREVENTA");
    }



    public void actualizarEstado(Cliente cliente, String nuevoEstado) {
        cliente.setEstado(nuevoEstado);
    }
}