package Swing;

import concesionarioo.Vehiculo;
import Servicio.VehiculoService;

import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.text.DecimalFormat;
import java.util.List;

public class Catalogo {

    private final VehiculoService vehiculoService;

    public Catalogo(VehiculoService vehiculoService) {
        this.vehiculoService = vehiculoService;
    }

    public void verCatalogo() {
        List<Vehiculo> vehiculos = vehiculoService.obtenerCatalogo();

        if (vehiculos.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No hay vehículos disponibles en el catálogo.");
            return;
        }

        String[] columnas = {"Marca", "Modelo", "Precio", "País", "Cantidad"};
        DefaultTableModel modelo = new DefaultTableModel(columnas, 0);
        DecimalFormat df = new DecimalFormat("#,###");

        for (Vehiculo v : vehiculos) {
            modelo.addRow(new Object[]{
                    v.getMarca(),
                    v.getModelo(),
                    "$" + df.format(v.getCosto()),
                    v.getPais_ensamblaje(),
                    v.getCantidad()
            });
        }

        JTable tabla = new JTable(modelo);

        // --- Estilos tabla ---
        tabla.setBackground(new Color(30, 30, 30));
        tabla.setForeground(Color.WHITE);
        tabla.setSelectionBackground(new Color(70, 130, 180));
        tabla.setSelectionForeground(Color.WHITE);
        tabla.setGridColor(new Color(80, 80, 80));
        tabla.setFont(new Font("Century Gothic", Font.PLAIN, 13));
        tabla.setRowHeight(25);
        tabla.setShowGrid(true);

        // --- Encabezado azul intenso ---
        JTableHeader header = tabla.getTableHeader();
        header.setForeground(Color.BLACK);
        header.setFont(new Font("Century Gothic", Font.BOLD, 14));
        header.setOpaque(true);

        // --- ScrollPane ---
        JScrollPane scrollPane = new JScrollPane(tabla);
        scrollPane.getViewport().setBackground(new Color(30, 30, 30));
        scrollPane.setBorder(BorderFactory.createLineBorder(new Color(59, 95, 184), 2));

        // --- Panel contenedor con fondo oscuro ---
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(new Color(38, 50, 56));
        panel.add(scrollPane, BorderLayout.CENTER);

        // --- Mostrar en un JFrame en lugar de JOptionPane (para que no pierda color) ---
        JDialog dialogo = new JDialog((Frame) null, "Catálogo de Vehículos", true);
        dialogo.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        dialogo.getContentPane().add(panel);
        dialogo.setSize(700, 400);
        dialogo.setLocationRelativeTo(null);
        dialogo.setVisible(true);
    }
}
