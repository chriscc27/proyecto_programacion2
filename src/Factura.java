import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Factura {
    private int numeroFactura;
    private LocalDateTime fechaEmision;
    private List<Producto> productos;
    private double total;

    public Factura(int numeroFactura, LocalDateTime fechaEmision) {
        this.numeroFactura = numeroFactura;
        this.fechaEmision = fechaEmision;
        this.productos = new ArrayList<>();
        this.total = 0.0;
    }

    public int getNumeroFactura() {
        return numeroFactura;
    }

    public void setNumeroFactura(int numeroFactura) {
        this.numeroFactura = numeroFactura;
    }

    public LocalDateTime getFechaEmision() {
        return fechaEmision;
    }

    public void setFechaEmision(LocalDateTime fechaEmision) {
        this.fechaEmision = fechaEmision;
    }

    public List<Producto> getProductos() {
        return productos;
    }

    public void agregarProducto(Producto producto) {
        this.productos.add(producto);
        this.total += producto.getPrecio();
    }

    public double getTotal() {
        return total;
    }

    @Override
    public String toString() {
        return "Factura{" +
                "numeroFactura=" + numeroFactura +
                ", fechaEmision=" + fechaEmision +
                ", productos=" + productos +
                ", total=" + total +
                '}';
    }
}
