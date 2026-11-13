/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Swing;

import Repositorio.iRepoAsesor;
import Servicio.*;
import concesionarioo.*;
import Repositorio.*;
import javax.swing.*;
import java.awt.*;
import java.util.List;
public class Registro_cliente extends JFrame {
    JLabel nombre_l = new JLabel("NOMBRE");
    JTextField nombre_t = new JTextField(7);
    JLabel apellido_l = new JLabel("APELLIDO");
    JTextField apellido_t = new JTextField(7);
    JLabel tipoId_l = new JLabel("TIPO ID");
    JComboBox<String> tipoid = new JComboBox<>(new String[]{" ", "Cedula de ciudadania", "Cedula de extrangeria", "NIT",});
    JLabel ID_l = new JLabel("ID");
    JTextField ID_t = new JTextField(7);
    JLabel telefono_l = new JLabel("TELEFONO");
    JTextField telefono_t = new JTextField(7);
    JLabel correo_l = new JLabel("CORREO ELECTRONICO");
    JLabel estado_l = new JLabel("ESTADO");
    JComboBox<String> estado_t = new JComboBox<>(new String[]{" ", "PREVENTA", "VENTA"});
    JTextField correo_t = new JTextField(7);


    JButton registrar = new JButton("REGISTRAR ");
    JButton salir = new JButton("SALIR");

    private ConcesionarioService concesionario;
    private JTextArea areac;


    public Registro_cliente(ConcesionarioService concesionario) {

        this.concesionario = concesionario;
        loadform();
    }



    public void loadform() {

        UIManager.put("OptionPane.messageFont", new Font("Century Gothic", Font.BOLD, 16));

        areac = new JTextArea(5, 5);
        areac.setEditable(false);
        JScrollPane scroll = new JScrollPane(areac);
        this.add(scroll, BorderLayout.CENTER);

        JPanel campos = new JPanel();
        campos.setLayout(new FlowLayout(FlowLayout.CENTER));
        campos.setLayout(new GridLayout(7, 2, 7, 7));
        campos.setBackground(new Color(38, 50, 56));

        campos.add(nombre_l);
        nombre_l.setBorder(BorderFactory.createEmptyBorder(0, 30, 0, 0));
        nombre_l.setForeground(Color.white);
        nombre_t.setBackground(Color.white);
        nombre_t.setFont(new Font("Segoe UI", Font.BOLD, 15));
        campos.add(nombre_t);
        apellido_l.setBorder(BorderFactory.createEmptyBorder(0, 30, 0, 0));
        apellido_l.setForeground(Color.white);
        apellido_t.setBackground(Color.white);
        apellido_t.setFont(new Font("Segoe UI", Font.BOLD, 15));
        campos.add(apellido_l);
        campos.add(apellido_t);
        tipoId_l.setBorder(BorderFactory.createEmptyBorder(0, 30, 0, 0));
        tipoId_l.setForeground(Color.white);
        tipoid.setBackground(Color.white);
        campos.add(tipoId_l);
        tipoid.setBackground(Color.white);
        tipoid.setFont(new Font("Century Gothic", Font.BOLD, 15));
        campos.add(tipoid);
        ID_l.setBorder(BorderFactory.createEmptyBorder(0, 30, 0, 0));
        ID_l.setForeground(Color.white);
        ID_t.setBackground(Color.white);
        ID_t.setFont(new Font("Segoe UI", Font.BOLD, 15));
        campos.add(ID_l);
        campos.add(ID_t);
        campos.add(telefono_l);
        telefono_l.setBorder(BorderFactory.createEmptyBorder(0, 30, 0, 0));
        telefono_l.setForeground(Color.white);
        telefono_t.setBackground(Color.white);
        telefono_t.setFont(new Font("Segoe UI", Font.BOLD, 15));
        campos.add(telefono_t);


        campos.add(correo_l);
        correo_l.setBorder(BorderFactory.createEmptyBorder(0, 30, 0, 0));
        correo_l.setForeground(Color.white);
        correo_t.setBackground(Color.white);
        correo_t.setFont(new Font("Segoe UI", Font.BOLD, 15));
        campos.add(correo_t);

        campos.add(estado_l);
        estado_l.setBorder(BorderFactory.createEmptyBorder(0, 30, 0, 0));
        estado_l.setForeground(Color.white);
        estado_l.setBackground(Color.white);
        estado_t.setFont(new Font("Segoe UI", Font.BOLD, 15));
        campos.add(estado_t);


        JPanel boton = new JPanel();
        boton.setLayout(new FlowLayout(FlowLayout.LEADING));
        boton.setBackground(new Color(38, 50, 56));
        registrar.setFont(new Font("Century Gothic", Font.BOLD, 15));
        salir.setFont(new Font("Century Gothic", Font.BOLD, 15));
        registrar.setForeground(Color.white);
        registrar.setBackground(new Color(38, 50, 56));
        salir.setForeground(Color.white);
        salir.setBackground(new Color(38, 50, 56));
        boton.add(registrar);
        boton.add(salir);
        nombre_t.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                if (!Validador.esSoloLetras(evt.getKeyChar())) {
                    evt.consume();
                }
            }
        });
        apellido_t.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                if (!Validador.esSoloLetras(evt.getKeyChar())) {
                    evt.consume();
                }
            }
        });
        ID_t.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                if (!Validador.esSoloNumeros(evt.getKeyChar())) {
                    evt.consume();
                }
            }
        });
        salir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dispose();


            }
        });

        JPanel mainpanel = new JPanel();
        mainpanel.setBackground(new Color(38, 50, 56));
        mainpanel.add(campos, BorderLayout.CENTER);
        registrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jregistro();


            }
        });
        mainpanel.add(boton, BorderLayout.SOUTH);

        nombre_l.setFont(new Font("Century Gothic", Font.BOLD, 15));
        apellido_l.setFont(new Font("Century Gothic", Font.BOLD, 15));
        tipoId_l.setFont(new Font("Century Gothic", Font.BOLD, 15));
        ID_l.setFont(new Font("Century Gothic", Font.CENTER_BASELINE, 15));
        telefono_l.setFont(new Font("Century Gothic", Font.BOLD, 15));
        correo_l.setFont(new Font("Century Gothic", Font.BOLD, 15));

        add(mainpanel);
        setSize(500, 400);
        this.setLocationRelativeTo(null);
        setBackground(Color.black);
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("REGISTRO");
        setVisible(true);

    }

    JLabel actual_l = new JLabel("VEHICULO ACTUAL");
    private JComboBox<String> comboVehiculoactual;
    JButton registrar_preventa = new JButton("REGISTRAR");

