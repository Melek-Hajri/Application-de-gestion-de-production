/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package projetjava.Controller;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import static projetjava.Controller.connecterDB.connecterDB;
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
       @FXML
    private TableView<?> tableview;
       @FXML
    private TextField textFieldNom; // Example text field for Chef's name

    @FXML
    private TextField textFieldPrenom;
   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    private ObservableList<Chef> fetchChefDataFromDatabase() {
    // Fetch Chef data from the database using JDBC or any other data access method
    // Example:
    ObservableList<Chef> chefList = FXCollections.observableArrayList();
    try {
        // Assuming you have a ChefDAO class to interact with the database
        // Fetch all Chef records from the database
        chefList.addAll(ChefDAO.getAllChefs()); // Replace ChefDAO.getAllChefs() with your database retrieval logic
    } catch (SQLException e) {
        // Handle SQL exception (display error message, log the exception, etc.)
        e.printStackTrace();
    }
   
    @FXML
private void handleEditChef(ActionEvent event) {
    // Retrieve the selected Chef object from the TableView
    Chef selectedChef = (Chef) tableview.getSelectionModel().getSelectedItem();
    
    // Perform null check to ensure a Chef is selected
    if (selectedChef != null) {
        // Update the selected Chef object based on the edited values
        selectedChef.setNom(textFieldNom.getText());
        selectedChef.setPrenom(textFieldPrenom.getText());
        // Update other attributes similarly
        // For example:
        selectedChef.setNumeroTelephone(textFieldNumeroTelephone.getText());
        selectedChef.setAdresseEmail(textFieldAdresseEmail.getText());
        
        
        // Save changes to the database
        updateChefDetails(selectedChef);
    }
}
private void updateChefDetails(Chef updatedChef) {
        // Your logic to update the Chef's details in the database
        // For instance, use JDBC or an ORM like Hibernate to perform an update query

        // Example using JDBC:
        try (Connection connection = connecterDB.connecterDB() ) {
            String updateQuery = "UPDATE Chef SET nom = ?, prenom = ? WHERE id = ?";
              PreparedStatement preparedStatement = connection.prepareStatement(updateQuery);
            preparedStatement.setString(1, updatedChef.getNom());
            preparedStatement.setString(2, updatedChef.getPrenom());
            preparedStatement.setString(3, updatedChef.getId());
            
            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Chef details updated successfully in the database.");
            } else {
                System.out.println("Failed to update Chef details in the database.");
            }
        } catch (SQLException e) {
            System.out.println("erreur update chef"); // Handle exception according to your application's needs
        }
    }
}
