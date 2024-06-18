import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RegistroLibro extends JFrame {
    private JTextField txtIdLibro;
    private JTextField txtTitulo;
    private JTextField txtAutor;
    private JTextField txtAnio;
    private JTextField txtISBN;
    private JButton btnRegistrar;
    private ReporteLibros reporteLibros;

    public RegistroLibro() {


        setTitle("Registro de Libro");
        setSize(300, 250);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel(new GridLayout(6, 2));

        JLabel lblIdLibro = new JLabel("ID Libro:");
        txtIdLibro = new JTextField(10);

        JLabel lblTitulo = new JLabel("Título:");
        txtTitulo = new JTextField(10);

        JLabel lblAutor = new JLabel("Autor:");
        txtAutor = new JTextField(10);

        JLabel lblAnio = new JLabel("Año de Publicación:");
        txtAnio = new JTextField(10);

        JLabel lblISBN = new JLabel("ISBN:");
        txtISBN = new JTextField(10);

        btnRegistrar = new JButton("Registrar");
        btnRegistrar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                registrar();
            }
        });

        panel.add(lblIdLibro);
        panel.add(txtIdLibro);
        panel.add(lblTitulo);
        panel.add(txtTitulo);
        panel.add(lblAutor);
        panel.add(txtAutor);
        panel.add(lblAnio);
        panel.add(txtAnio);
        panel.add(lblISBN);
        panel.add(txtISBN);
        panel.add(new JLabel()); // Espacio en blanco
        panel.add(btnRegistrar);

        add(panel);
        setLocationRelativeTo(null); // Centrar la ventana en la pantalla
    }

    private void registrar() {
        int idLibro = Integer.parseInt(txtIdLibro.getText());
        String titulo = txtTitulo.getText();
        String autor = txtAutor.getText();
        int anio = Integer.parseInt(txtAnio.getText());
        String isbn = txtISBN.getText();

        Libro libro = new Libro(idLibro, titulo, autor, anio, isbn);
        Libro.guardarLibro(libro);

        JOptionPane.showMessageDialog(this, "Libro registrado correctamente");
        limpiarCampos();
    }

    private void limpiarCampos() {
        txtIdLibro.setText("");
        txtTitulo.setText("");
        txtAutor.setText("");
        txtAnio.setText("");
        txtISBN.setText("");
    }
}