public void jregistro() {
    // Validar campos
    if ((nombre_t.getText().trim().isEmpty() ||
            apellido_t.getText().trim().isEmpty() ||
            tipoid.getSelectedItem() == null ||
            ID_t.getText().trim().isEmpty() ||
            estado_t.getSelectedItem() == null)) {

        JOptionPane.showMessageDialog(this, "HAY ALGÚN CAMPO SIN LLENAR");
        return;
    }

    String estado = estado_t.getSelectedItem().toString();

    // Registrar cliente en el sistema
    Cliente cliente = concesionario.getClientService().registrarCliente(
            nombre_t.getText(),
            apellido_t.getText(),
            (String) tipoid.getSelectedItem(),
            ID_t.getText(),
            telefono_t.getText(),
            correo_t.getText(),
            estado
    );

    // Asignar asesor (puede ser aleatorio)
    Asesor asesor = concesionario.getRepoAsesor().obtenerAleatorio();
    List<Vehiculo> disponibles = concesionario.getVehiculoService().obtenerCatalogo();

    if (disponibles == null || disponibles.isEmpty()) {
        JOptionPane.showMessageDialog(this,
                "No hay vehículos disponibles.",
                "Sin vehículos",
                JOptionPane.WARNING_MESSAGE);
        return;
    }

    // 🔹 Caso 1: Estado = VENTA
    if (estado.equalsIgnoreCase("VENTA")) {
        PagoForm pago = new PagoForm(cliente, asesor, disponibles, concesionario);
        pago.setVisible(true);
        dispose();
    }

    // 🔹 Caso 2: Estado = PREVENTA
    else if (estado.equalsIgnoreCase("PREVENTA")) {
        Registro_preventa preventa = new Registro_preventa(cliente, asesor, disponibles);
        preventa.setVisible(true);
        dispose();
    }
}



   

}