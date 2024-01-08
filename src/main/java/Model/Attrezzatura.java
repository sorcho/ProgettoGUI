package Model;

public class Attrezzatura {
    private String seriale;
    private String tipo;
    private float costo;
    private Laboratorio nomeLab;
    private Progetto cup;

    public Attrezzatura(String seriale, String tipo, float costo, Laboratorio nomeLab, Progetto cup) {
        this.seriale = seriale;
        this.tipo = tipo;
        this.costo = costo;
        this.nomeLab = nomeLab;
        this.cup = cup;
    }

    public String getSeriale() {
        return seriale;
    }

    public void setSeriale(String seriale) {
        this.seriale = seriale;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public float getCosto() {
        return costo;
    }

    public void setCosto(float costo) {
        this.costo = costo;
    }

    public Laboratorio getNomeLab() {
        return nomeLab;
    }

    public void setNomeLab(Laboratorio nomeLab) {
        this.nomeLab = nomeLab;
    }

    public Progetto getCup() {
        return cup;
    }

    public void setCup(Progetto cup) {
        this.cup = cup;
    }
}
