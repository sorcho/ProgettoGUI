package Controller;

import DAO.*;
import ImplementazionePostgresDAO.*;
import Model.*;

import java.sql.Array;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Controller {
    // TODO aggiungi documentazione funzioni
    private List<Impiegato> listaImpiegati = new ArrayList<>();
    private List<Laboratorio> listaLaboratori = new ArrayList<>();
    private List<Progetto> listaProgetti = new ArrayList<>();
    private List<Attrezzatura> listaAttrezzature = new ArrayList<>();
    private List<Promozione> listaPromozioni = new ArrayList<>();

    public Controller() {
        getImpiegatiDatabase();
        getLaboratoriDatabase();
        getPromozioniDatabase();
        getAttrezzatureDatabase();
        getProgettiDatabase();
    }

    private void getImpiegatiDatabase() {
        GestionaleDAO gestionale = new ImplementazioneGestionaleDAO();

        ArrayList<String> listaCF = new ArrayList<>();
        ArrayList<String> listaNomi = new ArrayList<>();
        ArrayList<String> listaCognomi = new ArrayList<>();
        ArrayList<Date> listaDateNascita = new ArrayList<>();
        ArrayList<Date> listaDateAssunzione = new ArrayList<>();
        ArrayList<Integer> listaEta = new ArrayList<>();
        ArrayList<String> listaCategorie = new ArrayList<>();
        ArrayList<Float> listaSalari = new ArrayList<>();
        ArrayList<Date> listaDateScadenza = new ArrayList<>();
        ArrayList<String> listaTipiContratto = new ArrayList<>();

        gestionale.getImpiegati(
                listaCF,
                listaNomi,
                listaCognomi,
                listaDateNascita,
                listaDateAssunzione,
                listaEta,
                listaCategorie,
                listaSalari,
                listaDateScadenza,
                listaTipiContratto
        );

        for (int i = 0; i < listaCF.size(); i++) {
            listaImpiegati.add(new Impiegato(
                    listaCF.get(i),
                    listaNomi.get(i),
                    listaCognomi.get(i),
                    listaDateNascita.get(i),
                    listaDateAssunzione.get(i),
                    listaEta.get(i),
                    listaCategorie.get(i),
                    listaSalari.get(i),
                    listaDateScadenza.get(i),
                    listaTipiContratto.get(i)
            ));
        }
    }

    private void getLaboratoriDatabase() {
        GestionaleDAO gestionale = new ImplementazioneGestionaleDAO();

        ArrayList<String> listaNomi = new ArrayList<>();
        ArrayList<String> listaRespSci = new ArrayList<>();
        ArrayList<String> listaTopic = new ArrayList<>();
        ArrayList<Integer> listaNAfferenti = new ArrayList<>();
        ArrayList<String> listaProgetti = new ArrayList<>();

        gestionale.getLaboratori(listaNomi, listaRespSci, listaTopic, listaNAfferenti, listaProgetti);

        for (int i = 0; i < listaNomi.size(); i++) {
            listaLaboratori.add(new Laboratorio(
                    listaNomi.get(i),
                    listaRespSci.get(i),
                    listaTopic.get(i),
                    listaNAfferenti.get(i),
                    listaProgetti.get(i)
            ));
        }
    }

    private void getPromozioniDatabase() {
        GestionaleDAO gestionale = new ImplementazioneGestionaleDAO();

        ArrayList<String> listaCF = new ArrayList<>();
        ArrayList<Date> listaDatePassaggio = new ArrayList<>();
        ArrayList<String> listaVecchieCategorie = new ArrayList<>();
        ArrayList<String> listaNuoveCategorie = new ArrayList<>();
        ArrayList<String> listaPromotoriDirigenti = new ArrayList<>();

        gestionale.getPromozioni(
                listaCF,
                listaDatePassaggio,
                listaVecchieCategorie,
                listaNuoveCategorie,
                listaPromotoriDirigenti
        );

        for (int i = 0; i < listaCF.size(); i++) {
            listaPromozioni.add(new Promozione(
                    listaDatePassaggio.get(i),
                    listaVecchieCategorie.get(i),
                    listaNuoveCategorie.get(i),
                    listaPromotoriDirigenti.get(i),
                    listaCF.get(i)));
        }
    }

    private void getProgettiDatabase() {
        GestionaleDAO gestionale = new ImplementazioneGestionaleDAO();

        ArrayList<String> listaCUP = new ArrayList<>();
        ArrayList<String> listaRefSci = new ArrayList<>();
        ArrayList<String> listaResp = new ArrayList<>();
        ArrayList<String> listaNome = new ArrayList<>();
        ArrayList<Float> listaBudget = new ArrayList<>();

        gestionale.getProgetti(listaCUP, listaRefSci, listaResp, listaNome, listaBudget);

        for (int i = 0; i < listaCUP.size(); i++) {
            listaProgetti.add(new Progetto(
                    listaCUP.get(i),
                    listaRefSci.get(i),
                    listaResp.get(i),
                    listaNome.get(i),
                    listaBudget.get(i)
            ));
        }
    }

    private void getAttrezzatureDatabase() {
        GestionaleDAO gestionale = new ImplementazioneGestionaleDAO();

        ArrayList<String> listaSeriale = new ArrayList<>();
        ArrayList<String> listaTipo = new ArrayList<>();
        ArrayList<Float> listaCosto = new ArrayList<>();
        ArrayList<String> listaNomiLab = new ArrayList<>();
        ArrayList<String> listaCUP = new ArrayList<>();

        gestionale.getAttrezzatura(
                listaSeriale,
                listaTipo,
                listaCosto,
                listaNomiLab,
                listaCUP
        );

        for (int i = 0; i < listaSeriale.size(); i++) {
            listaAttrezzature.add(new Attrezzatura(
                    listaSeriale.get(i),
                    listaTipo.get(i),
                    listaCosto.get(i),
                    listaNomiLab.get(i),
                    listaCUP.get(i)
            ));
        }
    }

    public ArrayList<String> getListaCF() {
        ArrayList<String> listaCF = new ArrayList<>();

        for (Impiegato i : listaImpiegati)
            listaCF.add(i.getCf());

        return listaCF;
    }

    public ArrayList<String> getListaNomi() {
        ArrayList<String> listaNomi = new ArrayList<>();

        for (Impiegato i : listaImpiegati)
            listaNomi.add(i.getNome());

        return listaNomi;
    }

    public ArrayList<String> getListaCognomi() {
        ArrayList<String> listaCognomi = new ArrayList<>();

        for (Impiegato i : listaImpiegati)
            listaCognomi.add(i.getCognome());

        return listaCognomi;
    }

    public ArrayList<String> getListaLaboratoriFromCF(String CF) {
        ImpiegatoDAO impiegatoDAO = new ImplementazioneImpiegatoDAO();

        Impiegato impiegato = null;

        for (Impiegato i : listaImpiegati) {
            if (i.getCf().equals(CF)) {
                impiegato = i;
                break;
            }
        }

        ArrayList<String> listaLaboratoriImpiegato = new ArrayList<>();
        boolean risultato = impiegatoDAO.getAfferenze(CF, listaLaboratoriImpiegato);

        if (risultato) {
            assert impiegato != null;
            impiegato.setListaLaboratori(listaLaboratoriImpiegato);
        }

        return listaLaboratoriImpiegato;
    }

    public ArrayList<Promozione> getListaPromozioniFromCF(String CF) {
        ImpiegatoDAO impiegatoDAO = new ImplementazioneImpiegatoDAO();

        Impiegato impiegato = null;

        for (Impiegato i : listaImpiegati) {
            if (i.getCf().equals(CF)) {
                impiegato = i;
                break;
            }
        }

        ArrayList<Promozione> listaPromozioniImpiegato = new ArrayList<>();
        boolean risultato = impiegatoDAO.getPromozioni(CF, listaPromozioniImpiegato);

        if (risultato) {
            assert impiegato != null;
            impiegato.setListaPromozioni(listaPromozioniImpiegato);
        }

        return listaPromozioniImpiegato;
    }

    public void aggiungiImpiegato(String cf, String nome, String cognome, Date dataNasc, Date dataAss, Date dataScad, String categoria, String cup) throws SQLException {
        ImpiegatoDAO impiegatoDAO = new ImplementazioneImpiegatoDAO();

        if (dataScad == null)
            impiegatoDAO.assumiImpiegato(cf, nome, cognome, dataNasc, dataAss, categoria);
        else
            impiegatoDAO.assumiImpiegatoProgetto(cf, nome, cognome, dataNasc, dataAss, dataScad, cup);

        listaImpiegati.clear();
        getImpiegatiDatabase();
    }

    public void rimuoviImpiegato(String cf) throws SQLException {
        ImpiegatoDAO impiegatoDAO = new ImplementazioneImpiegatoDAO();

        impiegatoDAO.licenziaImpiegato(cf);

        listaImpiegati.clear();
        getImpiegatiDatabase();
    }

    public Impiegato getDatiProfilo(String cf) {
        for (Impiegato i : listaImpiegati) {
            if (Objects.equals(cf, i.getCf())) {
                return i;
            }
        }

        return null;
    }

    public ArrayList<String> getListaNomiLaboratori() {
        ArrayList<String> listaNomi = new ArrayList<>();

        for (Laboratorio l : listaLaboratori)
            listaNomi.add(l.getNome());

        return listaNomi;
    }

    public ArrayList<String> getListaRespSci() {
        ArrayList<String> listaRespSci = new ArrayList<>();

        for (Laboratorio l : listaLaboratori)
            listaRespSci.add(l.getRespSci());

        return listaRespSci;
    }

    public ArrayList<String> getListaRefSci() {
        ArrayList<String> listaRefSci = new ArrayList<>();

        for (Progetto p : listaProgetti)
            listaRefSci.add(p.getRefSci());

        return listaRefSci;
    }

    public ArrayList<String> getListaResponsabili() {
        ArrayList<String> listaResponsabili = new ArrayList<>();

        for (Progetto p : listaProgetti)
            listaResponsabili.add(p.getResp());

        return listaResponsabili;
    }

    public ArrayList<Float> getListaBudget() {
        ArrayList<Float> listaBudget = new ArrayList<>();

        for (Progetto p : listaProgetti)
            listaBudget.add(p.getBudget());

        return listaBudget;
    }

    public ArrayList<String> getListaTopic() {
        ArrayList<String> listaTopic = new ArrayList<>();

        for (Laboratorio l : listaLaboratori)
            listaTopic.add(l.getTopic());

        return listaTopic;
    }

    public ArrayList<Integer> getListaNAfferenti() {
        ArrayList<Integer> listaNAfferenti = new ArrayList<>();

        for (Laboratorio l : listaLaboratori)
            listaNAfferenti.add(l.getN_afferenti());

        return listaNAfferenti;
    }

    public ArrayList<String> getListaProgetti() {
        ArrayList<String> listaProgetti = new ArrayList<>();

        for (Laboratorio l : listaLaboratori)
            listaProgetti.add(l.getProgetto());

        return listaProgetti;
    }

    public ArrayList<String> getListaSenior() {
        ArrayList<String> listaSenior = new ArrayList<>();

        for (Impiegato i : listaImpiegati) {
            if (Objects.equals(i.getCategoria(), "senior")) {
                listaSenior.add(i.getCf());
            }
        }
        return listaSenior;
    }

    public ArrayList<String> getListaDirigenti() {
        ArrayList<String> listaDirigenti = new ArrayList<>();

        for (Impiegato i : listaImpiegati) {
            if (Objects.equals(i.getCategoria(), "dirigente")) {
                listaDirigenti.add(i.getCf());
            }
        }

        return listaDirigenti;
    }

    public ArrayList<String> getCupProgetti() {
        ArrayList<String> listaCupProgetti = new ArrayList<>();

        for (Progetto p : listaProgetti)
            listaCupProgetti.add(p.getCup());

        return listaCupProgetti;
    }

    public ArrayList<String> getListaNomiProgetti() {
        ArrayList<String> listaNomiProgetti = new ArrayList<>();

        for (Progetto p : listaProgetti)
            listaNomiProgetti.add(p.getNome());

        return listaNomiProgetti;
    }

    public void aggiungiLaboratorio(String nome, String respSci, String topic) throws SQLException {
        LaboratorioDAO laboratorioDAO = new ImplementazioneLaboratorioDAO();

        laboratorioDAO.aggiungiLaboratorio(nome, respSci, topic);

        listaLaboratori.clear();
        getLaboratoriDatabase();
    }

    public void rimuoviLaboratorio(String nomeLab) throws SQLException {
        LaboratorioDAO laboratorioDAO = new ImplementazioneLaboratorioDAO();

        laboratorioDAO.rimuoviLaboratorio(nomeLab);

        listaLaboratori.clear();
        getLaboratoriDatabase();
    }

    public void associaProgettoLaboratorio(String cup, String laboratorio) throws SQLException {
        ProgettoDAO progettoDAO = new ImplementazioneProgettoDAO();
        progettoDAO.collegaLaboratorio(laboratorio, cup);

        for (Laboratorio l : listaLaboratori) {
            if (l.getNome().equals(laboratorio))
                l.setProgetto(cup);
        }
    }

    public ArrayList<String> getListaAfferentiLaboratorio(String laboratorio) {
        GestionaleDAO gestionaleDAO = new ImplementazioneGestionaleDAO();
        ArrayList<String> listaAfferenti = new ArrayList<>();

        gestionaleDAO.getListaAfferenti(laboratorio, listaAfferenti);

        for (Laboratorio l : listaLaboratori) {
            if (l.getNome().equals(laboratorio))
                l.setListaAfferenti(listaAfferenti);
        }

        return listaAfferenti;
    }

    public void promuovi(String cf, String promotore) throws SQLException {
        ImpiegatoDAO impiegatoDAO = new ImplementazioneImpiegatoDAO();

        impiegatoDAO.promuoviImpiegato(cf, promotore);
        listaImpiegati.clear();
        getImpiegatiDatabase();
    }

    public void aggiungiAfferente(String cf, String laboratorio) throws SQLException {
        LaboratorioDAO laboratorioDAO = new ImplementazioneLaboratorioDAO();
        laboratorioDAO.aggiungiAfferente(cf, laboratorio);

        for (Laboratorio l : listaLaboratori) {
            if (l.getNome().equals(laboratorio)) {
                l.addAfferente(cf);
            }
        }
    }

    public void aggiungiProgetto(String cup, String nome, String refSci, String resp) throws SQLException {
        ProgettoDAO progettoDAO = new ImplementazioneProgettoDAO();
        progettoDAO.aggiungiProgetto(cup, refSci, resp, nome);

        listaProgetti.clear();
        getProgettiDatabase();
    }

    public void rimuoviProgetto(String cup) throws SQLException {
        ProgettoDAO progettoDAO = new ImplementazioneProgettoDAO();

        progettoDAO.eliminaProgetto(cup);

        listaProgetti.removeIf(p -> p.getCup().equals(cup));
    }

    public ArrayList<String> getSerialiAttrezzature() {
        ArrayList<String> listaSeriali = new ArrayList<>();

        for (Attrezzatura a : listaAttrezzature)
            listaSeriali.add(a.getSeriale());

        return listaSeriali;
    }

    public ArrayList<String> getListaTipiAttrezzature() {
        ArrayList<String> listaTipi = new ArrayList<>();

        for (Attrezzatura a : listaAttrezzature)
            listaTipi.add(a.getTipo());

        return listaTipi;
    }

    public ArrayList<Float> getListaCostiAttrezzature() {
        ArrayList<Float> listaCosti = new ArrayList<>();

        for (Attrezzatura a : listaAttrezzature)
            listaCosti.add(a.getCosto());

        return listaCosti;
    }

    public void acquistaAttrezzatura(String seriale, String nomeLab) throws SQLException {
        LaboratorioDAO laboratorioDAO = new ImplementazioneLaboratorioDAO();

        laboratorioDAO.acquistaAttrezzatura(seriale, nomeLab);

        listaAttrezzature.clear();
        getAttrezzatureDatabase();
    }

    public void aggiungiAttrezzatura(String seriale, String tipo) throws SQLException {
        AttrezzaturaDAO attrezzaturaDAO = new ImplementazioneAttrezzaturaDAO();

        attrezzaturaDAO.aggiungiAttrezzatura(seriale, tipo);

        listaAttrezzature.clear();
        getAttrezzatureDatabase();
    }

    public Attrezzatura getDatiAttrezzatura(String seriale) {
        for (Attrezzatura a : listaAttrezzature)
            if (a.getSeriale().equals(seriale))
                return a;

        return null;
    }
}