import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Principal extends JFrame implements ActionListener {
    private JPanel panelMenu;
    private JButton btnClientes;
    private JButton btnEmpleados;
    private JButton btnLibreria;
    private JButton btnRecetas;

    public Principal() {
        // Configuración de la ventana principal
        setTitle("Menú Principal");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Creación del panel principal
        panelMenu = new JPanel();
        panelMenu.setLayout(new GridLayout(4, 1, 10, 10)); // Layout de cuadrícula para organizar los botones
        panelMenu.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // Añade un margen alrededor del panel

        // Creación de los botones del menú
        btnClientes = new JButton("Clientes");
        btnEmpleados = new JButton("Empleados");
        btnLibreria = new JButton("Librería");
        btnRecetas = new JButton("Recetas");

        // Asignación de ActionListener a los botones
        btnClientes.addActionListener(this);
        btnEmpleados.addActionListener(this);
        btnLibreria.addActionListener(this);
        btnRecetas.addActionListener(this);

        // Añadir los botones al panel principal
        panelMenu.add(btnClientes);
        panelMenu.add(btnEmpleados);
        panelMenu.add(btnLibreria);
        panelMenu.add(btnRecetas);

        // Agregar el panel principal a la ventana
        add(panelMenu);

        // Hacer visible la ventana
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnClientes) {
            JOptionPane.showMessageDialog(this, "Funcionalidad de gestión de clientes");
        } else if (e.getSource() == btnEmpleados) {
            JOptionPane.showMessageDialog(this, "Funcionalidad de gestión de empleados");
        } else if (e.getSource() == btnLibreria) {
            JOptionPane.showMessageDialog(this, "Funcionalidad de gestión de la librería");
        } else if (e.getSource() == btnRecetas) {
            JOptionPane.showMessageDialog(this, "Funcionalidad de gestión de recetas");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Principal());
    }
}
