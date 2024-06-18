import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.ArrayList;

public class ReporteEmpleados extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTable table;
    private ArrayList<Empleado> empleados;

    public ReporteEmpleados() {
        setTitle("Reporte de Empleados");
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setBounds(100, 100, 800, 400); // Se ajusta el tamaño de la ventana
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(new BorderLayout(0, 0));

        JPanel panTitulo = new JPanel();
        panTitulo.setBackground(new Color(64, 224, 208));
        contentPane.add(panTitulo, BorderLayout.NORTH);
        panTitulo.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

        JLabel lblTitulo = new JLabel("Reporte de Empleados");
        lblTitulo.setFont(new Font("Vensim Sans KR", Font.BOLD | Font.ITALIC, 20));
        panTitulo.add(lblTitulo);

        JPanel panReporte = new JPanel();
        contentPane.add(panReporte, BorderLayout.CENTER);
        panReporte.setLayout(new BorderLayout(0, 0));

        empleados = Empleado.ver_empleados();

        // Crear un modelo de tabla
        Object[][] data = getDataFromEmpleados();
        String[] columnNames = {"ID", "Nombre", "Apellido Paterno", "Apellido Materno", "Teléfono", "Correo", "Salario", "Fecha de Contratación", "Cargo"};
        table = new JTable(data, columnNames);
        JScrollPane scrollPane = new JScrollPane(table); // Agregar un panel de desplazamiento para la tabla
        panReporte.add(scrollPane, BorderLayout.CENTER);

        JPanel panBoton = new JPanel();
        FlowLayout fl_panBoton = (FlowLayout) panBoton.getLayout();
        fl_panBoton.setAlignment(FlowLayout.RIGHT);
        panReporte.add(panBoton, BorderLayout.SOUTH);

        JButton btnModificar = new JButton("Modificar Empleado");
        panBoton.add(btnModificar);

        btnModificar.addActionListener(e -> {
            int rowIndex = table.getSelectedRow();
            if (rowIndex != -1) {
                Empleado empleado = empleados.get(rowIndex);
                ModificarEmpleado modificarEmpleado = new ModificarEmpleado(empleado, this); // Pasar referencia a ReporteEmpleados
                modificarEmpleado.setVisible(true);
            } else {
                JOptionPane.showMessageDialog(this, "Selecciona un empleado para modificar.");
            }
        });

        // Añadir botón de eliminar
        JButton btnEliminar = new JButton("Eliminar Empleado");
        panBoton.add(btnEliminar);

        btnEliminar.addActionListener(e -> {
            int rowIndex = table.getSelectedRow();
            if (rowIndex != -1) {
                int idEmpleado = (int) table.getValueAt(rowIndex, 0);
                Empleado.eliminar_empleado(idEmpleado);
                JOptionPane.showMessageDialog(this, "Empleado eliminado correctamente.");
                actualizarTabla(); // Actualizar la tabla
            } else {
                JOptionPane.showMessageDialog(this, "Selecciona un empleado para eliminar.");
            }
        });

        setVisible(true);
    }

    // Método para actualizar la tabla
    public void actualizarTabla() {
        empleados = Empleado.ver_empleados();
        Object[][] data = getDataFromEmpleados();
        table.setModel(new javax.swing.table.DefaultTableModel(data, new String[]{"ID", "Nombre", "Apellido Paterno", "Apellido Materno", "Teléfono", "Correo", "Salario", "Fecha de Contratación", "Cargo"}));
    }

    // Método para obtener datos de los empleados
    private Object[][] getDataFromEmpleados() {
        Object[][] data = new Object[empleados.size()][9];
        for (int i = 0; i < empleados.size(); i++) {
            Empleado empleado = empleados.get(i);
            data[i][0] = empleado.getIdEmpleado();
            data[i][1] = empleado.getNombre();
            data[i][2] = empleado.getPaterno();
            data[i][3] = empleado.getMaterno();
            data[i][4] = empleado.getTelefono();
            data[i][5] = empleado.getCorreo_electronico();
            data[i][6] = empleado.getSalario();
            data[i][7] = empleado.getFechaContratacion();
            data[i][8] = empleado.getCargo();
        }
        return data;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(ReporteEmpleados::new);
    }
}
