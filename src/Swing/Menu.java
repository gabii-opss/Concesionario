/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Swing;

import Servicio.*;
import javax.swing.*;
import java.awt.*;
import java.text.DecimalFormat;

public class Menu extends JFrame {
    JButton registro=new JButton ("REGISTRAR CLIENTE");
    JButton catalogo=new JButton ("CATALOGO");
    JButton registro_asesores=new JButton ("REGISTRO ASESORES");
    JButton info_asesores=new JButton("INFORMACION \n ASESORES");
    JButton info_clientes=new JButton("INFORMACION \n CLIENTES");
    JButton info_clientes_preventa=new JButton("INFORMACION \nPREVENTA");
    JButton salir=new JButton ("SALIR");
    JLabel titulo=new JLabel (" Sistema Comercializadora de Vehículos ");

    ConcesionarioService concesionario;
     VehiculoService vehiculoService;

    public Menu(ConcesionarioService concesionario, VehiculoService vehiculoService) {
        this.concesionario = concesionario;
        this.vehiculoService = vehiculoService;
        loadform();
    }

    public void loadform(){
        JPanel tituloo = new JPanel();
        tituloo.add(titulo);
        titulo.setFont(new Font("Century Gothic",Font.BOLD,15));
        tituloo.setBackground(new Color(59, 95, 184));
        titulo.setForeground(Color.WHITE);

        JPanel botones =new JPanel();
        botones.setBackground(new Color(38, 50, 56));
        registro.setBackground(new Color(38, 50, 56));
        registro.setForeground(Color.white);
        registro.setFont(new Font("Century Gothic",Font.BOLD,15));
        catalogo.setFont(new Font("Century Gothic",Font.BOLD,15));
        catalogo.setForeground(Color.white);
        catalogo.setBackground(new Color(38, 50, 56));
        registro_asesores.setBackground(new Color(38, 50, 56));
        registro_asesores.setForeground(Color.white);
        registro_asesores.setFont(new Font("Century Gothic",Font.BOLD,15));
        info_asesores.setFont(new Font("Century Gothic",Font.BOLD,15));
        info_asesores.setForeground(Color.white);
        info_asesores.setBackground(new Color(38, 50, 56));
        info_clientes.setFont(new Font("Century Gothic",Font.BOLD,15));
        info_clientes_preventa.setFont(new Font("Century Gothic",Font.BOLD,15));
        info_clientes.setForeground(Color.white);
        info_clientes.setBackground(new Color(38, 50, 56));
        info_clientes_preventa.setForeground(Color.white);
        info_clientes_preventa.setBackground(new Color(38, 50, 56));
        salir.setFont(new Font("Century Gothic",Font.BOLD,15));
        salir.setForeground(Color.white);
        salir.setBackground(new Color(38, 50, 56));

        botones.add(Box.createRigidArea(new Dimension(0,10)));
        registro.setFocusPainted(false);
        info_asesores.setFocusPainted(false);
        botones.add(registro);
        botones.add(Box.createRigidArea(new Dimension(0,10)));
        botones.add(catalogo);
        botones.add(Box.createRigidArea(new Dimension(0,10)));
        botones.add(registro_asesores);
        botones.add(Box.createRigidArea(new Dimension(0,10)));
        botones.add(info_asesores);
        botones.add(Box.createRigidArea(new Dimension(0,10)));
        botones.add(info_clientes);
        botones.add(Box.createRigidArea(new Dimension(0,10)));
        botones.add(info_clientes_preventa);
        botones.add(Box.createRigidArea(new Dimension(0,10)));
        botones.add(salir);
        botones.add(Box.createRigidArea(new Dimension(0,10)));


        botones.setLayout(new BoxLayout(botones, BoxLayout.Y_AXIS));
        Dimension size = new Dimension(250, 50);
        registro.setMaximumSize(size);
        catalogo.setMaximumSize(size);
        registro_asesores.setMaximumSize(size);
        salir.setMaximumSize(size);
        info_asesores.setMaximumSize(size);
        info_clientes.setMaximumSize(size);
        info_clientes_preventa.setMaximumSize(size);


        registro.setAlignmentX(Component.CENTER_ALIGNMENT);
        registro_asesores.setAlignmentX(Component.CENTER_ALIGNMENT);
        info_asesores.setAlignmentX(Component.CENTER_ALIGNMENT);
        info_clientes.setAlignmentX(Component.CENTER_ALIGNMENT);
        info_clientes_preventa.setAlignmentX(Component.CENTER_ALIGNMENT);
        catalogo.setAlignmentX(Component.CENTER_ALIGNMENT);
        salir.setAlignmentX(Component.CENTER_ALIGNMENT);

        registro.addActionListener(e -> {

            new Registro_cliente(concesionario);

        });
        registro_asesores.addActionListener(e -> {

            new RegistroAsesor(concesionario);

        });


        info_asesores.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JOptionPane.showMessageDialog(null, concesionario.generarReporteAsesores()+"\n");

            }
        });
        salir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dispose();



            }
        });
        info_clientes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JOptionPane.showMessageDialog(null, concesionario.generarReporteClientes());



            }
        });
        info_clientes_preventa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JOptionPane.showMessageDialog(null, concesionario.generarReportePreventa());



            }
        });

        catalogo.addActionListener(e -> {
            mostrarCatalogo();
        });
        JPanel mainpanel = new JPanel(new BorderLayout());
        mainpanel.add(tituloo, BorderLayout.NORTH);
        mainpanel.add(botones, BorderLayout.CENTER);

        add(mainpanel);

        setSize(350,450);
        this.setLocationRelativeTo(null);
        setBackground(Color.black);
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("MENU REGISTRO");
        setVisible(true);

    }
    private void mostrarCatalogo() {
        var lista = vehiculoService.obtenerCatalogo();

        if (lista.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No hay vehículos registrados en el catálogo.",
                    "Catálogo vacío", JOptionPane.WARNING_MESSAGE);
            return;
        }

        // Columnas
        String[] columnas = {"Marca", "Modelo", "Precio", "País de Ensamblaje"};

        // Datos
        Object[][] datos = new Object[lista.size()][columnas.length];
        DecimalFormat df = new DecimalFormat("#,###");

        for (int i = 0; i < lista.size(); i++) {
            var v = lista.get(i);
            datos[i][0] = v.getMarca();
            datos[i][1] = v.getModelo();
            datos[i][2] = "$" + df.format(v.getCosto());
            datos[i][3] = v.getPais_ensamblaje();
        }

        // Crear tabla
        JTable tabla = new JTable(datos, columnas);
        tabla.setEnabled(false);
        tabla.setFont(new Font("Century Gothic", Font.PLAIN, 14));
        tabla.setRowHeight(28);

        // 🎨 Tabla blanca, texto negro
        tabla.setBackground(Color.WHITE);
        tabla.setForeground(Color.BLACK);
        tabla.setGridColor(new Color(200, 200, 200));
        tabla.setSelectionBackground(new Color(220, 230, 255));
        tabla.setSelectionForeground(Color.BLACK);

        // 🎨 Encabezado azul
        tabla.getTableHeader().setFont(new Font("Century Gothic", Font.BOLD, 15));
        tabla.getTableHeader().setBackground(new Color(59, 95, 184));
        tabla.getTableHeader().setForeground(Color.WHITE);
        tabla.getTableHeader().setOpaque(true);

        // Scrollpane
        JScrollPane scroll = new JScrollPane(tabla);
        scroll.getViewport().setBackground(Color.WHITE);
        scroll.setPreferredSize(new Dimension(700, 350));
        scroll.setBorder(BorderFactory.createLineBorder(new Color(59, 95, 184), 2));

        // 🎨 Fondo oscuro general
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(new Color(38, 50, 56)); // mismo fondo que tu app
        panel.add(scroll, BorderLayout.CENTER);

        // Mostrar
        JOptionPane.showMessageDialog(this, panel, "🚗 Catálogo de Vehículos", JOptionPane.PLAIN_MESSAGE);
    }






}
