package classes;

public class OBRA_SOCIAL {
    private String nombreObra;

    public OBRA_SOCIAL(String nombreObra) {
        this.nombreObra = nombreObra;
    }

    public String getNombreObra() {
        return nombreObra;
    }

    public void setNombreObra(String nombreObra) {
        this.nombreObra = nombreObra;
    }
    
    public String toString(){
        String datos = "Obra Social: "+nombreObra;
        return datos;
    }
}
