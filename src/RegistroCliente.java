import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;

public class RegistroCliente extends JFrame {
    private JLabel lblIdCliente;
    private JLabel lblNombre;
    private JLabel lblPaterno;
    private JLabel lblMaterno;
    private JLabel lblTelefono;
    private JLabel lblCorreo;
    private JLabel lblFechaRegistro;
    private JLabel lblDireccion;

    private JTextField txtIdCliente;
    private JTextField txtNombre;
    private JTextField txtPaterno;
    private JTextField txtMaterno;
    private JTextField txtTelefono;
    private JTextField txtCorreo;
    private JTextField txtFechaRegistro;
    private JTextField txtDireccion;

    private JButton btnRegistrar;

    public RegistroCliente() {
        setTitle("Registro de Cliente");
        setSize(400, 400);
        setLayout(new GridLayout(9, 2));

        lblIdCliente = new JLabel("ID Cliente:");
        lblNombre = new JLabel("Nombre:");
        lblPaterno = new JLabel("Apellido Paterno:");
        lblMaterno = new JLabel("Apellido Materno:");
        lblTelefono = new JLabel("Teléfono:");
        lblCorreo = new JLabel("Correo Electrónico:");
        lblFechaRegistro = new JLabel("Fecha Registro (YYYY-MM-DD):");
        lblDireccion = new JLabel("Dirección:");

        txtIdCliente = new JTextField();
        txtNombre = new JTextField();
        txtPaterno = new JTextField();
        txtMaterno = new JTextField();
        txtTelefono = new JTextField();
        txtCorreo = new JTextField();
        txtFechaRegistro = new JTextField();
        txtDireccion = new JTextField();

        btnRegistrar = new JButton("Registrar");

        add(lblIdCliente);
        add(txtIdCliente);
        add(lblNombre);
        add(txtNombre);
        add(lblPaterno);
        add(txtPaterno);
        add(lblMaterno);
        add(txtMaterno);
        add(lblTelefono);
        add(txtTelefono);
        add(lblCorreo);
        add(txtCorreo);
        add(lblFechaRegistro);
        add(txtFechaRegistro);
        add(lblDireccion);
        add(txtDireccion);
        add(new JLabel()); // Espacio en blanco
        add(btnRegistrar);

        btnRegistrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Obtener los datos ingresados
                int idCliente = Integer.parseInt(txtIdCliente.getText());
                String nombre = txtNombre.getText();
                String paterno = txtPaterno.getText();
                String materno = txtMaterno.getText();
                String telefono = txtTelefono.getText();
                String correo = txtCorreo.getText();
                LocalDate fechaRegistro = LocalDate.parse(txtFechaRegistro.getText());
                String direccion = txtDireccion.getText();

                // Crear un nuevo objeto Cliente con los datos ingresados
                Cliente cliente = new Cliente(idCliente, nombre, paterno, materno, telefono, correo,
                        fechaRegistro, direccion);

                // Llamar al método para agregar el cliente
                Cliente.agregar_cliente(cliente);

                // Mostrar mensaje de éxito
                JOptionPane.showMessageDialog(null, "Cliente registrado correctamente");

                // Limpiar los campos
                limpiarCampos();
            }
        });

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);
        setLocationRelativeTo(null); // Centrar la ventana en la pantalla
    }

    private void limpiarCampos() {
        txtIdCliente.setText("");
        txtNombre.setText("");
        txtPaterno.setText("");
        txtMaterno.setText("");
        txtTelefono.setText("");
        txtCorreo.setText("");
        txtFechaRegistro.setText("");
        txtDireccion.setText("");
    }
}
