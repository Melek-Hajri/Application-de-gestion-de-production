/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package projetjava.Controller;

import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.function.Consumer;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import projetjava.Model.Gestion.Chef;

/**
 * FXML Controller class
 *
 * @author hajri
 */
public class AjoutChefController implements Initializable {

    /**
     * Initializes the controller class.
     */
    private int dirID;
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
    private INTERF1DirecteurController interf1DirecteurController;

    // Setter method for INTERF1DirecteurController
    public void setInterfController(INTERF1DirecteurController interf1DirecteurController) {
        this.interf1DirecteurController = interf1DirecteurController;
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }   
    public void setDirID(int DirID){
        this.dirID = DirID;
    }
    @FXML
    public void handleAddButton() {
        // Get the updated values from the TextFields
        String Nom = nom.getText();
        String Prenom = prenom.getText();
        double Age = Double.parseDouble(age.getText());
        int NbreHeures = Integer.parseInt(nbreHeures.getText());
        String AdresseEmail = adresseEmail.getText();
        String Id = id.getText();
        String Mdp = mdp.getText();
        String NumeroTelephone = numeroTelephone.getText();
        double PrixParHeure = Double.parseDouble(prixParHeure.getText());
        Chef chef = new Chef(Nom,Prenom,Id,AdresseEmail,NumeroTelephone,Age,NbreHeures,PrixParHeure,Mdp);
        chef.calculSalaire();
        try {
            // Assuming you have a Connection object named "connection"
            // You should replace this with your actual way of obtaining a database connection
            // Connection connection = ...;
            // Assuming your table name is "Chefs" and you have fields like "Nom", "Prenom", etc.
        ChefDAO.saveChef(chef, dirID);
        interf1DirecteurController.refreshTableData();
        } catch (SQLException e) {
            System.out.println("erreur handleUpdateButton");
            e.printStackTrace();
        }

        // Close the update scene
        nom.getScene().getWindow().hide();

    }
}
