/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package projetjava.Controller;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import projetjava.Model.Production.LigneDeProduction;

/**
 * FXML Controller class
 *
 * @author hajri
 */
public class AjoutLigneController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private ChoiceBox disponible;

    @FXML
    private TextField id;

    @FXML
    private TextField nom;
    
    private GestionLignesController ligneController;
    

    // Setter method for INTERF1DirecteurController
    public void setLigneController(GestionLignesController ligneController) {
        this.ligneController = ligneController;
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        disponible.getItems().addAll("true", "false");
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
        if (disponible == null){
            errorMessage += "Disponibilite non valide\n";
        }
        if (errorMessage.length() == 0) { return true; } else {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("champs invalides");
        alert.setHeaderText("svp corrigez les champs invalides");
        alert.setContentText(errorMessage);
        alert.showAndWait();
        return false; } }
    @FXML
    public void handleAddButton() {
        // Get the updated values from the TextFields
        if(isInputValid()){
            int Id = Integer.parseInt(id.getText());
            String Nom = nom.getText();
            boolean Disponible = Boolean.parseBoolean((String) disponible.getValue());
            LigneDeProduction ligne = new LigneDeProduction(Id, Nom, Disponible);
            try {
                // Assuming you have a Connection object named "connection"
                // You should replace this with your actual way of obtaining a database connection
                // Connection connection = ...;
                // Assuming your table name is "Chefs" and you have fields like "Nom", "Prenom", etc.
                    LigneDAO.createLigneDeProduction(ligne);
                    ligneController.refreshTableData();
            } catch (SQLException e) {
                System.out.println("erreur handleUpdateButton");    }

            nom.getScene().getWindow().hide();
        }
    }
}
