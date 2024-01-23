package DAO;

import java.sql.SQLException;

public interface LaboratorioDAO {
    /**
     * @param nome Nome del Laboratorio
     * @param resp_sci Responsabile Scientifico che si occupa del laboratorio
     * @param topic Topic del laboratorio
     * @return Restituisce il successo o il fallimento dell'operazione
     * @throws SQLException Lancia un'eccezione se fallisce l'operazione sul database
     */
    boolean aggiungiLaboratorio(String nome, String resp_sci, String topic) throws SQLException;

    /**
     * @param cf Codice Fiscale dell'impiegato che deve lavorare nel Laboratorio
     * @param nomeLab Laboratorio al quale viene aggiunto un afferente
     * @return Restituisce il successo o il fallimento dell'operazione
     * @throws SQLException Lancia un'eccezione se fallisce l'operazione sul database
     */
    boolean aggiungiAfferente(String cf, String nomeLab) throws SQLException;

    boolean rimuoviLaboratorio(String nomeLab) throws SQLException;

    //boolean getProgetto(String nomeLab, String cup);
}
