import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ModificarPlato extends JFrame {
    private JTextField txtIdPlato;
    private JTextField txtNombre;
    private JTextField txtPrecio;
    private JTextField txtCategoria;
    private JTextField txtTipoPresentacion;

    public ModificarPlato(int idPlato) {
        setTitle("Modificar Plato");
        setSize(300, 250);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        Plato plato = Plato.obtenerPlatosDisponibles().stream()
                .filter(p -> p.getId_producto() == idPlato)
                .findFirst()
                .orElse(null);

        if (plato == null) {
            JOptionPane.showMessageDialog(null, "Plato no encontrado.");
            dispose();
            return;
        }

        JPanel panel = new JPanel(new GridLayout(6, 2));

        JLabel lblIdPlato = new JLabel("ID Plato:");
        txtIdPlato = new JTextField(10);
        txtIdPlato.setText(String.valueOf(plato.getId_producto()));
        txtIdPlato.setEditable(false);

        JLabel lblNombre = new JLabel("Nombre:");
        txtNombre = new JTextField(10);
        txtNombre.setText(plato.getNombre());

        JLabel lblPrecio = new JLabel("Precio:");
        txtPrecio = new JTextField(10);
        txtPrecio.setText(String.valueOf(plato.getPrecio()));

        JLabel lblCategoria = new JLabel("Categoría:");
        txtCategoria = new JTextField(10);
        txtCategoria.setText(plato.getCategoria());

        JLabel lblTipoPresentacion = new JLabel("Tipo Presentación:");
        txtTipoPresentacion = new JTextField(10);
        txtTipoPresentacion.setText(plato.getTipoPresentacion());

        JButton btnModificar = new JButton("Modificar");
        btnModificar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String nombre = txtNombre.getText();
                double precio = Double.parseDouble(txtPrecio.getText());
                String categoria = txtCategoria.getText();
                String tipoPresentacion = txtTipoPresentacion.getText();

                Plato platoModificado = new Plato(idPlato, nombre, precio, categoria, tipoPresentacion);
                Plato.modificarPlato(idPlato, platoModificado);
                JOptionPane.showMessageDialog(null, "Plato modificado correctamente: " + platoModificado);
                dispose();
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
        panel.add(btnModificar);

        add(panel);
        setLocationRelativeTo(null); // Centrar la ventana en la pantalla
    }
}
