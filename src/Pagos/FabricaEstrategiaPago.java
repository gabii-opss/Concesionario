package Pagos;

import java.text.Normalizer;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

/**
 * PATRÓN FACTORY (fábrica simple con registro).
 *
 * Dado el texto de la forma de pago ("Crédito", "CREDITO", " leasing "...)
 * devuelve la {@link EstrategiaPago} correspondiente. Centraliza en un solo
 * lugar la decisión que antes estaba repartida en varios switch.
 *
 * Es abierta a extensión: {@link #registrar(EstrategiaPago)} permite sumar una
 * forma de pago nueva sin modificar la calculadora ni la interfaz.
 */
public class FabricaEstrategiaPago {

    private final Map<String, EstrategiaPago> estrategias = new LinkedHashMap<>();

    public FabricaEstrategiaPago() {
        registrar(new EstrategiaContado());
        registrar(new EstrategiaCredito());
        registrar(new EstrategiaLeasing());
    }

    public void registrar(EstrategiaPago estrategia) {
        estrategias.put(normalizar(estrategia.getNombre()), estrategia);
    }

    /**
     * @throws PagoInvalidoException si el tipo es nulo, vacío o no está registrado
     */
    public EstrategiaPago obtener(String tipoPago) {
        String clave = normalizar(tipoPago);
        EstrategiaPago estrategia = estrategias.get(clave);
        if (estrategia == null) {
            throw new PagoInvalidoException("Forma de pago no soportada: '" + tipoPago
                    + "'. Disponibles: " + tiposDisponibles());
        }
        return estrategia;
    }

    public Set<String> tiposDisponibles() {
        return estrategias.keySet();
    }

    /** Pasa a mayúsculas y quita tildes: " Crédito " -> "CREDITO". */
    static String normalizar(String texto) {
        if (texto == null) {
            return "";
        }
        String sinTildes = Normalizer.normalize(texto.trim(), Normalizer.Form.NFD)
                .replaceAll("\\p{M}", "");
        return sinTildes.toUpperCase();
    }
}
