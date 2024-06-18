import java.io.*;
import java.util.ArrayList;

public class Libro {
    // Atributos esenciales
    private int id_libro;
    private String titulo;
    private String autor;
    private int anio_publicacion;
    private String isbn;
    private boolean estado; // Indica si el libro está disponible (true) o no (false)

    // Constructor
    public Libro(int id_libro, String titulo, String autor, int anio_publicacion, String isbn) {
        this.id_libro = id_libro;
        this.titulo = titulo;
        this.autor = autor;
        this.anio_publicacion = anio_publicacion;
        this.isbn = isbn;
        this.estado = false; // Por defecto, el libro no está prestado
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

    public int getAnio_publicacion() {
        return anio_publicacion;
    }

    public void setAnio_publicacion(int anio_publicacion) {
        this.anio_publicacion = anio_publicacion;
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
    private static void guardarLibros(ArrayList<Libro> libros) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(NombresArchivos.file_libros))) {
            for (Libro libro : libros) {
                String libroStr = String.format("%d;%s;%s;%d;%s;%s",
                        libro.getId_libro(), libro.getTitulo(), libro.getAutor(),
                        libro.getAnio_publicacion(), libro.getIsbn(), libro.isEstado());
                writer.write(libroStr);
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Método para eliminar un libro del archivo de texto por su ID
    public static void eliminarLibro(int idLibro) {
        try {
            File inputFile = new File(NombresArchivos.file_libros);
            File tempFile = new File("temp.txt");

            BufferedReader reader = new BufferedReader(new FileReader(inputFile));
            BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));

            String lineToRemove = idLibro + ";";

            String currentLine;
            while ((currentLine = reader.readLine()) != null) {
                if (!currentLine.contains(lineToRemove)) {
                    writer.write(currentLine);
                    writer.newLine();
                }
            }
            writer.close();
            reader.close();
            
            if (!inputFile.delete()) {
                System.out.println("Could not delete file");
                return;
            }
            if (!tempFile.renameTo(inputFile)) {
                System.out.println("Could not rename file");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Método para modificar la información de un libro en el archivo de texto por su ID
    public static void modificarLibro(int idLibro, Libro libroModificado) {
        try {
            File inputFile = new File(NombresArchivos.file_libros);
            File tempFile = new File("temp.txt");

            BufferedReader reader = new BufferedReader(new FileReader(inputFile));
            BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));

            String lineToModify = idLibro + ";";

            String currentLine;
            while ((currentLine = reader.readLine()) != null) {
                if (currentLine.contains(lineToModify)) {
                    // Modificar la línea con los datos del libro modificado
                    String libroStr = libroModificado.getId_libro() + ";" + libroModificado.getTitulo() + ";" +
                            libroModificado.getAutor() + ";" + libroModificado.getAnio_publicacion() + ";" +
                            libroModificado.getIsbn() + ";" + libroModificado.isEstado();
                    writer.write(libroStr);
                } else {
                    // Escribir la línea sin cambios
                    writer.write(currentLine);
                }
                writer.newLine();
            }
            writer.close();
            reader.close();
            
            if (!inputFile.delete()) {
                System.out.println("Could not delete file");
                return;
            }
            if (!tempFile.renameTo(inputFile)) {
                System.out.println("Could not rename file");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Método para ver la información de todos los libros y retornar un ArrayList
    public static ArrayList<Libro> verLibros() {
        ArrayList<Libro> libros = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(NombresArchivos.file_libros))) {
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

                // Crear un objeto Libro temporal con los datos de la línea y agregarlo a la lista
                Libro libro = new Libro(id, titulo, autor, anio, isbn);
                libro.setEstado(estado);
                libros.add(libro);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return libros;
    }
    
    public static void modificarEstadoLibro(int idLibro, boolean nuevoEstado) {
        ArrayList<Libro> libros = verLibros();
        for (Libro libro : libros) {
            if (libro.getId_libro() == idLibro) {
                libro.setEstado(nuevoEstado);
                break; // No necesitamos seguir buscando
            }
        }
        guardarLibros(libros); // Guardar la lista actualizada en el archivo
    }

    // Método toString para representación en cadena
    @Override
    public String toString() {
        return "Libro{" +
                "id_libro=" + id_libro +
                ", titulo='" + titulo + '\'' +
                ", autor='" + autor + '\'' +
                ", anio_publicacion=" + anio_publicacion +
                ", isbn='" + isbn + '\'' +
                ", estado=" + estado +
                '}';
    }
}