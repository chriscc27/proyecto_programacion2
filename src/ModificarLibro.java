import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ModificarLibro extends JFrame {
    private JTextField txtIdLibro;
    private JTextField txtTitulo;
    private JTextField txtAutor;
    private JTextField txtAnio;
    private JTextField txtISBN;
    private JButton btnModificar;
    private Libro libroOriginal;
    private ReporteLibros reporteLibros;

    public ModificarLibro(Libro libro, ReporteLibros reporteLibros) {
        this.libroOriginal = libro;
        this.reporteLibros = reporteLibros;

        setTitle("Modificar Libro");
        setSize(300, 250);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel(new GridLayout(6, 2));

        JLabel lblIdLibro = new JLabel("ID Libro:");
        txtIdLibro = new JTextField(10);
        txtIdLibro.setText(String.valueOf(libro.getId_libro()));
        txtIdLibro.setEditable(false);

        JLabel lblTitulo = new JLabel("Título:");
        txtTitulo = new JTextField(10);
        txtTitulo.setText(libro.getTitulo());

        JLabel lblAutor = new JLabel("Autor:");
        txtAutor = new JTextField(10);
        txtAutor.setText(libro.getAutor());

        JLabel lblAnio = new JLabel("Año de Publicación:");
        txtAnio = new JTextField(10);
        txtAnio.setText(String.valueOf(libro.getAnio_publicacion()));

        JLabel lblISBN = new JLabel("ISBN:");
        txtISBN = new JTextField(10);
        txtISBN.setText(libro.getIsbn());

        btnModificar = new JButton("Modificar");
        btnModificar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                modificar();
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
        panel.add(btnModificar);

        add(panel);
        setLocationRelativeTo(null); // Centrar la ventana en la pantalla
    }

    private void modificar() {
        String titulo = txtTitulo.getText();
        String autor = txtAutor.getText();
        int anio = Integer.parseInt(txtAnio.getText());
        String isbn = txtISBN.getText();

        Libro libroModificado = new Libro(
                libroOriginal.getId_libro(),
                titulo,
                autor,
                anio,
                isbn
        );

        Libro.modificarLibro(libroOriginal.getId_libro(), libroModificado);

        JOptionPane.showMessageDialog(this, "Libro modificado correctamente");
        dispose();
        reporteLibros.actualizarTabla();
    }
}
