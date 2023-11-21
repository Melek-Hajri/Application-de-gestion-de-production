/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package projetjava.Controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.SimpleBooleanProperty;
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
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import projetjava.Model.Production.Exceptions.DateDebFinException;
import projetjava.Model.Production.Exceptions.InvalidPrixException;
import projetjava.Model.Production.Exceptions.NoLignesDisponiblesException;
import projetjava.Model.Production.OrdreDeProduction;

/**
 * FXML Controller class
 *
 * @author hajri
 */
public class OrdresNonPlanifiesController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private TableColumn<OrdreDeProduction, OrdreDeProduction> Action;



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
    private TableView<OrdreDeProduction> tableview;

    private ObservableList<OrdreDeProduction> ordreList;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initTableView();
        try {
            addActionColumn();
            loadLignes();
        } catch (InvalidPrixException | DateDebFinException ex) {
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

        
    }
    
private void addActionColumn() {
        Action.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue()));
        Action.setCellFactory(param -> new TableCell<>() {
            private final Button planifierButton = new Button("Planifier");

            {
                planifierButton.setOnAction(event -> {
                    OrdreDeProduction ordre = getTableView().getItems().get(getIndex());
                    try {
                        PlanDAO.getPlan().planifierProduction(ordre);
                        refreshTableData();
                    } catch (NoLignesDisponiblesException ex) {
                        Logger.getLogger(OrdresNonPlanifiesController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    System.out.println("Update ordre: " + ordre);
                });
                planifierButton.setStyle("-fx-background-color: #474BCA; -fx-text-fill: #ffffff;-fx-font-style: italic; -fx-font-weight: bold;");

               
            }

            @Override
            protected void updateItem(OrdreDeProduction item, boolean empty) {
                super.updateItem(item, empty);
                if (item == null || empty) {
                    setGraphic(null);
                } else {
                    HBox buttonsContainer = new HBox(planifierButton);
                    buttonsContainer.setSpacing(10); // Réglez la valeur de l'espace ici (5 pixels dans cet exemple)
                    setGraphic(buttonsContainer);
                }
            }
        });
    }
    

    private void loadLignes() throws InvalidPrixException, DateDebFinException {
        try {
            ordreList = FXCollections.observableArrayList(PlanDAO.getPlan().getOrdresWithNullLigne());
            tableview.setItems(ordreList);
        } catch (NoLignesDisponiblesException ex) {
            Logger.getLogger(OrdresNonPlanifiesController.class.getName()).log(Level.SEVERE, null, ex);
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

    @FXML
    private void navigateToLignesDisponiblesScene() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/projetjava/views/LignesDisponibles.fxml"));
            Parent root = loader.load();

            Scene ajoutScene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(ajoutScene);
            stage.setTitle("Lignes disponibles");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error navigating to the lignes disponibles scene");
        }
    }
    @FXML
    public void navigateToPrecedenteOrdresScene(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/projetjava/views/GestionOrdres.fxml"));
            Parent root = loader.load();

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();

            ((Node)(event.getSource())).getScene().getWindow().hide();
        } catch (Exception e) {
            System.out.println("Error going back");
        }
    }

}
    

