package projetjava.Controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
    public void login() {
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

    private boolean checkDirecteurCredentials(String username, String password) {
        try (Connection connection = connecterDB.connecterDB();
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM Directeur WHERE adresseEmail = ? AND mdp = ?")) {
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
}
