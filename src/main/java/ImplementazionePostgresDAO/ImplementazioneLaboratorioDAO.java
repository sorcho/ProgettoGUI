package ImplementazionePostgresDAO;

import DAO.LaboratorioDAO;
import Database.ConnessioneDatabase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ImplementazioneLaboratorioDAO implements LaboratorioDAO {
    private Connection connection;

    public ImplementazioneLaboratorioDAO() {
        try {
            connection = ConnessioneDatabase.getInstance().getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean aggiungiLaboratorio(String nome, String resp_sci, String topic) throws SQLException {
        PreparedStatement query;
        query = connection.prepareCall("call add_laboratorio(?, ?, ?)");
        query.setString(1, nome);
        query.setString(2, resp_sci);
        query.setString(3, topic);
        int risultato = query.executeUpdate();
        return risultato == 1;
    }

    @Override
    public boolean aggiungiAfferente(String cf, String nomeLab) throws SQLException {
        PreparedStatement query;
        query = connection.prepareCall("call add_utilizza(?, ?)");
        query.setString(1, cf);
        query.setString(2, nomeLab);
        int risultato = query.executeUpdate();
        return risultato == 1;
    }

    @Override
    public boolean rimuoviLaboratorio(String nomeLab) throws SQLException {
        PreparedStatement query;
        query = connection.prepareCall("delete from laboratorio where nome = ?");
        query.setString(1, nomeLab);

        int risultato = query.executeUpdate();

        return risultato == 1;
    }
}
