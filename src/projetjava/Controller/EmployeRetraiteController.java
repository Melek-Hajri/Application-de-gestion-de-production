/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package projetjava.Controller;

import java.net.URL;
import java.util.List;
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
import projetjava.Model.Gestion.Chef;
import projetjava.Model.Gestion.Employe;

/**
 * FXML Controller class
 *
 * @author admin
 */
public class EmployeRetraiteController implements Initializable {

    private List<Chef> filteredEmployees;
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
    private TableColumn<Chef,Double>salaire;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initTableView();
      
    }

    public EmployeRetraiteController(List<Chef> filteredEmployees) {
        this.filteredEmployees = filteredEmployees;
    }
    
    
    private void initTableView() {
        ObservableList<Chef> chefList  = FXCollections.observableList(filteredEmployees);
         System.out.println("interf employRet"+chefList);
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
     

    
    public void setFilteredEmployees(List<Chef> filteredEmployees) {
        this.filteredEmployees = filteredEmployees;
     //   refreshTableView();
    }
    //******Bouton back**********
   @FXML
    public void navigateToPrecedenteDircteurScene(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/projetjava/views/INTERF1Directeur.fxml"));
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
