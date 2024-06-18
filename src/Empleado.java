import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class Empleado extends Persona {

    // Atributos esenciales
    private int id_empleado;
    private double salario;
    private LocalDate fecha_contratacion;
    private String cargo;

    // Constructor
    public Empleado(int id_empleado, String nombre, String paterno, String materno, String telefono, String correo_electronico,
                    double salario, LocalDate fecha_contratacion, String cargo) {
        // Llama al constructor de la clase padre (Persona)
        super(nombre, paterno, materno, telefono, correo_electronico);
        this.id_empleado = id_empleado;
        this.salario = salario;
        this.fecha_contratacion = fecha_contratacion;
        this.cargo = cargo;
    }

    // Métodos getters y setters
    public int getIdEmpleado() {
        return id_empleado;
    }

    public void setIdEmpleado(int id_empleado) {
        this.id_empleado = id_empleado;
    }

    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }

    public LocalDate getFechaContratacion() {
        return fecha_contratacion;
    }

    public void setFechaContratacion(LocalDate fecha_contratacion) {
        this.fecha_contratacion = fecha_contratacion;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    // Método para agregar empleados al archivo de texto
    public static void agregar_empleado(Empleado empleado) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(NombresArchivos.file_empleados, true))) {
            String empleadoStr = empleado.getIdEmpleado() + ";" + empleado.getNombre() + ";" + empleado.getPaterno() + ";" +
                    empleado.getMaterno() + ";" + empleado.getTelefono() + ";" + empleado.getCorreo_electronico() + ";" +
                    empleado.getSalario() + ";" + empleado.getFechaContratacion() + ";" + empleado.getCargo();
            writer.write(empleadoStr);
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Método para eliminar empleados del archivo de texto por su ID
    public static void eliminar_empleado(int id_empleado) {
        try {
            File inputFile = new File(NombresArchivos.file_empleados);
            File tempFile = new File("temp.txt");

            BufferedReader reader = new BufferedReader(new FileReader(inputFile));
            BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));

            String currentLine;
            while ((currentLine = reader.readLine()) != null) {
                String[] datos = currentLine.split(";");
                if (Integer.parseInt(datos[0]) != id_empleado) {
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

    // Método para modificar la información de un empleado en el archivo de texto por su ID
    public static void modificar_empleado(int id_empleado, Empleado empleado_modificado) {
        try {
            File inputFile = new File(NombresArchivos.file_empleados);
            File tempFile = new File("temp.txt");

            BufferedReader reader = new BufferedReader(new FileReader(inputFile));
            BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));

            String currentLine;
            while ((currentLine = reader.readLine()) != null) {
                String[] datos = currentLine.split(";");
                if (Integer.parseInt(datos[0]) == id_empleado) {
                    // Escribir el empleado modificado en lugar del original
                    String empleadoStr = empleado_modificado.getIdEmpleado() + ";" + empleado_modificado.getNombre() + ";" +
                                         empleado_modificado.getPaterno() + ";" + empleado_modificado.getMaterno() + ";" +
                                         empleado_modificado.getTelefono() + ";" + empleado_modificado.getCorreo_electronico() + ";" +
                                         empleado_modificado.getSalario() + ";" + empleado_modificado.getFechaContratacion() + ";" + 
                                         empleado_modificado.getCargo();
                    writer.write(empleadoStr + System.getProperty("line.separator"));
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

    // Método para ver la información de todos los empleados
    public static ArrayList<Empleado> ver_empleados() {
        ArrayList<Empleado> empleados = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(NombresArchivos.file_empleados))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] datos = line.split(";");
                int id_empleado = Integer.parseInt(datos[0]);
                String nombre = datos[1];
                String paterno = datos[2];
                String materno = datos[3];
                String telefono = datos[4];
                String correo_electronico = datos[5];
                double salario = Double.parseDouble(datos[6]);
                LocalDate fecha_contratacion = LocalDate.parse(datos[7]);
                String cargo = datos[8];
                Empleado empleado = new Empleado(id_empleado, nombre, paterno, materno, telefono, correo_electronico,
                        salario, fecha_contratacion, cargo);
                empleados.add(empleado);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return empleados;
    }

    // Método toString para representación en cadena
    @Override
    public String toString() {
        return "Empleado{" +
                "id_empleado=" + id_empleado +
                ", nombre='" + getNombre() + '\'' +
                ", paterno='" + getPaterno() + '\'' +
                ", materno='" + getMaterno() + '\'' +
                ", telefono='" + getTelefono() + '\'' +
                ", correoElectronico='" + getCorreo_electronico() + '\'' +
                ", salario=" + salario +
                ", fechaContratacion=" + fecha_contratacion +
                ", cargo='" + cargo + '\'' +
                '}';
    }
}
