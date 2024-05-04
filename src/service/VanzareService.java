package service;

import config.DatabaseConfiguration;
import interfaces.CrudService;
import model.ProdusVanzare;
import model.Vanzare;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class VanzareService implements CrudService<Vanzare> {
    private List<Vanzare> vanzari;

    public VanzareService() {
        this.vanzari = new ArrayList<>();
    }

    public VanzareService(List<Vanzare> vanzari) {
        this.vanzari = vanzari;
    }


    public int getLastInsertedId() {
        String selectSql = "SELECT idVanzare FROM vanzare ORDER BY idVanzare DESC LIMIT 1";

        Connection connection = DatabaseConfiguration.getDatabaseConnection();

        try {
            Statement stmt = connection.createStatement();
            ResultSet resultSet = stmt.executeQuery(selectSql);
            if (resultSet.next()) {
                return resultSet.getInt("idVanzare");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return -1; // Returnăm -1 în cazul în care nu există niciun produs de vânzare în baza de date
    }

    public void insertProdusVanzareCantitate(int idVanzare, int idProdusVanzare, double cantitate) {
        String insertSql = "INSERT INTO vanzare_produsvanzare (idVanzare, idProdusVanzare, cantitate) VALUES (?, ?, ?)";

        Connection connection = DatabaseConfiguration.getDatabaseConnection();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(insertSql);
            preparedStatement.setInt(1, idVanzare);
            preparedStatement.setInt(2, idProdusVanzare);
            preparedStatement.setDouble(3, cantitate);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void add(Vanzare vanzare) {
        String insertCategorySql = "INSERT INTO vanzare(pretTotal, dataTranzactie, cashCard) VALUES(?, ?, ?)";

        Connection connection = DatabaseConfiguration.getDatabaseConnection();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(insertCategorySql);
            preparedStatement.setDouble(1, vanzare.getPretTotal());

            LocalDate dataTranzactie = vanzare.getDataTranzactie();
            preparedStatement.setDate(2, java.sql.Date.valueOf(dataTranzactie));

            preparedStatement.setString(3, vanzare.getCashCard());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(int id, Vanzare item) {

    }

    @Override
    public void remove(int id) {
        String removeSQL = "DELETE FROM vanzare WHERE idVanzare=?\n";

        Connection connection = DatabaseConfiguration.getDatabaseConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(removeSQL);
            preparedStatement.setInt(1, id);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteSellsById(int id)
    {
        String removeSellSQL = "DELETE FROM vanzare_produsvanzare WHERE idVanzare=?\n";

        Connection connection = DatabaseConfiguration.getDatabaseConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(removeSellSQL);
            preparedStatement.setInt(1, id);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void display() {
        String selectSql = "SELECT v.idVanzare, v.pretTotal, v.dataTranzactie, v.cashCard, " +
                "GROUP_CONCAT(v_pv.idProdusVanzare) AS iduriProduseVanzare, " +
                "GROUP_CONCAT(v_pv.cantitate) AS cantitati " +
                "FROM vanzare v " +
                "LEFT JOIN vanzare_produsvanzare v_pv ON v.idVanzare = v_pv.idVanzare " +
                "GROUP BY v.idVanzare";

        Connection connection = DatabaseConfiguration.getDatabaseConnection();

        try {
            Statement stmt = connection.createStatement();
            ResultSet resultSet = stmt.executeQuery(selectSql);
            while (resultSet.next()) {
                System.out.println("\n");
                System.out.println("Id-ul vanzarii:" + resultSet.getInt(1));
                System.out.println("Pret total:" + resultSet.getDouble(2));
                System.out.println("Data tranzactiei:" + resultSet.getDate(3));
                System.out.println("Cash/card:" + resultSet.getString(4));
                System.out.println("Id-urile produselor de vanzare:" + resultSet.getString(5));
                System.out.println("Cantitatile:" + resultSet.getString(6));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
