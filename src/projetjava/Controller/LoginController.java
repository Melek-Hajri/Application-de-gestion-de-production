/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package projetjava.Controller;

import java.net.URL;
import java.sql.Connection;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import static projetjava.Controller.connecterDB.connecterDB;

/**
 * FXML Controller class
 *
 * @author hajri
 */
public class LoginController implements Initializable {

    /**
     * Initializes the controller class.
     */
   
    Connection cnx=connecterDB();
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
