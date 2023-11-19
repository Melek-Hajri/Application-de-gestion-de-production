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
import projetjava.Model.Production.LigneDeProduction;
import projetjava.Model.Production.Machine;

public class LigneDAO {
    private static final String SELECT_MACHINES_BY_LIGNE_ID = "SELECT id, nom, type, enMaintenance FROM Machine WHERE ligne_id = ?";
    private static final String INSERT_LIGNE = "INSERT INTO LigneDeProduction (id, nom, disponible, plan_id) VALUES (?, ?, ?, 11)";
    private static final String UPDATE_LIGNE = "UPDATE LigneDeProduction SET nom = ?, disponible = ? WHERE id = ?";
    private static final String DELETE_LIGNE = "DELETE FROM LigneDeProduction WHERE id = ?";
    private static final String SELECT_ALL_LIGNES = "SELECT id, nom, disponible FROM LigneDeProduction";
    

    public static void createLigneDeProduction(LigneDeProduction ligne) throws SQLException {
        try (Connection connection = connecterDB.connecterDB();
             PreparedStatement statement = connection.prepareStatement(INSERT_LIGNE)) {
            statement.setInt(1, ligne.getId());
            statement.setString(2, ligne.getNom());
            statement.setBoolean(3, ligne.getDisponible());
            statement.executeUpdate();
        } 
    }

    public static int updateLigneDeProduction(LigneDeProduction ligne) throws SQLException {
        try (Connection connection = connecterDB.connecterDB();
             PreparedStatement statement = connection.prepareStatement(UPDATE_LIGNE)) {
            statement.setString(1, ligne.getNom());
            statement.setBoolean(2, ligne.getDisponible());
            statement.setInt(3, ligne.getId());
            return statement.executeUpdate();
        }
    }

    public static void deleteLigneDeProduction(int id) throws SQLException {
        try (Connection connection = connecterDB.connecterDB();
             PreparedStatement statement = connection.prepareStatement(DELETE_LIGNE)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        }
    }

    public static List<LigneDeProduction> getAllLignes() throws SQLException {
        List<LigneDeProduction> lignes = new ArrayList<>();
        try (Connection connection = connecterDB.connecterDB();
             PreparedStatement statement = connection.prepareStatement(SELECT_ALL_LIGNES)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String nom = resultSet.getString("nom");
                boolean disponible = resultSet.getBoolean("disponible");
                // Assuming you have a constructor for LigneDeProduction
                LigneDeProduction ligne = new LigneDeProduction(id, nom, disponible);
                lignes.add(ligne);
            }
        }
        return lignes;
    }

    public static List<Machine> getMachinesByLigneId(int ligneId) throws SQLException {
        List<Machine> machines = new ArrayList<>();
        try (Connection connection = connecterDB.connecterDB();
             PreparedStatement statement = connection.prepareStatement(SELECT_MACHINES_BY_LIGNE_ID)) {
            statement.setInt(1, ligneId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Machine machine = new Machine(resultSet.getInt("id"), resultSet.getString("nom"),
                        resultSet.getString("type"), resultSet.getBoolean("enMaintenance"));
                machines.add(machine);
            }
        }
        return machines;
    }
}
