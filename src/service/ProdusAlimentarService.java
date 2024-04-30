package service;
import config.DatabaseConfiguration;
import interfaces.CrudService;
import model.Categorie;
import model.Furnizor;
import model.Produs;
import model.ProdusAlimentar;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
public class ProdusAlimentarService implements CrudService<ProdusAlimentar> {
    private List<ProdusAlimentar> produseAlimentare;

    public ProdusAlimentarService(){
        this.produseAlimentare = new ArrayList<ProdusAlimentar>();
    }

    public ProdusAlimentarService(List<ProdusAlimentar> produseAlimentare){
        this.produseAlimentare = produseAlimentare;
    }

    public ProdusAlimentar getProdusAlimentarByID(int idProdusAlimentar){
        String query = "SELECT * FROM produsalimentar WHERE id = ?";

        Connection connection = DatabaseConfiguration.getDatabaseConnection();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, idProdusAlimentar);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                String numeProdus = resultSet.getString("numeProdus");
                String descriere = resultSet.getString("descriere");
                double pret = resultSet.getDouble("pret");
                int furnizorId = resultSet.getInt("furnizorId");
                int categorieId = resultSet.getInt("categorieId");

                Date dataExpirare2 = resultSet.getDate("dataExpirare");
                LocalDate dataExpirareLocalDate = dataExpirare2.toLocalDate();

                int stoc = resultSet.getInt("stoc");
                int id = resultSet.getInt("id");

                return new ProdusAlimentar(numeProdus, descriere, pret, furnizorId, categorieId, dataExpirareLocalDate, stoc );
            } else {
                System.out.println("Produsul alimentar cu id-ul " + idProdusAlimentar + " nu a fost găsit.");
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void add(ProdusAlimentar produsAlimentar) {
        String insertCategorySql = "INSERT INTO produsalimentar(numeProdus, descriere, pret, furnizorId, categorieId, dataExpirare, stoc) VALUES(?, ?, ?, ?, ?, ?, ?)";

        Connection connection = DatabaseConfiguration.getDatabaseConnection();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(insertCategorySql);
            preparedStatement.setString(1, produsAlimentar.getNumeProdus());
            preparedStatement.setString(2, produsAlimentar.getDescriere());
            preparedStatement.setDouble(3, produsAlimentar.getPret());
            preparedStatement.setInt(4, produsAlimentar.getFurnizor());
            preparedStatement.setInt(5, produsAlimentar.getCategorie());

            LocalDate dataExpirare = produsAlimentar.getDataExpirare();
            preparedStatement.setDate(6, java.sql.Date.valueOf(dataExpirare));

            preparedStatement.setInt(7, produsAlimentar.getStoc());


            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(int id, ProdusAlimentar produsAlimentar) {
        String updateCategorySQL = "UPDATE produsalimentar SET numeProdus=?, descriere=?, pret=?, furnizorId=?, categorieId=?, dataExpirare=?, stoc=? WHERE id=?";

        Connection connection = DatabaseConfiguration.getDatabaseConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(updateCategorySQL);
            preparedStatement.setString(1, produsAlimentar.getNumeProdus());
            preparedStatement.setString(2, produsAlimentar.getDescriere());
            preparedStatement.setDouble(3, produsAlimentar.getPret());
            preparedStatement.setInt(4, produsAlimentar.getFurnizor());
            preparedStatement.setInt(5, produsAlimentar.getCategorie());

            LocalDate dataExpirare = produsAlimentar.getDataExpirare();
            preparedStatement.setDate(6, java.sql.Date.valueOf(dataExpirare));

            preparedStatement.setInt(7, produsAlimentar.getStoc());
            preparedStatement.setInt(8, id);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void remove(int id) {
        String removeAlimProductSQL = "DELETE FROM produsalimentar WHERE id=?\n";

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
        String selectSql = "SELECT * FROM produsalimentar";

        Connection connection = DatabaseConfiguration.getDatabaseConnection();

        try {
            Statement stmt = connection.createStatement();
            ResultSet resultSet = stmt.executeQuery(selectSql);
            while (resultSet.next()) {
                System.out.println("\n");
                System.out.println("Id-ul produsului alimentar:" + resultSet.getInt(1));
                System.out.println("Nume:" + resultSet.getString(2));
                System.out.println("Descriere:" + resultSet.getString(3));
                System.out.println("Pret:" + resultSet.getDouble(4));
                System.out.println("Furnizor id:" + resultSet.getInt(5));
                System.out.println("Categorie id:" + resultSet.getInt(6));
                System.out.println("Data expirare:" + resultSet.getDate(7));
                System.out.println("Stoc:" + resultSet.getInt(8));

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
