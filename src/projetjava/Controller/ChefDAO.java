/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package projetjava.Controller;

/**
 *
 * @author hajri
 */
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import projetjava.Model.Gestion.Chef;

public class ChefDAO {

    public static List<Chef> getAllChefs() throws SQLException {
        List<Chef> chefList = new ArrayList<>();

        try (Connection connection = connecterDB.connecterDB();
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT id,nom,prenom,adresseEmail,numeroTelephone,age,nbreHeures,prixParHeure,mdp FROM Chef");
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                String id = resultSet.getString("id");
                String nom = resultSet.getString("nom");
                String prenom = resultSet.getString("prenom");
                String adresseEmail = resultSet.getString("adresseEmail");
                String numeroTelephone = resultSet.getString("numeroTelephone");
                double age = resultSet.getDouble("age");
                int nbreHeures = resultSet.getInt("nbreHeures");
                double prixParHeure = resultSet.getDouble("prixParHeure");
                String mdp = resultSet.getString("mdp");

                Chef chef = new Chef(nom, prenom, id, adresseEmail, numeroTelephone, age, nbreHeures, prixParHeure, mdp);
                // Calculate and set the salary
                double calculatedSalaire = chef.calculSalaire();
                chef.setSalaire(calculatedSalaire);
                chefList.add(chef);
            }
        } catch (SQLException e) {
            System.out.println("Erreur lors de la récupération des chefs depuis la base de données: " + e.getMessage());
            throw e; // Re-lance l'exception pour signaler l'erreur
        }

        return chefList;
    }
     //**********to connect to table chef and delete chef by id*****************
    public static void deleteChefById(String chefId) throws SQLException {
        String deleteQuery = "DELETE FROM Chef WHERE id = ?";

        try (Connection connection = connecterDB.connecterDB();
             PreparedStatement preparedStatement = connection.prepareStatement(deleteQuery)) {

            preparedStatement.setString(1, chefId);

            // Execute the delete query
            int rowsDeleted = preparedStatement.executeUpdate();

            if (rowsDeleted > 0) {
                System.out.println("Chef with ID " + chefId + " deleted successfully.");
            } else {
                System.out.println("No chef found with ID " + chefId + ". Deletion failed.");
            }
        } catch (SQLException e) {
            System.out.println("Error deleting chef: " + e.getMessage());
            throw e; // Re-throw the exception to signal the error
        }
    }
    // Ajoutez d'autres méthodes pour les opérations CRUD (Create, Read, Update, Delete) si nécessaire
    public static void saveChef(Chef chef, int dirID) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        
        try {
            connection = connecterDB.connecterDB();
            preparedStatement = connection.prepareStatement("INSERT INTO Chef VALUES(?,?,?,?,?,?,?,?,?,?,?);");
            
            preparedStatement.setString(1, chef.getId());
            preparedStatement.setString(2, chef.getNom());
            preparedStatement.setString(3, chef.getPrenom());
            preparedStatement.setString(4, chef.getAdresseEmail());
            preparedStatement.setString(5, chef.getNumeroTelephone());
            preparedStatement.setDouble(6, chef.getAge());
            preparedStatement.setDouble(7, chef.getNbreHeures());
            preparedStatement.setDouble(8, chef.getPrixParHeure());
            preparedStatement.setDouble(9, chef.getSalaire());
            preparedStatement.setString(10, chef.getMdp());
            preparedStatement.setInt(11, dirID);
            
            // Execute query
            preparedStatement.executeUpdate();
        } finally {
            // Close connections
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
        
    }  
}

