import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;

public class ModificarCliente extends JFrame {
    private JTextField txtIdCliente;
    private JTextField txtNombre;
    private JTextField txtPaterno;
    private JTextField txtMaterno;
    private JTextField txtTelefono;
    private JTextField txtCorreo;
    private JTextField txtFechaRegistro;
    private JTextField txtDireccion;
    private JButton btnModificar;
    private Cliente clienteOriginal;
    private ReporteClientes reporteClientes; // Referencia a ReporteClientes

    public ModificarCliente(Cliente cliente, ReporteClientes reporteClientes) {
        this.clienteOriginal = cliente;
        this.reporteClientes = reporteClientes; // Guardar la referencia

        setTitle("Modificar Cliente");
        setSize(300, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel(new GridLayout(9, 2));

        // Crear componentes y rellenarlos con los datos del cliente original
        JLabel lblIdCliente = new JLabel("ID Cliente:");
        txtIdCliente = new JTextField(10);
        txtIdCliente.setText(String.valueOf(cliente.getIdCliente()));
        txtIdCliente.setEditable(false);

        JLabel lblNombre = new JLabel("Nombre:");
        txtNombre = new JTextField(10);
        txtNombre.setText(cliente.getNombre());

        JLabel lblPaterno = new JLabel("Apellido Paterno:");
        txtPaterno = new JTextField(10);
        txtPaterno.setText(cliente.getPaterno());

        JLabel lblMaterno = new JLabel("Apellido Materno:");
        txtMaterno = new JTextField(10);
        txtMaterno.setText(cliente.getMaterno());

        JLabel lblTelefono = new JLabel("Teléfono:");
        txtTelefono = new JTextField(10);
        txtTelefono.setText(cliente.getTelefono());

        JLabel lblCorreo = new JLabel("Correo Electrónico:");
        txtCorreo = new JTextField(10);
        txtCorreo.setText(cliente.getCorreo_electronico());

        JLabel lblFechaRegistro = new JLabel("Fecha de Registro:");
        txtFechaRegistro = new JTextField(10);
        txtFechaRegistro.setText(cliente.getFechaRegistro().toString());

        JLabel lblDireccion = new JLabel("Dirección:");
        txtDireccion = new JTextField(10);
        txtDireccion.setText(cliente.getDireccion());

        btnModificar = new JButton("Modificar");
        btnModificar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                modificar();
            }
        });

        // Agregar componentes al panel
        panel.add(lblIdCliente);
        panel.add(txtIdCliente);
        panel.add(lblNombre);
        panel.add(txtNombre);
        panel.add(lblPaterno);
        panel.add(txtPaterno);
        panel.add(lblMaterno);
        panel.add(txtMaterno);
        panel.add(lblTelefono);
        panel.add(txtTelefono);
        panel.add(lblCorreo);
        panel.add(txtCorreo);
        panel.add(lblFechaRegistro);
        panel.add(txtFechaRegistro);
        panel.add(lblDireccion);
        panel.add(txtDireccion);
        panel.add(new JLabel()); // Espacio en blanco
        panel.add(btnModificar);

        add(panel);
        setLocationRelativeTo(null); // Centrar la ventana en la pantalla
    }

    private void modificar() {
        // Obtener los datos del cliente modificado
        String nombre = txtNombre.getText();
        String paterno = txtPaterno.getText();
        String materno = txtMaterno.getText();
        String telefono = txtTelefono.getText();
        String correo = txtCorreo.getText();
        LocalDate fechaRegistro = LocalDate.parse(txtFechaRegistro.getText());
        String direccion = txtDireccion.getText();

        // Crear objeto Cliente modificado
        Cliente clienteModificado = new Cliente(
                clienteOriginal.getIdCliente(),
                nombre,
                paterno,
                materno,
                telefono,
                correo,
                fechaRegistro,
                direccion
        );

        // Modificar el cliente en el archivo
        Cliente.modificar_cliente(clienteOriginal.getIdCliente(), clienteModificado);

        // Mostrar mensaje de éxito y cerrar ventana
        JOptionPane.showMessageDialog(this, "Cliente modificado correctamente");
        dispose();
        reporteClientes.actualizarTabla(); // Actualizar la tabla en ReporteClientes
    }
}
