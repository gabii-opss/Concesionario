package Pagos;

import static org.junit.Assert.*;

import org.junit.Test;

/** Pruebas de la fábrica: selección correcta de la estrategia y errores. */
public class FabricaEstrategiaPagoTest {

    private final FabricaEstrategiaPago fabrica = new FabricaEstrategiaPago();

    @Test
    public void devuelveLaEstrategiaCorrectaPorTipo() {
        assertTrue(fabrica.obtener("CONTADO") instanceof EstrategiaContado);
        assertTrue(fabrica.obtener("CREDITO") instanceof EstrategiaCredito);
        assertTrue(fabrica.obtener("LEASING") instanceof EstrategiaLeasing);
    }

    @Test
    public void ignoraTildesMayusculasYEspacios() {
        assertTrue(fabrica.obtener("CRÉDITO") instanceof EstrategiaCredito);
        assertTrue(fabrica.obtener(" crédito ") instanceof EstrategiaCredito);
        assertTrue(fabrica.obtener("leasing") instanceof EstrategiaLeasing);
    }

    @Test(expected = PagoInvalidoException.class)
    public void tipoDesconocidoLanzaExcepcion() {
        fabrica.obtener("BITCOIN");
    }

    @Test(expected = PagoInvalidoException.class)
    public void tipoNuloLanzaExcepcion() {
        fabrica.obtener(null);
    }

    @Test
    public void permiteRegistrarUnaNuevaFormaDePago() {
        fabrica.registrar(new EstrategiaPago() {
            @Override public String getNombre() { return "TRUEQUE"; }
            @Override public void aplicar(Servicio.DatosFactura f, Servicio.DatosAdicionales d) { }
        });
        assertEquals("TRUEQUE", fabrica.obtener("trueque").getNombre());
    }
}
