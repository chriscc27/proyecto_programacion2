import java.util.ArrayList;
import java.util.List;

public class Libreria{
    // Atributos
    private List<Libro> libros;

    // Constructor
    public Libreria() {
        this.libros = new ArrayList<>();
    }

    // Método para agregar un libro a la librería
    public void agregarLibro(Libro libro) {
        libros.add(libro);
    }

    // Método para buscar un libro por ISBN
    public Libro buscarPorISBN(String isbn) {
        for (Libro libro : libros) {
            if (libro.getIsbn().equals(isbn)) {
                return libro;
            }
        }
        return null; // Si no se encuentra el libro
    }

    // Método para prestar un libro por ISBN
    public void prestarLibro(String isbn) {
        Libro libro = buscarPorISBN(isbn);
        if (libro != null) {
            if (!libro.isEstado()) {
                libro.setEstado(true);
                System.out.println("El libro \"" + libro.getTitulo() + "\" ha sido prestado.");
            } else {
                System.out.println("El libro \"" + libro.getTitulo() + "\" ya está prestado.");
            }
        } else {
            System.out.println("No se encontró ningún libro con el ISBN proporcionado.");
        }
    }

    // Método para devolver un libro por ISBN
    public void devolverLibro(String isbn) {
        Libro libro = buscarPorISBN(isbn);
        if (libro != null) {
            if (libro.isEstado()) {
                libro.setEstado(false);
                System.out.println("Se ha devuelto el libro \"" + libro.getTitulo() + "\".");
            } else {
                System.out.println("El libro \"" + libro.getTitulo() + "\" no está prestado actualmente.");
            }
        } else {
            System.out.println("No se encontró ningún libro con el ISBN proporcionado.");
        }
    }

    // Método para imprimir todos los libros de la librería
    public void imprimirCatalogo() {
        System.out.println("Catálogo de la librería:");
        for (Libro libro : libros) {
            System.out.println(libro);
        }
    }
}
