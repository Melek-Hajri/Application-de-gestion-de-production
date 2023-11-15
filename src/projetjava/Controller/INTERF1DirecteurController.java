/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package projetjava.Controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import projetjava.Model.Gestion.Chef;

/**
 * FXML Controller class
 *
 * @author admin
 */
public class INTERF1DirecteurController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private TableColumn<Chef, ?> Action;

    @FXML
    private TableColumn<Chef, ?> Salaire;

    @FXML
    private TableColumn<Chef, ?> adresseEmail;

    @FXML
    private TableColumn<Chef, ?> nom;

    @FXML
    private TableColumn<Chef, ?> numeroTel;

    @FXML
    private TableColumn<Chef, ?> prenom;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
