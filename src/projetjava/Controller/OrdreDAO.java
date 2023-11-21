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
import projetjava.Model.Gestion.Date;
import projetjava.Model.Production.Exceptions.DateDebFinException;
import projetjava.Model.Production.Exceptions.InvalidPrixException;
import projetjava.Model.Production.LigneDeProduction;
import projetjava.Model.Production.OrdreDeProduction;
import projetjava.Model.Production.Produit;

public class OrdreDAO {
    private static final String INSERT_ORDRE = "INSERT INTO Produit1 (numero, nom, description, prix, quantite, jourd, moisd, anneed, jourf, moisf, anneef, plan_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, 11)";
    private static final String UPDATE_ORDRE = "UPDATE Produit1 SET nom = ?, description = ?, prix = ?, quantite = ?, jourd = ?, moisd = ?, anneed = ?, jourf = ?, moisf = ?, anneef = ? WHERE numero = ?";
    private static final String DELETE_ORDRE = "DELETE FROM Produit1 WHERE numero = ?";
    private static final String SELECT_ALL_ORDRES = "SELECT * FROM Produit1";
    private static final String SELECT_LIGNE = "SELECT * FROM Lignedeproduction WHERE id = ?";
    private static final String SET_LIGNE = "UPDATE Produit1 SET ligne_id = ? WHERE numero = ?";
    private static final String SET_LIGNE_DISPO = "UPDATE Lignedeproduction SET disponible = ? WHERE id = ?";
    

    public static void createOrdreDeProduction(OrdreDeProduction ordre) throws SQLException {
        try (Connection connection = connecterDB.connecterDB();
             PreparedStatement statement = connection.prepareStatement(INSERT_ORDRE)) {
            statement.setInt(1, ordre.getNumero());
            statement.setString(2, ordre.getProduit().getNom());
            statement.setString(3, ordre.getProduit().getDescription());
            statement.setDouble(4, ordre.getProduit().getPrix());
            statement.setInt(5, ordre.getQuantite());
            statement.setInt(6, ordre.getDateDeb().getJour());
            statement.setInt(7, ordre.getDateDeb().getMois());
            statement.setInt(8, ordre.getDateDeb().getAnnee());
            statement.setInt(9, ordre.getDateFin().getJour());
            statement.setInt(10, ordre.getDateFin().getMois());
            statement.setInt(11, ordre.getDateFin().getAnnee());
            statement.executeUpdate();
        } 
    }

    public static int updateOrdreDeProduction(OrdreDeProduction ordre) throws SQLException {
        try (Connection connection = connecterDB.connecterDB();
             PreparedStatement statement = connection.prepareStatement(UPDATE_ORDRE)) {
            statement.setString(1, ordre.getProduit().getNom());
            statement.setString(2, ordre.getProduit().getDescription());
            statement.setDouble(3, ordre.getProduit().getPrix());
            statement.setInt(4, ordre.getQuantite());
            statement.setInt(5, ordre.getDateDeb().getJour());
            statement.setInt(6, ordre.getDateDeb().getMois());
            statement.setInt(7, ordre.getDateDeb().getAnnee());
            statement.setInt(8, ordre.getDateFin().getJour());
            statement.setInt(9, ordre.getDateFin().getMois());
            statement.setInt(10, ordre.getDateFin().getAnnee());
            statement.setInt(11, ordre.getNumero());
            
            return statement.executeUpdate();
        }
    }

    public static void deleteOrdreDeProduction(int id) throws SQLException {
        try (Connection connection = connecterDB.connecterDB();
            PreparedStatement statement = connection.prepareStatement(DELETE_ORDRE)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        }
    }

    public static List<OrdreDeProduction> getAllOrdres() throws SQLException, InvalidPrixException, DateDebFinException {
        List<OrdreDeProduction> ordres = new ArrayList<>();
        try (Connection connection = connecterDB.connecterDB();
             PreparedStatement statement = connection.prepareStatement(SELECT_ALL_ORDRES)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int numero = resultSet.getInt("numero");
                String nom = resultSet.getString("nom");
                String description = resultSet.getString("description");
                double prix = resultSet.getInt("prix");
                int quantite = resultSet.getInt("quantite");
                int jourd = resultSet.getInt("jourd");
                int moisd = resultSet.getInt("moisd");
                int anneed = resultSet.getInt("anneed");
                int jourf = resultSet.getInt("jourf");
                int moisf = resultSet.getInt("moisf");
                int anneef = resultSet.getInt("anneef");
                Integer ligne_id = resultSet.getInt("ligne_id");
                
                LigneDeProduction ligne = null;
                if (ligne_id != null){
                    ligne = getLigneByOrdreId(ligne_id);
                }
                
                Date dateDeb = new Date(jourd, moisd, anneed);
                Date dateFin = new Date(jourf, moisf, anneef);
                
                Produit produit = new Produit(nom, description, prix);
                // Assuming you have a constructor for LigneDeProduction
                OrdreDeProduction ordre = new OrdreDeProduction(numero, produit, quantite, dateDeb, dateFin);
                ordres.add(ordre);
            }
        }
        return ordres;
    }

    public static LigneDeProduction getLigneByOrdreId(int ordreId) throws SQLException {
        LigneDeProduction Ligne = null;
        try (Connection connection = connecterDB.connecterDB();
             PreparedStatement statement = connection.prepareStatement(SELECT_LIGNE)) {
            statement.setInt(1, ordreId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                Ligne = new LigneDeProduction(resultSet.getInt("id"), resultSet.getString("nom"),
                        resultSet.getBoolean("disponible"));
                
            }
        }
        return Ligne;
    }
    
    public static int setLigneToOrdre(int ligneId, int ordreId) throws SQLException{
        try (Connection connection = connecterDB.connecterDB();
             PreparedStatement statement = connection.prepareStatement(SET_LIGNE)) {
            statement.setInt(1, ligneId);
            statement.setInt(2, ordreId);
            int dispo = setLigneDispo(ligneId, false);
            return statement.executeUpdate();
        }
    }
    public static int setLigneDispo(int ligneId, boolean dispo) throws SQLException{
        try (Connection connection = connecterDB.connecterDB();
             PreparedStatement statement = connection.prepareStatement(SET_LIGNE_DISPO)) {
            statement.setInt(1, ligneId);
            statement.setBoolean(2, dispo);
            
            return statement.executeUpdate();
        }
    }
}
