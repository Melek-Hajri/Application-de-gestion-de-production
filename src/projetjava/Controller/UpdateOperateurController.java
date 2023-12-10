/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package projetjava.Controller;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.function.Consumer;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;

/**
 * FXML Controller class
 *
 * @author hajri
 */

import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import projetjava.Model.Gestion.Operateur;

public class UpdateOperateurController {

    @FXML
    private Button Sauvegarder;

    @FXML
    private TextField adresseEmail;

    @FXML
    private TextField age;

    @FXML
    private TextField id;

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
           
   public Consumer<Operateur> updateCallback; // Callback to notify the main controller of updates
    private Operateur operateur;

    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    public void setOperateur(Operateur operateur) {
        this.operateur = operateur;

        // Populate the fields with Operateur data
        nom.setText(operateur.getNom());
        prenom.setText(operateur.getPrenom());
        age.setText(String.valueOf(operateur.getAge()));
        adresseEmail.setText(operateur.getAdresseEmail());
        id.setText(String.valueOf(operateur.getId()));
        nbreHeures.setText(String.valueOf(operateur.getNbreHeures()));
        numeroTelephone.setText(operateur.getNumeroTelephone());
        prixParHeure.setText(String.valueOf(operateur.getPrixParHeure()));
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
            String updatedNumeroTelephone = numeroTelephone.getText();
            double updatedPrixParHeure = Double.parseDouble(prixParHeure.getText());
                System.out.println("start bd");
            try (java.sql.Connection connection = connecterDB.connecterDB();
                 java.sql.PreparedStatement preparedStatement = connection.prepareStatement(
                            "UPDATE Operateur SET Nom=?, Prenom=?, AdresseEmail=?, NumeroTelephone=?, Age=?, NbreHeures=?, PrixParHeure=? WHERE ID=?")) {
                preparedStatement.setString(1, updatedNom);
                preparedStatement.setString(2, updatedPrenom);
                preparedStatement.setString(3, updatedAdresseEmail);
                preparedStatement.setString(4, updatedNumeroTelephone);
                preparedStatement.setDouble(5, updatedAge);
                preparedStatement.setDouble(6, updatedNbreHeures);
                preparedStatement.setDouble(7, updatedPrixParHeure);
                preparedStatement.setString(8, operateur.getId());
                System.out.println("Execution de l'update :");

                // Execute the update query
                System.out.println("SQL Query: " + preparedStatement.toString());
                int rowsUpdated = preparedStatement.executeUpdate();
                if (rowsUpdated > 0) {
                    System.out.println("Operateur updated successfully.");
                } else {
                    System.out.println("Failed to update Operateur.");
                }

                // Notify the main controller of the update
                if (updateCallback != null) {
                    updateCallback.accept(operateur);
                }

            } catch (SQLException e) {
                System.out.println("Error in handleUpdateButton");
            }

            // Close the update scene
            nom.getScene().getWindow().hide();
        }
    }

    public void setUpdateCallback(Consumer<Operateur> updateCallback) {
        this.updateCallback = updateCallback;
    }
   
}