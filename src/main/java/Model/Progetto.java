package Model;

import java.util.ArrayList;

public class Progetto {
    private String cup; // CUP
    private Impiegato refSci;
    private Impiegato resp;
    private String nome;
    private float budget;
    private ArrayList<Laboratorio> listaLaboratori = new ArrayList<>();
    private ArrayList<Attrezzatura> listaAttrezzatura = new ArrayList<>();

    public Progetto(String cup, Impiegato refSci, Impiegato resp, String nome, float budget) {
        this.cup = cup;
        this.refSci = refSci;
        this.resp = resp;
        this.nome = nome;
        this.budget = budget;
    }

    public void addAttrezzatura(Attrezzatura a){
        listaAttrezzatura.add(a);
    }

    public void removeAttrezzatura(Attrezzatura a){
        listaAttrezzatura.remove(a);
    }

    public void addLaboratorio(Laboratorio l){
        listaLaboratori.add(l);
    }

    public void removeLaboratorio(Laboratorio l){
        listaLaboratori.remove(l);
    }

    public String getCup() {
        return cup;
    }

    public void setCup(String cup) {
        this.cup = cup;
    }

    public Impiegato getRefSci() {
        return refSci;
    }

    public void setRefSci(Impiegato refSci) {
        this.refSci = refSci;
    }

    public Impiegato getResp() {
        return resp;
    }

    public void setResp(Impiegato resp) {
        this.resp = resp;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public float getBudget() {
        return budget;
    }

    public void setBudget(float budget) {
        this.budget = budget;
    }
}
