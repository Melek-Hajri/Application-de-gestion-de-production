/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package projetjava.Controller;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import projetjava.Model.Production.Exceptions.NoLignesDisponiblesException;
import projetjava.Model.Production.LigneDeProduction;

/**
 * FXML Controller class
 *
 * @author hajri
 */
public class LignesDisponiblesController implements Initializable {

    /**
     * Initializes the controller class.
     */
    

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
        loadLignes();
    }

    private void initTableView() {
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        disponible.setCellValueFactory(new PropertyValueFactory<>("disponible"));
    }

    

    private void loadLignes() {
        try {
            ligneList = FXCollections.observableArrayList(PlanDAO.getPlan().getLignesDisponibles());
            tableview.setItems(ligneList);
        } catch (NoLignesDisponiblesException ex) {
            Logger.getLogger(LignesDisponiblesController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


    @FXML
    public void navigateToPrecedenteOrdreScene(ActionEvent event) {
        try {
            ((Node)(event.getSource())).getScene().getWindow().hide();
        } catch (Exception e) {
            System.out.println("Error going back");
        }
    }

}