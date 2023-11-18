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
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
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
import projetjava.Model.Gestion.NbreHeuresNegatifException;
import projetjava.Model.Gestion.Operateur;


/**
 * FXML Controller class
 *
 * @author admin
 */
public class Interf2DirecteurGopController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private TableColumn<Operateur, Operateur> Action;

    @FXML
    private Button ButtonBack;

    @FXML
    private TableColumn<Operateur, String> adresseEmail;

    @FXML
    private TableColumn<Operateur, Integer> age;

    @FXML
    private Button buttonAdd;

    @FXML
    private TableColumn<Operateur, Integer> id;

    

    @FXML
    private TableColumn<Operateur, Integer> nbreHeures;

    @FXML
    private TableColumn<Operateur, String> nom;

    @FXML
    private TableColumn<Operateur, String> numeroTelephone;

    @FXML
    private TableColumn<Operateur, String> prenom;

    @FXML
    private TableColumn<Operateur, String> prixParHeure;

    @FXML
    private TableColumn<Operateur, String> salaire;

    @FXML
    private TableView<Operateur> tableview;
    private int dirID;

     public void setDirID(int DirID) {
        this.dirID = DirID;
    }

    ObservableList<Operateur> fetchOperateurDataFromDatabase() throws NbreHeuresNegatifException {
        ObservableList<Operateur> operateurList = FXCollections.observableArrayList();
        try {
            operateurList.addAll(OperateurDAO.getAllOperateurs());
            System.out.println(operateurList);
            System.out.println("Nombre d'opérateurs récupérés : " + operateurList.size());
        } catch (SQLException e) {
            System.out.println("Erreur lors de la récupération des opérateurs depuis la base de données : " + e.getMessage());
        }
        return operateurList;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            initTableView();
        } catch (NbreHeuresNegatifException ex) {
            Logger.getLogger(Interf2DirecteurGopController.class.getName()).log(Level.SEVERE, null, ex);
        }
        addActionColumn();
    }

    private void initTableView() throws NbreHeuresNegatifException {
        ObservableList<Operateur> operateurList = fetchOperateurDataFromDatabase();
        tableview.setItems(operateurList);
        tableview.refresh();
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        prenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        adresseEmail.setCellValueFactory(new PropertyValueFactory<>("adresseEmail"));
        numeroTelephone.setCellValueFactory(new PropertyValueFactory<>("numeroTelephone"));
        age.setCellValueFactory(new PropertyValueFactory<>("age"));
        nbreHeures.setCellValueFactory(new PropertyValueFactory<>("nbreHeures"));
        prixParHeure.setCellValueFactory(new PropertyValueFactory<>("prixParHeure"));
        salaire.setCellValueFactory(new PropertyValueFactory<>("salaire"));
    }

    private void addActionColumn() {
        Action.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue()));
        Action.setCellFactory(param -> new TableCell<>() {
            private final Button updateButton = new Button("Update");
            private final Button deleteButton = new Button("Delete");

            {
                updateButton.setOnAction(event -> {
                    Operateur operateur = getTableView().getItems().get(getIndex());
                    // Handle update action here
                    // Implement the logic to update the row corresponding to 'operateur'
                    // Here is a simple example assuming you have a method navigateToUpdateScene
                  //thennnnnnnn  navigateToUpdateScene(operateur);
                    System.out.println("Update operateur: " + operateur);
                });

                deleteButton.setOnAction(event -> {
                    Operateur operateur = getTableView().getItems().get(getIndex());

                    // Show a confirmation dialog before deleting
                    boolean confirmed = showDeleteConfirmationDialog(operateur);

                    if (confirmed) {
                        try {
                            // Assuming OperateurDAO has a method to delete the operateur by ID
                            OperateurDAO.deleteOperateurById(operateur.getId());

                            // Optional: Show a success message
                            System.out.println("Operateur deleted successfully.");

                            // Refresh the table data after deletion
                           refreshTableData();
                        } catch (SQLException e) {
                            // Handle the exception appropriately
                            System.out.println("Error deleting operateur: " + e.getMessage());
                        } catch (NbreHeuresNegatifException ex) {
                            Logger.getLogger(Interf2DirecteurGopController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                });
            }

            @Override
            protected void updateItem(Operateur item, boolean empty) {
                super.updateItem(item, empty);
                if (item == null || empty) {
                    setGraphic(null);
                } else {
                    setGraphic(new HBox(updateButton, deleteButton));
                }
            }
        });
    }
/*
    private void navigateToUpdateScene(Operateur operateur) {
        try {
            // Load the FXML file for the update scene
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/projetjava/views/UpdateOperateur.fxml"));
            Parent root = loader.load();

            // Get the controller associated with the FXML file
            UpdateOperateurController updateController = loader.getController();

            // Pass the Operateur data to the UpdateController
            updateController.setOperateur(operateur);
            updateController.setUpdateCallback(this::handleOperateurUpdate);
            // Create a new scene and set it on the stage
            Scene updateScene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(updateScene);
            stage.setTitle("Update Operateur");
            stage.show();
        } catch (IOException e) {
            // Handle the exception appropriately
            System.out.println("Erreur en navigating to the new scene");
        }
    }
*/
    private void handleOperateurUpdate(Operateur updatedOperateur) throws NbreHeuresNegatifException {
        // Implement the logic to update the row in your data structure
        // Refresh the table data
        refreshTableData();
    }

    public void refreshTableData() throws NbreHeuresNegatifException {
        ObservableList<Operateur> operateurList = fetchOperateurDataFromDatabase();
        tableview.setItems(operateurList);
        tableview.refresh();
    }

    private boolean showDeleteConfirmationDialog(Operateur operateur) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Delete Confirmation");
        alert.setHeaderText("Delete Operateur");
        alert.setContentText("Are you sure you want to delete operateur: " + operateur.getNom() + "?");

        Optional<ButtonType> result = alert.showAndWait();
        return result.orElse(ButtonType.CANCEL) == ButtonType.OK;
    }

    /*@FXML
    private void navigateToAjouterOperateurScene() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/projetjava/views/AjoutOperateur.fxml"));
            Parent root = loader.load();
            AjoutOperateurController directeurController = loader.getController();
            directeurController.setDirID(dirID);
            directeurController.setInterfController(this);

            Scene updateScene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(updateScene);
            stage.setTitle("Ajouter Operateur");
            stage.show();
        } catch (IOException e) {
            System.out.println("Erreur en navigating to the new scene");
        }
    }*/
     //***********bouton back***************
    public void navigateToPrecedenteDircteurScene(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/projetjava/views/Directeur.fxml"));
            Parent root = loader.load();

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();

            // Close the current window if needed
             ((Node)(event.getSource())).getScene().getWindow().hide();
        } catch (Exception e) {
            System.out.println("erreur en back");            // Handle exception appropriately (e.g., show an error message)
        }
    }

}
