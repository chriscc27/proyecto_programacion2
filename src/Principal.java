import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Principal extends JFrame {
    private JPanel panelMenu;
    private JButton btnClientes;
    private JButton btnEmpleados;
    private JButton btnLibreria;
    private JButton btnRecetas;
    private JButton btnIngredientes;

    public Principal() {
        // Configuración de la ventana principal
        setTitle("Menú Principal");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Creación del panel principal
        panelMenu = new JPanel();
        panelMenu.setLayout(new GridLayout(5, 1, 10, 10)); // Layout de cuadrícula para organizar los botones
        panelMenu.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // Añade un margen alrededor del panel

        // Creación de los botones del menú
        btnClientes = new JButton("Clientes");
        btnClientes.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Mostrar un menú emergente con dos opciones
                String[] options = {"Agregar cliente", "Mostrar clientes"};
                int choice = JOptionPane.showOptionDialog(null, "Seleccione una opción", "Menú Clientes",
                        JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);

                // Dependiendo de la opción seleccionada, realiza la acción correspondiente
                switch (choice) {
                    case 0:
                        RegistroCliente registroCliente = new RegistroCliente();
                        registroCliente.setVisible(true);
                        break;
                    case 1:
                        ReporteClientes reporteClientes = new ReporteClientes();
                        reporteClientes.setVisible(true);
                        break;
                    default:
                        break;
                }
            }
        });

        btnEmpleados = new JButton("Empleados");
        btnEmpleados.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Mostrar un menú emergente con dos opciones
                String[] options = {"Agregar empleado", "Mostrar empleados"};
                int choice = JOptionPane.showOptionDialog(null, "Seleccione una opción", "Menú Empleados",
                        JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);

                // Dependiendo de la opción seleccionada, realiza la acción correspondiente
                switch (choice) {
                    case 0:
                        RegistroEmpleado registroEmpleado = new RegistroEmpleado();
                        registroEmpleado.setVisible(true);
                        break;
                    case 1:
                        ReporteEmpleados reporteEmpleados = new ReporteEmpleados();
                        reporteEmpleados.setVisible(true);
                        break;
                    default:
                        break;
                }
            }
        });

        btnLibreria = new JButton("Librería");
        btnLibreria.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Mostrar un menú emergente con dos opciones
                String[] options = {"Añadir libro", "Mostrar libros"};
                int choice = JOptionPane.showOptionDialog(null, "Seleccione una opción", "Menú Librería",
                        JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);

                // Dependiendo de la opción seleccionada, realiza la acción correspondiente
                switch (choice) {
                    case 0:
                        RegistroLibro registroLibro = new RegistroLibro();
                        registroLibro.setVisible(true);
                        break;
                    case 1:
                        ReporteLibros reporteLibros = new ReporteLibros();
                        reporteLibros.setVisible(true);
                        break;
                    default:
                        break;
                }
            }
        });

        btnRecetas = new JButton("Menu");
        btnRecetas.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Aquí puedes agregar la lógica para la opción Menú
                JOptionPane.showMessageDialog(null, "Funcionalidad de gestión de recetas");
            }
        });

        btnIngredientes = new JButton("Despensa");
        btnIngredientes.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Aquí puedes agregar la lógica para la opción Despensa
                JOptionPane.showMessageDialog(null, "Funcionalidad de gestión de ingredientes");
            }
        });

        // Añadir los botones al panel principal
        panelMenu.add(btnEmpleados);
        panelMenu.add(btnClientes);
        panelMenu.add(btnLibreria);
        panelMenu.add(btnRecetas);
        panelMenu.add(btnIngredientes);

        // Agregar el panel principal a la ventana
        getContentPane().add(panelMenu);

        // Hacer visible la ventana
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Principal::new);
    }
}
