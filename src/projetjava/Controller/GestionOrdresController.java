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
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
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
import projetjava.Model.Gestion.Date;
import projetjava.Model.Production.Exceptions.DateDebFinException;
import projetjava.Model.Production.Exceptions.InvalidPrixException;
import projetjava.Model.Production.LigneDeProduction;
import projetjava.Model.Production.OrdreDeProduction;

/**
 * FXML Controller class
 *
 * @author hajri
 */
public class GestionOrdresController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private TableColumn<OrdreDeProduction, OrdreDeProduction> Action;

    @FXML
    private Button ButtonBack;

    @FXML
    private Button buttonAdd;

    @FXML
    private TableColumn<OrdreDeProduction, String> dateDeb;

    @FXML
    private TableColumn<OrdreDeProduction, String> dateFin;

    @FXML
    private TableColumn<OrdreDeProduction, Integer> id;

    @FXML
    private TableColumn<OrdreDeProduction, Integer> quantite;

    @FXML
    private TableColumn<OrdreDeProduction, String> description;

    @FXML
    private TableColumn<OrdreDeProduction, String> nom;

    @FXML
    private TableColumn<OrdreDeProduction, Boolean> planifie;

    @FXML
    private TableColumn<OrdreDeProduction, Double> prix;
    
    @FXML
    private TableColumn<OrdreDeProduction, ?> produit;
    
    @FXML
    private TableView<OrdreDeProduction> tableview;

    private ObservableList<OrdreDeProduction> ordreList;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initTableView();
        try {
            addActionColumn();
            loadLignes();
        } catch (InvalidPrixException ex) {
            Logger.getLogger(GestionOrdresController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (DateDebFinException ex) {
            Logger.getLogger(GestionOrdresController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void initTableView() {
        id.setCellValueFactory(data -> new SimpleIntegerProperty(data.getValue().getNumero()).asObject());
        nom.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getProduit().getNom()));
        description.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getProduit().getDescription()));
        prix.setCellValueFactory(data -> new SimpleObjectProperty<>(data.getValue().getProduit().getPrix()));
        quantite.setCellValueFactory(data -> new SimpleIntegerProperty(data.getValue().getQuantite()).asObject());
        dateDeb.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getDateDeb().toString()));
        dateFin.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getDateFin().toString()));
        planifie.setCellValueFactory(data -> new SimpleBooleanProperty(data.getValue().getLigne() != null));

        // Implement action column cell factory here
        
        // Set data to the TableView using OrdreDeProduction list
        // For instance:
        // List<OrdreDeProduction> ordres = OrdreDAO.getAllOrdres();
        // tableview.setItems(FXCollections.observableArrayList(ordres));
    }
    
private void addActionColumn() {
        Action.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue()));
        Action.setCellFactory(param -> new TableCell<>() {
            private final Button updateButton = new Button("Update");
            private final Button deleteButton = new Button("Delete");
            private final Button lignesButton = new Button("Planifier");

            {
                updateButton.setOnAction(event -> {
                    OrdreDeProduction ordre = getTableView().getItems().get(getIndex());
                    navigateToUpdateScene(ordre);
                    System.out.println("Update ordre: " + ordre);
                });

                deleteButton.setOnAction(event -> {
                    OrdreDeProduction ordre = getTableView().getItems().get(getIndex());
                    boolean confirmed = showDeleteConfirmationDialog(ordre);
                    if (confirmed) {
                        try {
                            OrdreDAO.deleteOrdreDeProduction(ordre.getNumero());
                            System.out.println("Ordre deleted successfully.");
                            refreshTableData();
                        } catch (SQLException e) {
                            System.out.println("Error deleting ordre: " + e.getMessage());
                        }
                    }
                });
//                machinesButton.setOnAction(event -> {
//                    LigneDeProduction ligne = getTableView().getItems().get(getIndex());
//                    navigateToMachineScene(ligne);
//                    System.out.println("Machines de la ligne: " + ligne);
//                });
            }

            @Override
            protected void updateItem(OrdreDeProduction item, boolean empty) {
                super.updateItem(item, empty);
                if (item == null || empty) {
                    setGraphic(null);
                } else {
                    setGraphic(new HBox(updateButton, deleteButton, lignesButton));
                }
            }
        });
    }
    

    private void loadLignes() throws InvalidPrixException, DateDebFinException {
        try {
            ordreList = FXCollections.observableArrayList(OrdreDAO.getAllOrdres());
            tableview.setItems(ordreList);
        } catch (SQLException e) {
            System.out.println("Error fetching lignes: " + e.getMessage());
        }
    }

    private void navigateToUpdateScene(OrdreDeProduction ordre) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/projetjava/views/UpdateOrdre.fxml"));
            Parent root = loader.load();

            UpdateOrdreController updateController = loader.getController();
            updateController.setOrdre(ordre);
            updateController.setOrdreController(this);
            updateController.setUpdateCallback(this::handleOrdreUpdate);

            Scene updateScene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(updateScene);
            stage.setTitle("Update Ordre");
            stage.show();
        } catch (IOException e) {
            System.out.println("Error navigating to the update scene");
        }
    }

    public void refreshTableData() {
        try {
            loadLignes();
        } catch (InvalidPrixException ex) {
            Logger.getLogger(GestionOrdresController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (DateDebFinException ex) {
            Logger.getLogger(GestionOrdresController.class.getName()).log(Level.SEVERE, null, ex);
        }
        tableview.refresh();
    }
    private void handleOrdreUpdate(OrdreDeProduction ordre) {
        refreshTableData();
        System.out.println("");
    }

    private boolean showDeleteConfirmationDialog(OrdreDeProduction ordre) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Delete Confirmation");
        alert.setHeaderText("Delete Ligne");
        alert.setContentText("Are you sure you want to delete ligne: " + ordre.getNumero() + "?");

        Optional<ButtonType> result = alert.showAndWait();
        return result.orElse(ButtonType.CANCEL) == ButtonType.OK;
    }

    @FXML
    private void navigateToAjouterOrdreScene() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/projetjava/views/AjoutOrdre.fxml"));
            Parent root = loader.load();

            AjoutOrdreController ajoutOrdreController = loader.getController();
            ajoutOrdreController.setOrdreController(this);

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
//    private void navigateToMachineScene(LigneDeProduction ligne) {
//        try {
//            FXMLLoader loader = new FXMLLoader(getClass().getResource("/projetjava/views/Machines.fxml"));
//            loader.setControllerFactory(c -> new MachinesController(ligne));
//            Parent root = loader.load();
//
//            //MachinesController machineController = loader.getController();
//            //machineController.setLigne(ligne); // Ensure setting the ligne object
//
//            Scene updateScene = new Scene(root);
//            Stage stage = new Stage();
//            stage.setScene(updateScene);
//            stage.setTitle("Machines de la ligne");
//            stage.show();
//        } catch (IOException e) {
//            e.printStackTrace();
//            System.out.println("Error navigating to the machines scene");
//        }
//    }
}