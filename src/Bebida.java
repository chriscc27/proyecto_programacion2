import java.io.*;
import java.util.ArrayList;

public class Bebida extends Producto {
    private String tipo;
    private String envase;

    public Bebida(int id_producto, String nombre, double precio, String tipo, String envase) {
        super(id_producto, nombre, precio);
        this.tipo = tipo;
        this.envase = envase;
    }

    public String getTipo() {
        return tipo;
    }

    public String getEnvase() {
        return envase;
    }

    public static ArrayList<Bebida> mostrarBebidas() {
        ArrayList<Bebida> bebidas = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader("bebidas.txt"))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(";");
                int id_producto = Integer.parseInt(datos[0]);
                String nombre = datos[1];
                double precio = Double.parseDouble(datos[2]);
                String tipo = datos[3];
                String envase = datos[4];
                bebidas.add(new Bebida(id_producto, nombre, precio, tipo, envase));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bebidas;
    }

    public static void agregarBebida(Bebida bebida) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("bebidas.txt", true))) {
            bw.write(bebida.getId_producto() + ";" + bebida.getNombre() + ";" + bebida.getPrecio() + ";" + bebida.getTipo() + ";" + bebida.getEnvase());
            bw.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void eliminarBebida(int id_producto) {
        ArrayList<Bebida> bebidas = mostrarBebidas();
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("bebidas.txt"))) {
            for (Bebida bebida : bebidas) {
                if (bebida.getId_producto() != id_producto) {
                    bw.write(bebida.getId_producto() + ";" + bebida.getNombre() + ";" + bebida.getPrecio() + ";" + bebida.getTipo() + ";" + bebida.getEnvase());
                    bw.newLine();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void modificarBebida(int id_producto, Bebida bebidaModificada) {
        ArrayList<Bebida> bebidas = mostrarBebidas();
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("bebidas.txt"))) {
            for (Bebida bebida : bebidas) {
                if (bebida.getId_producto() == id_producto) {
                    bw.write(bebidaModificada.getId_producto() + ";" + bebidaModificada.getNombre() + ";" + bebidaModificada.getPrecio() + ";" + bebidaModificada.getTipo() + ";" + bebidaModificada.getEnvase());
                } else {
                    bw.write(bebida.getId_producto() + ";" + bebida.getNombre() + ";" + bebida.getPrecio() + ";" + bebida.getTipo() + ";" + bebida.getEnvase());
                }
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Bebida buscarBebida(int id_producto) {
        ArrayList<Bebida> bebidas = mostrarBebidas();
        for (Bebida bebida : bebidas) {
            if (bebida.getId_producto() == id_producto) {
                return bebida;
            }
        }
        return null;
    }
}
