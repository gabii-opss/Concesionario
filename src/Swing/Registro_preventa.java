
package Swing;
import Repositorio.iRepoAsesor;
import Servicio.*;
import concesionarioo.*;
import Repositorio.*;
import javax.swing.*;
import java.awt.*;
public class Registro_preventa extends JFrame {
    JLabel combov = new JLabel("VEHICULO DE PREFERENCIA");
    private JComboBox<String> comboVehiculosDeseados = new JComboBox<>();
    JLabel comboa = new JLabel("VEHICULO ACTUAL");
    private JComboBox<String> comboVehiculosActual = new JComboBox<>(new String[]{"NO APLICA "});
    
    
    private JButton continuar = new JButton("CONTINUAR");
    private JButton cancelar = new JButton("CANCELAR");
     private Cliente cliente;
    private Asesor asesor;
    private java.util.List<Vehiculo> vehiculosDisponibles;

    public Registro_preventa(Cliente cliente, Asesor asesor, java.util.List<Vehiculo> vehiculosDisponibles) {
        this.cliente = cliente;
        this.asesor = asesor;
        this.vehiculosDisponibles = vehiculosDisponibles;
        loadform();
    }
    public void loadform(){
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
        JLabel[] labels = {comboa, combov};
        for (JLabel label : labels) {
            label.setFont(fontLabel);
            label.setForeground(Color.WHITE);
        }

        // Combo vehículos (marca + modelo)
        for (Vehiculo v : vehiculosDisponibles) {
            comboVehiculosDeseados.addItem(v.getMarca() + " " + v.getModelo());
            comboVehiculosActual.addItem(v.getMarca() + " " + v.getModelo());
            
            
        }
        comboVehiculosDeseados.setFont(fontField);
        comboVehiculosDeseados.setBackground(Color.WHITE);

        // Combo forma de pago
        comboVehiculosDeseados.setFont(fontField);
        comboVehiculosDeseados.setBackground(Color.WHITE);

        // Campo costo
        comboVehiculosActual.setFont(fontField);
        comboVehiculosActual.setBackground(Color.WHITE);
        comboVehiculosActual.setForeground(Color.BLACK);
        
         gbc.gridx = 0; gbc.gridy = 1;
        panel.add(comboa, gbc);
        gbc.gridx = 1;
        panel.add(comboVehiculosActual, gbc);

        // Fila 3: valor a pagar
        gbc.gridx = 0; gbc.gridy = 2;
        panel.add(combov, gbc);
        gbc.gridx = 1;
        panel.add(comboVehiculosDeseados, gbc);

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
    Vehiculo seleccionado = vehiculoSeleccionado(comboVehiculosDeseados);
});
continuar.addActionListener(e -> {
    Vehiculo vehiculoDeseado = vehiculoSeleccionado(comboVehiculosDeseados);
    Vehiculo vehiculoActual = vehiculoSeleccionado(comboVehiculosActual);

    if (vehiculoDeseado == null) {
        JOptionPane.showMessageDialog(this, "Debe seleccionar un vehículo de preferencia.");
        return;
    }

    // 🔹 Asociar ambos vehículos al cliente
    cliente.setCarro_deseado(vehiculoDeseado);
    cliente.setCarro_actual(vehiculoActual); // ⚠️ asegúrate de tener este atributo en Cliente
    cliente.setEstado("PREVENTA");

    // 🔹 Confirmar al usuario
    JOptionPane.showMessageDialog(this,
            "REGISTRO EXITOSO!!!\n" +
            "Vehículo actual: " + 
                (vehiculoActual != null ? vehiculoActual.getMarca() + " " + vehiculoActual.getModelo() : "No aplica") + "\n" +
            "Vehículo deseado: " + 
                vehiculoDeseado.getMarca() + " " + vehiculoDeseado.getModelo(),
            "Registro Preventa",
            JOptionPane.INFORMATION_MESSAGE);

    dispose();
});

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
private Vehiculo vehiculoSeleccionado(JComboBox<String> combo) {
    String seleccion = (String) combo.getSelectedItem();
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
}
