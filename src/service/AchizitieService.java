package service;

import config.DatabaseConfiguration;
import interfaces.CrudService;
import model.Achizitie;
import model.ProdusAlimentar;
import model.Vanzare;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class AchizitieService implements CrudService<Achizitie>{
    private List<Achizitie> achizitii;

    public AchizitieService() {
        this.achizitii = new ArrayList<>();
    }

    public AchizitieService(List<Achizitie> achizitii) {
        this.achizitii = achizitii;
    }


    public int getLastInsertedId() {
        String selectSql = "SELECT idAchizitie FROM achizitie ORDER BY idAchizitie DESC LIMIT 1";

        Connection connection = DatabaseConfiguration.getDatabaseConnection();

        try {
            Statement stmt = connection.createStatement();
            ResultSet resultSet = stmt.executeQuery(selectSql);
            if (resultSet.next()) {
                return resultSet.getInt("idAchizitie");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return -1; // Returnăm -1 în cazul în care nu există niciun produs de vânzare în baza de date
    }

    public void insertProdusAlimentarCantitate(int idAchizitie, int idProdusAlimentar, double cantitate) {
        String insertSql = "INSERT INTO achizitie_produsalimentar (idAchizitie, idProdusAlimentar, cantitate) VALUES (?, ?, ?)";

        Connection connection = DatabaseConfiguration.getDatabaseConnection();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(insertSql);
            preparedStatement.setInt(1, idAchizitie);
            preparedStatement.setInt(2, idProdusAlimentar);
            preparedStatement.setDouble(3, cantitate);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void insertRetailCantitate(int idAchizitie, int idRetail, double cantitate) {
        String insertSql = "INSERT INTO achizitie_retail (idAchizitie, idRetail, cantitate) VALUES (?, ?, ?)";

        Connection connection = DatabaseConfiguration.getDatabaseConnection();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(insertSql);
            preparedStatement.setInt(1, idAchizitie);
            preparedStatement.setInt(2, idRetail);
            preparedStatement.setDouble(3, cantitate);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void add(Achizitie achizitie) {
        String insertCategorySql = "INSERT INTO achizitie(pretTotal, dataTranzactie, cashCard) VALUES(?, ?, ?)";

        Connection connection = DatabaseConfiguration.getDatabaseConnection();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(insertCategorySql);
            preparedStatement.setDouble(1, achizitie.getPretTotal());

            LocalDate dataTranzactie = achizitie.getDataTranzactie();
            preparedStatement.setDate(2, java.sql.Date.valueOf(dataTranzactie));

            preparedStatement.setString(3, achizitie.getCashCard());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(int id, Achizitie item) {

    }

    @Override
    public void remove(int id) {
        String removeSQL = "DELETE FROM achizitie WHERE idAchizitie=?\n";

        Connection connection = DatabaseConfiguration.getDatabaseConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(removeSQL);
            preparedStatement.setInt(1, id);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteProdusAlimentarAchizitieById(int id)
    {
        String removeSellSQL = "DELETE FROM achizitie_produsalimentar WHERE idAchizitie=?\n";

        Connection connection = DatabaseConfiguration.getDatabaseConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(removeSellSQL);
            preparedStatement.setInt(1, id);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteRetailAchizitieById(int id)
    {
        String removeSellSQL = "DELETE FROM achizitie_retail WHERE idAchizitie=?\n";

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
        String selectSql = "SELECT v.idAchizitie, v.pretTotal, v.dataTranzactie, v.cashCard, " +
                "GROUP_CONCAT(v_pv.idProdusAlimentar) AS iduriProduseAlimentare, " +
                "GROUP_CONCAT(v_pv.cantitate) AS cantitati, " +
                "GROUP_CONCAT(v_pv2.idRetail) AS iduriRetail, " +
                "GROUP_CONCAT(v_pv2.cantitate) AS cantitati2 " +
                "FROM achizitie v " +
                "LEFT JOIN achizitie_produsalimentar v_pv ON v.idAchizitie = v_pv.idAchizitie " +
                "LEFT JOIN achizitie_retail v_pv2 ON v.idAchizitie = v_pv2.idAchizitie " +
                "GROUP BY v.idAchizitie";

        Connection connection = DatabaseConfiguration.getDatabaseConnection();

        try {
            Statement stmt = connection.createStatement();
            ResultSet resultSet = stmt.executeQuery(selectSql);
            while (resultSet.next()) {
                System.out.println("\n");
                System.out.println("Id-ul achizitiei:" + resultSet.getInt(1));
                System.out.println("Pret total:" + resultSet.getDouble(2));
                System.out.println("Data tranzactiei:" + resultSet.getDate(3));
                System.out.println("Cash/card:" + resultSet.getString(4));
                System.out.println("Id-urile produselor alimentare:" + resultSet.getString(5));
                System.out.println("Cantitatile:" + resultSet.getString(6));
                System.out.println("Id-urile retail-urilor:" + resultSet.getString(7));
                System.out.println("Cantitatile:" + resultSet.getString(8));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
