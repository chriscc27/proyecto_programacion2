import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class Ingrediente {
    // Atributos
    private int id_ingrediente;
    private String descripcion;
    private LocalDate fechaCaducidad;
    private String nombre;
    private LocalDate fechaUltimaCompra;
    private int stock;

    // Variable para el nombre del archivo de ingredientes
    private static final String fileIngredientes = "ingredientes.txt";

    // Constructor
    public Ingrediente(int id_ingrediente, String descripcion, LocalDate fechaCaducidad, String nombre, LocalDate fechaUltimaCompra, int stock) {
        this.id_ingrediente = id_ingrediente;
        this.descripcion = descripcion;
        this.fechaCaducidad = fechaCaducidad;
        this.nombre = nombre;
        this.fechaUltimaCompra = fechaUltimaCompra;
        this.stock = stock;
    }

    // Métodos getters y setters
    public int getIdIngrediente() {
        return id_ingrediente;
    }

    public void setIdIngrediente(int id_ingrediente) {
        this.id_ingrediente = id_ingrediente;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public LocalDate getFechaCaducidad() {
        return fechaCaducidad;
    }

    public void setFechaCaducidad(LocalDate fechaCaducidad) {
        this.fechaCaducidad = fechaCaducidad;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public LocalDate getFechaUltimaCompra() {
        return fechaUltimaCompra;
    }

    public void setFechaUltimaCompra(LocalDate fechaUltimaCompra) {
        this.fechaUltimaCompra = fechaUltimaCompra;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    // Método para agregar ingredientes al archivo de texto
    public static void agregarIngrediente(Ingrediente ingrediente) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileIngredientes, true))) {
            String ingredienteStr = ingrediente.getIdIngrediente() + ";" + ingrediente.getDescripcion() + ";" +
                    ingrediente.getFechaCaducidad() + ";" + ingrediente.getNombre() + ";" +
                    ingrediente.getFechaUltimaCompra() + ";" + ingrediente.getStock();
            writer.write(ingredienteStr);
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Método para eliminar ingredientes del archivo de texto por su ID
    public static void eliminarIngrediente(int idIngrediente) {
        try {
            File inputFile = new File(fileIngredientes);
            File tempFile = new File("temp.txt");

            BufferedReader reader = new BufferedReader(new FileReader(inputFile));
            BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));

            String lineToRemove = idIngrediente + ";";

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

    // Método para añadir stock a un ingrediente en el archivo de texto por su ID
    public static void añadirStock(int idIngrediente, int cantidad) {
        try {
            File inputFile = new File(fileIngredientes);
            File tempFile = new File("temp.txt");

            BufferedReader reader = new BufferedReader(new FileReader(inputFile));
            BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));

            String lineToModify = idIngrediente + ";";

            String currentLine;
            while ((currentLine = reader.readLine()) != null) {
                if (currentLine.contains(lineToModify)) {
                    String[] datos = currentLine.split(";");
                    int stockActual = Integer.parseInt(datos[5]);
                    stockActual += cantidad;
                    datos[5] = String.valueOf(stockActual);
                    currentLine = String.join(";", datos);
                }
                writer.write(currentLine + System.getProperty("line.separator"));
            }
            writer.close();
            reader.close();
            tempFile.renameTo(inputFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Método para mostrar la información de todos los ingredientes y retornar un ArrayList de tipo Ingrediente
    public static ArrayList<Ingrediente> mostrarIngredientes() {
        ArrayList<Ingrediente> ingredientes = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(fileIngredientes))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] datos = line.split(";");
                int id_ingrediente = Integer.parseInt(datos[0]);
                String descripcion = datos[1];
                LocalDate fechaCaducidad = LocalDate.parse(datos[2]);
                String nombre = datos[3];
                LocalDate fechaUltimaCompra = LocalDate.parse(datos[4]);
                int stock = Integer.parseInt(datos[5]);
                Ingrediente ingrediente = new Ingrediente(id_ingrediente, descripcion, fechaCaducidad, nombre, fechaUltimaCompra, stock);
                ingredientes.add(ingrediente);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ingredientes;
    }

    // Método toString para representación en cadena
    @Override
    public String toString() {
        return "Ingrediente{" +
                "id_ingrediente=" + id_ingrediente +
                ", descripcion='" + descripcion + '\'' +
                ", fechaCaducidad=" + fechaCaducidad +
                ", nombre='" + nombre + '\'' +
                ", fechaUltimaCompra=" + fechaUltimaCompra +
                ", stock=" + stock +
                '}';
    }
}
