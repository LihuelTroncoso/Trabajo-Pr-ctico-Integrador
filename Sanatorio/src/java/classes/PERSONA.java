package classes;

public class PERSONA {
    protected String nombre;
    protected String apellido;

    public PERSONA(String nombre, String apellido) {
        this.nombre = nombre;
        this.apellido = apellido;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }
        
    public String toString(){
        String datos = "Nombre: "+nombre+ "apellido: "+apellido;
        return datos;
    }
}
