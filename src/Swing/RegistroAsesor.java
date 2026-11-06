package Swing;
import Repositorio.iRepoAsesor;
import Servicio.*;
import concesionarioo.*;
import Repositorio.*;
import javax.swing.*;
import java.awt.*;
public class RegistroAsesor extends JFrame {
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
    JTextField correo_t = new JTextField(7);
    JLabel  atencion_l = new JLabel("ATENCION");
    JTextField atencion_t = new JTextField(7);

    JButton registrar = new JButton("REGISTRAR ");
    JButton salir = new JButton("SALIR");

    public RegistroAsesor(ConcesionarioService concesionario) {
        this.concesionario = concesionario;
        loadform(); // aquí se construye la interfaz
    }
    private ConcesionarioService concesionario;
    private JTextArea areac;
    public void loadform(){

        UIManager.put("OptionPane.messageFont", new Font("Century Gothic", Font.BOLD, 16));

        areac = new JTextArea(5, 5);
        areac.setEditable(false);
        JScrollPane scroll = new JScrollPane(areac);

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

        campos.add(atencion_l);
        atencion_l.setBorder(BorderFactory.createEmptyBorder(0, 30, 0, 0));
        atencion_l.setForeground(Color.white);
        atencion_l.setBackground(Color.white);
        atencion_t.setFont(new Font("Segoe UI", Font.BOLD, 15));
        campos.add(atencion_t);


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
    public void jregistro() {

        String nombre = nombre_t.getText().trim();
        String apellido = apellido_t.getText().trim();
        String tipoId = (String) tipoid.getSelectedItem();
        String id = ID_t.getText().trim();
        String telefono = telefono_t.getText().trim();
        String correo = correo_t.getText().trim();
        String atencion = atencion_t.getText().trim();

        // Validación básica
        if (nombre.isEmpty() || apellido.isEmpty() || id.isEmpty() || atencion.isEmpty()) {
            JOptionPane.showMessageDialog(null,
                    "Por favor completa todos los campos obligatorios",
                    "Campos incompletos",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        // Crear el asesor
        Asesor nuevo = new Asesor(nombre, apellido, tipoId, id, 0, 0, atencion);

        // Registrar en el servicio
        concesionario.registrarAsesor(nuevo);

        JOptionPane.showMessageDialog(null,
                "Asesor registrado con éxito:\n" + nuevo.getNombre(),
                "Registro exitoso",
                JOptionPane.INFORMATION_MESSAGE);


    }


}
