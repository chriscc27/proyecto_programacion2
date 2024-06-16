import java.io.*;
import java.util.ArrayList;

public class Cliente extends Persona {
    // Atributos adicionales
    private int id_cliente;
    private String feedback;
    private String direccion;

    // Variable para el nombre del archivo de clientes
    private static final String fileClientes = "clientes.txt";

    // Constructor
    public Cliente(int id_cliente, String nombre, String paterno, String materno, String telefono, String correoElectronico, String feedback, String direccion) {
        // Llama al constructor de la clase padre (Persona)
        super(nombre, paterno, materno, telefono, correoElectronico);
        this.id_cliente = id_cliente;
        this.feedback = feedback;
        this.direccion = direccion;
    }

    // Métodos getters y setters adicionales
    public int getIdCliente() {
        return id_cliente;
    }

    public void setIdCliente(int id_cliente) {
        this.id_cliente = id_cliente;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    // Método para agregar clientes al archivo de texto
    public static void agregarCliente(Cliente cliente) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileClientes, true))) {
            String clienteStr = cliente.getIdCliente() + ";" + cliente.getNombre() + ";" + cliente.getPaterno() + ";" +
                    cliente.getMaterno() + ";" + cliente.getTelefono() + ";" + cliente.getCorreoElectronico() + ";" +
                    cliente.getFeedback() + ";" + cliente.getDireccion();
            writer.write(clienteStr);	
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Método para modificar la información de un cliente en el archivo de texto por su ID
    public static void modificarCliente(int idCliente, Cliente clienteModificado) {
        eliminarCliente(idCliente);
        agregarCliente(clienteModificado);
    }

    // Método para ver la información de todos los clientes y retornar un ArrayList de tipo Cliente
    public static ArrayList<Cliente> verClientes() {
        ArrayList<Cliente> clientes = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(fileClientes))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] datos = line.split(";");
                int id_cliente = Integer.parseInt(datos[0]);
                String nombre = datos[1];
                String paterno = datos[2];
                String materno = datos[3];
                String telefono = datos[4];
                String correoElectronico = datos[5];
                String feedback = datos[6];
                String direccion = datos[7];
                Cliente cliente = new Cliente(id_cliente, nombre, paterno, materno, telefono, correoElectronico, feedback, direccion);
                clientes.add(cliente);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return clientes;
    }

    // Método para eliminar clientes del archivo de texto por su ID
    public static void eliminarCliente(int idCliente) {
        try {
            File inputFile = new File(fileClientes);
            File tempFile = new File("temp.txt");

            BufferedReader reader = new BufferedReader(new FileReader(inputFile));
            BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));

            String lineToRemove = idCliente + ";";

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

    // Método toString para representación en cadena
    @Override
    public String toString() {
        return "Cliente{" +
                "id_cliente=" + id_cliente +
                ", nombre='" + getNombre() + '\'' +
                ", paterno='" + getPaterno() + '\'' +
                ", materno='" + getMaterno() + '\'' +
                ", telefono='" + getTelefono() + '\'' +
                ", correoElectronico='" + getCorreoElectronico() + '\'' +
                ", feedback='" + feedback + '\'' +
                ", direccion='" + direccion + '\'' +
                '}';
    }
}
