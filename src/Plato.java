import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Plato extends Producto {
    private String categoria;
    private String tipo_presentacion;
    private ArrayList<String> ingredientes;

    public Plato(int idProducto, String nombre, double precio, String categoria, String tipo_presentacion) {
        super(idProducto, nombre, precio);
        this.categoria = categoria;
        this.tipo_presentacion = tipo_presentacion;
        this.ingredientes = new ArrayList<>();
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getTipoPresentacion() {
        return tipo_presentacion;
    }

    public void setTipoPresentacion(String tipo_presentacion) {
        this.tipo_presentacion = tipo_presentacion;
    }

    public void agregarIngrediente(String ingrediente) {
        this.ingredientes.add(ingrediente);
    }

    public void mostrarIngredientes() {
        for (String ingrediente : this.ingredientes) {
            System.out.println(" - " + ingrediente);
        }
    }

    // Método para agregar platos al archivo de texto
    public static void agregarPlato(Plato plato) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(NombresArchivos.file_platos, true))) {
            String platoStr = plato.getId_producto() + ";" + plato.getNombre() + ";" + plato.getPrecio() + ";" +
                    plato.getCategoria() + ";" + plato.getTipoPresentacion();
            writer.write(platoStr);
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Método para modificar la información de un plato en el archivo de texto por su ID
    public static void modificarPlato(int idPlato, Plato platoModificado) {
        eliminarPlato(idPlato);
        agregarPlato(platoModificado);
    }

    // Método para mostrar la información de todos los platos
    public static void mostrarPlatos() {
        try (BufferedReader reader = new BufferedReader(new FileReader(NombresArchivos.file_platos))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] datos = line.split(";");
                int id = Integer.parseInt(datos[0]);
                String nombre = datos[1];
                double precio = Double.parseDouble(datos[2]);
                String categoria = datos[3];
                String tipo_presentacion = datos[4];
                Plato plato = new Plato(id, nombre, precio, categoria, tipo_presentacion);

                // Leer los ingredientes del plato
                ArrayList<String> ingredientes = obtenerIngredientesPorPlato(id);
                for (String ingrediente : ingredientes) {
                    plato.agregarIngrediente(ingrediente);
                }

                // Imprimir información del plato y sus ingredientes
                System.out.println(plato);
                System.out.println("Ingredientes:");
                plato.mostrarIngredientes();
                System.out.println();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static ArrayList<String> obtenerIngredientesPorPlato(int idPlato) {
        ArrayList<String> ingredientes = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(NombresArchivos.file_ingredientes))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] datos = line.split(";");
                int idPlatoActual = Integer.parseInt(datos[0]);
                if (idPlatoActual == idPlato) {
                    ingredientes.add(datos[1]); // El segundo campo es el nombre del ingrediente
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ingredientes;
    }

    // Método para eliminar platos del archivo de texto por su ID
    public static void eliminarPlato(int idPlato) {
        try {
            File inputFile = new File(NombresArchivos.file_platos);
            File tempFile = new File("temp.txt");

            BufferedReader reader = new BufferedReader(new FileReader(inputFile));
            BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));

            String lineToRemove = idPlato + ";";

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

    @Override
    public String toString() {
        return "Plato{" +
                "idProducto=" + getId_producto() +
                ", nombre='" + getNombre() + '\'' +
                ", precio=" + getPrecio() +
                ", categoria='" + categoria + '\'' +
                ", tipo_presentacion='" + tipo_presentacion + '\'' +
                '}';
    }
}
