package classes;
import java.time.LocalDate;

public class ESTUDIO {
    private String nomEstudio;
    private double precio;
    private int ID;
    LocalDate fecha = null;

    public ESTUDIO(String nomEstudio, double precio, int ID) {
        this.nomEstudio = nomEstudio;
        this.precio = precio;
        this.ID = ID;
        this.fecha = LocalDate.now();
    }

    public String getNomEstudio() {
        return nomEstudio;
    }

    public double getPrecio() {
        return precio;
    }

    public int getID() {
        return ID;
    }

    public LocalDate getFecha() {
        System.out.println(fecha);
        return fecha;
    }
}
