import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Receta{
    // Atributos
    private int id_receta;
    private String nombre;
    private List<Ingrediente> ingredientes;

    // Variable para el nombre del archivo de recetas
    private static final String fileRecetas = "recetas.txt";

    // Constructor
    public Receta(int id_receta, String nombre) {
        this.id_receta = id_receta;
        this.nombre = nombre;
        this.ingredientes = new ArrayList<>();
    }

    // Método para agregar un ingrediente a la receta
    public void agregarIngrediente(Ingrediente ingrediente) {
        ingredientes.add(ingrediente);
    }

    // Método para agregar una receta al archivo de texto
    public static void agregarReceta(Receta receta) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileRecetas, true))) {
            StringBuilder recetaStr = new StringBuilder();
            recetaStr.append(receta.id_receta).append(";").append(receta.nombre).append(";");
            for (Ingrediente ingrediente : receta.ingredientes) {
                recetaStr.append(ingrediente.getNombre()).append(";").append(ingrediente.getStock()).append(";");
            }
            writer.write(recetaStr.toString());
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Método para eliminar una receta del archivo de texto por su ID
    public static void eliminarReceta(int idReceta) {
        try {
            File inputFile = new File(fileRecetas);
            File tempFile = new File("temp.txt");

            BufferedReader reader = new BufferedReader(new FileReader(inputFile));
            BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));

            String lineToRemove = idReceta + ";";

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

    // Método para modificar una receta en el archivo de texto por su ID
    public static void modificarReceta(int idReceta, Receta recetaModificada) {
        eliminarReceta(idReceta);
        agregarReceta(recetaModificada);
    }
    
    public static void verRecetas() {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileRecetas))) {
            String line;
            while ((line = reader.readLine()) != null) {

                String[] partes = line.split(";");
                int id = Integer.parseInt(partes[0]);
                String nombre = partes[1];
                System.out.println("ID: " + id + ", Nombre: " + nombre);

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
