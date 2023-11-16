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
import javafx.application.Platform;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import static projetjava.Controller.connecterDB.connecterDB;
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
    private TableColumn<Chef,?> Action;
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
    }
    @FXML
    private void initTableView() {
     
    ObservableList<Chef> chefList = fetchChefDataFromDatabase();
    tableview.setItems(chefList);
    tableview.refresh();

    // Configure TableView columns with Chef object properties
    id.setCellValueFactory(new PropertyValueFactory<Chef,Integer>("id"));
    nom.setCellValueFactory(new PropertyValueFactory<Chef,String>("nom"));
    prenom.setCellValueFactory(new PropertyValueFactory<Chef,String>("prenom"));
   
    adresseEmail.setCellValueFactory(new PropertyValueFactory<Chef,String>("adresseEmail"));
    numeroTelephone.setCellValueFactory(new PropertyValueFactory<Chef,String>("numeroTelephone"));
    age.setCellValueFactory(new PropertyValueFactory<Chef,Integer>("age"));
    nbreHeures.setCellValueFactory(new PropertyValueFactory<Chef,Integer>("nbreHeures"));
    prixParHeure.setCellValueFactory(new PropertyValueFactory<Chef,Double>("prixParHeure"));
    mdp.setCellValueFactory(new PropertyValueFactory<Chef,String>("mdp"));
    salaire.setCellValueFactory(new PropertyValueFactory<>("salaire"));
    
     

    
     
}

    
}


