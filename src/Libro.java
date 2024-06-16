import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Libro {
    // Atributos
    private int id_libro;
    private String titulo;
    private String autor;
    private int anioPublicacion;
    private String isbn;
    private boolean estado; // Indica si el libro está disponible (true) o no (false)

    // Variable para el nombre del archivo de libros
    private static final String fileLibros = "libros.txt";

    // Constructor
    public Libro(int id_libro, String titulo, String autor, int anioPublicacion, String isbn) {
        this.id_libro = id_libro;
        this.titulo = titulo;
        this.autor = autor;
        this.anioPublicacion = anioPublicacion;
        this.isbn = isbn;
        this.estado = false; // Por defecto, el libro no es prestado
    }

    // Métodos getters y setters
    public int getId_libro() {
        return id_libro;
    }

    public void setId_libro(int id_libro) {
        this.id_libro = id_libro;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public int getAnioPublicacion() {
        return anioPublicacion;
    }

    public void setAnioPublicacion(int anioPublicacion) {
        this.anioPublicacion = anioPublicacion;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    // Método para guardar un libro en el archivo de texto
    public static void guardarLibro(Libro libro) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileLibros, true))) {
            String libroStr = libro.getId_libro() + ";" + libro.getTitulo() + ";" + libro.getAutor() + ";" +
                    libro.getAnioPublicacion() + ";" + libro.getIsbn() + ";" + libro.isEstado();
            writer.write(libroStr);
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Método para eliminar un libro del archivo de texto por su ID
    public static void eliminarLibro(int idLibro) {
        try {
            File inputFile = new File(fileLibros);
            File tempFile = new File("temp.txt");

            BufferedReader reader = new BufferedReader(new FileReader(inputFile));
            BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));

            String lineToRemove = idLibro + ";";

            String currentLine;
            while ((currentLine = reader.readLine()) != null) {
                if (!currentLine.contains(lineToRemove)) {
                    writer.write(currentLine + System.getProperty("line.separator"));
                }
            }
            writer.close();
            reader.close();
            tempFile.renameTo(inputFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Método para modificar la información de un libro en el archivo de texto por su ID
    public static void modificarLibro(int idLibro, Libro libroModificado) {
        eliminarLibro(idLibro);
        guardarLibro(libroModificado);
    }
    
    public static void verLibros() {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileLibros))) {
            String line;
            while ((line = reader.readLine()) != null) {
                // Dividir la línea en sus partes (id, título, autor, año, isbn, estado)
                String[] partes = line.split(";");
                int id = Integer.parseInt(partes[0]);
                String titulo = partes[1];
                String autor = partes[2];
                int anio = Integer.parseInt(partes[3]);
                String isbn = partes[4];
                boolean estado = Boolean.parseBoolean(partes[5]);
                
                // Crear un objeto Libro temporal con los datos de la línea y mostrarlo
                Libro libro = new Libro(id, titulo, autor, anio, isbn);
                libro.setEstado(estado);
                System.out.println(libro);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    @Override
    public String toString() {
        return "Libro{" +
                "id_libro=" + id_libro +
                ", titulo='" + titulo + '\'' +
                ", autor='" + autor + '\'' +
                ", anioPublicacion=" + anioPublicacion +
                ", isbn='" + isbn + '\'' +
                ", estado=" + estado +
                '}';
    }
}
