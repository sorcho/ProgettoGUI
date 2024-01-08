package Model;

import java.util.ArrayList;

public class Laboratorio {
    private String nome;
    private Impiegato RespSci;
    private String topic;
    private int n_afferenti = 0;
    private Progetto progetto;
    private ArrayList<Impiegato> listaAfferenti = new ArrayList<>();
    private ArrayList<Attrezzatura> listaAttrezzatura = new ArrayList<>();

    public Laboratorio(String nome, Impiegato respSci, String topic) {
        this.nome = nome;
        RespSci = respSci;
        this.topic = topic;
    }

    public void addAfferente(Impiegato i){
        listaAfferenti.add(i);
        this.n_afferenti += 1;
    }

    public void removeAfferente(Impiegato i){
        listaAfferenti.remove(i);
        this.n_afferenti -= 1;
    }

    public void addAttrezzatura(Attrezzatura a){
        listaAttrezzatura.add(a);
    }

    public void removeAttrezzatura(Attrezzatura a){
        listaAttrezzatura.remove(a);
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Impiegato getRespSci() {
        return RespSci;
    }

    public void setRespSci(Impiegato respSci) {
        RespSci = respSci;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public int getN_afferenti() {
        return n_afferenti;
    }

    public void setN_afferenti(int n_afferenti) {
        this.n_afferenti = n_afferenti;
    }

    public Progetto getProgetto() {
        return progetto;
    }

    public void setProgetto(Progetto progetto) {
        this.progetto = progetto;
    }
}
