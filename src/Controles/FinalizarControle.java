/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controles;

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Worker;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.text.Text;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebEvent;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

/**
 *
 * @author LabPesquisa
 */
public class FinalizarControle implements Initializable{

    @FXML
    WebView web;
    @FXML
    Text texto;
    @FXML
    Button btnovamente;
    @FXML
    Button btsair;
    @FXML
    Label texto2;
    private Tela application;
    public static int acertos, erros;
    
    
    public static String msg;

    
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        btnovamente.setVisible(false);
        btsair.setVisible(false);
        String txt = msg.replaceAll("§", "\n");
        texto.setText(txt);
        WebEngine eng = web.getEngine();
        texto2.setText("Enviando email...");
        eng.getLoadWorker().stateProperty().addListener(
            (ObservableValue<? extends Worker.State> observable, Worker.State oldValue, Worker.State newValue) -> {
            if( newValue != Worker.State.SUCCEEDED ) {
                return;
            }
            
            btnovamente.setVisible(true);
            btsair.setVisible(true);
            texto2.setVisible(false);
            System.out.println("aqui");
        });
         Thread t = new Thread() {
			 boolean inter=false;
                         int cont=15;
			@Override
			public void run() {
                            for(int i=0; i<15; i++){
                                if(consegueConectar("http://www.google.com.br")) {
                                    Platform.runLater(new Runnable() {
                                        @Override public void run() {
                                           eng.load("https://inadaptable-number.000webhostapp.com/enviar.php?email="+LoginControle.Pemail+"&nome="+LoginControle.Pnome+"&matricula="+LoginControle.Pmatricula+"&acertos="+acertos+"&erros="+erros+"&msg="+msg);
                                           //eng.load("http://www.google.com.br");
                                        }
                                    });
                                    
                                    inter=true;
                                    break;
                                }else{
                                    Platform.runLater(new Runnable() {
                                        @Override public void run() {
                                            texto2.setText(cont+" tentativa de conexão...");
                                        }
                                    });
                                    cont--;
                                }  
                                try {
                                    Thread.sleep(1000);
                                } catch (InterruptedException ex) {
                                    Logger.getLogger(FinalizarControle.class.getName()).log(Level.SEVERE, null, ex);
                                }
                            }
                            if(!inter){
                                Platform.runLater(new Runnable() {
                                        @Override public void run() {
                                            texto2.setText("Não foi possível enviar o email, devido a falta de conexão!");
                                            btnovamente.setVisible(true);
                                            btsair.setVisible(true);
                                        }
                                    });
                            }
                        }
                        };
        t.start();
       
    }
    
    public void JogarNovamente(){
        try {
            Tela.stage.close();
            Login.stage.close();
            
            new Tela().start(new Stage());
        } catch (Exception ex) {
            Logger.getLogger(FinalizarControle.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
     public void Sair(){
        System.exit(0);
    }
     
      public static boolean consegueConectar(String address) {
		try {
			URL url = new URL(address);
			URLConnection connection = url.openConnection();
			connection.connect();
			return true;
		} catch (IOException e) {
			
			return false;
		}
	}

    
}
