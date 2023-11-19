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
import projetjava.Model.Production.LigneDeProduction;

/**
 * FXML Controller class
 *
 * @author hajri
 */
public class GestionLignesController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private TableColumn<LigneDeProduction, LigneDeProduction> Action;

    @FXML
    private TableColumn<LigneDeProduction, Boolean> disponible;

    @FXML
    private TableColumn<LigneDeProduction, Integer> id;

    @FXML
    private TableColumn<LigneDeProduction, String> nom;

    @FXML
    private TableView<LigneDeProduction> tableview;
    
    
    private ObservableList<LigneDeProduction> ligneList;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initTableView();
        addActionColumn();
        loadLignes();
    }

    private void initTableView() {
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        disponible.setCellValueFactory(new PropertyValueFactory<>("disponible"));
    }
private void addActionColumn() {
        Action.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue()));
        Action.setCellFactory(param -> new TableCell<>() {
            private final Button updateButton = new Button("Update");
            private final Button deleteButton = new Button("Delete");
            private final Button machinesButton = new Button("Machines");

            {
                updateButton.setOnAction(event -> {
                    LigneDeProduction ligne = getTableView().getItems().get(getIndex());
                    navigateToUpdateScene(ligne);
                    System.out.println("Update ligne: " + ligne);
                });

                deleteButton.setOnAction(event -> {
                    LigneDeProduction ligne = getTableView().getItems().get(getIndex());
                    boolean confirmed = showDeleteConfirmationDialog(ligne);
                    if (confirmed) {
                        try {
                            LigneDAO.deleteLigneDeProduction(ligne.getId());
                            System.out.println("Ligne deleted successfully.");
                            refreshTableData();
                        } catch (SQLException e) {
                            System.out.println("Error deleting ligne: " + e.getMessage());
                        }
                    }
                });
                machinesButton.setOnAction(event -> {
                    LigneDeProduction ligne = getTableView().getItems().get(getIndex());
                    navigateToMachineScene(ligne);
                    System.out.println("Machines de la ligne: " + ligne);
                });
            }

            @Override
            protected void updateItem(LigneDeProduction item, boolean empty) {
                super.updateItem(item, empty);
                if (item == null || empty) {
                    setGraphic(null);
                } else {
                    setGraphic(new HBox(updateButton, deleteButton, machinesButton));
                }
            }
        });
    }
    

    private void loadLignes() {
        try {
            ligneList = FXCollections.observableArrayList(LigneDAO.getAllLignes());
            tableview.setItems(ligneList);
        } catch (SQLException e) {
            System.out.println("Error fetching lignes: " + e.getMessage());
        }
    }

    private void navigateToUpdateScene(LigneDeProduction ligne) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/projetjava/views/UpdateLigne.fxml"));
            Parent root = loader.load();

            UpdateLigneController updateController = loader.getController();
            updateController.setLigne(ligne);
            updateController.setLigneController(this);
            updateController.setUpdateCallback(this::handleChefUpdate);

            Scene updateScene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(updateScene);
            stage.setTitle("Update Ligne");
            stage.show();
        } catch (IOException e) {
            System.out.println("Error navigating to the update scene");
        }
    }

    public void refreshTableData() {
        loadLignes();
        tableview.refresh();
    }
    private void handleChefUpdate(LigneDeProduction ligne) {
        refreshTableData();
        System.out.println("");
    }

    private boolean showDeleteConfirmationDialog(LigneDeProduction ligne) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Delete Confirmation");
        alert.setHeaderText("Delete Ligne");
        alert.setContentText("Are you sure you want to delete ligne: " + ligne.getNom() + "?");

        Optional<ButtonType> result = alert.showAndWait();
        return result.orElse(ButtonType.CANCEL) == ButtonType.OK;
    }

    @FXML
    private void navigateToAjouterLigneScene() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/projetjava/views/AjoutLigne.fxml"));
            Parent root = loader.load();

            AjoutLigneController ajoutLigneController = loader.getController();
            ajoutLigneController.setLigneController(this);

            Scene ajoutScene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(ajoutScene);
            stage.setTitle("Ajouter Ligne");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error navigating to the add scene");
        }
    }
    @FXML
    public void navigateToPrecedenteChefScene(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/projetjava/views/Chef.fxml"));
            Parent root = loader.load();

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();

            ((Node)(event.getSource())).getScene().getWindow().hide();
        } catch (Exception e) {
            System.out.println("Error going back");
        }
    }
    private void navigateToMachineScene(LigneDeProduction ligne) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/projetjava/views/Machines.fxml"));
            loader.setControllerFactory(c -> new MachinesController(ligne));
            Parent root = loader.load();

            //MachinesController machineController = loader.getController();
            //machineController.setLigne(ligne); // Ensure setting the ligne object

            Scene updateScene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(updateScene);
            stage.setTitle("Machines de la ligne");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error navigating to the machines scene");
        }
    }
}