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
    JButton b_asesores=new JButton("BUSCAR ");
    JButton info_clientes=new JButton("INFORMACION \n CLIENTES");
    JButton salir=new JButton ("SALIR");
    JLabel titulo=new JLabel (" Sistema Comercializadora de Vehículos ");

    ConcesionarioService concesionario;
     VehiculoService vehiculoService;
     Catalogo c;

    public Menu(ConcesionarioService concesionario, VehiculoService vehiculoService) {
        this.concesionario = concesionario;
        this.vehiculoService = vehiculoService;
        this.c = new Catalogo(vehiculoService);
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
        info_clientes.setForeground(Color.white);
        info_clientes.setBackground(new Color(38, 50, 56));
        salir.setFont(new Font("Century Gothic",Font.BOLD,15));
        salir.setForeground(Color.white);
        salir.setBackground(new Color(38, 50, 56));
        
        b_asesores.setFont(new Font("Century Gothic",Font.BOLD,15));
        b_asesores.setForeground(Color.white);
        b_asesores.setBackground(new Color(38, 50, 56));
        b_asesores.setFont(new Font("Century Gothic",Font.BOLD,15));
        b_asesores.setForeground(Color.white);

        botones.add(Box.createRigidArea(new Dimension(0,10)));
        botones.add(catalogo);
        botones.add(Box.createRigidArea(new Dimension(0,10)));
        registro.setFocusPainted(false);
        info_asesores.setFocusPainted(false);
        botones.add(registro);

        botones.add(Box.createRigidArea(new Dimension(0,10)));
        botones.add(registro_asesores);
        botones.add(Box.createRigidArea(new Dimension(0,10)));
        botones.add(info_asesores);

        botones.add(Box.createRigidArea(new Dimension(0,10)));
        botones.add(info_clientes);
         botones.add(Box.createRigidArea(new Dimension(0,10)));
        botones.add(b_asesores);
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
        b_asesores.setMaximumSize(size);
        info_clientes.setMaximumSize(size);


        registro.setAlignmentX(Component.CENTER_ALIGNMENT);
        registro_asesores.setAlignmentX(Component.CENTER_ALIGNMENT);
        info_asesores.setAlignmentX(Component.CENTER_ALIGNMENT);
        b_asesores.setAlignmentX(Component.CENTER_ALIGNMENT);
        info_clientes.setAlignmentX(Component.CENTER_ALIGNMENT);
        catalogo.setAlignmentX(Component.CENTER_ALIGNMENT);
        salir.setAlignmentX(Component.CENTER_ALIGNMENT);

        registro.addActionListener(e -> {

            new Registro_cliente(concesionario);

        });
        registro_asesores.addActionListener(e -> {

            new RegistroAsesor(concesionario);

        });
b_asesores.addActionListener(e -> {
    String[] opciones = {"Cliente", "Asesor", "Vehículo", "Compra"};
    String tipo = (String) JOptionPane.showInputDialog(
            null,
            "¿Qué deseas buscar?",
            "Búsqueda",
            JOptionPane.QUESTION_MESSAGE,
            null,
            opciones,
            "Cliente"
    );

    if (tipo == null) return; // Si el usuario cancela

    switch (tipo) {
        case "Cliente" -> {
            String id = JOptionPane.showInputDialog("Ingrese el ID del cliente:");
            if (id != null) {
                var cliente = concesionario.buscarClientePorId(id);
                if (cliente != null)
                    cliente.mostrarInfo();
                else
                    JOptionPane.showMessageDialog(null, "No se encontró ningún cliente con ese ID.", "Sin resultados", JOptionPane.WARNING_MESSAGE);
            }
        }

        case "Asesor" -> {
            String nombre = JOptionPane.showInputDialog("Ingrese el nombre del asesor:");
            if (nombre != null) {
                var asesor = concesionario.buscarAsesorPorNombre(nombre);
                if (asesor != null)
                    asesor.mostrarInfo();
                else
                    JOptionPane.showMessageDialog(null, "No se encontró ningún asesor con ese nombre.", "Sin resultados", JOptionPane.WARNING_MESSAGE);
            }
        }

        case "Compra" -> {
            String texto = JOptionPane.showInputDialog("Ingrese el número de compra:");
            if (texto != null) {
                try {
                    var compra = concesionario.buscarCompraPorId(Integer.parseInt(texto.trim()));
                    if (compra != null)
                        JOptionPane.showMessageDialog(null, compra.toString(), "Compra encontrada", JOptionPane.INFORMATION_MESSAGE);
                    else
                        JOptionPane.showMessageDialog(null, "No se encontró ninguna compra con ese número.", "Sin resultados", JOptionPane.WARNING_MESSAGE);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Ingrese un número válido.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        }

        case "Vehículo" -> {
            String modelo = JOptionPane.showInputDialog("Ingrese el modelo del vehículo:");
            if (modelo != null) {
                var vehiculo = concesionario.buscarVehiculoPorModelo(modelo);
                if (vehiculo != null)
                    JOptionPane.showMessageDialog(null, vehiculo.toString(), "Vehículo encontrado", JOptionPane.INFORMATION_MESSAGE);
                else
                    JOptionPane.showMessageDialog(null, "No se encontró ningún vehículo con ese modelo.", "Sin resultados", JOptionPane.WARNING_MESSAGE);
            }
        }

            
        
    }
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


        catalogo.addActionListener(e -> {
            c.verCatalogo();
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
   





}
