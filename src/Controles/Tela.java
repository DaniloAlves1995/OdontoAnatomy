/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Controles;

import Controles.LoginControle;
import Controles.PrincipalControle;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 *
 * @author RC
 */
public class Tela extends Application{
    
     public static Stage stage;
     public static PrincipalControle jogo;
    
    @Override
    public void start(Stage primaryStage) throws Exception {
        try {
            stage = primaryStage;
          //  primaryStage.initStyle(StageStyle.UNDECORATED);
             Iniciando();
             primaryStage.resizableProperty().set(false);
             primaryStage.setTitle("....::: OdontoANATOMY :::....");
            primaryStage.show();
           
        } catch (Exception ex) {
            Logger.getLogger(Tela.class.getName()).log(Level.SEVERE, null, ex);
        }
        
       
        
    }
    
     public static void main(String[] args) {
        Application.launch(Tela.class, (java.lang.String[])null);
       
    }
     
     
    private Initializable replaceSceneContent(String fxml) throws Exception {
        FXMLLoader loader = new FXMLLoader();
           InputStream in = Tela.class.getResourceAsStream(fxml);
           loader.setBuilderFactory(new JavaFXBuilderFactory());
           loader.setLocation(Tela.class.getResource(fxml));
           AnchorPane page;
        try {
            page = (AnchorPane) loader.load(in);
        } finally {
            in.close();
        } 
        Scene scene = new Scene(page);
        //scene.getStylesheets().add(TelaPrincipal.class.getResource("LoginCSS.css").toExternalForm());
        stage.setScene(scene);
        stage.sizeToScene();
        stage.getIcons().add(new Image(getClass().getResourceAsStream("/Imagens/icon.png")));
        
        return (Initializable) loader.getController();
    }
    
    public void IniciarLogin(){
         try {
            LoginControle login = (LoginControle) replaceSceneContent("/Telas/Login.fxml");
            //stage.getScene().getStylesheets().add(Principal.class.getResource("/Css/Css.css").toExternalForm());
            
            login.setApp(this);
        } catch (Exception ex) {
            Logger.getLogger(Tela.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
  
    public void Iniciando() {
        try {
            jogo = (PrincipalControle) replaceSceneContent("/Telas/Principal.fxml");
            jogo.setApp(this);
            
        } catch (Exception ex) {
            Logger.getLogger(Tela.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
   
    
   /* public void Web() {
        try {
            WebControle web = (WebControle) replaceSceneContent("Web.fxml");
            
            web.setApp(this);
        } catch (Exception ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }*/
    public Stage getStage(){
        return stage;
    }
}
