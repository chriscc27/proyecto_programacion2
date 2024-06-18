import java.io.*;
import java.util.ArrayList;

public class Plato extends Producto {
    private String categoria;
    private String tipo_presentacion;

    public Plato(int idProducto, String nombre, double precio, String categoria, String tipo_presentacion) {
        super(idProducto, nombre, precio);
        this.categoria = categoria;
        this.tipo_presentacion = tipo_presentacion;
    }

    public String getCategoria() {
        return categoria;
    }

    public String getTipoPresentacion() {
        return tipo_presentacion;
    }

    public static void agregarPlato(Plato plato) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("platos.txt", true))) {
            String platoStr = plato.getId_producto() + ";" + plato.getNombre() + ";" + plato.getPrecio() + ";" +
                    plato.getCategoria() + ";" + plato.getTipoPresentacion();
            writer.write(platoStr);
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void modificarPlato(int idPlato, Plato platoModificado) {
        ArrayList<Plato> platos = obtenerPlatosDisponibles();
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("platos.txt"))) {
            for (Plato plato : platos) {
                if (plato.getId_producto() == idPlato) {
                    writer.write(platoModificado.getId_producto() + ";" + platoModificado.getNombre() + ";" +
                            platoModificado.getPrecio() + ";" + platoModificado.getCategoria() + ";" +
                            platoModificado.getTipoPresentacion());
                } else {
                    writer.write(plato.getId_producto() + ";" + plato.getNombre() + ";" + plato.getPrecio() + ";" +
                            plato.getCategoria() + ";" + plato.getTipoPresentacion());
                }
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static ArrayList<Plato> obtenerPlatosDisponibles() {
        ArrayList<Plato> platos = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader("platos.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] datos = line.split(";");
                int id = Integer.parseInt(datos[0]);
                String nombre = datos[1];
                double precio = Double.parseDouble(datos[2]);
                String categoria = datos[3];
                String tipo_presentacion = datos[4];
                Plato plato = new Plato(id, nombre, precio, categoria, tipo_presentacion);
                platos.add(plato);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return platos;
    }

    public static void eliminarPlato(int idPlato) {
        ArrayList<Plato> platos = obtenerPlatosDisponibles();
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("platos.txt"))) {
            for (Plato plato : platos) {
                if (plato.getId_producto() != idPlato) {
                    writer.write(plato.getId_producto() + ";" + plato.getNombre() + ";" + plato.getPrecio() + ";" +
                            plato.getCategoria() + ";" + plato.getTipoPresentacion());
                    writer.newLine();
                }
            }
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
