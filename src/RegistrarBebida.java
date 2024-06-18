import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RegistrarBebida extends JFrame {
    private JTextField txtIdBebida;
    private JTextField txtNombre;
    private JTextField txtPrecio;
    private JTextField txtTipo;
    private JTextField txtEnvase;

    public RegistrarBebida() {
        setTitle("Registrar Bebida");
        setSize(300, 250);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel(new GridLayout(6, 2));

        JLabel lblIdBebida = new JLabel("ID Bebida:");
        txtIdBebida = new JTextField(10);

        JLabel lblNombre = new JLabel("Nombre:");
        txtNombre = new JTextField(10);

        JLabel lblPrecio = new JLabel("Precio:");
        txtPrecio = new JTextField(10);

        JLabel lblTipo = new JLabel("Tipo:");
        txtTipo = new JTextField(10);

        JLabel lblEnvase = new JLabel("Envase:");
        txtEnvase = new JTextField(10);

        JButton btnRegistrar = new JButton("Registrar");
        btnRegistrar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int idBebida = Integer.parseInt(txtIdBebida.getText());
                String nombre = txtNombre.getText();
                double precio = Double.parseDouble(txtPrecio.getText());
                String tipo = txtTipo.getText();
                String envase = txtEnvase.getText();

                Bebida nuevaBebida = new Bebida(idBebida, nombre, precio, tipo, envase);
                Bebida.agregarBebida(nuevaBebida);
                JOptionPane.showMessageDialog(null, "Bebida registrada correctamente: " + nuevaBebida);
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
        panel.add(btnRegistrar);

        add(panel);
        setLocationRelativeTo(null); // Centrar la ventana en la pantalla
    }
}
