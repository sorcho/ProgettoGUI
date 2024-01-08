package Model;

import java.sql.Date;

public class Promozione {
    private Date dataPassaggio;
    private String vecchiaCategoria;
    private String nuovaCategoria;
    private String promotoreDirigente;
    private Impiegato impiegato;

    public Promozione(Date dataPassaggio, String vecchiaCategoria, String nuovaCategoria, String promotoreDirigente, Impiegato impiegato) {
        this.dataPassaggio = dataPassaggio;
        this.vecchiaCategoria = vecchiaCategoria;
        this.nuovaCategoria = nuovaCategoria;
        this.promotoreDirigente = promotoreDirigente;
        this.impiegato = impiegato;
    }

    public Date getDataPassaggio() {
        return dataPassaggio;
    }

    public void setDataPassaggio(Date dataPassaggio) {
        this.dataPassaggio = dataPassaggio;
    }

    public String getVecchiaCategoria() {
        return vecchiaCategoria;
    }

    public void setVecchiaCategoria(String vecchiaCategoria) {
        this.vecchiaCategoria = vecchiaCategoria;
    }

    public String getNuovaCategoria() {
        return nuovaCategoria;
    }

    public void setNuovaCategoria(String nuovaCategoria) {
        this.nuovaCategoria = nuovaCategoria;
    }

    public String getPromotoreDirigente() {
        return promotoreDirigente;
    }

    public void setPromotoreDirigente(String promotoreDirigente) {
        this.promotoreDirigente = promotoreDirigente;
    }

    public Impiegato getImpiegato() {
        return impiegato;
    }

    public void setImpiegato(Impiegato impiegato) {
        this.impiegato = impiegato;
    }
}
