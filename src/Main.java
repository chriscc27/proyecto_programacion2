import java.time.*;
import java.util.*;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Bienvenido al sistema de la cafetería");
        
        while (true) {
            System.out.println("Menu Principal:");
            System.out.println("1. Clientes");
            System.out.println("2. Empleados");
            System.out.println("3. Libros");
            System.out.println("4. Recetas");
            System.out.println("5. Ingredientes");
            System.out.println("6. Salir");

            System.out.print("Seleccione una opción: ");
            int opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    menuClientes();
                    break;
                case 2:
                    menuEmpleados();
                    break;
                case 3:
                    menuLibros();
                    break;
                case 4:
                    menuRecetas();
                    break;
                case 5:
                    menuIngredientes();
                    break;
                case 6:
                    System.out.println("Gracias por utilizar nuestro sistema. ¡Hasta luego!");
                    System.exit(0);
                default:
                    System.out.println("Opción no válida. Por favor, seleccione una opción válida.");
                    break;
            }
        }
    }

    public static void menuClientes() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nMenú Clientes:");
            System.out.println("1. Registrar nuevo cliente");
            System.out.println("2. Modificar datos de cliente");
            System.out.println("3. Ver clientes");
            System.out.println("4. Volver al menú principal");

            System.out.print("Seleccione una opción: ");
            int opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    System.out.println("Ingrese el ID del cliente:");
                    int idCliente = scanner.nextInt();
                    scanner.nextLine(); 
                    System.out.println("Ingrese el nombre del cliente:");
                    String nombre = scanner.nextLine();
                    System.out.println("Ingrese el apellido paterno del cliente:");
                    String paterno = scanner.nextLine();
                    System.out.println("Ingrese el apellido materno del cliente:");
                    String materno = scanner.nextLine();
                    System.out.println("Ingrese el teléfono del cliente:");
                    String telefono = scanner.nextLine();
                    System.out.println("Ingrese el correo electrónico del cliente:");
                    String correoElectronico = scanner.nextLine();
                    System.out.println("Ingrese el feedback del cliente:");
                    String feedback = scanner.nextLine();
                    System.out.println("Ingrese la dirección del cliente:");
                    String direccion = scanner.nextLine();
                    
                    Cliente cliente = new Cliente(idCliente, nombre, paterno, materno, telefono, correoElectronico, feedback, direccion);
                    Cliente.agregarCliente(cliente);
                    
                    break;
                case 2:
                	System.out.println("Ingrese el id del clientes: ");
                	int id = scanner.nextInt();
                    System.out.println("Ingrese el nombre del cliente:");
                    String nombre1 = scanner.nextLine();
                    System.out.println("Ingrese el apellido paterno del cliente:");
                    String paterno1 = scanner.nextLine();
                    System.out.println("Ingrese el apellido materno del cliente:");
                    String materno1 = scanner.nextLine();
                    System.out.println("Ingrese el teléfono del cliente:");
                    String telefono1 = scanner.nextLine();
                    System.out.println("Ingrese el correo electrónico del cliente:");
                    String correoElectronico1 = scanner.nextLine();
                    System.out.println("Ingrese el feedback del cliente:");
                    String feedback1 = scanner.nextLine();
                    System.out.println("Ingrese la dirección del cliente:");
                    String direccion1 = scanner.nextLine();
                    
                    Cliente cliente_modificicado = new Cliente(id, nombre1, paterno1, materno1, telefono1, correoElectronico1, feedback1, direccion1);
                    Cliente.modificarCliente(id, cliente_modificicado);
                	
                
                    break;
                case 3:
                	System.out.println("Lista de clientes:");
                    ArrayList<Cliente> clientes = Cliente.verClientes();
                    for (Cliente cliente1 : clientes) {
                        System.out.println(cliente1);
                    }
                    break;

                case 4:
                    return;
                default:
                    System.out.println("Opción no válida. Por favor, seleccione una opción válida.");
                    break;
            }
        }
    }

    public static void menuEmpleados() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nMenú Empleados:");
            System.out.println("1. Añadir empleado");
            System.out.println("2. Modificar datos de empleado");
            System.out.println("3. Despedir empleado");
            System.out.println("4. Ver empleados");
            System.out.println("5. Volver al menú principal");

            System.out.print("Seleccione una opción: ");
            int opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    System.out.println("Ingrese el ID del empleado que desea modificar:");
                    int id = scanner.nextInt();
                    
                	System.out.println("Ingrese el nuevo nombre del empleado:");
                    String nombre = scanner.next();

                    System.out.println("Ingrese el nuevo apellido paterno del empleado:");
                    String paterno = scanner.next();

                    System.out.println("Ingrese el nuevo apellido materno del empleado:");
                    String materno = scanner.next();

                    System.out.println("Ingrese el nuevo teléfono del empleado:");
                    String telefono = scanner.next();

                    System.out.println("Ingrese el nuevo correo electrónico del empleado:");
                    String correo = scanner.next();

                    System.out.println("Ingrese el nuevo uniforme del empleado:");
                    String uniforme = scanner.next();

                    System.out.println("Ingrese el nuevo salario del empleado:");
                    double salario = scanner.nextDouble();

                    System.out.println("Ingrese la nueva fecha de contratación del empleado (YYYY-MM-DD):");
                    String fechaContratacionStr = scanner.next();
                    LocalDate fechaContratacion = LocalDate.parse(fechaContratacionStr);

                    System.out.println("Ingrese el nuevo horario del empleado:");
                    String horario = scanner.next();

                    System.out.println("Ingrese el nuevo cargo del empleado:");
                    String cargo = scanner.next();

                    Empleado empleado= new Empleado(id, nombre, paterno, materno, telefono, correo, uniforme, salario, fechaContratacion, horario, cargo);
                    Empleado.agregarEmpleado(empleado);
                    
                    break;
                    
                case 2:
                    System.out.println("Ingrese el ID del empleado que desea modificar:");
                    int id1 = scanner.nextInt();
                    
                	System.out.println("Ingrese el nuevo nombre del empleado:");
                    String nombre1 = scanner.next();

                    System.out.println("Ingrese el nuevo apellido paterno del empleado:");
                    String paterno1 = scanner.next();

                    System.out.println("Ingrese el nuevo apellido materno del empleado:");
                    String materno1 = scanner.next();

                    System.out.println("Ingrese el nuevo teléfono del empleado:");
                    String telefono1 = scanner.next();

                    System.out.println("Ingrese el nuevo correo electrónico del empleado:");
                    String correo1 = scanner.next();

                    System.out.println("Ingrese el nuevo uniforme del empleado:");
                    String uniforme1 = scanner.next();

                    System.out.println("Ingrese el nuevo salario del empleado:");
                    double salario1 = scanner.nextDouble();

                    System.out.println("Ingrese la nueva fecha de contratación del empleado (YYYY-MM-DD):");
                    String fechaContratacionStr1 = scanner.next();
                    LocalDate fechaContratacion1 = LocalDate.parse(fechaContratacionStr1);

                    System.out.println("Ingrese el nuevo horario del empleado:");
                    String horario1 = scanner.next();

                    System.out.println("Ingrese el nuevo cargo del empleado:");
                    String cargo1 = scanner.next();

                    Empleado empleado_modificado = new Empleado(id1, nombre1, paterno1, materno1, telefono1, correo1, uniforme1, salario1, fechaContratacion1, horario1, cargo1);
                    Empleado.modificarEmpleado(id1, empleado_modificado);
                    
                    break;

                case 3:
                    System.out.println("Ingrese el ID del empleado que desea despedir:");
                    int id_eliminar = scanner.nextInt();
                    Empleado.eliminarEmpleado(id_eliminar);
                    break;
                case 4:
                	
                	System.out.println("Lista de empleados:");
                    ArrayList<Empleado> empleados = Empleado.verEmpleados();
                    for (Empleado empleado1 : empleados) {
                        System.out.println(empleado1);
                    }
                    break;
                case 5:
                    return; 
                default:
                    System.out.println("Opción no válida. Por favor, seleccione una opción válida.");
                    break;
            }
        }
    }

    public static void menuLibros() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nMenú Libros:");
            System.out.println("1. Añadir libro");
            System.out.println("2. Modificar datos de libro");
            System.out.println("3. Eliminar libro");
            System.out.println("4. Ver todos los libros");
            System.out.println("5. Volver al menú principal");

            System.out.print("Seleccione una opción: ");
            int opcion = scanner.nextInt();

            switch (opcion) {
                case 1:

                    System.out.println("Ingrese el ID del libro:");
                    int id_libro = scanner.nextInt();
                    scanner.nextLine(); 
                    System.out.println("Ingrese el título del libro:");
                    String titulo = scanner.nextLine();
                    System.out.println("Ingrese el autor del libro:");
                    String autor = scanner.nextLine();
                    System.out.println("Ingrese el año de publicación del libro:");
                    int anioPublicacion = scanner.nextInt();
                    scanner.nextLine(); 
                    System.out.println("Ingrese el ISBN del libro:");
                    String isbn = scanner.nextLine();
                    
                    Libro libro = new Libro(id_libro, titulo, autor, anioPublicacion, isbn);
                    Libro.guardarLibro(libro);
                    System.out.println("Libro añadido correctamente.");
                    
                    break;
                    
                case 2:
                	
                    System.out.println("Ingrese el ID del libro:");
                    int id_libro1 = scanner.nextInt();
                    scanner.nextLine(); 
                    System.out.println("Ingrese el título del libro:");
                    String titulo1 = scanner.nextLine();
                    System.out.println("Ingrese el autor del libro:");
                    String autor1 = scanner.nextLine();
                    System.out.println("Ingrese el año de publicación del libro:");
                    int anioPublicacion1 = scanner.nextInt();
                    scanner.nextLine(); 
                    System.out.println("Ingrese el ISBN del libro:");
                    String isbn1 = scanner.nextLine();
                    
                    Libro libro_modificado = new Libro(id_libro1, titulo1, autor1, anioPublicacion1, isbn1);
                    Libro.modificarLibro(id_libro1, libro_modificado);
                    System.out.println("Libro modificado correctamente.");
                    
                    break;
                case 3:
                    System.out.println("Ingrese el ID del libro que desea eliminar:");
                    int id_libro_eliminar = scanner.nextInt();
                    
                    Libro.eliminarLibro(id_libro_eliminar);
                    System.out.println("Libro eliminado correctamente.");
                    break;
                case 4:
                	
                	Libro.verLibros();;
                    
                    break;

                case 5:
                    return; 
                default:
                    System.out.println("Opción no válida. Por favor, seleccione una opción válida.");
                    break;
            }
        }
    }

    public static void menuRecetas() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nMenú Recetas:");
            System.out.println("1. Añadir receta");
            System.out.println("2. Modificar receta");
            System.out.println("3. Eliminar receta");
            System.out.println("4. Ver recetas");
            System.out.println("5. Volver al menú principal");

            System.out.print("Seleccione una opción: ");
            int opcion = scanner.nextInt();

            switch (opcion) {
                case 1:

                    System.out.println("Ingrese el ID de la receta:");
                    int id_receta = scanner.nextInt();
                    scanner.nextLine(); 
                    System.out.println("Ingrese el nombre de la receta:");
                    String nombre = scanner.nextLine();
                    
                    Receta receta = new Receta(id_receta, nombre);

                    Receta.agregarReceta(receta);
                    System.out.println("Receta añadida correctamente.");
                    break;
                case 2:
                    System.out.println("Ingrese el ID de la receta que desea modificar:");
                    int id_receta1 = scanner.nextInt();
                    scanner.nextLine();
                    System.out.println("Ingrese el nombre de la receta:");
                    String nombre1 = scanner.nextLine();
                    
                    Receta receta_modificada = new Receta(id_receta1, nombre1);

                    Receta.modificarReceta(id_receta1, receta_modificada);
                    System.out.println("Receta modificada correctamente.");
                    
                    break;
                case 3:
                    System.out.println("Ingrese el ID de la receta que desea modificar:");
                    int id_receta_eliminar = scanner.nextInt();
                    Receta.eliminarReceta(id_receta_eliminar);
                    
                    break;
                case 4:

                    Receta.verRecetas();
                    break;
                case 5:
                    return; 
                default:
                    System.out.println("Opción no válida. Por favor, seleccione una opción válida.");
                    break;
            }
        }
    }

    public static void menuIngredientes() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nMenú Ingredientes:");
            System.out.println("1. Nuevo ingrediente");
            System.out.println("2. Agregar stock ingrediente");
            System.out.println("3. Eliminar ingrediente");
            System.out.println("4. Ver ingredientes");
            System.out.println("5. Volver al menú principal");

            System.out.print("Seleccione una opción: ");
            int opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                	
                    System.out.println("Ingrese el ID del nuevo ingrediente:");
                    int id_ingrediente = scanner.nextInt();
                    scanner.nextLine(); 
                    System.out.println("Ingrese la descripción del ingrediente:");
                    String descripcion = scanner.nextLine();
                    System.out.println("Ingrese la fecha de caducidad del ingrediente (AAAA-MM-DD):");
                    String fechaCaducidadStr = scanner.nextLine();
                    LocalDate fechaCaducidad = LocalDate.parse(fechaCaducidadStr);
                    System.out.println("Ingrese el nombre del ingrediente:");
                    String nombre = scanner.nextLine();
                    System.out.println("Ingrese la fecha de última compra del ingrediente (AAAA-MM-DD):");
                    String fechaUltimaCompraStr = scanner.nextLine();
                    LocalDate fechaUltimaCompra = LocalDate.parse(fechaUltimaCompraStr);
                    System.out.println("Ingrese el stock del ingrediente:");
                    int stock = scanner.nextInt();
                    
                    Ingrediente ingrediente = new Ingrediente(id_ingrediente, descripcion, fechaCaducidad, nombre, fechaUltimaCompra, stock);
                    Ingrediente.agregarIngrediente(ingrediente);
                    System.out.println("Ingrediente agregado correctamente.");
                    break;
                case 2:
                    System.out.println("Ingrese el ID del nuevo ingrediente:");
                    int id_ingrediente1 = scanner.nextInt();
                    scanner.nextLine(); 
                    System.out.println("Ingrese la cantidad que desea añadir");
                    int cantidad = scanner.nextInt();

                    Ingrediente.añadirStock(id_ingrediente1, cantidad);
                    System.out.println("Stock agregado correctamente.");
                    break;
                case 3:

                    System.out.println("Ingrese el ID del ingrediente que desea eliminar:");
                    int idIngrediente = scanner.nextInt();
                    
                    Ingrediente.eliminarIngrediente(idIngrediente);
                    System.out.println("Ingrediente eliminado correctamente.");
                    break;
                case 4:
                	System.out.println("Lista de ingredientes:");
                    ArrayList<Ingrediente> ingredientes = Ingrediente.mostrarIngredientes();
                    for (Ingrediente ingrediente1 : ingredientes) {
                        System.out.println(ingrediente1);
                    }
                    break;
                case 5:
                    return; 
                default:
                    System.out.println("Opción no válida. Por favor, seleccione una opción válida.");
                    break;
            }
        }
    }
}


