/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controles;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;

/**
 *
 * @author LabPesquisa
 */
public class ControleInterface implements Initializable{

    @FXML
    private ImageView i1;
    @FXML
    private ImageView i2;
    @FXML
    private ImageView i3;
    @FXML
    private ImageView i4;
    @FXML
    private ImageView i5;
    @FXML
    private ImageView i6;
    @FXML
    private Text l1;
    @FXML
    private Text l2;
    @FXML
    private Text l3;
    @FXML
    private Text l4;
    @FXML
    private Text l5;
    @FXML
    private Text l6;
    
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Tela.jogo.SetElementos(l1, l2, l3, l4, l5, l6, i1, i2, i3, i4, i5, i6);
    }
    
}