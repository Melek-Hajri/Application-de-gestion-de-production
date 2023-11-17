/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package projetjava.Controller;

/**
 *
 * @author admin
 */
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import projetjava.Model.Gestion.NbreHeuresNegatifException;
import projetjava.Model.Gestion.Operateur;

public class OperateurDAO {

    public static List<Operateur> getAllOperateurs() throws SQLException, NbreHeuresNegatifException {
        List<Operateur> operateurList = new ArrayList<>();

        try (Connection connection = connecterDB.connecterDB();
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT id,nom,prenom,adresseEmail,numeroTelephone,age,nbreHeures,prixParHeure FROM operateur");
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
                


                // Create Operateur object with parameters in the correct order
                Operateur operateur = new Operateur(nom, prenom, id, adresseEmail, numeroTelephone, age, nbreHeures, prixParHeure);

                // Calculate and set the salary
                double calculatedSalaire = operateur.calculSalaire();
                operateur.setSalaire(calculatedSalaire);
                operateurList.add(operateur);
            }
        } catch (SQLException e) {
            System.out.println("Erreur lors de la récupération des opérateurs depuis la base de données: " + e.getMessage());
            throw e; // Re-lance l'exception pour signaler l'erreur
        }

        return operateurList;
    }

    // Similar to deleteChefById, you can create a deleteOperateurById method here

    // Ajoutez d'autres méthodes pour les opérations CRUD (Create, Read, Update, Delete) si nécessaire
    public static void saveOperateur(Operateur operateur) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = connecterDB.connecterDB();
            preparedStatement = connection.prepareStatement("INSERT INTO Operateur VALUES(?,?,?,?,?,?,?,?,?);");

            preparedStatement.setString(1, operateur.getId());
            preparedStatement.setString(2, operateur.getNom());
            preparedStatement.setString(3, operateur.getPrenom());
            preparedStatement.setString(4, operateur.getAdresseEmail());
            preparedStatement.setString(5, operateur.getNumeroTelephone());
            preparedStatement.setDouble(6, operateur.getAge());
            preparedStatement.setDouble(7, operateur.getNbreHeures());
            preparedStatement.setDouble(8, operateur.getPrixParHeure());
            preparedStatement.setDouble(9, operateur.getSalaire());

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
    //****************delete operator by id********************
    public static void deleteOperateurById(String operateurId) throws SQLException {
        String deleteQuery = "DELETE FROM Operateur WHERE id = ?";

        try (Connection connection = connecterDB.connecterDB();
             PreparedStatement preparedStatement = connection.prepareStatement(deleteQuery)) {

            preparedStatement.setString(1, operateurId);

            // Execute the delete query
            int rowsDeleted = preparedStatement.executeUpdate();

            if (rowsDeleted > 0) {
                System.out.println("Operateur with ID " + operateurId + " deleted successfully.");
            } else {
                System.out.println("No operateur found with ID " + operateurId + ". Deletion failed.");
            }
        } catch (SQLException e) {
            System.out.println("Error deleting operateur: " + e.getMessage());
            throw e; // Re-throw the exception to signal the error
        }
    }
}

