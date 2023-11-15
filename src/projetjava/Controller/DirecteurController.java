/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package projetjava.Controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author hajri
 */
public class DirecteurController  {

    /**
     * Initializes the controller class.
     */
    
      @FXML
    private void handleButtonClick(ActionEvent event) {
        
        try {
            
            // Charger le fichier FXML de l'interface interf1directeur
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/projetjava/Views/INTERF1Directeur.fxml"));
            Parent root = loader.load();

            // Créer une nouvelle scène
            Scene scene = new Scene(root);

            // Obtenir la fenêtre principale
            Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            // Définir la nouvelle scène sur la fenêtre principale
            primaryStage.setScene(scene);
            System.out.println("erreur  directeur ");

        } catch (IOException e) {
            System.out.println("erreur en affichage interface directeur ");
        }
    }
    
}
