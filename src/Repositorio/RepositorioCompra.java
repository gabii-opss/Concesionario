package Repositorio;

import concesionarioo.Compra;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class RepositorioCompra implements iRepoCompra {
    private final List<Compra> compras = new ArrayList<>();

    @Override
    public void agregar(Compra compra) {
        if (compra != null) {
            compras.add(compra);
        }
    }


    @Override
    public List<Compra> obtenerTodos() {
        return new ArrayList<>(compras); // Retorna copia para proteger la lista interna
    }

    @Override
    public Compra buscarPorId(Integer id) {
        return compras.stream()
                .filter(c -> c.getIdCompra() == id)
                .findFirst()
                .orElse(null);
    }

    @Override
    public List<Compra> obtenerPorAsesor(String nombreAsesor) {
        return compras.stream()
                .filter(c -> c.getAsesor().getNombre().equalsIgnoreCase(nombreAsesor))
                .collect(Collectors.toList());
    }

    @Override
    public List<Compra> obtenerPorCliente(String idCliente) {
        return compras.stream()
                .filter(c -> c.getCliente().getId().equalsIgnoreCase(idCliente))
                .collect(Collectors.toList());
    }

    @Override
    public List<Compra> obtenerPorTipoPago(String tipoPago) {
        return compras.stream()
                .filter(c -> c.getTipoPago().equalsIgnoreCase(tipoPago))
                .collect(Collectors.toList());
    }

    // Métodos adicionales útiles
    public int contarCompras() {
        return compras.size();
    }



    public List<Compra> obtenerComprasPorFecha(LocalDate fecha) {
        return compras.stream()
                .filter(c -> c.getFecha().equals(fecha))
                .collect(Collectors.toList());
    }

    public List<Compra> obtenerComprasEntreFechas(LocalDate inicio, LocalDate fin) {
        return compras.stream()
                .filter(c -> !c.getFecha().isBefore(inicio) && !c.getFecha().isAfter(fin))
                .collect(Collectors.toList());
    }

    public double calcularTotalVentas() {
        return compras.stream()
                .mapToDouble(c -> c.getVehiculo().getCosto())
                .sum();
    }

    public double calcularTotalVentasPorAsesor(String nombreAsesor) {
        return compras.stream()
                .filter(c -> c.getAsesor().getNombre().equalsIgnoreCase(nombreAsesor))
                .mapToDouble(c -> c.getVehiculo().getCosto())
                .sum();
    }

    public double calcularTotalVentasPorTipoPago(String tipoPago) {
        return compras.stream()
                .filter(c -> c.getTipoPago().equalsIgnoreCase(tipoPago))
                .mapToDouble(c -> c.getVehiculo().getCosto())
                .sum();
    }

    public int contarComprasPorTipoPago(String tipoPago) {
        return (int) compras.stream()
                .filter(c -> c.getTipoPago().equalsIgnoreCase(tipoPago))
                .count();
    }

    public Compra obtenerCompraMasReciente() {
        return compras.stream()
                .max((c1, c2) -> c1.getFecha().compareTo(c2.getFecha()))
                .orElse(null);
    }

    public List<Compra> obtenerComprasDelMes(int mes, int anio) {
        return compras.stream()
                .filter(c -> c.getFecha().getMonthValue() == mes &&
                        c.getFecha().getYear() == anio)
                .collect(Collectors.toList());
    }

    public double obtenerPromedioVenta() {
        return compras.stream()
                .mapToDouble(c -> c.getVehiculo().getCosto())
                .average()
                .orElse(0.0);
    }
}