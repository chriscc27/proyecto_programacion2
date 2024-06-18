import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ModificarBebida extends JFrame {
    private JTextField txtIdBebida;
    private JTextField txtNombre;
    private JTextField txtPrecio;
    private JTextField txtTipo;
    private JTextField txtEnvase;

    public ModificarBebida(int idBebida) {
        setTitle("Modificar Bebida");
        setSize(300, 250);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        Bebida bebida = Bebida.buscarBebida(idBebida);

        JPanel panel = new JPanel(new GridLayout(6, 2));

        JLabel lblIdBebida = new JLabel("ID Bebida:");
        txtIdBebida = new JTextField(10);
        txtIdBebida.setText(String.valueOf(idBebida));
        txtIdBebida.setEditable(false);

        JLabel lblNombre = new JLabel("Nombre:");
        txtNombre = new JTextField(10);
        txtNombre.setText(bebida.getNombre());

        JLabel lblPrecio = new JLabel("Precio:");
        txtPrecio = new JTextField(10);
        txtPrecio.setText(String.valueOf(bebida.getPrecio()));

        JLabel lblTipo = new JLabel("Tipo:");
        txtTipo = new JTextField(10);
        txtTipo.setText(bebida.getTipo());

        JLabel lblEnvase = new JLabel("Envase:");
        txtEnvase = new JTextField(10);
        txtEnvase.setText(bebida.getEnvase());

        JButton btnModificar = new JButton("Modificar");
        btnModificar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String nombre = txtNombre.getText();
                double precio = Double.parseDouble(txtPrecio.getText());
                String tipo = txtTipo.getText();
                String envase = txtEnvase.getText();

                Bebida bebidaModificada = new Bebida(idBebida, nombre, precio, tipo, envase);
                Bebida.modificarBebida(idBebida, bebidaModificada);
                JOptionPane.showMessageDialog(null, "Bebida modificada correctamente: " + bebidaModificada);
                dispose();
            }
        });

        panel.add(lblIdBebida);
        panel.add(txtIdBebida);
        panel.add(lblNombre);
        panel.add(txtNombre);
        panel.add(lblPrecio);
        panel.add(txtPrecio);
        panel.add(lblTipo);
        panel.add(txtTipo);
        panel.add(lblEnvase);
        panel.add(txtEnvase);
        panel.add(new JLabel()); // Espacio en blanco
        panel.add(btnModificar);

        add(panel);
        setLocationRelativeTo(null); // Centrar la ventana en la pantalla
    }
}
