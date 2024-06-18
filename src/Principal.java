import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Principal extends JFrame {
    private JPanel panelMenu;
    private JButton btnClientes;
    private JButton btnEmpleados;
    private JButton btnLibreria;
    private JButton btnMenu;
    private ArrayList<VentaItem> orden; // ArrayList para almacenar la orden

    public Principal() {
        orden = new ArrayList<>(); 
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
        btnClientes.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Mostrar un menú emergente con dos opciones
                String[] options = {"Agregar cliente", "Mostrar clientes"};
                int choice = JOptionPane.showOptionDialog(null, "Seleccione una opción", "Menú Clientes",
                        JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);

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

        btnMenu = new JButton("Menú");
        btnMenu.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Mostrar un menú emergente con tres opciones
                String[] options = {"Bebidas", "Platos", "Finalizar Compra"};
                int choice = JOptionPane.showOptionDialog(null, "Seleccione una opción", "Menú",
                        JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);

                switch (choice) {
                    case 0:
                        ReporteBebidas reporteBebidas = new ReporteBebidas(orden); // Pasar el ArrayList
                        reporteBebidas.setVisible(true);
                        break;
                    case 1:
                        ReportePlatos reportePlatos = new ReportePlatos(orden); // Pasar el ArrayList
                        reportePlatos.setVisible(true);
                        break;
                    case 2:
                        finalizarCompra();
                        break;
                    default:
                        break;
                }
            }
        });

        // Añadir los botones al panel principal
        panelMenu.add(btnClientes);
        panelMenu.add(btnEmpleados);
        panelMenu.add(btnLibreria);
        panelMenu.add(btnMenu);

        // Agregar el panel principal a la ventana
        getContentPane().add(panelMenu);
        setLocationRelativeTo(null); // Centrar la ventana en la pantalla

        // Hacer visible la ventana
        setVisible(true);
    }

    // Método para finalizar la compra
    private void finalizarCompra() {
        if (orden.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No hay productos en la orden.");
            return;
        }

        // Crear la factura
        StringBuilder factura = new StringBuilder("Factura:\n\n");
        double total = 0.0;
        for (VentaItem p : orden) {
            factura.append(p.getNombre()).append(" - Cantidad: ").append(p.getCantidad())
                    .append(" - Precio: $").append(p.getPrecioTotal()).append("\n");
            total += p.getPrecioTotal();
        }
        factura.append("\nTotal: $").append(total);

        // Mostrar la factura en un JOptionPane
        JOptionPane.showMessageDialog(null, factura.toString(), "Factura", JOptionPane.INFORMATION_MESSAGE);

        // Reiniciar la orden
        orden.clear();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Principal::new);
    }
}

