package Swing;

import concesionarioo.Vehiculo;
import Servicio.VehiculoService;
import javax.swing.*;
import java.text.DecimalFormat;
import java.util.List;

public class Catalogo {

    private final VehiculoService vehiculoService;

    public Catalogo(VehiculoService vehiculoService) {
        this.vehiculoService = vehiculoService;
    }

    public void verCatalogo() {
        // obtener lista de vehículos
        List<Vehiculo> vehiculos = vehiculoService.obtenerCatalogo();

        StringBuilder sb = new StringBuilder();
        DecimalFormat df = new DecimalFormat("#,###");

        sb.append("🚗 CATÁLOGO DE VEHÍCULOS:\n\n");

        if (vehiculos.isEmpty()) {
            sb.append("No hay vehículos disponibles en el catálogo.");
        } else {
            for (Vehiculo v : vehiculos) {
                sb.append("MARCA: ").append(v.getMarca());
                sb.append("   MODELO: ").append(v.getModelo());
                sb.append("   PRECIO: $").append(df.format(v.getCosto()));
                sb.append("   PAÍS: ").append(v.getPais_ensamblaje());
                sb.append("   CANTIDAD: ").append(v.getCantidad());
                sb.append("\n------------------------------------------------------------\n");
            }
        }

        JOptionPane.showMessageDialog(
                null,
                sb.toString(),
                "Catálogo de Vehículos",
                JOptionPane.INFORMATION_MESSAGE
        );
    }
}
