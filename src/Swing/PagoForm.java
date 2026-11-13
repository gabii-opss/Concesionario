package Swing;

import Pagos.PagoInvalidoException;
import Servicio.ConcesionarioService;
import Servicio.DatosAdicionales;
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
    private final ConcesionarioService concesionario;

    public PagoForm(Cliente cliente, Asesor asesor, List<Vehiculo> vehiculosDisponibles,
                    ConcesionarioService concesionario) {
        this.concesionario = concesionario;
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

        try {
            DatosAdicionales datos = solicitarDatosAdicionales(tipo);
            if (datos == null) {
                return; // el usuario canceló alguno de los cuadros de diálogo
            }

            // El patrón Strategy se aplica dentro del servicio: el formulario ya
            // no decide cómo se calcula cada forma de pago.
            concesionario.registrarCompra(cliente, vehiculoSeleccionado, asesor, tipo, datos);
            dispose();

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Ingrese valores numéricos válidos.", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (PagoInvalidoException | IllegalArgumentException | IllegalStateException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Datos de pago inválidos", JOptionPane.WARNING_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error al procesar el pago: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Pide al usuario solo los datos que necesita cada forma de pago.
     * Es lógica de interfaz (qué preguntar); el cálculo vive en las estrategias.
     *
     * @return los datos capturados, o null si el usuario canceló
     */
    private DatosAdicionales solicitarDatosAdicionales(String tipo) {
        DatosAdicionales datos = new DatosAdicionales();
        String normalizado = tipo.trim().toUpperCase();

        if (normalizado.equals("CONTADO")) {
            return datos;
        }

        if (normalizado.equals("LEASING")) {
            String entidad = JOptionPane.showInputDialog(this, "Entidad financiera:");
            if (entidad == null) {
                return null;
            }
            datos.setEntidadFinanciera(entidad);
        }

        String cuotas = JOptionPane.showInputDialog(this, "Número de cuotas:");
        if (cuotas == null) {
            return null;
        }
        datos.setNumeroCuotas(Integer.parseInt(cuotas.trim()));

        if (normalizado.equals("LEASING")) {
            String inicial = JOptionPane.showInputDialog(this, "Valor de la cuota inicial:");
            if (inicial == null) {
                return null;
            }
            datos.setCuotaInicial(Double.parseDouble(inicial.trim()));
        }
        return datos;
    }
}
