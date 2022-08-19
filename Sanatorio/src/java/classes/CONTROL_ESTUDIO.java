package classes;
import java.util.ArrayList;

public class CONTROL_ESTUDIO {
    private String nomEstudio;
    private int IDestudio;
    private ArrayList<ESTUDIO> estudios = new ArrayList();
    private Paciente paciente;
    private OBRA_SOCIAL obraSocial;
    private ESTUDIO estudio;
    private DOCTOR doctor;

    public CONTROL_ESTUDIO(String nomEstudio, int IDestudio, Paciente paciente, OBRA_SOCIAL obraSocial, ESTUDIO estudio, DOCTOR doctor) {
        this.nomEstudio = nomEstudio;
        this.IDestudio = IDestudio;
        this.paciente = paciente;
        this.obraSocial = obraSocial;
        this.estudio = estudio;
        this.doctor = doctor;
    }

    public String getNomEstudio() {
        return nomEstudio;
    }

    public void setNomEstudio(String nomEstudio) {
        this.nomEstudio = nomEstudio;
    }

    public int getIDestudio() {
        return IDestudio;
    }

    public void setIDestudio(int IDestudio) {
        this.IDestudio = IDestudio;
    }

    public ArrayList getEstudios() {
        return estudios;
    }

    public void setEstudios(ArrayList estudios) {
        this.estudios = estudios;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public OBRA_SOCIAL getObraSocial() {
        return obraSocial;
    }

    public void setObraSocial(OBRA_SOCIAL obraSocial) {
        this.obraSocial = obraSocial;
    }

    public ESTUDIO getEstudio() {
        return estudio;
    }

    public void setEstudio(ESTUDIO estudio) {
        this.estudio = estudio;
    }

    public DOCTOR getDoctor() {
        return doctor;
    }

    public void setDoctor(DOCTOR doctor) {
        this.doctor = doctor;
    }
}
