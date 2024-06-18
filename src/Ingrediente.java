import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class Ingrediente {
    // Atributos esenciales
    private int id_ingrediente;
    private String descripcion;
    private String nombre;
    private int stock;



    // Constructor
    public Ingrediente(int id_ingrediente, String descripcion, String nombre, int stock) {
        this.id_ingrediente = id_ingrediente;
        this.descripcion = descripcion;
        this.nombre = nombre;
        this.stock = stock;
    }

    // Métodos getters y setters
    public int getId_ingrediente() {
        return id_ingrediente;
    }

    public void setId_ingrediente(int id_ingrediente) {
        this.id_ingrediente = id_ingrediente;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    // Método para agregar ingredientes al archivo de texto
    public static void agregar_ingrediente(Ingrediente ingrediente) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(NombresArchivos.file_ingredientes, true))) {
            String ingredienteStr = ingrediente.getId_ingrediente() + ";" + ingrediente.getDescripcion() + ";" +
                    ingrediente.getNombre() + ";" + ingrediente.getStock();
            writer.write(ingredienteStr);
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Método para eliminar ingredientes del archivo de texto por su ID
    public static void eliminar_ingrediente(int id_ingrediente) {
        try {
            File inputFile = new File(NombresArchivos.file_ingredientes);
            File tempFile = new File("temp.txt");

            BufferedReader reader = new BufferedReader(new FileReader(inputFile));
            BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));

            String lineToRemove = id_ingrediente + ";";

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
    public static void añadir_stock(int id_ingrediente, int cantidad) {
        try {
            File inputFile = new File(NombresArchivos.file_ingredientes);
            File tempFile = new File("temp.txt");

            BufferedReader reader = new BufferedReader(new FileReader(inputFile));
            BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));

            String lineToModify = id_ingrediente + ";";

            String currentLine;
            while ((currentLine = reader.readLine()) != null) {
                if (currentLine.contains(lineToModify)) {
                    String[] datos = currentLine.split(";");
                    int stockActual = Integer.parseInt(datos[3]);
                    stockActual += cantidad;
                    datos[3] = String.valueOf(stockActual);
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
    public static ArrayList<Ingrediente> mostrar_ingredientes() {
        ArrayList<Ingrediente> ingredientes = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(NombresArchivos.file_ingredientes))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] datos = line.split(";");
                int id_ingrediente = Integer.parseInt(datos[0]);
                String descripcion = datos[1];
                String nombre = datos[2];
                int stock = Integer.parseInt(datos[3]);
                Ingrediente ingrediente = new Ingrediente(id_ingrediente, descripcion, nombre, stock);
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
                ", nombre='" + nombre + '\'' +
                ", stock=" + stock +
                '}';
    }
}
