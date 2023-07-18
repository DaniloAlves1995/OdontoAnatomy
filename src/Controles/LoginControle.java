/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Controles;

import com.sun.javafx.property.adapter.PropertyDescriptor;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 *
 * @author Danilo
 */
public class LoginControle implements Initializable{

    @FXML
    private TextField nome;
    @FXML
    private TextField email;
    @FXML
    private Label titulo1;
    @FXML
    private Label titulo2;
    @FXML
    private Label msg;
    @FXML
    private Button botao;
    
    private Tela application;
    //dados da pessoa que joga
    public static String Pnome;
    public static String Pemail;
    public static String Pmatricula;
    
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        msg.setVisible(false);
        
    }
    public void Logar(){
        if(nome.getText().trim().equals("")){
            nome.setStyle("-fx-border-color: #EE0000; -fx-border-width: 2px;");
            msg.setVisible(true);
            EfeitoErrar();
        }else{
            if(email.getText().trim().equals("")){
                email.setStyle("-fx-border-color: #EE0000; -fx-border-width: 2px;");
                msg.setVisible(true);
                EfeitoErrar();
            }else{
                String linha = "";
                boolean veri=false;
                try {
                    System.out.println(getClass().getResourceAsStream("dados.txt"));
                    InputStream in = getClass().getResourceAsStream("dados.txt");
                    
                   
                    BufferedReader br = new BufferedReader(new InputStreamReader(in, "UTF-8"));
                    
                    String [] valores;
                    System.out.println(nome.getText());
                    while(br.ready()){                   
                            linha = br.readLine();
                            if(linha.split("-")[0].equals(nome.getText())){
                                veri = true;
                                break;
                            }
                            
                    }
                    br.close();
                } catch (IOException ex) {
                        Logger.getLogger(LoginControle.class.getName()).log(Level.SEVERE, null, ex);
                }
                if(veri){
                    Pnome = linha.split("-")[1];
                    Pmatricula = nome.getText();
                    Pemail = email.getText();
                    try {
                        //chama a tela principal
                        new Tela().start(new Stage());
                        Login.stage.close();
                    } catch (Exception ex) {
                        Logger.getLogger(LoginControle.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }else{
                    msg.setText("Essa matrícula não é válida.");
                    msg.setVisible(true);
                    EfeitoErrar();
                }
            }
        }
      
    }
    
    public void setApp(Tela application) {
        this.application = application;
    }
    
    public void EfeitoErrar(){
        //efeito de fazer o texto piscar, chamando a atênção do usuário
            final Timeline timeline = new Timeline();
            timeline.setCycleCount(6);
            timeline.setAutoReverse(true);
            final KeyValue kv = new KeyValue(msg.opacityProperty(), 0);
            final KeyFrame kf = new KeyFrame(Duration.millis(600), kv);
 
            timeline.getKeyFrames().add(kf);
            timeline.play();
    }
}
