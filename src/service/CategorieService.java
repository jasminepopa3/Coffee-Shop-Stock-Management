package service;

import config.DatabaseConfiguration;
import interfaces.CrudService;
import model.Categorie;
import model.Furnizor;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CategorieService implements CrudService<Categorie> {
    private List<Categorie> categorii;

    //când cream o nouă instanță a clasei CategorieService,
    // o listă nouă de categorii va fi creată și asociată instanței respective.
    // Această listă va fi utilizată pentru a adăuga, actualiza și șterge categorii în
    // cadrul serviciului CategorieService
    // Constructor pentru inițializare cu o listă goală de categorii
    public CategorieService() {
        this.categorii = new ArrayList<>();
    }

    // Constructor pentru inițializare cu o listă de categorii preexistente
    public CategorieService(List<Categorie> categorii) {
        this.categorii = categorii;
    }



    @Override
    public void display() {
        String selectSql = "SELECT * FROM categorie";

        Connection connection = DatabaseConfiguration.getDatabaseConnection();

        try {
            Statement stmt = connection.createStatement();
            ResultSet resultSet = stmt.executeQuery(selectSql);
            while (resultSet.next()) {
                System.out.println("\n");
                System.out.println("Id-ul categoriei:" + resultSet.getString(1));
                System.out.println("Denumire:" + resultSet.getString(2));
                System.out.println("Descriere:" + resultSet.getString(3));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void add(Categorie categorie) {
        String insertCategorySql = "INSERT INTO categorie(numeCategorie, descriere) VALUES(?, ?)";

        Connection connection = DatabaseConfiguration.getDatabaseConnection();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(insertCategorySql);
            preparedStatement.setString(1, categorie.getNumeCategorie());
            preparedStatement.setString(2, categorie.getDescriere());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(int id, Categorie categorie) {
        String updateCategorySQL = "UPDATE categorie SET numeCategorie=?, descriere=? WHERE idCategorie=?";

        Connection connection = DatabaseConfiguration.getDatabaseConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(updateCategorySQL);
            preparedStatement.setString(1, categorie.getNumeCategorie());
            preparedStatement.setString(2, categorie.getDescriere());
            preparedStatement.setInt(3, id);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void remove(int id) {
        String removeCategorySQL = "DELETE FROM categorie WHERE idCategorie=?\n";

        Connection connection = DatabaseConfiguration.getDatabaseConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(removeCategorySQL);
            preparedStatement.setInt(1, id);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    


    //de revenit asupra ei
    public Categorie getCategorieByID(int idCategorie) {

            String query = "SELECT * FROM categorie WHERE idCategorie = ?";

            Connection connection = DatabaseConfiguration.getDatabaseConnection();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, idCategorie);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                String nume = resultSet.getString("numeCategorie");
                String descriere = resultSet.getString("descriere");
                int id = resultSet.getInt("idCategorie");

                return new Categorie(nume, descriere);
            } else {
                System.out.println("Categoria cu id-ul " + idCategorie + " nu a fost găsită.");
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

}
