import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;

public class ModificarEmpleado extends JFrame {
    private JTextField txtIdEmpleado;
    private JTextField txtNombre;
    private JTextField txtPaterno;
    private JTextField txtMaterno;
    private JTextField txtTelefono;
    private JTextField txtCorreo;
    private JTextField txtSalario;
    private JTextField txtFechaContratacion;
    private JTextField txtCargo;
    private JButton btnModificar;
    private Empleado empleadoOriginal;
    private ReporteEmpleados reporteEmpleados; // Añadir referencia al ReporteEmpleados

    public ModificarEmpleado(Empleado empleado, ReporteEmpleados reporteEmpleados) {
        this.empleadoOriginal = empleado;
        this.reporteEmpleados = reporteEmpleados; // Guardar la referencia

        setTitle("Modificar Empleado");
        setSize(300, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel(new GridLayout(10, 2));

        // Crear componentes y rellenarlos con los datos del empleado original
        JLabel lblIdEmpleado = new JLabel("ID Empleado:");
        txtIdEmpleado = new JTextField(10);
        txtIdEmpleado.setText(String.valueOf(empleado.getIdEmpleado()));
        txtIdEmpleado.setEditable(false);

        JLabel lblNombre = new JLabel("Nombre:");
        txtNombre = new JTextField(10);
        txtNombre.setText(empleado.getNombre());

        JLabel lblPaterno = new JLabel("Apellido Paterno:");
        txtPaterno = new JTextField(10);
        txtPaterno.setText(empleado.getPaterno());

        JLabel lblMaterno = new JLabel("Apellido Materno:");
        txtMaterno = new JTextField(10);
        txtMaterno.setText(empleado.getMaterno());

        JLabel lblTelefono = new JLabel("Teléfono:");
        txtTelefono = new JTextField(10);
        txtTelefono.setText(empleado.getTelefono());

        JLabel lblCorreo = new JLabel("Correo Electrónico:");
        txtCorreo = new JTextField(10);
        txtCorreo.setText(empleado.getCorreo_electronico());

        JLabel lblSalario = new JLabel("Salario:");
        txtSalario = new JTextField(10);
        txtSalario.setText(String.valueOf(empleado.getSalario()));

        JLabel lblFechaContratacion = new JLabel("Fecha de Contratación:");
        txtFechaContratacion = new JTextField(10);
        txtFechaContratacion.setText(empleado.getFechaContratacion().toString());

        JLabel lblCargo = new JLabel("Cargo:");
        txtCargo = new JTextField(10);
        txtCargo.setText(empleado.getCargo());

        btnModificar = new JButton("Modificar");
        btnModificar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                modificar();
            }
        });

        // Agregar componentes al panel
        panel.add(lblIdEmpleado);
        panel.add(txtIdEmpleado);
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
        panel.add(lblSalario);
        panel.add(txtSalario);
        panel.add(lblFechaContratacion);
        panel.add(txtFechaContratacion);
        panel.add(lblCargo);
        panel.add(txtCargo);
        panel.add(new JLabel()); // Espacio en blanco
        panel.add(btnModificar);

        add(panel);
        setLocationRelativeTo(null); // Centrar la ventana en la pantalla
    }

    private void modificar() {
        // Obtener los datos del empleado modificado
        String nombre = txtNombre.getText();
        String paterno = txtPaterno.getText();
        String materno = txtMaterno.getText();
        String telefono = txtTelefono.getText();
        String correo = txtCorreo.getText();
        double salario = Double.parseDouble(txtSalario.getText());
        LocalDate fechaContratacion = LocalDate.parse(txtFechaContratacion.getText());
        String cargo = txtCargo.getText();

        // Crear objeto Empleado modificado
        Empleado empleadoModificado = new Empleado(
                empleadoOriginal.getIdEmpleado(),
                nombre,
                paterno,
                materno,
                telefono,
                correo,
                salario,
                fechaContratacion,
                cargo
        );

        // Modificar el empleado en el archivo
        Empleado.modificar_empleado(empleadoOriginal.getIdEmpleado(), empleadoModificado);

        // Mostrar mensaje de éxito y cerrar ventana
        JOptionPane.showMessageDialog(this, "Empleado modificado correctamente");
        dispose();
        reporteEmpleados.actualizarTabla(); // Actualizar la tabla en ReporteEmpleados
    }
}
