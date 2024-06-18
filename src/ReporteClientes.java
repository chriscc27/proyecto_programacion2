import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.ArrayList;

public class ReporteClientes extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTable table;
    private ArrayList<Cliente> clientes;

    public ReporteClientes() {
        setTitle("Reporte de Clientes");
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

        JLabel lblTitulo = new JLabel("Reporte de Clientes");
        lblTitulo.setFont(new Font("Vensim Sans KR", Font.BOLD | Font.ITALIC, 20));
        panTitulo.add(lblTitulo);

        JPanel panReporte = new JPanel();
        contentPane.add(panReporte, BorderLayout.CENTER);
        panReporte.setLayout(new BorderLayout(0, 0));

        clientes = Cliente.ver_clientes();

        // Crear un modelo de tabla
        Object[][] data = getDataFromClientes();
        String[] columnNames = {"ID", "Nombre", "Apellido Paterno", "Apellido Materno", "Teléfono", "Correo", "Fecha de Registro", "Dirección"};
        table = new JTable(data, columnNames);
        JScrollPane scrollPane = new JScrollPane(table); // Agregar un panel de desplazamiento para la tabla
        panReporte.add(scrollPane, BorderLayout.CENTER);

        JPanel panBoton = new JPanel();
        FlowLayout fl_panBoton = (FlowLayout) panBoton.getLayout();
        fl_panBoton.setAlignment(FlowLayout.RIGHT);
        panReporte.add(panBoton, BorderLayout.SOUTH);

        JButton btnModificar = new JButton("Modificar Cliente");
        panBoton.add(btnModificar);

        btnModificar.addActionListener(e -> {
            int rowIndex = table.getSelectedRow();
            if (rowIndex != -1) {
                Cliente cliente = clientes.get(rowIndex);
                ModificarCliente modificarCliente = new ModificarCliente(cliente, this); // Pasar referencia a ReporteClientes
                modificarCliente.setVisible(true);
            } else {
                JOptionPane.showMessageDialog(this, "Selecciona un cliente para modificar.");
            }
        });

        // Añadir botón de eliminar
        JButton btnEliminar = new JButton("Eliminar Cliente");
        panBoton.add(btnEliminar);

        btnEliminar.addActionListener(e -> {
            int rowIndex = table.getSelectedRow();
            if (rowIndex != -1) {
                int idCliente = (int) table.getValueAt(rowIndex, 0);
                Cliente.eliminar_cliente(idCliente);
                JOptionPane.showMessageDialog(this, "Cliente eliminado correctamente.");
                actualizarTabla(); // Actualizar la tabla
            } else {
                JOptionPane.showMessageDialog(this, "Selecciona un cliente para eliminar.");
            }
        });

        setVisible(true);
    }

    // Método para actualizar la tabla
    public void actualizarTabla() {
        clientes = Cliente.ver_clientes();
        Object[][] data = getDataFromClientes();
        table.setModel(new javax.swing.table.DefaultTableModel(data, new String[]{"ID", "Nombre", "Apellido Paterno", "Apellido Materno", "Teléfono", "Correo", "Fecha de Registro", "Dirección"}));
    }

    // Método para obtener datos de los clientes
    private Object[][] getDataFromClientes() {
        Object[][] data = new Object[clientes.size()][8];
        for (int i = 0; i < clientes.size(); i++) {
            Cliente cliente = clientes.get(i);
            data[i][0] = cliente.getIdCliente();
            data[i][1] = cliente.getNombre();
            data[i][2] = cliente.getPaterno();
            data[i][3] = cliente.getMaterno();
            data[i][4] = cliente.getTelefono();
            data[i][5] = cliente.getCorreo_electronico();
            data[i][6] = cliente.getFechaRegistro();
            data[i][7] = cliente.getDireccion();
        }
        return data;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(ReporteClientes::new);
    }
}
