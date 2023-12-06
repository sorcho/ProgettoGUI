package Model;
import java.util.Date;

public class Impiegato {
    private String CF, nome, cognome, categoria, tipocontratto;
    private Date datanascita, dataassunzione, datascadenza;
    private int eta;
    private float salario;

    public Impiegato(String CF, String nome, String cognome, String categoria, String tipocontratto, Date datanascita, Date dataassunzione, Date datascadenza, int eta, float salario) {
        this.CF = CF;
        this.nome = nome;
        this.cognome = cognome;
        this.categoria = categoria;
        this.tipocontratto = tipocontratto;
        this.datanascita = datanascita;
        this.dataassunzione = dataassunzione;
        this.datascadenza = datascadenza;
        this.eta = eta;
        this.salario = salario;
    }
}
