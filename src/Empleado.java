import java.io.*;
import java.time.LocalDate;
import java.util.*;

public class Empleado extends Persona {
    // Atributos adicionales
    private int id_empleado;
    private String uniforme;
    private double salario;
    private LocalDate fechaContratacion;
    private String horario;
    private String cargo;

    // Variable para el nombre del archivo de empleados
    private static final String fileEmpleados = "empleados.txt";

    // Constructor
    public Empleado(int id_empleado, String nombre, String paterno, String materno, String telefono, String correoElectronico,
                    String uniforme, double salario, LocalDate fechaContratacion, String horario, String cargo) {
        // Llama al constructor de la clase padre (Persona)
        super(nombre, paterno, materno, telefono, correoElectronico);
        this.id_empleado = id_empleado;
        this.uniforme = uniforme;
        this.salario = salario;
        this.fechaContratacion = fechaContratacion;
        this.horario = horario;
        this.cargo = cargo;
    }

    // Métodos getters y setters adicionales
    public int getIdEmpleado() {
        return id_empleado;
    }

    public void setIdEmpleado(int id_empleado) {
        this.id_empleado = id_empleado;
    }

    public String getUniforme() {
        return uniforme;
    }

    public void setUniforme(String uniforme) {
        this.uniforme = uniforme;
    }

    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }

    public LocalDate getFechaContratacion() {
        return fechaContratacion;
    }

    public void setFechaContratacion(LocalDate fechaContratacion) {
        this.fechaContratacion = fechaContratacion;
    }

    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    // Método para agregar empleados al archivo de texto
    public static void agregarEmpleado(Empleado empleado) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileEmpleados, true))) {
            String empleadoStr = empleado.getIdEmpleado() + ";" + empleado.getNombre() + ";" + empleado.getPaterno() + ";" +
                    empleado.getMaterno() + ";" + empleado.getTelefono() + ";" + empleado.getCorreoElectronico() + ";" +
                    empleado.getUniforme() + ";" + empleado.getSalario() + ";" + empleado.getFechaContratacion() + ";" +
                    empleado.getHorario() + ";" + empleado.getCargo();
            writer.write(empleadoStr);
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Método para eliminar empleados del archivo de texto por su ID
    public static void eliminarEmpleado(int idEmpleado) {
        try {
            File inputFile = new File(fileEmpleados);
            File tempFile = new File("temp.txt");

            BufferedReader reader = new BufferedReader(new FileReader(inputFile));
            BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));

            String lineToRemove = idEmpleado + ";";

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

    // Método para modificar la información de un empleado en el archivo de texto por su ID
    public static void modificarEmpleado(int idEmpleado, Empleado empleadoModificado) {
        eliminarEmpleado(idEmpleado);
        agregarEmpleado(empleadoModificado);
    }

    // Método para ver la información de todos los empleados
    public static ArrayList<Empleado> verEmpleados() {
        ArrayList<Empleado> empleados = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(fileEmpleados))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] datos = line.split(";");
                int id_empleado = Integer.parseInt(datos[0]);
                String nombre = datos[1];
                String paterno = datos[2];
                String materno = datos[3];
                String telefono = datos[4];
                String correoElectronico = datos[5];
                String uniforme = datos[6];
                double salario = Double.parseDouble(datos[7]);
                LocalDate fechaContratacion = LocalDate.parse(datos[8]);
                String horario = datos[9];
                String cargo = datos[10];
                Empleado empleado = new Empleado(id_empleado, nombre, paterno, materno, telefono, correoElectronico,
                        uniforme, salario, fechaContratacion, horario, cargo);
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
                ", correoElectronico='" + getCorreoElectronico() + '\'' +
                ", uniforme='" + uniforme + '\'' +
                ", salario=" + salario +
                ", fechaContratacion=" + fechaContratacion +
                ", horario='" + horario + '\'' +
                ", cargo='" + cargo + '\'' +
                '}';
    }
}
