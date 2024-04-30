package service;

import config.DatabaseConfiguration;
import interfaces.CrudService;
import model.Furnizor;
import model.ProdusAlimentar;
import model.Retail;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class RetailService implements CrudService<Retail> {
    private List<Retail> retails;

    public RetailService(){
        this.retails = new ArrayList<Retail>();
    }

    public RetailService(List<Retail> retails){
        this.retails = retails;
    }


    public Retail getRetailByID(int idRetail){
        String query = "SELECT * FROM retail WHERE id = ?";

        Connection connection = DatabaseConfiguration.getDatabaseConnection();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, idRetail);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                String numeProdus = resultSet.getString("numeProdus");
                String descriere = resultSet.getString("descriere");
                double pret = resultSet.getDouble("pret");
                int categorieId = resultSet.getInt("categorieId");
                int furnizorId = resultSet.getInt("furnizorId");
                String material = resultSet.getString("material");
                int stoc = resultSet.getInt("stoc");
                int id = resultSet.getInt("id");

                return new Retail(numeProdus, descriere, pret, categorieId, furnizorId, material, stoc );
            } else {
                System.out.println("Retail-ul cu id-ul " + idRetail + " nu a fost găsit.");
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void add(Retail retail) {
        String insertCategorySql = "INSERT INTO retail(numeProdus, descriere, pret, categorieId, furnizorId, material, stoc) VALUES(?, ?, ?, ?, ?, ?, ?)";

        Connection connection = DatabaseConfiguration.getDatabaseConnection();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(insertCategorySql);
            preparedStatement.setString(1, retail.getNumeProdus());
            preparedStatement.setString(2, retail.getDescriere());
            preparedStatement.setDouble(3, retail.getPret());
            preparedStatement.setInt(4, retail.getCategorie());
            preparedStatement.setInt(5, retail.getFurnizor());
            preparedStatement.setString(6, retail.getMaterial());
            preparedStatement.setInt(7, retail.getStoc());


            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(int id, Retail retail) {
        String updateCategorySQL = "UPDATE retail SET numeProdus=?, descriere=?, pret=?, categorieId=?, furnizorId=?, material=?, stoc=? WHERE id=?";

        Connection connection = DatabaseConfiguration.getDatabaseConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(updateCategorySQL);
            preparedStatement.setString(1, retail.getNumeProdus());
            preparedStatement.setString(2, retail.getDescriere());
            preparedStatement.setDouble(3, retail.getPret());
            preparedStatement.setInt(4, retail.getCategorie());
            preparedStatement.setInt(5, retail.getFurnizor());
            preparedStatement.setString(6, retail.getMaterial());
            preparedStatement.setInt(7, retail.getStoc());
            preparedStatement.setInt(8, id);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void remove(int id) {
        String removeAlimProductSQL = "DELETE FROM retail WHERE id=?\n";

        Connection connection = DatabaseConfiguration.getDatabaseConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(removeAlimProductSQL);
            preparedStatement.setInt(1, id);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void display() {
        String selectSql = "SELECT * FROM retail";

        Connection connection = DatabaseConfiguration.getDatabaseConnection();

        try {
            Statement stmt = connection.createStatement();
            ResultSet resultSet = stmt.executeQuery(selectSql);
            while (resultSet.next()) {
                System.out.println("\n");
                System.out.println("Id-ul produsului retail:" + resultSet.getInt(1));
                System.out.println("Nume:" + resultSet.getString(2));
                System.out.println("Descriere:" + resultSet.getString(3));
                System.out.println("Pret:" + resultSet.getDouble(4));
                System.out.println("Categorie id:" + resultSet.getInt(5));
                System.out.println("Furnizor id:" + resultSet.getInt(6));
                System.out.println("Material:" + resultSet.getString(7));
                System.out.println("Stoc:" + resultSet.getInt(8));

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
