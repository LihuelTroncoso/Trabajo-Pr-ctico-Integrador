package classes;

import java.util.ArrayList;

public class DOCTOR extends PERSONA{
    private int id;
    private int DNI;
    ArrayList<ESTUDIO> estudiosSolicitados = new ArrayList();
    private int cantEstudios;
    
    public DOCTOR(String nombre, String apellido, int DNI, int ce) {
        super(nombre, apellido);
        this.DNI = DNI;
        this.cantEstudios = ce;
    }
    
    public void agregarEstudio(ESTUDIO estudio){
        estudiosSolicitados.add(estudio);
        cantEstudios++;
    }
    
    public int getCantEstudios(){
        return cantEstudios;
    }

    public int getDNI() {
        return DNI;
    }

    public void setDNI(int DNI) {
        this.DNI = DNI;
    }

    public ArrayList<ESTUDIO> getEstudiosSolicitados() {
        return estudiosSolicitados;
    }

    public void setEstudiosSolicitados(ArrayList<ESTUDIO> estudiosSolicitados) {
        this.estudiosSolicitados = estudiosSolicitados;
    }

    public void setCantEstudios(int cantEstudios) {
        this.cantEstudios = cantEstudios;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}