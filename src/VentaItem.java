public class VentaItem {
    private String nombre;
    private int cantidad;
    private double precioTotal;

    public VentaItem(String nombre, int cantidad, double precioTotal) {
        this.nombre = nombre;
        this.cantidad = cantidad;
        this.precioTotal = precioTotal;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getPrecioTotal() {
        return precioTotal;
    }

    public void setPrecioTotal(double precioTotal) {
        this.precioTotal = precioTotal;
    }

	@Override
	public String toString() {
		return "VentaItem [nombre=" + nombre + ", cantidad=" + cantidad + ", precioTotal=" + precioTotal + "]";
	}
    
    
}
