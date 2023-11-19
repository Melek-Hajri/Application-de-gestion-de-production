/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package projetjava.Controller;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import projetjava.Model.Production.LigneDeProduction;
import projetjava.Model.Production.Machine;

/**
 * FXML Controller class
 *
 * @author hajri
 */
public class MachinesController implements Initializable {

    /**
     * Initializes the controller class.
     */

    @FXML
    private TableColumn<Machine, Boolean> enMaintenance;

    @FXML
    private TableColumn<Machine, Integer> id;

    @FXML
    private TableColumn<Machine, String> nom;

    @FXML
    private TableView<Machine> tableview;

    @FXML
    private TableColumn<Machine, String> type;
    
    private ObservableList<Machine> machineList;
    
    private LigneDeProduction ligne;
    
    public void setLigne(LigneDeProduction ligne){
        this.ligne = ligne;
    }
    public MachinesController(LigneDeProduction ligne){
        this.ligne = ligne;
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initTableView();
        loadMachines();
    }
    private void initTableView() {
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        type.setCellValueFactory(new PropertyValueFactory<>("type"));
        enMaintenance.setCellValueFactory(new PropertyValueFactory<>("enMaintenance"));
    } 
    private void loadMachines() {
        try {
            
                    System.out.println(ligne);
            machineList = FXCollections.observableArrayList(LigneDAO.getMachinesByLigneId(ligne.getId()));
            tableview.setItems(machineList);
        } catch (SQLException e) {
            System.out.println("Error fetching machines: " + e.getMessage());
        }
    }
    @FXML
    public void navigateToPrecedenteChefScene(ActionEvent event) {
        ((Node)(event.getSource())).getScene().getWindow().hide();
    }
    
}
