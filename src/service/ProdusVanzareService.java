package service;

import config.DatabaseConfiguration;
import interfaces.CrudService;
import model.ProdusAlimentar;
import model.ProdusVanzare;
import model.Retail;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ProdusVanzareService implements CrudService<ProdusVanzare> {
    private List<ProdusVanzare> produseVanzare;

    public ProdusVanzareService() {
        this.produseVanzare = new ArrayList<>();
    }

    public ProdusVanzareService(List<ProdusVanzare> produseVanzare) {
        this.produseVanzare = produseVanzare;
    }

    public ProdusVanzare getProdusVanzareByID(int idProdusVanzare){
        String query = "SELECT * FROM produsvanzare WHERE idProdus = ?";

        Connection connection = DatabaseConfiguration.getDatabaseConnection();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, idProdusVanzare);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                String numeProdus = resultSet.getString("numeProdus");
                String descriere = resultSet.getString("descriere");
                double pret = resultSet.getDouble("pret");
                int categorieId = resultSet.getInt("categorie");
                int id = resultSet.getInt("idProdus");

                return new ProdusVanzare(numeProdus, descriere, pret, categorieId);
            } else {
                System.out.println("Produsul de vanzare cu id-ul " + idProdusVanzare + " nu a fost găsit.");
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void add(ProdusVanzare produsVanzare) {
        String insertCategorySql = "INSERT INTO produsvanzare(numeProdus, descriere, pret, categorie) VALUES(?, ?, ?, ?)";

        Connection connection = DatabaseConfiguration.getDatabaseConnection();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(insertCategorySql);
            preparedStatement.setString(1, produsVanzare.getNumeProdus());
            preparedStatement.setString(2, produsVanzare.getDescriere());
            preparedStatement.setDouble(3, produsVanzare.getPret());
            preparedStatement.setInt(4, produsVanzare.getCategorie());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(int id, ProdusVanzare produsVanzare) {
        String updateCategorySQL = "UPDATE produsvanzare SET numeProdus=?, descriere=?, pret=?, categorie=? WHERE idProdus=?";

        Connection connection = DatabaseConfiguration.getDatabaseConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(updateCategorySQL);
            preparedStatement.setString(1, produsVanzare.getNumeProdus());
            preparedStatement.setString(2, produsVanzare.getDescriere());
            preparedStatement.setDouble(3, produsVanzare.getPret());
            preparedStatement.setInt(4, produsVanzare.getCategorie());

            preparedStatement.setInt(5, id);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void remove(int id) {
        String removeProductSQL = "DELETE FROM produsvanzare WHERE idProdus=?\n";

        Connection connection = DatabaseConfiguration.getDatabaseConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(removeProductSQL);
            preparedStatement.setInt(1, id);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void display() {
        String selectSql = "SELECT pv.idProdus, pv.numeProdus, pv.descriere, pv.pret, pv.categorie, " +
                "GROUP_CONCAT(pi.idProdusAlimentar) AS iduriProduseAlimentare, " +
                "GROUP_CONCAT(pi.cantitate) AS cantitati " +
                "FROM produsvanzare pv " +
                "LEFT JOIN produsvanzare_ingrediente pi ON pv.idProdus = pi.idProdusVanzare " +
                "GROUP BY pv.idProdus";

        Connection connection = DatabaseConfiguration.getDatabaseConnection();

        try {
            Statement stmt = connection.createStatement();
            ResultSet resultSet = stmt.executeQuery(selectSql);
            while (resultSet.next()) {
                System.out.println("\n");
                System.out.println("Id-ul produsului de vanzare:" + resultSet.getInt(1));
                System.out.println("Nume:" + resultSet.getString(2));
                System.out.println("Descriere:" + resultSet.getString(3));
                System.out.println("Pret:" + resultSet.getDouble(4));
                System.out.println("Categorie id:" + resultSet.getInt(5));
                System.out.println("Id-urile produselor alimentare:" + resultSet.getString(6));
                System.out.println("Cantitatile:" + resultSet.getString(7));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteIngredienteProductById(int id)
    {
        String removeProductSQL = "DELETE FROM produsvanzare_ingrediente WHERE idProdusVanzare=?\n";

        Connection connection = DatabaseConfiguration.getDatabaseConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(removeProductSQL);
            preparedStatement.setInt(1, id);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int getLastInsertedProductId() {
        String selectSql = "SELECT idProdus FROM produsvanzare ORDER BY idProdus DESC LIMIT 1";

        Connection connection = DatabaseConfiguration.getDatabaseConnection();

        try {
            Statement stmt = connection.createStatement();
            ResultSet resultSet = stmt.executeQuery(selectSql);
            if (resultSet.next()) {
                return resultSet.getInt("idProdus");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return -1; // Returnăm -1 în cazul în care nu există niciun produs de vânzare în baza de date
    }



    public void insertProdusVanzareIngrediente(int idProdusVanzare, int idProdusAlimentar, double cantitate) {
        String insertSql = "INSERT INTO produsvanzare_ingrediente (idProdusVanzare, idProdusAlimentar, cantitate) VALUES (?, ?, ?)";

        Connection connection = DatabaseConfiguration.getDatabaseConnection();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(insertSql);
            preparedStatement.setInt(1, idProdusVanzare);
            preparedStatement.setInt(2, idProdusAlimentar);
            preparedStatement.setDouble(3, cantitate);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
