/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controles;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 *
 * @author Danilo
 */
public class Ajudar extends Application{

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent parent= FXMLLoader.load(getClass().getResource("/Telas/Ajuda.fxml"));
            Scene scene = new Scene(parent);
            primaryStage.initModality(Modality.APPLICATION_MODAL);
            primaryStage.setScene(scene);
           // primaryStage.resizableProperty().set(false);
            primaryStage.setTitle("....::: OdontoANATOMY :::....");
            primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("/Imagens/icon.png")));
            
            primaryStage.show();
    }
    
}
