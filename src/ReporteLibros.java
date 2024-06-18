import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ReporteLibros extends JFrame {
    private JTable tablaLibros;

    public ReporteLibros() {
        setTitle("Reporte de Libros");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel(new BorderLayout());

        // Crear tabla para mostrar los libros
        tablaLibros = new JTable();
        JScrollPane scrollPane = new JScrollPane(tablaLibros);
        panel.add(scrollPane, BorderLayout.CENTER);

        actualizarTabla();

        // Botones para eliminar y modificar libros
        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton btnEliminar = new JButton("Eliminar Libro");
        JButton btnModificar = new JButton("Modificar Libro");
        JButton btnPrestar = new JButton("Prestar Libro");
        JButton btnDevolver = new JButton("Devolver Libro");

        btnEliminar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int selectedRow = tablaLibros.getSelectedRow();
                if (selectedRow != -1) {
                    int idLibro = (int) tablaLibros.getValueAt(selectedRow, 0);
                    Libro.eliminarLibro(idLibro);
                    JOptionPane.showMessageDialog(null, "Libro eliminado correctamente.");
                    actualizarTabla();
                } else {
                    JOptionPane.showMessageDialog(null, "Por favor, selecciona un libro para eliminar.");
                }
            }
        });

        btnModificar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int selectedRow = tablaLibros.getSelectedRow();
                if (selectedRow != -1) {
                    int idLibro = (int) tablaLibros.getValueAt(selectedRow, 0);
                    Libro libroSeleccionado = obtenerLibroPorId(idLibro);
                    if (libroSeleccionado != null) {
                        ModificarLibro modificarLibro = new ModificarLibro(libroSeleccionado, ReporteLibros.this);
                        modificarLibro.setVisible(true);
                    } else {
                        JOptionPane.showMessageDialog(null, "El libro seleccionado no existe.");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Por favor, selecciona un libro para modificar.");
                }
            }
        });

        btnPrestar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int selectedRow = tablaLibros.getSelectedRow();
                if (selectedRow != -1) {
                    int idLibro = (int) tablaLibros.getValueAt(selectedRow, 0);
                    Libro.modificarEstadoLibro(idLibro, true); // Cambiar estado a true (no disponible)
                    JOptionPane.showMessageDialog(null, "Libro prestado correctamente.");
                    actualizarTabla(); // Actualizar la tabla
                } else {
                    JOptionPane.showMessageDialog(null, "Por favor, selecciona un libro para prestar.");
                }
            }
        });

        btnDevolver.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int selectedRow = tablaLibros.getSelectedRow();
                if (selectedRow != -1) {
                    int idLibro = (int) tablaLibros.getValueAt(selectedRow, 0);
                    Libro.modificarEstadoLibro(idLibro, false); // Cambiar estado a false (disponible)
                    JOptionPane.showMessageDialog(null, "Libro devuelto correctamente.");
                    actualizarTabla(); // Actualizar la tabla
                } else {
                    JOptionPane.showMessageDialog(null, "Por favor, selecciona un libro para devolver.");
                }
            }
        });


        panelBotones.add(btnEliminar);
        panelBotones.add(btnModificar);
        panelBotones.add(btnPrestar);
        panelBotones.add(btnDevolver);
        panel.add(panelBotones, BorderLayout.SOUTH);

        add(panel);
    }

    public void actualizarTabla() {
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("ID");
        model.addColumn("Título");
        model.addColumn("Autor");
        model.addColumn("Año de Publicación");
        model.addColumn("ISBN");
        model.addColumn("Estado");

        // Obtener la lista de libros y agregarlos al modelo de tabla
        ArrayList<Libro> libros = Libro.verLibros();
        for (Libro libro : libros) {
            model.addRow(new Object[]{
                    libro.getId_libro(),
                    libro.getTitulo(),
                    libro.getAutor(),
                    libro.getAnio_publicacion(),
                    libro.getIsbn(),
                    libro.isEstado() ? "No disponible" : "Disponible"
            });
        }

        tablaLibros.setModel(model);
    }

    // Método para actualizar la tabla manteniendo la selección
    public void actualizarTabla(int idLibroSeleccionado) {
        DefaultTableModel model = (DefaultTableModel) tablaLibros.getModel();
        int rowCount = model.getRowCount();
        int selectedRow = -1;

        // Buscar la fila que contiene el libro seleccionado antes de la actualización
        for (int i = 0; i < rowCount; i++) {
            if ((int) model.getValueAt(i, 0) == idLibroSeleccionado) {
                selectedRow = i;
                break;
            }
        }

        // Actualizar la tabla
        actualizarTabla();

        // Restaurar la selección
        if (selectedRow != -1) {
            tablaLibros.setRowSelectionInterval(selectedRow, selectedRow);
        }
    }

    // Método para obtener un libro por su ID
    private Libro obtenerLibroPorId(int idLibro) {
        ArrayList<Libro> libros = Libro.verLibros();
        for (Libro libro : libros) {
            if (libro.getId_libro() == idLibro) {
                return libro;
            }
        }
        return null;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(ReporteLibros::new);
    }
}
