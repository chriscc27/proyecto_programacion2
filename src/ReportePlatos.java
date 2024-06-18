import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ReportePlatos extends JFrame {
    private JTable tablaPlatos;
    private ArrayList<VentaItem> orden; // ArrayList para almacenar la orden

    public ReportePlatos(ArrayList<VentaItem> orden) {
        this.orden = orden;
        setTitle("Reporte de Platos");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel(new BorderLayout());

        // Crear tabla para mostrar los platos
        tablaPlatos = new JTable();
        JScrollPane scrollPane = new JScrollPane(tablaPlatos);
        panel.add(scrollPane, BorderLayout.CENTER);

        // Botones para añadir, eliminar y modificar platos
        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton btnAgregar = new JButton("Añadir Plato");
        JButton btnEliminar = new JButton("Eliminar Plato");
        JButton btnModificar = new JButton("Modificar Plato");
        JButton btnAñadirOrden = new JButton("Añadir a la Orden");

        btnAgregar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                RegistrarPlato registrarPlato = new RegistrarPlato();
                registrarPlato.setVisible(true);
                registrarPlato.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosed(java.awt.event.WindowEvent windowEvent) {
                        actualizarTabla();
                    }
                });
            }
        });

        btnEliminar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int filaSeleccionada = tablaPlatos.getSelectedRow();
                if (filaSeleccionada != -1) {
                    int idPlato = (int) tablaPlatos.getValueAt(filaSeleccionada, 0);
                    Plato.eliminarPlato(idPlato);
                    actualizarTabla();
                } else {
                    JOptionPane.showMessageDialog(null, "Por favor, selecciona un plato para eliminar.");
                }
            }
        });

        btnModificar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int filaSeleccionada = tablaPlatos.getSelectedRow();
                if (filaSeleccionada != -1) {
                    int idPlato = (int) tablaPlatos.getValueAt(filaSeleccionada, 0);
                    ModificarPlato modificarPlato = new ModificarPlato(idPlato);
                    modificarPlato.setVisible(true);
                    modificarPlato.addWindowListener(new java.awt.event.WindowAdapter() {
                        @Override
                        public void windowClosed(java.awt.event.WindowEvent windowEvent) {
                            actualizarTabla();
                        }
                    });
                } else {
                    JOptionPane.showMessageDialog(null, "Por favor, selecciona un plato para modificar.");
                }
            }
        });

        btnAñadirOrden.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int filaSeleccionada = tablaPlatos.getSelectedRow();
                if (filaSeleccionada != -1) {
                    int idPlato = (int) tablaPlatos.getValueAt(filaSeleccionada, 0);
                    String nombrePlato = (String) tablaPlatos.getValueAt(filaSeleccionada, 1);
                    añadirProducto(nombrePlato, idPlato);
                } else {
                    JOptionPane.showMessageDialog(null, "Por favor, selecciona un plato para añadir a la orden.");
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

    private Plato buscarPlatoPorID(int id) {
        // Método para buscar un plato por su ID
        ArrayList<Plato> platos = Plato.obtenerPlatosDisponibles();
        for (Plato plato : platos) {
            if (plato.getId_producto() == id) {
                return plato;
            }
        }
        return null; // Si no se encuentra el plato
    }

    private void añadirProducto(String nombre, int id) {
        Plato platoSeleccionado = buscarPlatoPorID(id);
        if (platoSeleccionado != null) {
            double precioUnitario = platoSeleccionado.getPrecio();
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
            JOptionPane.showMessageDialog(null, "Plato no encontrado.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void actualizarTabla() {
        ArrayList<Plato> platos = Plato.obtenerPlatosDisponibles();
        String[] columnas = {"ID", "Nombre", "Precio", "Categoría", "Tipo Presentación"};
        DefaultTableModel model = new DefaultTableModel(columnas, 0);

        for (Plato plato : platos) {
            Object[] fila = {plato.getId_producto(), plato.getNombre(), plato.getPrecio(), plato.getCategoria(), plato.getTipoPresentacion()};
            model.addRow(fila);
        }

        tablaPlatos.setModel(model);
    }
}
