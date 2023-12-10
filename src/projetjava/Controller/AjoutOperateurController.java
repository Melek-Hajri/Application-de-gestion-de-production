/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package projetjava.Controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import projetjava.Model.Gestion.NbreHeuresNegatifException;
import projetjava.Model.Gestion.Operateur;
import java.sql.SQLException;
import javafx.scene.control.Alert;

/**
 * FXML Controller class
 *
 * @author hajri
 */
public class AjoutOperateurController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
      private int dirID;
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
    private Interf2DirecteurGopController Interf2DirecteurGopController;

    // Setter method for INTERF1DirecteurController
    public void setInterfController(Interf2DirecteurGopController Interf2DirecteurGopController) {
        this.Interf2DirecteurGopController = Interf2DirecteurGopController;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
     public void setDirID(int DirID) {
        this.dirID = DirID;
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
    public void handleAddButton() throws NbreHeuresNegatifException {
    // Get the updated values from the TextFields
        if (isInputValid()){
            String Nom = nom.getText();
            String Prenom = prenom.getText();
            int NbreHeures = Integer.parseInt(nbreHeures.getText());
            String AdresseEmail = adresseEmail.getText();
            String Id = id.getText();
            String NumeroTelephone = numeroTelephone.getText();
            double PrixParHeure = Double.parseDouble(prixParHeure.getText());
            String ageText = age.getText();
            if (!ageText.isEmpty()) {
                System.out.println("saisi age");
                double Age = Double.parseDouble(ageText);
                 Operateur operateur = new Operateur(Nom, Prenom, Id, AdresseEmail, NumeroTelephone, Age, NbreHeures, PrixParHeure);
                operateur.calculSalaire();
                        try {
                    // Save the Operateur to the database
                    OperateurDAO.saveOperateur(operateur, dirID);
                    Interf2DirecteurGopController.refreshTableData();
                } catch (SQLException e) {
                    System.out.println("Error in handleAddButton");
                    e.printStackTrace();
                }

                // Close the scene
                nom.getScene().getWindow().hide();
            } else {
                // Handle the case where the 'age' text field is empty
                System.out.println("Please enter a valid age.");
            }
        }
    }
}
