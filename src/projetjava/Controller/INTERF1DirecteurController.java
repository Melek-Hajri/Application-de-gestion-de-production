/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package projetjava.Controller;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import projetjava.Model.Gestion.Chef;

/**
 * FXML Controller class
 *
 * @author admin
 */
public class INTERF1DirecteurController implements Initializable {
    @FXML
    private TableColumn<Chef, Integer> id;
    @FXML
    private TableColumn<Chef, String> mdp;
    @FXML
    private TableColumn<Chef, Integer> age;
    @FXML
    private TableColumn<Chef, Integer> nbreHeures;
    @FXML
    private TableColumn<Chef, Double> prixParHeure;
    @FXML
    private TableColumn<Chef, String> adresseEmail;
    @FXML
    private TableColumn<Chef, String> nom;
    @FXML
    private TableColumn<Chef, String> numeroTelephone;
    @FXML
    private TableColumn<Chef, String> prenom;
    @FXML
    private TableView<Chef> tableview;
    @FXML
    private TableColumn<Chef,Chef> Action;
    @FXML
    private TableColumn<Chef,?>Modifier;
    @FXML
    private TableColumn<Chef,?> Supprimer;
    @FXML
    private TableColumn<Chef,Double>salaire;
    
    
    ObservableList<Chef> fetchChefDataFromDatabase() {
        ObservableList<Chef> chefList = FXCollections.observableArrayList();
        try {
            chefList.addAll(ChefDAO.getAllChefs());
            System.out.println(chefList);
            System.out.println("Nombre de chefs récupérés : " + chefList.size());
        } catch (SQLException e) {
            System.out.println("Erreur lors de la récupération des chefs depuis la base de données : " + e.getMessage());
        }
        return chefList;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initTableView();
        addActionColumn();
    }
    
    private void initTableView() {
        ObservableList<Chef> chefList = fetchChefDataFromDatabase();
        tableview.setItems(chefList);
        tableview.refresh();
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        prenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        adresseEmail.setCellValueFactory(new PropertyValueFactory<>("adresseEmail"));
        numeroTelephone.setCellValueFactory(new PropertyValueFactory<>("numeroTelephone"));
        age.setCellValueFactory(new PropertyValueFactory<>("age"));
        nbreHeures.setCellValueFactory(new PropertyValueFactory<>("nbreHeures"));
        prixParHeure.setCellValueFactory(new PropertyValueFactory<>("prixParHeure"));
        mdp.setCellValueFactory(new PropertyValueFactory<>("mdp"));
        salaire.setCellValueFactory(new PropertyValueFactory<>("salaire"));
    } 
    
    private void addActionColumn() {
        Action.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue()));
        Action.setCellFactory(param -> new TableCell<>() {
            private final Button updateButton = new Button("Update");
            private final Button deleteButton = new Button("Delete");

            {
                updateButton.setOnAction(event -> {
                    Chef chef = getTableView().getItems().get(getIndex());
                    // Handle update action here
                    // Implement the logic to update the row corresponding to 'chef'
                    // Here is a simple example assuming you have a method navigateToUpdateScene
                navigateToUpdateScene(chef);
                    System.out.println("Update chef: " + chef);
                });

                deleteButton.setOnAction(event -> {
                    Chef chef = getTableView().getItems().get(getIndex());
                    // Handle delete action here
                    // Implement the logic to delete the row corresponding to 'chef'
                     // dataSource.deleteChef(chef);
                    System.out.println("Delete chef: " + chef);
                });
            }

            @Override
            protected void updateItem(Chef item, boolean empty) {
                super.updateItem(item, empty);
                if (item == null || empty) {
                    setGraphic(null);
                } else {
                    setGraphic(new HBox(updateButton, deleteButton));
                }
            }
        });
    }
    private void navigateToUpdateScene(Chef chef) {
    try {
        // Load the FXML file for the update scene
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/projetjava/views/UpdateScene.fxml"));
        Parent root = loader.load();

        // Get the controller associated with the FXML file
        UpdateSceneController updateController = loader.getController();

        // Pass the Chef data to the UpdateController
        updateController.setChef(chef);
        updateController.setUpdateCallback(this::handleChefUpdate);
        // Create a new scene and set it on the stage
        Scene updateScene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(updateScene);
        stage.setTitle("Update Chef");
        stage.show();
    } catch (IOException e) {
        // Handle the exception appropriately
        System.out.println("erreur en navigating to the new scene");     
         
    }
}
    private void handleChefUpdate(Chef updatedChef) {
    // Implement the logic to update the row in your data structure
    // Refresh the table data
    refreshTableData();
}
 private void refreshTableData() {
        ObservableList<Chef> chefList = fetchChefDataFromDatabase();
        tableview.setItems(chefList);
        tableview.refresh();
    }

private boolean showDeleteConfirmationDialog(Chef chef) {
    // Implement the logic to show a confirmation dialog before deleting
    // For example, you can use JavaFX Alert
    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
    alert.setTitle("Delete Confirmation");
    alert.setHeaderText("Delete Chef");
    alert.setContentText("Are you sure you want to delete chef: " + chef.getNom() + "?");

    Optional<ButtonType> result = alert.showAndWait();
    return result.orElse(ButtonType.CANCEL) == ButtonType.OK;
}

    

}


