/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controles;

import static Controles.Login.stage;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 *
 * @author LabPesquisa
 */
public class Finalizar extends Application{

    @Override
    public void start(Stage primaryStage) throws Exception {
        try {
            Parent parent= FXMLLoader.load(getClass().getResource("/Telas/Finalizar.fxml"));
            Scene scene = new Scene(parent);
           // primaryStage.initModality(Modality.APPLICATION_MODAL);
            primaryStage.setScene(scene);
            primaryStage.resizableProperty().set(false);
            primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("/Imagens/icon.png")));
            
            stage = primaryStage;
            primaryStage.setTitle("....::: OdontoANATOMY :::....");
            primaryStage.show();
           
        } catch (Exception ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
     public static void main(String[] args) {
        Application.launch(Finalizar.class, (java.lang.String[])null);
       
    }
    
}
