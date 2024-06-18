import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RegistrarPlato extends JFrame {
    private JTextField txtIdPlato;
    private JTextField txtNombre;
    private JTextField txtPrecio;
    private JTextField txtCategoria;
    private JTextField txtTipoPresentacion;

    public RegistrarPlato() {
        setTitle("Registrar Plato");
        setSize(300, 250);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel(new GridLayout(6, 2, 5, 5));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel lblIdPlato = new JLabel("ID Plato:");
        txtIdPlato = new JTextField(10);

        JLabel lblNombre = new JLabel("Nombre:");
        txtNombre = new JTextField(10);

        JLabel lblPrecio = new JLabel("Precio:");
        txtPrecio = new JTextField(10);

        JLabel lblCategoria = new JLabel("Categoría:");
        txtCategoria = new JTextField(10);

        JLabel lblTipoPresentacion = new JLabel("Tipo Presentación:");
        txtTipoPresentacion = new JTextField(10);

        JButton btnRegistrar = new JButton("Registrar");
        btnRegistrar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    int idPlato = Integer.parseInt(txtIdPlato.getText());
                    String nombre = txtNombre.getText();
                    String precioStr = txtPrecio.getText();
                    String categoria = txtCategoria.getText();
                    String tipoPresentacion = txtTipoPresentacion.getText();

                    if (nombre.isEmpty() || categoria.isEmpty() || tipoPresentacion.isEmpty() || precioStr.isEmpty()) {
                        throw new IllegalArgumentException("Todos los campos deben estar completos.");
                    }

                    double precio = Double.parseDouble(precioStr);
                    if (precio <= 0) {
                        throw new IllegalArgumentException("El precio debe ser mayor que cero.");
                    }

                    Plato nuevoPlato = new Plato(idPlato, nombre, precio, categoria, tipoPresentacion);
                    Plato.agregarPlato(nuevoPlato);
                    JOptionPane.showMessageDialog(null, "Plato registrado correctamente: " + nuevoPlato);
                    dispose();
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Error en el formato del ID o del precio.", "Error", JOptionPane.ERROR_MESSAGE);
                } catch (IllegalArgumentException ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        panel.add(lblIdPlato);
        panel.add(txtIdPlato);
        panel.add(lblNombre);
        panel.add(txtNombre);
        panel.add(lblPrecio);
        panel.add(txtPrecio);
        panel.add(lblCategoria);
        panel.add(txtCategoria);
        panel.add(lblTipoPresentacion);
        panel.add(txtTipoPresentacion);
        panel.add(new JLabel()); // Espacio en blanco
        panel.add(btnRegistrar);

        add(panel);
        setLocationRelativeTo(null); // Centrar la ventana en la pantalla
    }
}
