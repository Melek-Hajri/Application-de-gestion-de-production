package projetjava.Controller;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class LoginController {

    @FXML
    private TextField IDadmin;

    @FXML
    private PasswordField passwordAdmin;

    @FXML
    private RadioButton chefRadioButton;

    @FXML
    private RadioButton directeurRadioButton;

    
    @FXML
    public void login() throws SQLException {
        String username = IDadmin.getText();
        String password = passwordAdmin.getText();

        if (chefRadioButton.isSelected()) {
            if (checkChefCredentials(username, password)) {
                // Chef login successful, implement your logic here
                System.out.println("Chef login successful");
            } else {
                System.out.println("Invalid Chef credentials");
            }
        } else if (directeurRadioButton.isSelected()) {
            if (checkDirecteurCredentials(username, password)) {
                // Directeur login successful, implement your logic here
                System.out.println("Directeur login successful");
                Connection connection = connecterDB.connecterDB();
                PreparedStatement preparedStatement = connection.prepareStatement("SELECT id FROM directeur WHERE adresseEmail = 'john.doe@example.com' AND mdp = 'password123'");
                //preparedStatement.setString(1, username);
                //preparedStatement.setString(2, password);
                ResultSet resultSet = preparedStatement.executeQuery();
                if (resultSet.next()) {
                int DirID = resultSet.getInt("id");
                openDirecteurFXML(DirID);
                }
            } else {
                System.out.println("Invalid Directeur credentials");
            }
        } else {
            System.out.println("Please select a role.");
        }
    }

    private boolean checkChefCredentials(String username, String password) {
        try (Connection connection = connecterDB.connecterDB();
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM Chef WHERE adresseEmail = ? AND mdp = ?")) {
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                return resultSet.next();
            }
        } catch (SQLException e) {
           System.out.println("cnx failed");
            return false;
        }
    }
    private void openDirecteurFXML(int DirID) {
    try {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/projetjava/Views/Directeur.fxml"));
        Parent root = fxmlLoader.load();
        DirecteurController directeurController = fxmlLoader.getController();

        directeurController.setDirID(DirID);
        // Créer la scène
        Scene scene = new Scene(root);
        
        // Créer la fenêtre du directeur
        Stage stage = new Stage();
        stage.setTitle("Directeur Dashboard"); // Titre de la fenêtre
        stage.setScene(scene);
        
        // Afficher la fenêtre
        stage.show();
        
        // Fermer la fenêtre de connexion (si nécessaire)
        Stage loginStage = (Stage) IDadmin.getScene().getWindow();
        loginStage.close();
    } catch (IOException e) {
       System.out.println("fail d ouverture d'interface ");
    }
}

    private boolean checkDirecteurCredentials(String username, String password) {
        try (Connection connection = connecterDB.connecterDB();
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM directeur WHERE adresseEmail = 'john.doe@example.com' AND mdp = 'password123'")) {
            //preparedStatement.setString(1, username);
            //preparedStatement.setString(2, password);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                return resultSet.next();
            }
        } catch (SQLException e) {
            System.out.println("cnx failed");
            return false;
        }
    }
}
    

