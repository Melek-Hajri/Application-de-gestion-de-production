/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package projetjava.Controller;


import java.net.URL;
import java.sql.Connection;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import projetjava.Model.Gestion.Chef;
import java.sql.SQLException;
import java.util.function.Consumer;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;

/**
 * FXML Controller class
 *
 * @author admin
 */
public class UpdateChefController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private TextField adresseEmail;

    @FXML
    private TextField age;

    @FXML
    private TextField id;

    @FXML
    private TextField mdp;

    @FXML
    private TextField nbreHeures;

    @FXML
    private TextField nom;

    @FXML
    private TextField numeroTelephone;

    @FXML
    private TextField prenom;

    @FXML
    private TextField prixParHeure;
    
    private Chef chef;
    
    public Consumer<Chef> updateCallback; // Callback to notify the main controller of updates

 
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    public void setChef(Chef chef) {
        this.chef = chef;

        // Populate the fields with Chef data
        nom.setText(chef.getNom());
        prenom.setText(chef.getPrenom());
        age.setText(String.valueOf(chef.getAge()));
        adresseEmail.setText(chef.getAdresseEmail());
        id.setText(String.valueOf(chef.getId()));
        mdp.setText(chef.getMdp());
        nbreHeures.setText(String.valueOf(chef.getNbreHeures()));
        numeroTelephone.setText(chef.getNumeroTelephone());
        prixParHeure.setText(String.valueOf(chef.getPrixParHeure()));
    }
    
    private boolean isInputValid() {

        String errorMessage = "";
        if (id.getText() == null || id.getText().length() == 0) {
            errorMessage += "Id non valide\n"; } else {
            try { Integer.parseInt(id.getText()); }
            catch (NumberFormatException e) { errorMessage += "Id doit etre un entier\n"; } 
        }
        if (nom.getText() == null || nom.getText().length() == 0) {
            errorMessage += "Nom non valide\n"; }
        if (prenom.getText() == null || prenom.getText().length() == 0) {
            errorMessage += "Prenom non valide\n"; }
        if (adresseEmail.getText() == null || adresseEmail.getText().length() == 0) {
            errorMessage += "Adresse email non valide\n"; } 
        if (numeroTelephone.getText() == null || numeroTelephone.getText().length() == 0) {
            errorMessage += "Numero Telephone non valide\n"; } else {
            try { Integer.parseInt(numeroTelephone.getText()); }
            catch (NumberFormatException e) { errorMessage += "Numero Telephone doit etre un entier\n"; } 
        }
        if (age.getText() == null || age.getText().length() == 0) {
            errorMessage += "Age non valide\n"; } else {
            try { Double.parseDouble(age.getText()); }
            catch (NumberFormatException e) { errorMessage += "Age doit etre un reel\n"; } 
        }
        if (nbreHeures.getText() == null || nbreHeures.getText().length() == 0) {
            errorMessage += "Nombre heures non valide\n"; } else {
            try { Integer.parseInt(nbreHeures.getText()); }
            catch (NumberFormatException e) { errorMessage += "Nombre heures doit etre un entier\n"; } 
        }
        if (prixParHeure.getText() == null || prixParHeure.getText().length() == 0) {
            errorMessage += "Prix Par Heure non valide\n"; } else {
            try { Double.parseDouble(prixParHeure.getText()); }
            catch (NumberFormatException e) { errorMessage += "Prix Par Heure doit etre un reel\n"; } 
        }
        if (mdp.getText() == null || mdp.getText().length() == 0) {
            errorMessage += "Mot de passe non valide\n"; } 
        if (errorMessage.length() == 0) { return true; } else {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("champs invalides");
        alert.setHeaderText("svp corrigez les champs invalides");
        alert.setContentText(errorMessage);
        alert.showAndWait();
        return false; } }
    @FXML
    public void handleUpdateButton() {
        // Get the updated values from the TextFields
        if(isInputValid()){
            String updatedNom = nom.getText();
            String updatedPrenom = prenom.getText();
            double updatedAge = Double.parseDouble(age.getText());
            double updatedNbreHeures = Double.parseDouble(nbreHeures.getText());
            String updatedAdresseEmail = adresseEmail.getText();
            String updatedId = id.getText();
            String updatedMdp = mdp.getText();
            String updatedNumeroTelephone = numeroTelephone.getText();
            double updatedPrixParHeure = Double.parseDouble(prixParHeure.getText());
            try {
                // Assuming you have a Connection object named "connection"
                // You should replace this with your actual way of obtaining a database connection
                // Connection connection = ...;
                // Assuming your table name is "Chefs" and you have fields like "Nom", "Prenom", etc.
            String updateQuery = "UPDATE Chef SET Nom=?, Prenom=?, AdresseEmail=?, NumeroTelephone=?, Age=?, NbreHeures=?, PrixParHeure=?, Mdp=? WHERE ID=?";
                try (Connection connection = connecterDB.connecterDB();
                     java.sql.PreparedStatement preparedStatement = connection.prepareStatement(updateQuery)) {
                    preparedStatement.setString(1, updatedNom);
                    preparedStatement.setString(2, updatedPrenom);
                    preparedStatement.setString(3, updatedAdresseEmail);
                    preparedStatement.setString(4, updatedNumeroTelephone);
                    preparedStatement.setDouble(5, updatedAge);
                    preparedStatement.setDouble(6, updatedNbreHeures);
                    preparedStatement.setDouble(7, updatedPrixParHeure);
                    preparedStatement.setString(8, updatedMdp);
                    preparedStatement.setString(9, chef.getId());// Assuming ID is the primary key and is a String
                                // Execute the update query
                    System.out.println("SQL Query: " + preparedStatement.toString());
                    System.out.println("execution de update:");
                    int rowsUpdated = preparedStatement.executeUpdate();

                    if (rowsUpdated > 0) {
                        System.out.println("Chef updated successfully.");
                    } else {
                        System.out.println("Failed to update Chef.");
                    }
                     // Notify the main controller of the update
                if (updateCallback != null) {
                    updateCallback.accept(chef);
                }

                }
            } catch (SQLException e) {
                System.out.println("erreur handleUpdateButton");    }

            // Close the update scene
            nom.getScene().getWindow().hide();
        }

    }
    public void setUpdateCallback(Consumer<Chef> updateCallback) {
        this.updateCallback = updateCallback;
    }
     
    
    
}
