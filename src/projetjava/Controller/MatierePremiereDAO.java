/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package projetjava.Controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import projetjava.Model.Production.Matiere_premiere;

public class MatierePremiereDAO {
    private static final String INSERT_MATIERE_PREMIERE = "INSERT INTO Matiere_premiere (nom, prix_unitaire) VALUES (?, ?)";
    private static final String SELECT_ALL_MATIERE_PREMIERE = "SELECT * FROM Matiere_premiere";
    // Add other CRUD queries as needed

    public void insertMatierePremiere(Matiere_premiere matierePremiere) throws SQLException {
        try (Connection connection = connecterDB.connecterDB();
             PreparedStatement statement = connection.prepareStatement(INSERT_MATIERE_PREMIERE)) {
            statement.setString(1, matierePremiere.nom());
            statement.setDouble(2, matierePremiere.prix_unitaire());
            statement.executeUpdate();
        }
    }

    public List<Matiere_premiere> getAllMatierePremiere() throws SQLException {
        List<Matiere_premiere> matierePremiereList = new ArrayList<>();
        try (Connection connection = connecterDB.connecterDB();
             PreparedStatement statement = connection.prepareStatement(SELECT_ALL_MATIERE_PREMIERE);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                // Create Matiere_premiere objects and add to the list
                Matiere_premiere matierePremiere = new Matiere_premiere(
                        resultSet.getString("nom"),
                        resultSet.getDouble("prix_unitaire")
                );
                matierePremiereList.add(matierePremiere);
            }
        }
        return matierePremiereList;
    }

    // Implement update, delete, and other CRUD operations similarly
}

