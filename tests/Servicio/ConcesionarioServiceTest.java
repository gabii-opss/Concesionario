package Servicio;

import static org.junit.Assert.*;

import Repositorio.*;
import concesionarioo.Asesor;
import concesionarioo.Cliente;
import concesionarioo.Compra;
import concesionarioo.Vehiculo;
import org.junit.Before;
import org.junit.Test;

/** Pruebas de las búsquedas y del flujo de compra a través de la fachada ConcesionarioService. */
public class ConcesionarioServiceTest {

    /** Notificador de prueba: evita abrir ventanas Swing y guarda la última factura. */
    static class NotificadorFalso implements iServicioNotificacion {
        DatosFactura ultimaFactura;
        public void notificarBienvenida(Cliente c, Asesor a) { }
        public void notificarPreventa(Cliente c, Asesor a) { }
        public void notificarCompraExitosa(Cliente c, String v) { }
        public void mostrarFactura(DatosFactura f) { ultimaFactura = f; }
    }

    private ConcesionarioService concesionario;
    private NotificadorFalso notificador;

    @Before
    public void preparar() {
        iRepoAsesor repoAsesor = new RepositorioAsesor();
        notificador = new NotificadorFalso();
        CompraService compraService = new CompraService(new RepositorioCompra(), new CalculadoraPago(), notificador);
        ClientService clientService = new ClientService(new RepositorioCliente(), repoAsesor, notificador);
        VehiculoService vehiculoService = new VehiculoService(new RepossitorioVehiculo());
        concesionario = new ConcesionarioService(clientService, vehiculoService, compraService, repoAsesor);
    }

    @Test
    public void buscaVehiculoPorModeloSinDistinguirMayusculas() {
        Vehiculo v = concesionario.buscarVehiculoPorModelo("onix");
        assertNotNull(v);
        assertEquals("Chevrolet", v.getMarca());
    }

    @Test
    public void buscaVehiculoPorMarca() {
        assertNotNull(concesionario.buscarVehiculoPorMarca("TOYOTA"));
    }

    @Test
    public void busquedaDeVehiculoInexistenteDevuelveNull() {
        assertNull(concesionario.buscarVehiculoPorModelo("NoExiste"));
        assertNull(concesionario.buscarVehiculoPorMarca("NoExiste"));
    }

    @Test
    public void buscaAsesorPorNombre() {
        assertNotNull(concesionario.buscarAsesorPorNombre("carlos"));
        assertNull(concesionario.buscarAsesorPorNombre("Nadie"));
    }

    @Test
    public void buscaClienteRegistradoPorId() {
        concesionario.registrarCliente("Ana", "Gómez", "CC", "1001", "300", "ana@mail.com", "VENTA");
        assertNotNull(concesionario.buscarClientePorId("1001"));
        assertNull(concesionario.buscarClientePorId("9999"));
    }

    @Test
    public void compraActualizaInventarioClienteYPermiteBuscarla() {
        Cliente cliente = concesionario.registrarCliente("Ana", "Gómez", "CC", "1001", "300", "ana@mail.com", "VENTA");
        Vehiculo onix = concesionario.buscarVehiculoPorModelo("Onix");
        int antes = onix.getCantidad();

        Compra compra = concesionario.registrarCompra(cliente, onix, cliente.getAsesor_asignado(), "CONTADO", null);

        assertEquals(antes - 1, onix.getCantidad());
        assertEquals("COMPRA", cliente.getEstado());
        assertSame(onix, cliente.getCarro_comprados());
        assertSame(compra, concesionario.buscarCompraPorId(compra.getIdCompra()));
        assertEquals(1, concesionario.buscarComprasDeCliente("1001").size());
        // El número de factura es el id real de la compra (no uno aleatorio)
        assertEquals(compra.getIdCompra(), notificador.ultimaFactura.getIdFactura());
    }

    @Test
    public void buscarCompraInexistenteDevuelveNull() {
        assertNull(concesionario.buscarCompraPorId(12345));
    }

    @Test
    public void losIdsDeCompraSonSecuencialesYUnicos() {
        Cliente cliente = concesionario.registrarCliente("Ana", "Gómez", "CC", "1001", "300", "ana@mail.com", "VENTA");
        Vehiculo v = concesionario.buscarVehiculoPorModelo("Onix");
        Compra c1 = concesionario.registrarCompra(cliente, v, cliente.getAsesor_asignado(), "CONTADO", null);
        Compra c2 = concesionario.registrarCompra(cliente, v, cliente.getAsesor_asignado(), "CONTADO", null);
        assertEquals(1, c1.getIdCompra());
        assertEquals(2, c2.getIdCompra());
    }
}
