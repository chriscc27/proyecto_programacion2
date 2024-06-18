import java.io.*;
import java.util.ArrayList;

public class Bebida extends Producto {
    private String tipo;
    private String envase;

    public Bebida(int idProducto, String nombre, double precio, String tipo, String envase) {
        super(idProducto, nombre, precio);
        this.tipo = tipo;
        this.envase = envase;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getEnvase() {
        return envase;
    }

    public void setEnvase(String envase) {
        this.envase = envase;
    }

    // Método para agregar bebidas al archivo de texto
    public static void agregarBebida(Bebida bebida) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(NombresArchivos.file_bebidas, true))) {
            String bebidaStr = bebida.getId_producto() + ";" + bebida.getNombre() + ";" + bebida.getPrecio() + ";" +
                    bebida.getTipo() + ";" + bebida.getEnvase();
            writer.write(bebidaStr);
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Método para modificar la información de una bebida en el archivo de texto por su ID
    public static void modificarBebida(int idBebida, Bebida bebidaModificada) {
        eliminarBebida(idBebida);
        agregarBebida(bebidaModificada);
    }

    // Método para mostrar la información de todas las bebidas
    public static void mostrarBebidas() {
        try (BufferedReader reader = new BufferedReader(new FileReader(NombresArchivos.file_bebidas))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] datos = line.split(";");
                int id = Integer.parseInt(datos[0]);
                String nombre = datos[1];
                double precio = Double.parseDouble(datos[2]);
                String tipo = datos[3];
                String envase = datos[4];
                Bebida bebida = new Bebida(id, nombre, precio, tipo, envase);
                System.out.println(bebida);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Método para eliminar bebidas del archivo de texto por su ID
    public static void eliminarBebida(int idBebida) {
        try {
            File inputFile = new File(NombresArchivos.file_bebidas);
            File tempFile = new File("temp.txt");

            BufferedReader reader = new BufferedReader(new FileReader(inputFile));
            BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));

            String lineToRemove = idBebida + ";";

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
        return "Bebida{" +
                "tipo='" + tipo + '\'' +
                ", envase='" + envase + '\'' +
                "} " + super.toString();
    }
}
