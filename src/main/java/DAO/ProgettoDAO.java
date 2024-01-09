package DAO;

import java.sql.SQLException;

public interface ProgettoDAO {
    /**
     * @param cup Codice Identificativo del Progetto
     * @param ref_sci Referente Scientifico che si occupa del progetto
     * @param resp Responsabile che coordina il progetto
     * @param nome Nome del progetto
     * @return Restituisce il successo o il fallimento dell'operazione
     * @throws SQLException Lancia un'eccezione se fallisce l'operazione sul database
     */
    boolean aggiungiProgetto(String cup, String ref_sci, String resp, String nome) throws SQLException;

    /**
     * @param nomeLab Laboratorio da collegare
     * @param cup Codice Identificativo del Progetto da collegare
     * @return Restituisce il successo o il fallimento dell'operazione
     * @throws SQLException Lancia un'eccezione se fallisce l'operazione sul database
     */
    boolean collegaLaboratorio(String nomeLab, String cup) throws SQLException;

    /**
     * @param seriale Seriale dell'attrezzatura da acquistare
     * @param nomeLab Laboratorio che utilizza l'attrezzatura
     * @return Restituisce il successo o il fallimento dell'operazione
     * @throws SQLException Lancia un'eccezione se fallisce l'operazione sul database
     */
    boolean acquistaAttrezzatura(String seriale, String nomeLab) throws SQLException;
}
