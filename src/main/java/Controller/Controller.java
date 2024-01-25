package Controller;

import DAO.*;
import ImplementazionePostgresDAO.*;
import Model.*;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Controller {
    // TODO aggiungi documentazione funzioni
    private final List<Impiegato> listaImpiegati = new ArrayList<>();
    private final List<Laboratorio> listaLaboratori = new ArrayList<>();
    private final List<Progetto> listaProgetti = new ArrayList<>();
    private final List<Attrezzatura> listaAttrezzature = new ArrayList<>();
    private final List<Promozione> listaPromozioni = new ArrayList<>();


    /**
     * Metodo costruttore della classe Controller, instanzia tutte le liste tramite le funzioni:
     * <li color="orange">getImpiegatoDatabase</li>
     * <li color="orange">getLaboratoriDatabase</li>
     * <li color="orange">getPromozioniDatabase</li>
     * <li color="orange">getAttrezzatureDatabase</li>
     * <li color="orange">getProgettiDatabase</li>
     */
    public Controller() {
        getImpiegatiDatabase();
        getLaboratoriDatabase();
        getPromozioniDatabase();
        getAttrezzatureDatabase();
        getProgettiDatabase();
    }

    /**
     * Prende tutti i dati degli impiegati dal database e li inserisce nell'ArrayList listaImpiegati
     */
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

    /**
     * Prende tutti i dati dei laboratori dal database e li inserisce nell'ArrayList listaLaboratori
     */
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

    /**
     * Prende tutti i dati delle promozioni dal database e li inserisce nell'ArrayList listaPromozioni
     */
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

    /**
     * Prende tutti i dati dei progetti dal database e li inserisce nell'ArrayList listaProgetti
     */
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

    /**
     * Prende tutti i dati delle attrezzature dal database e li inserisce nell'ArrayList listaAttrezzature
     */
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

    // FUNZIONI PER LA GUI RIFERITE AGLI IMPIEGATI

    /**
     * La funzione itera nella listaImpiegati ottenendo tutti i Codici Fiscali
     *
     * @return ArrayList<String> che contiene tutti i Codici Fiscali
     */
    public ArrayList<String> getListaCF() {
        ArrayList<String> listaCF = new ArrayList<>();

        for (Impiegato i : listaImpiegati)
            listaCF.add(i.getCf());

        return listaCF;
    }

    /**
     * La funzione itera nella listaImpiegati ottenendo tutti i Nomi
     *
     * @return ArrayList<String> che contiene tutti i Nomi
     */
    public ArrayList<String> getListaNomi() {
        ArrayList<String> listaNomi = new ArrayList<>();

        for (Impiegato i : listaImpiegati)
            listaNomi.add(i.getNome());

        return listaNomi;
    }

    /**
     * La funzione itera nella listaImpiegati ottenendo tutti i Cognomi
     *
     * @return ArrayList<String> che contiene tutti i Cognomi
     */
    public ArrayList<String> getListaCognomi() {
        ArrayList<String> listaCognomi = new ArrayList<>();

        for (Impiegato i : listaImpiegati)
            listaCognomi.add(i.getCognome());

        return listaCognomi;
    }

    /**
     * La funzione ottiene la lista dei laboratori ai quali l'impiegato lavora
     *
     * @param CF Codice Fiscale dell'impiegato del quale vogliamo sapere i laboratori ai quali lavora
     * @return ArrayList<String> che contiene tutti i Laboratori
     */
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

    /**
     * Prende i dati dalla GUI, li passa al DAO in modo tale da aggiungere l'impiegato al database e ricarica la listaImpiegati
     *
     * @param cf        Codice Fiscale dell'impiegato
     * @param nome      Nome dell'impiegato
     * @param cognome   Cognome dell'impiegato
     * @param dataNasc  Data di Nascita dell'impiegato
     * @param dataAss   Data di Assunzione dell'impiegato
     * @param dataScad  Data di Scadenza del contratto dell'impiegato "A Progetto"
     * @param categoria Categoria dell'impiegato
     * @param cup       CUP del Progetto a cui l'impiegato "A Progetto" lavora
     * @throws SQLException Nel caso ci siano dei controlli violati nel database
     */
    public void aggiungiImpiegato(String cf, String nome, String cognome, Date dataNasc, Date dataAss, Date dataScad, String categoria, String cup) throws SQLException {
        ImpiegatoDAO impiegatoDAO = new ImplementazioneImpiegatoDAO();

        if (dataScad == null)
            impiegatoDAO.assumiImpiegato(cf, nome, cognome, dataNasc, dataAss, categoria);
        else
            impiegatoDAO.assumiImpiegatoProgetto(cf, nome, cognome, dataNasc, dataAss, dataScad, cup);

        listaImpiegati.clear();
        getImpiegatiDatabase();
    }

    /**
     * Prende il Codice Fiscale dalla GUI, li passa al DAO in modo tale da eliminare l'impiegato dal database e lo rimuove dalla listaImpiegati
     *
     * @param cf Codice Fiscale dell'impiegato
     * @throws SQLException Nel caso ci siano dei controlli violati nel database
     */
    public void rimuoviImpiegato(String cf) throws SQLException {
        ImpiegatoDAO impiegatoDAO = new ImplementazioneImpiegatoDAO();

        impiegatoDAO.licenziaImpiegato(cf);

        listaImpiegati.removeIf(i -> i.getCf().equals(cf));
    }

    /**
     * Prende il Codice Fiscale dalla GUI e ottiene tutti i dati dell'impiegato scelto da listaImpiegati
     *
     * @param cf Codice Fiscale dell'impiegato
     * @return Un oggetto di tipo <font color="green">Impiegato</font> se il <font color="#00FFFF">cf</font> è presente nella listaImpiegati, <font color="red">null</font> altrimenti
     */
    public Impiegato getDatiProfilo(String cf) {
        ImpiegatoDAO impiegatoDAO = new ImplementazioneImpiegatoDAO();

        ArrayList<Promozione> listaPromozioni = new ArrayList<>();

        impiegatoDAO.getPromozioni(cf, listaPromozioni);

        for (Impiegato i : listaImpiegati) {
            if (Objects.equals(cf, i.getCf())) {
                i.setListaPromozioni(listaPromozioni);
                return i;
            }
        }

        return null;
    }

    /**
     * Ottiene la lista di tutti gli impiegati la cui categoria è 'senior'
     *
     * @return ArrayList<String> contenente tutti gli impiegati senior
     */
    public ArrayList<String> getListaSenior() {
        ArrayList<String> listaSenior = new ArrayList<>();

        for (Impiegato i : listaImpiegati) {
            if (Objects.equals(i.getCategoria(), "senior")) {
                listaSenior.add(i.getCf());
            }
        }
        return listaSenior;
    }

    /**
     * Ottiene la lista di tutti gli impiegati la cui categoria è 'dirigente'
     *
     * @return ArrayList<String> contenente tutti gli impiegati dirigenti
     */
    public ArrayList<String> getListaDirigenti() {
        ArrayList<String> listaDirigenti = new ArrayList<>();

        for (Impiegato i : listaImpiegati) {
            if (Objects.equals(i.getCategoria(), "dirigente")) {
                listaDirigenti.add(i.getCf());
            }
        }

        return listaDirigenti;
    }

    /**
     * Promuove un impiegato alla categoria successiva.
     * Se l'impiegato è senior ci sarà bisogno di un Promotore, ovvero un impiegato di categoria 'dirigente'
     *
     * @param cf        Codice Fiscale dell'impiegato da promuovere
     * @param promotore Codice Fiscale del Promotore
     * @throws SQLException Nel caso ci siano dei controlli violati nel database
     */
    public void promuovi(String cf, String promotore) throws SQLException {
        ImpiegatoDAO impiegatoDAO = new ImplementazioneImpiegatoDAO();

        impiegatoDAO.promuoviImpiegato(cf, promotore);

        listaImpiegati.clear();
        getImpiegatiDatabase();
    }

    // FUNZIONI PER LA GUI RIFERITE AI LABORATORI

    /**
     * Ottiene tutti i nomi dei laboratori da listaLaboratori
     *
     * @return ArrayList<String> contenente tutti i nomi dei laboratori
     */
    public ArrayList<String> getListaNomiLaboratori() {
        ArrayList<String> listaNomi = new ArrayList<>();

        for (Laboratorio l : listaLaboratori)
            listaNomi.add(l.getNome());

        return listaNomi;
    }

    /**
     * Ottiene tutti i responsabili scientifici dei laboratori da listaLaboratori
     *
     * @return ArrayList<String> contenente tutti i responsabili scientifici dei laboratori
     */
    public ArrayList<String> getListaRespSci() {
        ArrayList<String> listaRespSci = new ArrayList<>();

        for (Laboratorio l : listaLaboratori)
            listaRespSci.add(l.getRespSci());

        return listaRespSci;
    }

    /**
     * Ottiene tutti i budget dei laboratori da listaLaboratori
     *
     * @return ArrayList<String> contenente tutti i budget dei laboratori
     */
    public ArrayList<Float> getListaBudget() {
        ArrayList<Float> listaBudget = new ArrayList<>();

        for (Progetto p : listaProgetti)
            listaBudget.add(p.getBudget());

        return listaBudget;
    }

    /**
     * Ottiene tutti i topic dei laboratori da listaLaboratori
     *
     * @return ArrayList<String> contenente tutti i topic dei laboratori
     */
    public ArrayList<String> getListaTopic() {
        ArrayList<String> listaTopic = new ArrayList<>();

        for (Laboratorio l : listaLaboratori)
            listaTopic.add(l.getTopic());

        return listaTopic;
    }

    /**
     * Ottiene tutti i n_afferenti dei laboratori da listaLaboratori
     *
     * @return ArrayList<String> contenente tutti i n_afferenti dei laboratori
     */
    public ArrayList<Integer> getListaNAfferenti() {
        ArrayList<Integer> listaNAfferenti = new ArrayList<>();

        for (Laboratorio l : listaLaboratori)
            listaNAfferenti.add(l.getN_afferenti());

        return listaNAfferenti;
    }

    /**
     * Ottiene tutti i progetti associati ai laboratori da listaLaboratori
     *
     * @return ArrayList<String> contenente tutti i progetti associati ai laboratori
     */
    public ArrayList<String> getListaProgettiAssociati() {
        ArrayList<String> listaProgetti = new ArrayList<>();

        for (Laboratorio l : listaLaboratori)
            listaProgetti.add(l.getProgetto());

        return listaProgetti;
    }

    /**
     * Prende i dati dalla GUI e li passa al DAO in modo tale da aggiungere un nuovo laboratorio al database, ricarica successivamente listaLaboratori
     * @param nome Nome del laboratorio
     * @param respSci Responsabile Scientifico del laboratorio (impiegato di categoria 'senior')
     * @param topic Topic del laboratorio
     * @throws SQLException Nel caso ci siano dei controlli violati nel database
     */
    public void aggiungiLaboratorio(String nome, String respSci, String topic) throws SQLException {
        LaboratorioDAO laboratorioDAO = new ImplementazioneLaboratorioDAO();

        laboratorioDAO.aggiungiLaboratorio(nome, respSci, topic);

        listaLaboratori.clear();
        getLaboratoriDatabase();
    }


    /**
     *
     * @param nomeLab
     * @throws SQLException
     */
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

    public void aggiungiAfferente(String cf, String laboratorio) throws SQLException {
        LaboratorioDAO laboratorioDAO = new ImplementazioneLaboratorioDAO();
        laboratorioDAO.aggiungiAfferente(cf, laboratorio);

        for (Laboratorio l : listaLaboratori) {
            if (l.getNome().equals(laboratorio)) {
                l.addAfferente(cf);
            }
        }
    }

    public void acquistaAttrezzatura(String seriale, String nomeLab) throws SQLException {
        LaboratorioDAO laboratorioDAO = new ImplementazioneLaboratorioDAO();

        laboratorioDAO.acquistaAttrezzatura(seriale, nomeLab);

        listaAttrezzature.clear();
        getAttrezzatureDatabase();
    }


    // FUNZIONI PER LA GUI RIFERITE AI PROGETTI

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

    /**
     * Ottiene tutti i referenti scientifici dei progetti da listaProgetti
     *
     * @return ArrayList<String> contenente tutti i referenti scientifici dei progetti
     */
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

    // FUNZIONI PER LA GUI RIFERITE ALLE ATTREZZATURE

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