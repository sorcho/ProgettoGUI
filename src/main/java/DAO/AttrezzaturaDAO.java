package DAO;

import java.sql.SQLException;

public interface AttrezzaturaDAO {
    /**
     * @param seriale Seriale dell'attrezzatura
     * @param tipo Tipologia dell'attrezzatura
     * @return Restituisce il successo o il fallimento dell'operazione
     * @throws SQLException Lancia un'eccezione se fallisce l'operazione sul database
     */
    boolean aggiungiAttrezzatura(String seriale, String tipo) throws SQLException;

    boolean eliminaAttrezzatura(String seriale) throws SQLException;
}
