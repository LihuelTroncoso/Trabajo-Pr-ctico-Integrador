package classes;

public class FACTURA {
    private int importe = 0;
    DOCTOR doc;
    Paciente pac;
    ESTUDIO estudio;

    public FACTURA(DOCTOR doc, Paciente pac, ESTUDIO estudio) {
        this.doc = doc;
        this.pac = pac;
        this.estudio = estudio;
    }

    public int getImporte() {
        return importe;
    }

    public void setImporte(int importe) {
        this.importe = importe;
    }

    public DOCTOR getDoc() {
        return doc;
    }

    public void setDoc(DOCTOR doc) {
        this.doc = doc;
    }

    public Paciente getPac() {
        return pac;
    }

    public void setPac(Paciente pac) {
        this.pac = pac;
    }

    public ESTUDIO getEstudio() {
        return estudio;
    }

    public void setEstudio(ESTUDIO estudio) {
        this.estudio = estudio;
    }

    
}
