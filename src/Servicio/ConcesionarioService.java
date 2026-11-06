package Servicio;

import concesionarioo.*;
import Repositorio.*;
import java.util.List;
import java.util.Random;

public class ConcesionarioService {

    private final ClientService clientService;
    private final VehiculoService vehiculoService;
    private final CompraService compraService;
    private final iRepoAsesor repoAsesor;

    // 🔹 Constructor principal con todos los servicios inyectados
    public ConcesionarioService(ClientService clientService,
                                VehiculoService vehiculoService,
                                CompraService compraService,
                                iRepoAsesor repoAsesor) {
        this.clientService = clientService;
        this.vehiculoService = vehiculoService;
        this.compraService = compraService;
        this.repoAsesor = repoAsesor;

        inicializarDatos();
        precargarAsesores(); // ✅ precargar asesores automáticamente
    }

    // 🔹 Método para precargar asesores (arreglado)
    private void precargarAsesores() {
        if (repoAsesor == null) return; // evitar null pointer

        repoAsesor.agregar(new Asesor("Carlos", "Moya", "Cedula Ciudadania", "123", 0, 0, "Ventas"));
        repoAsesor.agregar(new Asesor("Maria", "Perez", "Cedula Ciudadania", "456", 0, 0, "Finanzas"));
        repoAsesor.agregar(new Asesor("Olga", "Gil", "Cedula Ciudadania", "789", 0, 0, "Créditos"));
    }

    // 🔹 Inicializa los vehículos de prueba
    private void inicializarDatos() {
        vehiculoService.precargarVehiculos();
    }

    // ================= CLIENTES =================

    public Cliente registrarCliente(String nombre, String apellido, String tipoid,
                                    String id, String telefono, String correo,
                                    String estado) {
        return clientService.registrarCliente(nombre, apellido, tipoid, id, telefono, correo, estado);
    }

    public Cliente buscarCliente(String id) {
        return clientService.buscarCliente(id);
    }

    public List<Cliente> obtenerClientes() {
        return clientService.obtenerTodos();
    }

    public List<Cliente> obtenerClientesPreventa() {
        return clientService.obtenerClientesPreventa();
    }

    // ================= VEHÍCULOS =================

    public void registrarVehiculo(Vehiculo vehiculo) {
        vehiculoService.registrarVehiculo(vehiculo);
    }

    public List<Vehiculo> obtenerCatalogo() {
        return vehiculoService.obtenerCatalogo();
    }

    // ================= ASESORES =================

    public void registrarAsesor(Asesor asesor) {
        repoAsesor.agregar(asesor);
    }

    public List<Asesor> obtenerAsesores() {
        return repoAsesor.obtenerTodos();
    }
    public VehiculoService getVehiculoService() {
        return vehiculoService;
    }
    public Vehiculo obtenerVehiculoAleatorio() {
        List<Vehiculo> vehiculos = vehiculoService.obtenerCatalogo();
        if (vehiculos.isEmpty()) return null;
        return vehiculos.get(new Random().nextInt(vehiculos.size()));
    }
    public Asesor asignarAsesorAleatorio() {
        return repoAsesor.obtenerAleatorio();
    }

    // ================= COMPRAS =================

    public Compra registrarCompra(Cliente cliente, Vehiculo vehiculo,
                                  Asesor asesor, String tipoPago,
                                  DatosAdicionales datosAdicionales, int idCompra) {
        return compraService.registrarCompra(cliente, vehiculo, asesor, tipoPago, datosAdicionales, idCompra);
    }

    public List<Compra> obtenerCompras() {
        return compraService.obtenerTodas();
    }

    public List<Compra> obtenerComprasPorCliente(String idCliente) {
        return compraService.obtenerPorCliente(idCliente);
    }

    public List<Compra> obtenerComprasPorAsesor(String nombreAsesor) {
        return compraService.obtenerPorAsesor(nombreAsesor);
    }

