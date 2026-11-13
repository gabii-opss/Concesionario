package Pagos;

import static org.junit.Assert.*;

import Servicio.DatosAdicionales;
import Servicio.DatosFactura;
import java.time.LocalDate;
import org.junit.Test;

/** Pruebas de cada estrategia concreta (cálculo y validaciones). */
public class EstrategiasPagoTest {

    private static final double MONTO = 100_000_000;

    private DatosFactura facturaBase(String tipo) {
        return new DatosFactura(tipo, "Ana Gómez", "Carlos", "Mazda 3", LocalDate.of(2026, 11, 6), MONTO);
    }

    @Test
    public void contadoNoGeneraFinanciacion() {
        DatosFactura f = facturaBase("CONTADO");
        new EstrategiaContado().aplicar(f, null);
        assertNull(f.getNumeroCuotas());
        assertNull(f.getValorCuota());
        assertNull(f.getEntidadFinanciera());
    }

    @Test
    public void creditoCalculaCuotaConInteres() {
        DatosFactura f = facturaBase("CREDITO");
        new EstrategiaCredito().aplicar(f, new DatosAdicionales(24, null));
        assertEquals(Integer.valueOf(24), f.getNumeroCuotas());
        // Cuota francesa: 100.000.000 al 2 % mensual a 24 meses ≈ 5.287.110
        assertEquals(5_287_110, f.getValorCuota(), 10);
        assertTrue(f.getValorCuota() * 24 > MONTO);
    }

    @Test
    public void creditoUsaDoceCuotasPorDefecto() {
        DatosFactura f = facturaBase("CREDITO");
        new EstrategiaCredito().aplicar(f, null);
        assertEquals(Integer.valueOf(12), f.getNumeroCuotas());
    }

    @Test(expected = PagoInvalidoException.class)
    public void creditoRechazaCuotasNegativas() {
        new EstrategiaCredito().aplicar(facturaBase("CREDITO"), new DatosAdicionales(-3, null));
    }

    @Test(expected = PagoInvalidoException.class)
    public void creditoRechazaCuotasEnCero() {
        new EstrategiaCredito().aplicar(facturaBase("CREDITO"), new DatosAdicionales(0, null));
    }

    @Test
    public void leasingUsaDiezPorCientoComoInicialPorDefecto() {
        DatosFactura f = facturaBase("LEASING");
        new EstrategiaLeasing().aplicar(f, new DatosAdicionales(36, "Bancolombia"));
        assertEquals(10_000_000, f.getCuotaInicial(), 0.01);
        assertEquals("Bancolombia", f.getEntidadFinanciera());
        assertEquals(Integer.valueOf(36), f.getNumeroCuotas());
        assertTrue(f.getValorCuota() > 0);
    }

    @Test
    public void leasingRespetaLaCuotaInicialIndicada() {
        DatosFactura f = facturaBase("LEASING");
        new EstrategiaLeasing().aplicar(f, new DatosAdicionales(12, "Davivienda", 30_000_000.0));
        assertEquals(30_000_000, f.getCuotaInicial(), 0.01);
    }

    @Test(expected = PagoInvalidoException.class)
    public void leasingExigeEntidadFinanciera() {
        new EstrategiaLeasing().aplicar(facturaBase("LEASING"), new DatosAdicionales(12, "  "));
    }

    @Test(expected = PagoInvalidoException.class)
    public void leasingSinDatosLanzaExcepcion() {
        new EstrategiaLeasing().aplicar(facturaBase("LEASING"), null);
    }

    @Test(expected = PagoInvalidoException.class)
    public void leasingRechazaInicialMayorAlValor() {
        new EstrategiaLeasing().aplicar(facturaBase("LEASING"),
                new DatosAdicionales(12, "Bancolombia", MONTO + 1));
    }

    @Test
    public void tasaCeroDividePorCuotas() {
        assertEquals(1_000, CalculoFinanciero.cuotaMensual(12_000, 12, 0), 0.0001);
    }
}
