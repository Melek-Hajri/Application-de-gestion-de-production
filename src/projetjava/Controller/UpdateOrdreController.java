/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package projetjava.Controller;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.function.Consumer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import projetjava.Model.Gestion.Date;
import projetjava.Model.Production.Exceptions.DateDebFinException;
import projetjava.Model.Production.Exceptions.InvalidPrixException;
import projetjava.Model.Production.OrdreDeProduction;
import projetjava.Model.Production.Produit;

/**
 * FXML Controller class
 *
 * @author hajri
 */
public class UpdateOrdreController implements Initializable {

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
    
    public Consumer<OrdreDeProduction> updateCallback;
    private OrdreDeProduction ordre;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        jourd.getItems().addAll(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31);
        moisd.getItems().addAll(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12);
        anneed.getItems().addAll(2023, 2024, 2025, 2026, 2027, 2028, 2029, 2030, 2031, 2032, 2033);

        jourf.getItems().addAll(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31);
        moisf.getItems().addAll(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12);
        anneef.getItems().addAll(2023, 2024, 2025, 2026, 2027, 2028, 2029, 2030, 2031, 2032, 2033);
    }    
    
    public void setOrdre(OrdreDeProduction ordre) {
        this.ordre = ordre;

        
        id.setText(String.valueOf(ordre.getNumero()));
        nom.setText(ordre.getProduit().getNom());
        description.setText(ordre.getProduit().getDescription());
        prix.setText(String.valueOf(ordre.getProduit().getPrix()));
        quantite.setText(String.valueOf(ordre.getQuantite()));
        
        jourd.setValue(ordre.getDateDeb().getJour());
        moisd.setValue(ordre.getDateDeb().getMois());
        anneed.setValue(ordre.getDateDeb().getAnnee());
        jourf.setValue(ordre.getDateFin().getJour());
        moisf.setValue(ordre.getDateFin().getMois());
        anneef.setValue(ordre.getDateFin().getAnnee());
    }
    
    public void setOrdreController(GestionOrdresController ordreController) {
        this.ordreController = ordreController;
    }
    
    @FXML
public void handleUpdateButton() {
    try {
        // Get the updated values from the TextFields
        int updatedId = Integer.parseInt(id.getText());
        String updatedNom = nom.getText();
        String updatedDescription = description.getText();
        double updatedPrix = Double.parseDouble(prix.getText());
        int updatedQuantite = Integer.parseInt(quantite.getText());
        int updatedJourd = jourd.getValue();
        int updatedMoisd = moisd.getValue();
        int updatedAnneed = anneed.getValue();
        int updatedJourf = jourf.getValue();
        int updatedMoisf = moisf.getValue();
        int updatedAnneef = anneef.getValue();
        
        Date updatedDateDeb = new Date(updatedJourd, updatedMoisd, updatedAnneed);
        Date updatedDateFin = new Date(updatedJourf, updatedMoisf, updatedAnneef);
        
        Produit updatedProduit = new Produit(updatedNom, updatedDescription, updatedPrix);
        
        OrdreDeProduction updatedOrdre = new OrdreDeProduction(updatedId, updatedProduit, updatedQuantite, updatedDateDeb, updatedDateFin);
        
        try {
            int rowsUpdated = OrdreDAO.updateOrdreDeProduction(updatedOrdre);
            
            if (rowsUpdated > 0) {
                System.out.println("Ordre updated successfully.");
                ordreController.refreshTableData();
            } else {
                System.out.println("Failed to update Ordre.");
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("erreur handleUpdateButton");
        }
        
        // Close the update scene
        nom.getScene().getWindow().hide();
    } catch (InvalidPrixException ex) {
            Logger.getLogger(UpdateOrdreController.class.getName()).log(Level.SEVERE, null, ex);
    }   catch (DateDebFinException ex) {
            Logger.getLogger(UpdateOrdreController.class.getName()).log(Level.SEVERE, null, ex);
        }
}

    public void setUpdateCallback(Consumer<OrdreDeProduction> updateCallback) {
        this.updateCallback = updateCallback;
    }
    
}