    // ================= REPORTES =================

    public String generarReporteClientes() {
        StringBuilder sb = new StringBuilder();
        sb.append("=======================================\n");
        sb.append("      CLIENTES REGISTRADOS\n");
        sb.append("=======================================\n\n");

        List<Cliente> clientes = clientService.obtenerTodos();

        if (clientes.isEmpty()) {
            sb.append("No hay clientes registrados.\n");
        } else {
            sb.append("Total de clientes: ").append(clientes.size()).append("\n\n");
            for (Cliente c : clientes) {
                sb.append(c.toString()).append("\n");
            }
        }

        return sb.toString();
    }

    public String generarReportePreventa() {
        StringBuilder sb = new StringBuilder();
        sb.append("=======================================\n");
        sb.append("      CLIENTES EN PREVENTA\n");
        sb.append("=======================================\n\n");

        List<Cliente> clientesPreventa = clientService.obtenerClientesPreventa();

        if (clientesPreventa.isEmpty()) {
            sb.append("No hay clientes en preventa.\n");
        } else {
            sb.append("Total en preventa: ").append(clientesPreventa.size()).append("\n\n");
            for (Cliente c : clientesPreventa) {
                sb.append(c.toString()).append("\n");
            }
        }

        return sb.toString();
    }

    public String generarReporteAsesores() {
        StringBuilder sb = new StringBuilder();
        sb.append("=======================================\n");
        sb.append("      DESEMPEÑO DE ASESORES\n");
        sb.append("=======================================\n\n");

        List<Asesor> asesores = repoAsesor.obtenerTodos();

        if (asesores.isEmpty()) {
            sb.append("No hay asesores registrados.\n");
        } else {
            for (Asesor a : asesores) {
                sb.append(a.toString()).append("\n");
            }
        }

        return sb.toString();
    }

    public String generarReporteVentas() {
        StringBuilder sb = new StringBuilder();
        sb.append("=======================================\n");
        sb.append("      REPORTE DE VENTAS\n");
        sb.append("=======================================\n\n");

        List<Compra> compras = compraService.obtenerTodas();

        if (compras.isEmpty()) {
            sb.append("No hay ventas registradas.\n");
        } else {
            sb.append("Total de ventas: ").append(compras.size()).append("\n\n");

            double totalVentas = compras.stream()
                    .mapToDouble(c -> c.getVehiculo().getCosto())
                    .sum();

            sb.append(String.format("Valor total de ventas: $%,.0f\n\n", totalVentas));

            for (Compra c : compras) {
                sb.append(c.toString()).append("\n");
            }
        }

        return sb.toString();
    }

    public String generarReporteVentasPorAsesor() {
        StringBuilder sb = new StringBuilder();
        sb.append("=======================================\n");
        sb.append("      VENTAS POR ASESOR\n");
        sb.append("=======================================\n\n");

        List<Asesor> asesores = repoAsesor.obtenerTodos();

        for (Asesor a : asesores) {
            List<Compra> ventasAsesor = compraService.obtenerPorAsesor(a.getNombre());
            double totalAsesor = ventasAsesor.stream()
                    .mapToDouble(c -> c.getVehiculo().getCosto())
                    .sum();

            sb.append(String.format("Asesor: %s\n", a.getNombre()));
            sb.append(String.format("  Ventas: %d\n", a.getCantidad_ventas()));
            sb.append(String.format("  Preventas: %d\n", a.getCantidad_preventas()));
            sb.append(String.format("  Total vendido: $%,.0f\n\n", totalAsesor));
        }

        return sb.toString();
    }

    // ================= OTROS SERVICIOS =================

    public iServicioNotificacion getNotificacionService() {
        return new ServicioNotificacionSwing();
    }

    public ClientService getClientService() { return clientService; }

    public iRepoAsesor getRepoAsesor() { return repoAsesor; }
}
