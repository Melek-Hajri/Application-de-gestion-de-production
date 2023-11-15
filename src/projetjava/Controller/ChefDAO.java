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
                chefList.add(chef);
                System.out.println(chefList.size());
            }
        } catch (SQLException e) {
            System.out.println("Erreur lors de la récupération des chefs depuis la base de données: " + e.getMessage());
            throw e; // Re-lance l'exception pour signaler l'erreur
        }

        return chefList;
    }
    // Ajoutez d'autres méthodes pour les opérations CRUD (Create, Read, Update, Delete) si nécessaire
    }

