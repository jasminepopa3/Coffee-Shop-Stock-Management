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

    //metoda pt adaugare furnizor
    public void adaugaFurnizor(Furnizor furnizor){
        furnizori.add(furnizor);
    }


    public void actualizeazaFurnizor(int id, Furnizor furnizor){
        for(int i=0;i<furnizori.size();i++){
            if(furnizori.get(i).getId() == id){
                furnizori.set(i, furnizor);
                System.out.println("Furnizorul cu id-ul " + id + " a fost actualizat cu succes.");
                return;
            }
        }
        System.out.println("Furnizorul cu id-ul " + id + " nu a fost găsită.");
    }


    public Furnizor getFurnizorByID(int idFurnizor){
        for(int i = 0; i < furnizori.size(); i++){
            if(furnizori.get(i).getId() == idFurnizor){
                return furnizori.get(i);
            }
        }
        System.out.println("Furnizorul nu a fost gasit.");
        return null;
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


}
