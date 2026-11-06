package Swing;

import Pagos.*;
import concesionarioo.*;
import javax.swing.*;
import java.awt.*;
import java.util.List;

public class PagoForm extends JFrame {

    private JLabel deseados_l = new JLabel("VEHÍCULO DESEADO");
    private JLabel forma_pago_l = new JLabel("FORMA DE PAGO");
    private JLabel costo_l = new JLabel("VALOR A PAGAR");

    private JComboBox<String> comboVehiculosDeseados = new JComboBox<>();
    private JComboBox<String> forma_pago = new JComboBox<>(new String[]{" ", "CONTADO", "CRÉDITO", "LEASING"});
    private JTextField costo_t = new JTextField(10);
    private JButton continuar = new JButton("CONTINUAR");
    private JButton cancelar = new JButton("CANCELAR");

    private Cliente cliente;
    private Asesor asesor;
    private List<Vehiculo> vehiculosDisponibles;

    public PagoForm(Cliente cliente, Asesor asesor, List<Vehiculo> vehiculosDisponibles) {
        this.cliente = cliente;
        this.asesor = asesor;
        this.vehiculosDisponibles = vehiculosDisponibles;
        loadform();
    }

    private void loadform() {
        UIManager.put("OptionPane.messageFont", new Font("Century Gothic", Font.BOLD, 16));

        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(new Color(38, 50, 56));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.CENTER;

        Font fontLabel = new Font("Century Gothic", Font.BOLD, 15);
        Font fontField = new Font("Segoe UI", Font.BOLD, 15);

        // Estilos de etiquetas
        JLabel[] labels = {deseados_l, forma_pago_l, costo_l};
        for (JLabel label : labels) {
            label.setFont(fontLabel);
            label.setForeground(Color.WHITE);
        }

        // Combo vehículos (marca + modelo)
        for (Vehiculo v : vehiculosDisponibles) {
            comboVehiculosDeseados.addItem(v.getMarca() + " " + v.getModelo());
        }
        comboVehiculosDeseados.setFont(fontField);
        comboVehiculosDeseados.setBackground(Color.WHITE);

        // Combo forma de pago
        forma_pago.setFont(fontField);
        forma_pago.setBackground(Color.WHITE);

        // Campo costo
        costo_t.setFont(fontField);
        costo_t.setEditable(false);
        costo_t.setBackground(Color.WHITE);
        costo_t.setForeground(Color.BLACK);

        // Fila 1: vehículo deseado
        gbc.gridx = 0; gbc.gridy = 0;
        panel.add(deseados_l, gbc);
        gbc.gridx = 1;
        panel.add(comboVehiculosDeseados, gbc);

        // Fila 2: forma de pago
        gbc.gridx = 0; gbc.gridy = 1;
        panel.add(forma_pago_l, gbc);
        gbc.gridx = 1;
        panel.add(forma_pago, gbc);

        // Fila 3: valor a pagar
        gbc.gridx = 0; gbc.gridy = 2;
        panel.add(costo_l, gbc);
        gbc.gridx = 1;
        panel.add(costo_t, gbc);

        // Botones
        JPanel botonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        botonPanel.setBackground(new Color(38, 50, 56));

        continuar.setFont(new Font("Century Gothic", Font.BOLD, 15));
        continuar.setForeground(Color.WHITE);
        continuar.setBackground(new Color(0, 121, 107));

        cancelar.setFont(new Font("Century Gothic", Font.BOLD, 15));
        cancelar.setForeground(Color.WHITE);
        cancelar.setBackground(new Color(198, 40, 40));

        botonPanel.add(continuar);
        botonPanel.add(cancelar);

        // Eventos
        comboVehiculosDeseados.addActionListener(e -> {
            Vehiculo seleccionado = vehiculoSeleccionado();
            if (seleccionado != null) {
                costo_t.setText(String.valueOf(seleccionado.getCosto()));
            } else {
                costo_t.setText("");
            }
        });

        continuar.addActionListener(e -> procesarPago());
        cancelar.addActionListener(e -> dispose());

        // Estructura principal
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(new Color(38, 50, 56));
        mainPanel.add(panel, BorderLayout.CENTER);
        mainPanel.add(botonPanel, BorderLayout.SOUTH);

        add(mainPanel);
        setTitle("Registro de Pago");
        setSize(500, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);
    }

    private Vehiculo vehiculoSeleccionado() {
        String seleccion = (String) comboVehiculosDeseados.getSelectedItem();
        if (seleccion != null) {
            for (Vehiculo v : vehiculosDisponibles) {
                String texto = v.getMarca() + " " + v.getModelo();
                if (texto.equals(seleccion)) {
                    return v;
                }
            }
        }
        return null;
    }

    private void procesarPago() {
        String tipo = (String) forma_pago.getSelectedItem();
        Vehiculo vehiculoSeleccionado = vehiculoSeleccionado();

        if (vehiculoSeleccionado == null) {
            JOptionPane.showMessageDialog(this, "Seleccione un vehículo válido.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (tipo == null || tipo.trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Seleccione una forma de pago.", "Advertencia", JOptionPane.WARNING_MESSAGE);
            return;
        }

        Pago pago;

        try {
            switch (tipo.toUpperCase()) {
                case "CONTADO" -> {
                    pago = new PagoContado(cliente, asesor, vehiculoSeleccionado);
                    JOptionPane.showMessageDialog(this, "Pago al contado registrado exitosamente.");
                    pago.generarFactura();
                }
                case "CRÉDITO" -> {
                    int cuotas = Integer.parseInt(JOptionPane.showInputDialog(this, "Número de cuotas:"));
                    pago = new PagoCredito(cliente, asesor, vehiculoSeleccionado, cuotas);
                    JOptionPane.showMessageDialog(this, "Pago a crédito registrado exitosamente.");
                    pago.generarFactura();
                }
                case "LEASING" -> {
                    String entidad = JOptionPane.showInputDialog(this, "Entidad financiera:");
                    int cuotas = Integer.parseInt(JOptionPane.showInputDialog(this, "Número de cuotas:"));
                    double inicial = Double.parseDouble(JOptionPane.showInputDialog(this, "Valor de la cuota inicial:"));
                    int duracion = Integer.parseInt(JOptionPane.showInputDialog(this, "Duración del contrato (meses):"));
                    pago = new PagoLeasing(vehiculoSeleccionado, cliente, asesor, entidad, cuotas, inicial, duracion);
                    JOptionPane.showMessageDialog(this, "Pago leasing registrado exitosamente.");
                    pago.generarFactura();
                }
                default -> {
                    JOptionPane.showMessageDialog(this, "Seleccione una forma de pago válida.", "Advertencia", JOptionPane.WARNING_MESSAGE);
                    return;
                }
            }

            dispose();

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Ingrese valores numéricos válidos.", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error al procesar el pago: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
