import java.io.*;
import java.util.ArrayList;
import java.time.LocalDate;

public class Cliente extends Persona {
    // Atributos adicionales
    private int id_cliente;
    private LocalDate fecha_registro;
    private String direccion;


    // Constructor
    public Cliente(int id_cliente, String nombre, String paterno, String materno, String telefono, String correo_electronico, LocalDate fecha_registro, String direccion) {
        // Llama al constructor de la clase padre (Persona)
        super(nombre, paterno, materno, telefono, correo_electronico);
        this.id_cliente = id_cliente;
        this.fecha_registro = fecha_registro;
        this.direccion = direccion;
    }

    // Métodos getters y setters adicionales
    public int getIdCliente() {
        return id_cliente;
    }

    public void setIdCliente(int id_cliente) {
        this.id_cliente = id_cliente;
    }

    public LocalDate getFechaRegistro() {
        return fecha_registro;
    }

    public void setFechaRegistro(LocalDate fecha_registro) {
        this.fecha_registro = fecha_registro;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    // Método para agregar clientes al archivo de texto
    public static void agregar_cliente(Cliente cliente) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(NombresArchivos.file_clientes, true))) {
            String clienteStr = cliente.getIdCliente() + ";" + cliente.getNombre() + ";" + cliente.getPaterno() + ";" +
                    cliente.getMaterno() + ";" + cliente.getTelefono() + ";" + cliente.getCorreo_electronico() + ";" +
                    cliente.getFechaRegistro() + ";" + cliente.getDireccion();
            writer.write(clienteStr);	
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Método para modificar la información de un cliente en el archivo de texto por su ID
    public static void modificar_cliente(int idCliente, Cliente clienteModificado) {
        try {
            File inputFile = new File(NombresArchivos.file_clientes);
            File tempFile = new File("temp.txt");

            BufferedReader reader = new BufferedReader(new FileReader(inputFile));
            BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));

            String currentLine;
            while ((currentLine = reader.readLine()) != null) {
                String[] datos = currentLine.split(";");
                if (Integer.parseInt(datos[0]) == idCliente) {
                    // Escribir el cliente modificado en lugar del original
                    String clienteStr = clienteModificado.getIdCliente() + ";" + clienteModificado.getNombre() + ";" +
                            clienteModificado.getPaterno() + ";" + clienteModificado.getMaterno() + ";" +
                            clienteModificado.getTelefono() + ";" + clienteModificado.getCorreo_electronico() + ";" +
                            clienteModificado.getFechaRegistro() + ";" + clienteModificado.getDireccion();
                    writer.write(clienteStr + System.getProperty("line.separator"));
                } else {
                    // Escribir la línea sin cambios
                    writer.write(currentLine + System.getProperty("line.separator"));
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


    // Método para ver la información de todos los clientes y retornar un ArrayList de tipo Cliente
    public static ArrayList<Cliente> ver_clientes() {
        ArrayList<Cliente> clientes = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(NombresArchivos.file_clientes))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] datos = line.split(";");
                int id_cliente = Integer.parseInt(datos[0]);
                String nombre = datos[1];
                String paterno = datos[2];
                String materno = datos[3];
                String telefono = datos[4];
                String correo_electronico = datos[5];
                LocalDate fecha_registro = LocalDate.parse(datos[6]);
                String direccion = datos[7];
                Cliente cliente = new Cliente(id_cliente, nombre, paterno, materno, telefono, correo_electronico, fecha_registro, direccion);
                clientes.add(cliente);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return clientes;
    }

    // Método para eliminar clientes del archivo de texto por su ID
    public static void eliminar_cliente(int idCliente) {
        try {
            File inputFile = new File(NombresArchivos.file_clientes);
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

    // Método toString para representación en cadena
    @Override
    public String toString() {
        return "Cliente{" +
                "id_cliente=" + id_cliente +
                ", nombre='" + getNombre() + '\'' +
                ", paterno='" + getPaterno() + '\'' +
                ", materno='" + getMaterno() + '\'' +
                ", telefono='" + getTelefono() + '\'' +
                ", correoElectronico='" + getCorreo_electronico() + '\'' +
                ", fechaRegistro='" + fecha_registro + '\'' +
                ", direccion='" + direccion + '\'' +
                '}';
    }
}
