package Servicio;

import static org.junit.Assert.*;

import Pagos.PagoInvalidoException;
import Repositorio.RepositorioCompra;
import concesionarioo.Asesor;
import concesionarioo.Cliente;
import concesionarioo.Compra;
import concesionarioo.Vehiculo;
import org.junit.Before;
import org.junit.Test;

/** Prueba de integración: CompraService + CalculadoraPago + estrategias + repositorio. */
public class CompraServiceTest {

    /** Notificador de prueba: guarda la última factura en lugar de abrir ventanas Swing. */
    static class NotificadorFalso implements iServicioNotificacion {
        DatosFactura ultimaFactura;
        int comprasNotificadas;
        public void notificarBienvenida(Cliente c, Asesor a) { }
        public void notificarPreventa(Cliente c, Asesor a) { }
        public void notificarCompraExitosa(Cliente c, String v) { comprasNotificadas++; }
        public void mostrarFactura(DatosFactura f) { ultimaFactura = f; }
    }

    private RepositorioCompra repo;
    private NotificadorFalso notificador;
    private CompraService servicio;
    private Cliente cliente;
    private Asesor asesor;
    private Vehiculo vehiculo;

    @Before
    public void preparar() {
        repo = new RepositorioCompra();
        notificador = new NotificadorFalso();
        servicio = new CompraService(repo, new CalculadoraPago(), notificador);
        asesor = new Asesor("Carlos", "Moya", "CC", "123", 0, 0, "Ventas");
        cliente = new Cliente("300", "ana@mail.com", asesor, "COMPRA", "Ana", "Gómez", "CC", "1001");
        vehiculo = new Vehiculo("Mazda", 85_000_000, "3", "Japon", 2);
    }

    @Test
    public void compraAlContadoSeRegistraYDescuentaInventario() {
        Compra c = servicio.registrarCompra(cliente, vehiculo, asesor, "CONTADO", null);
        assertEquals(1, repo.contarCompras());
        assertEquals(1, c.getIdCompra());
        assertEquals(1, vehiculo.getCantidad());
        assertEquals(1, asesor.getCantidad_ventas());
        assertEquals("CONTADO", notificador.ultimaFactura.getTipoFactura());
        assertEquals(1, notificador.comprasNotificadas);
    }

    @Test
    public void compraACreditoGeneraFacturaConCuotas() {
        servicio.registrarCompra(cliente, vehiculo, asesor, "CRÉDITO", new DatosAdicionales(12, null));
        assertEquals(Integer.valueOf(12), notificador.ultimaFactura.getNumeroCuotas());
        assertTrue(notificador.ultimaFactura.getValorCuota() > 0);
    }

    @Test
    public void compraLeasingGeneraFacturaConEntidad() {
        servicio.registrarCompra(cliente, vehiculo, asesor, "LEASING",
                new DatosAdicionales(36, "Bancolombia", 10_000_000.0));
        assertEquals("Bancolombia", notificador.ultimaFactura.getEntidadFinanciera());
    }

    @Test
    public void datosInvalidosNoGuardanLaCompraNiDescuentanInventario() {
        try {
            servicio.registrarCompra(cliente, vehiculo, asesor, "CREDITO", new DatosAdicionales(-1, null));
            fail("Debía lanzar PagoInvalidoException");
        } catch (PagoInvalidoException esperado) {
            assertEquals(0, repo.contarCompras());
            assertEquals(2, vehiculo.getCantidad());
            assertEquals(0, asesor.getCantidad_ventas());
        }
    }

    @Test(expected = PagoInvalidoException.class)
    public void formaDePagoDesconocidaLanzaExcepcion() {
        servicio.registrarCompra(cliente, vehiculo, asesor, "BITCOIN", null);
    }

    @Test(expected = IllegalStateException.class)
    public void sinInventarioNoSePermiteLaCompra() {
        vehiculo.setCantidad(0);
        servicio.registrarCompra(cliente, vehiculo, asesor, "CONTADO", null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void clienteNuloLanzaExcepcion() {
        servicio.registrarCompra(null, vehiculo, asesor, "CONTADO", null);
    }
}
