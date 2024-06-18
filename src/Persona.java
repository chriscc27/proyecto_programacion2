public class Persona {
	
    // Atributos
    private String nombre;
    private String paterno;
    private String materno;
    private String telefono;
    private String correo_electronico;

    // Constructor
    public Persona(String nombre, String paterno, String materno, String telefono, String correo_electronico) {
        this.nombre = nombre;
        this.paterno = paterno;
        this.materno = materno;
        this.telefono = telefono;
        this.correo_electronico = correo_electronico;
    }

    // Métodos getters y setters
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPaterno() {
        return paterno;
    }

    public void setPaterno(String paterno) {
        this.paterno = paterno;
    }

    public String getMaterno() {
        return materno;
    }

    public void setMaterno(String materno) {
        this.materno = materno;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCorreo_electronico() {
        return correo_electronico;
    }

    public void setCorreo_electronico(String correo_electronico) {
        this.correo_electronico = correo_electronico;
    }

    // Método toString para representación en cadena
    @Override
    public String toString() {
        return "Persona{" +
                "nombre='" + nombre + '\'' +
                ", paterno='" + paterno + '\'' +
                ", materno='" + materno + '\'' +
                ", telefono='" + telefono + '\'' +
                ", correo_electronico='" + correo_electronico + '\'' +
                '}';
    }
}
