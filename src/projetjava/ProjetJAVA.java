/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXML.java to edit this template
 */
package projetjava;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author admin
 */
public class ProjetJAVA extends Application {
    
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(ProjetJAVA.class.getResource("/projetjava/Views/Login.fxml"));
        Scene scene = new Scene(fxmlLoader.load(),843,483);
        stage.setTitle("Welcome page");
        stage.setScene(scene);
        stage.show();
        
       } 

    public static void main(String[] args) {
        launch();
    }
    
}
