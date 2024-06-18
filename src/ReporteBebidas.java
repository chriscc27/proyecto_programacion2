import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ReporteBebidas extends JFrame {
    private JTable tablaBebidas;
    private ArrayList<VentaItem> orden; // ArrayList para almacenar la orden

    public ReporteBebidas(ArrayList<VentaItem> orden) {
        this.orden = orden;
        setTitle("Reporte de Bebidas");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel(new BorderLayout());

        // Crear tabla para mostrar las bebidas
        tablaBebidas = new JTable();
        JScrollPane scrollPane = new JScrollPane(tablaBebidas);
        panel.add(scrollPane, BorderLayout.CENTER);

        // Botones para añadir, eliminar y modificar bebidas
        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton btnAgregar = new JButton("Añadir Bebida");
        JButton btnEliminar = new JButton("Eliminar Bebida");
        JButton btnModificar = new JButton("Modificar Bebida");
        JButton btnAñadirOrden = new JButton("Añadir a la Orden");

        btnAgregar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                RegistrarBebida registrarBebida = new RegistrarBebida();
                registrarBebida.setVisible(true);
                registrarBebida.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosed(java.awt.event.WindowEvent windowEvent) {
                        actualizarTabla();
                    }
                });
            }
        });

        btnEliminar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int filaSeleccionada = tablaBebidas.getSelectedRow();
                if (filaSeleccionada != -1) {
                    int idBebida = (int) tablaBebidas.getValueAt(filaSeleccionada, 0);
                    Bebida.eliminarBebida(idBebida);
                    actualizarTabla();
                } else {
                    JOptionPane.showMessageDialog(null, "Por favor, selecciona una bebida para eliminar.");
                }
            }
        });

        btnModificar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int filaSeleccionada = tablaBebidas.getSelectedRow();
                if (filaSeleccionada != -1) {
                    int idBebida = (int) tablaBebidas.getValueAt(filaSeleccionada, 0);
                    ModificarBebida modificarBebida = new ModificarBebida(idBebida);
                    modificarBebida.setVisible(true);
                    modificarBebida.addWindowListener(new java.awt.event.WindowAdapter() {
                        @Override
                        public void windowClosed(java.awt.event.WindowEvent windowEvent) {
                            actualizarTabla();
                        }
                    });
                } else {
                    JOptionPane.showMessageDialog(null, "Por favor, selecciona una bebida para modificar.");
                }
            }
        });

        btnAñadirOrden.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int filaSeleccionada = tablaBebidas.getSelectedRow();
                if (filaSeleccionada != -1) {
                    int idBebida = (int) tablaBebidas.getValueAt(filaSeleccionada, 0);
                    String nombreBebida = (String) tablaBebidas.getValueAt(filaSeleccionada, 1);
                    añadirProducto(nombreBebida, idBebida);
                } else {
                    JOptionPane.showMessageDialog(null, "Por favor, selecciona una bebida para añadir a la orden.");
                }
            }
        });



        panelBotones.add(btnAgregar);
        panelBotones.add(btnEliminar);
        panelBotones.add(btnModificar);
        panelBotones.add(btnAñadirOrden);
        panel.add(panelBotones, BorderLayout.SOUTH);

        add(panel);
        setLocationRelativeTo(null); // Centrar la ventana en la pantalla

        // Actualizar tabla al inicio
        actualizarTabla();
    }

    private Bebida buscarBebidaPorID(int id) {
        // Método para buscar una bebida por su ID
        ArrayList<Bebida> bebidas = Bebida.mostrarBebidas();
        for (Bebida bebida : bebidas) {
            if (bebida.getId_producto() == id) {
                return bebida;
            }
        }
        return null; // Si no se encuentra la bebida
    }

    private void añadirProducto(String nombre, int id) {
        Bebida bebidaSeleccionada = buscarBebidaPorID(id);
        if (bebidaSeleccionada != null) {
            double precioUnitario = bebidaSeleccionada.getPrecio();
            String cantidadStr = JOptionPane.showInputDialog("Ingrese la cantidad de " + nombre + ":");
            if (cantidadStr != null && !cantidadStr.isEmpty()) {
                try {
                    int cantidad = Integer.parseInt(cantidadStr);
                    if (cantidad > 0) {
                        double precioTotal = cantidad * precioUnitario;
                        VentaItem item = new VentaItem(nombre, cantidad, precioTotal);
                        orden.add(item);
                        JOptionPane.showMessageDialog(null, "Producto añadido a la orden.");
                    } else {
                        JOptionPane.showMessageDialog(null, "La cantidad debe ser mayor a cero.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Cantidad inválida.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        } else {
            JOptionPane.showMessageDialog(null, "Bebida no encontrada.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    
    public void actualizarTabla() {
        // Obtener la lista de bebidas y convertirla en un arreglo bidimensional
        ArrayList<Bebida> bebidas = Bebida.mostrarBebidas();
        Object[][] data = new Object[bebidas.size()][5];
        for (int i = 0; i < bebidas.size(); i++) {
            Bebida bebida = bebidas.get(i);
            data[i][0] = bebida.getId_producto();
            data[i][1] = bebida.getNombre();
            data[i][2] = bebida.getPrecio();
            data[i][3] = bebida.getTipo();
            data[i][4] = bebida.getEnvase();
        }

        // Definir los nombres de las columnas
        String[] columnNames = {"ID", "Nombre", "Precio", "Tipo", "Envase"};

        // Crear modelo de tabla y asignar datos y nombres de columnas
        DefaultTableModel model = new DefaultTableModel(data, columnNames);
        tablaBebidas.setModel(model);
    }
}
