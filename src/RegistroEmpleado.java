import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;

public class RegistroEmpleado extends JFrame {
    private JLabel lblIdEmpleado;
    private JLabel lblNombre;
    private JLabel lblPaterno;
    private JLabel lblMaterno;
    private JLabel lblTelefono;
    private JLabel lblCorreo;
    private JLabel lblSalario;
    private JLabel lblFechaContratacion;
    private JLabel lblCargo;

    private JTextField txtIdEmpleado;
    private JTextField txtNombre;
    private JTextField txtPaterno;
    private JTextField txtMaterno;
    private JTextField txtTelefono;
    private JTextField txtCorreo;
    private JTextField txtSalario;
    private JTextField txtFechaContratacion;
    private JTextField txtCargo;

    private JButton btnRegistrar;

    public RegistroEmpleado() {
        setTitle("Registro de Empleado");
        setSize(400, 400);
        setLayout(new GridLayout(10, 2));

        lblIdEmpleado = new JLabel("ID Empleado:");
        lblNombre = new JLabel("Nombre:");
        lblPaterno = new JLabel("Apellido Paterno:");
        lblMaterno = new JLabel("Apellido Materno:");
        lblTelefono = new JLabel("Teléfono:");
        lblCorreo = new JLabel("Correo Electrónico:");
        lblSalario = new JLabel("Salario:");
        lblFechaContratacion = new JLabel("Fecha Contratación (YYYY-MM-DD):");
        lblCargo = new JLabel("Cargo:");

        txtIdEmpleado = new JTextField();
        txtNombre = new JTextField();
        txtPaterno = new JTextField();
        txtMaterno = new JTextField();
        txtTelefono = new JTextField();
        txtCorreo = new JTextField();
        txtSalario = new JTextField();
        txtFechaContratacion = new JTextField();
        txtCargo = new JTextField();

        btnRegistrar = new JButton("Registrar");

        add(lblIdEmpleado);
        add(txtIdEmpleado);
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
        add(lblSalario);
        add(txtSalario);
        add(lblFechaContratacion);
        add(txtFechaContratacion);
        add(lblCargo);
        add(txtCargo);
        add(new JLabel()); // Espacio en blanco
        add(btnRegistrar);

        btnRegistrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Obtener los datos ingresados
                int idEmpleado = Integer.parseInt(txtIdEmpleado.getText());
                String nombre = txtNombre.getText();
                String paterno = txtPaterno.getText();
                String materno = txtMaterno.getText();
                String telefono = txtTelefono.getText();
                String correo = txtCorreo.getText();
                double salario = Double.parseDouble(txtSalario.getText());
                LocalDate fechaContratacion = LocalDate.parse(txtFechaContratacion.getText());
                String cargo = txtCargo.getText();

                // Crear un nuevo objeto Empleado con los datos ingresados
                Empleado empleado = new Empleado(idEmpleado, nombre, paterno, materno, telefono, correo,
                        salario, fechaContratacion, cargo);

                // Llamar al método para agregar el empleado
                Empleado.agregar_empleado(empleado);

                // Mostrar mensaje de éxito
                JOptionPane.showMessageDialog(null, "Empleado registrado correctamente");

                // Limpiar los campos
                limpiarCampos();
            }
        });

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);
    }

    private void limpiarCampos() {
        txtIdEmpleado.setText("");
        txtNombre.setText("");
        txtPaterno.setText("");
        txtMaterno.setText("");
        txtTelefono.setText("");
        txtCorreo.setText("");
        txtSalario.setText("");
        txtFechaContratacion.setText("");
        txtCargo.setText("");
    }
}
