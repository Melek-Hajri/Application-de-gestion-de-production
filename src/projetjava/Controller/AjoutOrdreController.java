/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package projetjava.Controller;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.ChoiceBox;
import projetjava.Model.Gestion.Date;
import projetjava.Model.Production.Exceptions.DateDebFinException;
import projetjava.Model.Production.Exceptions.InvalidPrixException;
import projetjava.Model.Production.LigneDeProduction;
import projetjava.Model.Production.OrdreDeProduction;
import projetjava.Model.Production.Produit;

/**
 * FXML Controller class
 *
 * @author hajri
 */
public class AjoutOrdreController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private Button Sauvegarder;

    @FXML
    private ChoiceBox<Integer> anneed;

    @FXML
    private ChoiceBox<Integer> anneef;

    @FXML
    private ChoiceBox<Integer> jourd;

    @FXML
    private ChoiceBox<Integer> jourf;

    @FXML
    private ChoiceBox<Integer> moisd;

    @FXML
    private ChoiceBox<Integer> moisf;

    @FXML
    private TextField description;

    @FXML
    private TextField id;

    @FXML
    private TextField nom;

    @FXML
    private TextField prix;

    @FXML
    private TextField quantite;

    private GestionOrdresController ordreController;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        jourd.getItems().addAll(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31);
        moisd.getItems().addAll(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12);
        anneed.getItems().addAll(2023, 2024, 2025, 2026, 2027, 2028, 2029, 2030, 2031, 2032, 2033);

        jourf.getItems().addAll(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31);
        moisf.getItems().addAll(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12);
        anneef.getItems().addAll(2023, 2024, 2025, 2026, 2027, 2028, 2029, 2030, 2031, 2032, 2033);
    }    
    public void setOrdreController(GestionOrdresController ordreController) {
        this.ordreController = ordreController;
    }
    @FXML
    public void handleAddButton() throws InvalidPrixException, DateDebFinException {
        // Get the updated values from the TextFields
        int Id = Integer.parseInt(id.getText());
        String Nom = nom.getText();
        String Description = description.getText();
        double Prix = Double.parseDouble(prix.getText());
        int Quantite = Integer.parseInt(quantite.getText());
        int Jourd = jourd.getValue();
        int Moisd = moisd.getValue();
        int Anneed = anneed.getValue();
        int Jourf = jourf.getValue();
        int Moisf = moisf.getValue();
        int Anneef = anneef.getValue();
        
        Date dateDeb = new Date(Jourd, Moisd, Anneed);
        Date dateFin = new Date(Jourf, Moisf, Anneef);
        
        Produit produit = new Produit(Nom, Description, Prix);
        
        OrdreDeProduction ordre = new OrdreDeProduction(Id, produit, Quantite, dateDeb, dateFin);
        
        try {
            // Assuming you have a Connection object named "connection"
            // You should replace this with your actual way of obtaining a database connection
            // Connection connection = ...;
            // Assuming your table name is "Chefs" and you have fields like "Nom", "Prenom", etc.
                OrdreDAO.createOrdreDeProduction(ordre);
                ordreController.refreshTableData();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("erreur handleUpdateButton");    }

        nom.getScene().getWindow().hide();

    }
}
