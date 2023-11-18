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

   @FXML
    public void handleAddButton() throws NbreHeuresNegatifException {
    // Get the updated values from the TextFields
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
