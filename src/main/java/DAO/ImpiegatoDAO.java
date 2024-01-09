package DAO;

import java.sql.Date;
import java.sql.SQLException;

public interface ImpiegatoDAO {
    /**
     * @param cf             Codice Fiscale dell'impiegato
     * @param nome           Nome dell'impiegato
     * @param cognome        Cognome dell'impiegato
     * @param dataNascita    Data di Nascita dell'impiegato
     * @param dataAssunzione Data di Assunzione dell'impiegato
     * @param categoria      Categoria del contratto dell'impiegato
     * @return Restituisce il successo o il fallimento dell'operazione
     * @throws SQLException Lancia un'eccezione se fallisce l'operazione sul database
     */
    boolean assumiImpiegato(String cf, String nome, String cognome, Date dataNascita, Date dataAssunzione, String categoria) throws SQLException;

    /**
     * @param cf             Codice Fiscale dell'impiegato
     * @param nome           Nome dell'impiegato
     * @param cognome        Cognome dell'impiegato
     * @param dataNascita    Data di Nascita dell'impiegato
     * @param dataAssunzione Data di Assunzione dell'impiegato
     * @param dataScadenza   Data di Scadenza del contratto
     * @param cup            Codice Identificativo del Progetto
     * @return Restituisce il successo o il fallimento dell'operazione
     * @throws SQLException Lancia un'eccezione se fallisce l'operazione sul database
     */
    boolean assumiImpiegatoProgetto(String cf, String nome, String cognome, Date dataNascita, Date dataAssunzione, Date dataScadenza, String cup) throws SQLException;

    /**
     * @param cf Codice Fiscale dell'impiegato da licenziare
     * @return Restituisce il successo o il fallimento dell'operazione
     * @throws SQLException Lancia un'eccezione se fallisce l'operazione sul database
     */
    boolean licenziaImpiegato(String cf) throws SQLException;

    /**
     * @param vecchioCF Codice Fiscale dell'impiegato da sostituire
     * @param nuovoCF   Codice Fiscale dell'impiegato che sostituisce
     * @return Restituisce il successo o il fallimento dell'operazione
     * @throws SQLException Lancia un'eccezione se fallisce l'operazione sul database
     */
    boolean sostituisciImpiegato(String vecchioCF, String nuovoCF) throws SQLException;

    /**
     * @param cf                 Codice Fiscale dell'impiegato da promuovere
     * @param promotoreDirigente Dirigente che raccomanda un impiegato per essere Dirigente
     * @return Restituisce il successo o il fallimento dell'operazione
     * @throws SQLException Lancia un'eccezione se fallisce l'operazione sul database
     */
    boolean promuoviImpiegato(String cf, String promotoreDirigente) throws SQLException;
}
