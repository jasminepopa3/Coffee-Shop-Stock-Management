package service;

import config.DatabaseConfiguration;
import interfaces.CrudService;
import model.Categorie;
import model.Furnizor;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
public class FurnizorService implements CrudService<Furnizor> {
    private List<Furnizor> furnizori;

    public FurnizorService(List<Furnizor> furnizori) {
        this.furnizori = furnizori;
    }

    public FurnizorService() {
        this.furnizori = new ArrayList<>();
    }


    public Furnizor getFurnizorByID(int idFurnizor){

        String query = "SELECT * FROM furnizor WHERE id = ?";

        Connection connection = DatabaseConfiguration.getDatabaseConnection();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, idFurnizor);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                String nume = resultSet.getString("nume");
                String adresa = resultSet.getString("adresa");
                String contBancar = resultSet.getString("contBancar");
                String email = resultSet.getString("email");
                String nrTelefon = resultSet.getString("nrTelefon");
                int id = resultSet.getInt("id");

                return new Furnizor(nume, adresa, contBancar, email, nrTelefon);
            } else {
                System.out.println("Furnizorul cu id-ul " + idFurnizor + " nu a fost găsit.");
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }






    @Override
    public void add(Furnizor furnizor){
        String insertCategorySql = "INSERT INTO furnizor(nume, adresa, contBancar, email, nrTelefon) VALUES(?, ?, ?, ?, ?)";

        Connection connection = DatabaseConfiguration.getDatabaseConnection();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(insertCategorySql);
            preparedStatement.setString(1, furnizor.getNume());
            preparedStatement.setString(2, furnizor.getAdresa());
            preparedStatement.setString(3, furnizor.getContBancar());
            preparedStatement.setString(4, furnizor.getEmail());
            preparedStatement.setString(5, furnizor.getNrTelefon());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(int id, Furnizor furnizor) {
        String updateCategorySQL = "UPDATE furnizor SET nume=?, adresa=?, contBancar=?, email=?, nrTelefon=? WHERE id=?";

        Connection connection = DatabaseConfiguration.getDatabaseConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(updateCategorySQL);
            preparedStatement.setString(1, furnizor.getNume());
            preparedStatement.setString(2, furnizor.getAdresa());
            preparedStatement.setString(3, furnizor.getContBancar());
            preparedStatement.setString(4, furnizor.getEmail());
            preparedStatement.setString(5, furnizor.getNrTelefon());
            preparedStatement.setInt(6, id);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void remove(int id) {
        String removeCategorySQL = "DELETE FROM furnizor WHERE id=?\n";

        Connection connection = DatabaseConfiguration.getDatabaseConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(removeCategorySQL);
            preparedStatement.setInt(1, id);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void display() {
        String selectSql = "SELECT * FROM furnizor";

        Connection connection = DatabaseConfiguration.getDatabaseConnection();

        try {
            Statement stmt = connection.createStatement();
            ResultSet resultSet = stmt.executeQuery(selectSql);
            while (resultSet.next()) {
                System.out.println("\n");
                System.out.println("Id-ul furnizorului:" + resultSet.getInt(1));
                System.out.println("Nume:" + resultSet.getString(2));
                System.out.println("Adresa:" + resultSet.getString(3));
                System.out.println("Cont bancar:" + resultSet.getString(4));
                System.out.println("E-mail:" + resultSet.getString(5));
                System.out.println("Nr. telefon:" + resultSet.getString(6));

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean areProduseAsociate(int idFurnizor) {
        try {
            Connection connection = DatabaseConfiguration.getDatabaseConnection();

            // Verificăm tabela produsalimentar
            String queryProdusalimentar = "SELECT * FROM produsalimentar WHERE furnizorId = ?";
            PreparedStatement statementProdusalimentar = connection.prepareStatement(queryProdusalimentar);
            statementProdusalimentar.setInt(1, idFurnizor);
            ResultSet resultSetProdusalimentar = statementProdusalimentar.executeQuery();

            if (resultSetProdusalimentar.next()) {
                return true; // Dacă găsim cel puțin un rând în produsalimentar, returnăm true
            }


            // Verificăm tabela retail
            String queryRetail = "SELECT * FROM retail WHERE furnizorId = ?";
            PreparedStatement statementRetail = connection.prepareStatement(queryRetail);
            statementRetail.setInt(1, idFurnizor);
            ResultSet resultSetRetail = statementRetail.executeQuery();

            if (resultSetRetail.next()) {
                return true; // Dacă găsim cel puțin un rând în retail, returnăm true
            }

            // Dacă nu găsim niciun produs asociat, returnăm false
            return false;
        } catch (SQLException e) {
            e.printStackTrace();
            // În cazul unei excepții, putem considera că există produse asociate (pentru a evita ștergerea greșită)
            return true;
        }
    }


}
