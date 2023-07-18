/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Controles;

import java.io.IOException;
import java.net.URL;
import java.text.Normalizer;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.effect.BlurType;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Effect;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 *
 * @author RC
 */
public class PrincipalControle implements Initializable{
   
    @FXML
    private Pane caixa;
    @FXML
    private Button proximo;
    @FXML
    private Button verificar;
    
    @FXML
    private TextField campo;
    @FXML
    private AnchorPane pane;
    @FXML
    private AnchorPane Panei;
    @FXML
    private Label acertosL;
    @FXML
    private Label errosL;
    @FXML
    private Label msg;
    @FXML
    private ImageView fechard;
    @FXML
    private Pane paned;
    @FXML
    private Text textod;
    @FXML
    private Button btrespostas;
    
  
    
    //Elementos para receber os campos do outro controle
    private Text l1, l2, l3, l4, l5, l6;
    private ImageView i1, i2, i3, i4, i5, i6;
    
  
    
    private Tela application;
    private Text labelc;//label corrente
    private int posicao;//valor da marcação que está escrevendo
    private int acertos = 0, erros=0;//variaveis para acertos e erros
    private int tela = 0;//variável para saber em qual tela está

    //matriz com as respostas corretas
    String resc[][] = {{"FORAME MENTUAL", "FISSURA PTERIGOPALATINA", "TÚBER DA MAXILA", "INCISURA SIGMÓIDE", "EXTENSÃO ALVEOLAR DO SEIO MAXILAR"},
        {"CÔNDILO DA MANDÍBULA","ESPINHA NASAL ANTERIOR","LINHA OBLÍQUA","BASE DA MANDÍBULA"},
        {"PROCESSO ESTILÓIDE","PALATO DURO","SEPTO NASAL","ESPAÇO AÉREO NASOFARÍNGEO","ARCO ZIGOMÁTICO","ASSOALHO DA FOSSA NASAL"},
        {"TUBÉRCULO GENIANO","EMINÊNCIA ARTICULAR","FÓVEA SUBMANDIBULAR"},
        {"LÂMINA PTERIGÓIDE"},
        {"HÂMULO PTERIGÓIDEO","PROCESSO ZIGOMÁTICO"},
        {"MEATO ACÚSTICO EXTERNO","PROCESSO MASTÓIDE","BORDA LATERAL DA ÓRBITA"},
        {"FORAME INFRAORBITÁRIO","CANAL INFRAORBITÁRIO"},
        {"SEIO MAXILAR"},
        {"CONCHA NASAL INFERIOR"},
        {"FORAME INCISIVO","PROCESSO CORONÓIDE DA MANDÍBULA","VÉRTEBRAS CERVICAIS"},
        {"CANAL DA MANDÍBULA"},
        {"LINHA OBLÍQUA","OSSO HIÓIDE"}};
    int totalacertos[] = new int[13];
    
    //vetores com labels e imagens
    ImageView imgs[] = new ImageView[6];
    Text labels[] = new Text[6];
    Text labelsR[] = new Text[6];
    //vetor com as respostas informadas
    String res [] = new String[6];
    
    //passar maouser na imagem 1
    public void Passar1() {
        if(!caixa.isVisible()){
            MostrarCaixa(i1);
            this.labelc = l1;
            posicao = 0;
        }
    }
    //passar maouser na imagem 2
    public void Passar2() {
        if(!caixa.isVisible()){
            MostrarCaixa(i2);
            this.labelc = l2;
            posicao = 1;
        }
    }
    //passar maouser na imagem 3
    public void Passar3() {
        if(!caixa.isVisible()){
            MostrarCaixa(i3);
            this.labelc = l3;
            posicao = 2;
        }
    }
    //passar maouser na imagem 4
    public void Passar4() {
        if(!caixa.isVisible()){
            MostrarCaixa(i4);
            this.labelc = l4;
            posicao = 3;
        }
    }
    //passar maouser na imagem 5
    public void Passar5() {
        if(!caixa.isVisible()){
            MostrarCaixa(i5);
            this.labelc = l5;
            posicao = 4;
        }
    }
    //passar maouser na imagem 6
    public void Passar6() {
        if(!caixa.isVisible()){
            MostrarCaixa(i6);
            this.labelc = l6;
            posicao = 5;
        }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
        Date d = new Date();
        int h = Integer.parseInt(""+d.getHours());
        if(h>6 && h<12){
            msg.setText("Bom dia "+LoginControle.Pnome+"!");
        }else{
            if(h>11 && h<18){
                msg.setText("Boa Tarde "+LoginControle.Pnome+"!");
            }else{
                if(h>17){
                    msg.setText("Boa Noite "+LoginControle.Pnome+"!");
                }else{
                    msg.setText("Boa Madru "+LoginControle.Pnome+"! ☾⋆");
                }
            }
        }
        //inicia alguns elementos invisiveis
        caixa.setVisible(false);
        paned.setVisible(false);
        fechard.setVisible(false);
        //inicializa vetor das respostas
        for (int i=0;i<res.length;i++)
            res[i]="";
        //Efeito para sombreamento das respostas
        DropShadow e = new DropShadow(BlurType.THREE_PASS_BOX, Color.BLACK, 1.88, 0.70, 0, 0);
        e.setHeight(4.5);
        e.setWidth(5);
        
        
        //inicaliza vetor com as respostas corretas a serem mostrados para o usuário
        for(int i=0;i<labels.length;i++){
            labelsR[i] = new Text();
            pane.getChildren().add(labelsR[i]);
            labelsR[i].setFill(Color.web("#1fd019"));
            labelsR[i].setFont(new Font("Times New Roman", 20));
            labelsR[i].setVisible(false);
            labelsR[i].setEffect(e);
        }
        
        /*for (int i=0;i<pane.getChildren().size();i++)
            System.out.println(pane.getChildren().get(i).getId());*/
        //deixa o botãode passar desabilitado
        proximo.setDisable(true);
        btrespostas.setDisable(true);
    
    }
    
    public void setApp(Controles.Tela applicatio) {
        this.application = application;
        
         Panei.getChildren().clear();
            FXMLLoader lo = new FXMLLoader(getClass().getResource("/Telas/T0.fxml"));
            try {
                Node  proj = (Node) lo.load();
                
                Panei.getChildren().add(proj);
            } catch (IOException ex) {
                Logger.getLogger(PrincipalControle.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
    
    //metodo para mostrar a caixa abaixo do icone
    private void MostrarCaixa(ImageView im){
            caixa.setVisible(true);
            campo.requestFocus();
            caixa.layoutXProperty().setValue(im.getLayoutX()+26);
            caixa.layoutYProperty().setValue(im.getLayoutY()+im.getFitHeight()+5+81);
            double xm = caixa.getLayoutX()+caixa.getPrefWidth();
            double ym = caixa.getLayoutY()+caixa.getPrefHeight();
            if(xm>1035){
                caixa.layoutXProperty().setValue(1020-caixa.getPrefWidth());
            }
            if(ym>650){
                caixa.layoutYProperty().setValue(645-caixa.getPrefHeight());
            }
    }
    
    //fechar a caixa
    public void EsconderCaixa(){
        caixa.setVisible(false);
        campo.setText("");
    }
    
    //botão para salvar o texto informado
    public void SalvarTxt(){
        labelc.setText(campo.getText());
        labelc.setVisible(true);
        res[posicao] = campo.getText();
        EsconderCaixa();
        campo.setText("");
    }
    
    //evento de clicar para salvar com ENTER
    public void clicar(KeyEvent evt){
        if(evt.getCode()==KeyCode.ENTER){
            SalvarTxt();
        }
    }
    
    //para limpar o texto do label
    public void LimparTxt(){
        labelc.setText("");
        EsconderCaixa();
    }
    
    //para verificar se as marcações estão corretas
    public void Verificar(){
        EsconderCaixa();
        int contacertos=0;
        for (int i=0; i<resc[tela].length; i++){
            
            if((removerAcentos(res[i]).equalsIgnoreCase(removerAcentos(resc[tela][i])))||(verificaSinonimos(removerAcentos(res[i]), resc[tela][i]))){
                //acertou
                EfeitoVerificar(labels[i], imgs[i], 1);
                acertos++;
                contacertos++;
            }else{
                EfeitoVerificar(labels[i], imgs[i], 0);
                erros++;
            }
            
        } 
        acertosL.setText(acertos+"");
        totalacertos[tela]=contacertos;
        errosL.setText(erros+"");
        proximo.setDisable(false);
        btrespostas.setDisable(false);
        verificar.setDisable(true);
        //insere no texto de desempenho os acertos da imagem
        textod.setText(textod.getText()+"\n Imagem "+(tela+1)+": "+contacertos+"/"+resc[tela].length);
    }
    //metodo para mostrar as respostas corretas
    public void MostrarC(){
        for(int i=0;i<labels.length;i++){
            if(labels[i] != null){
                labelsR[i].setText(resc[tela][i]);
                labelsR[i].setLayoutX(labels[i].getLayoutX());
                labelsR[i].setLayoutY(labels[i].getLayoutY()+Panei.getLayoutY()+labels[i].layoutBoundsProperty().getValue().getHeight()+10);
                labelsR[i].setVisible(true);
            }
        }
       // System.out.println("X= "+labels[0].get);
       // System.out.println("Y= "+labels[0].getScaleY());
    }
    //metodo para esconder as respostas corretas
    public void EsconderC(){
        for(int i=0;i<labelsR.length;i++){
           labelsR[i].setVisible(false);
        }
    }
    
    //metodo para mostrar a caixa com as informações de desempenho
    public void MostrarDesempenho(){
        paned.setVisible(true);
        fechard.setVisible(true);
    }
    
    //metodo para fehcar a caixa de desempenho
    public void FecharDesempenho(){
        paned.setVisible(false);
        fechard.setVisible(false);
    }
    
    //metodo para comparar com os sinônimos das estruturas
    public boolean verificaSinonimos(String est_infor, String est_corr){
        boolean retorno=false;
        switch(est_corr){
            case "FORAME MENTUAL":
                retorno = (est_infor.equalsIgnoreCase("Forame mentoniano"));
                break;
            case "FISSURA PTERIGOPALATINA":
                retorno = (est_infor.equalsIgnoreCase("Fossa pterigopalatina")) || (est_infor.equalsIgnoreCase("Fossa pterigomaxilar")) || 
                        (est_infor.equalsIgnoreCase("Fissura pterigomaxilar"));
                break;
            case "TÚBER DA MAXILA":
                retorno = (est_infor.equalsIgnoreCase(removerAcentos("Túber maxilar"))) || (est_infor.equalsIgnoreCase(removerAcentos("Eminência maxilar")));
                break;
            case "INCISURA SIGMÓIDE":
                retorno = (est_infor.equalsIgnoreCase(removerAcentos("Incisura da mandíbula"))) || (est_infor.equalsIgnoreCase("Incisura mandibular")) || 
                        (est_infor.equalsIgnoreCase("Chafrandura sigmoide"));
                break;
            case "EXTENSÃO ALVEOLAR DO SEIO MAXILAR":
                retorno = (est_infor.equalsIgnoreCase(removerAcentos("Extensão do seio maxilar")));
                break;
            case "CÔNDILO DA MANDÍBULA":
                retorno = (est_infor.equalsIgnoreCase(removerAcentos("Côndilo mandibular"))) || (est_infor.equalsIgnoreCase(removerAcentos("Cabeça da mandíbula")));
                break;
            case "ESPINHA NASAL ANTERIOR":
                retorno = (est_infor.equalsIgnoreCase("Espinha nasal anterior da maxila")) || (est_infor.equalsIgnoreCase("Espinha nasal da maxila")) || 
                        (est_infor.equalsIgnoreCase("Espinha nasal"));
                break;
            case "LINHA OBLÍQUA":
                retorno = (est_infor.equalsIgnoreCase(removerAcentos("Linha oblíqua externa")));
                break;
            case "BASE DA MANDÍBULA":
                retorno = (est_infor.equalsIgnoreCase("Base mandibular"));
                break;
            case "EMINÊNCIA ARTICULAR":
                retorno = (est_infor.equalsIgnoreCase(removerAcentos("Tubérculo articular")));
                break;
            case "SEIO MAXILAR":
                retorno = (est_infor.equalsIgnoreCase("Seio da maxila"));
                break;
            case "PROCESSO CORONÓIDE DA MANDÍBULA":
                retorno = (est_infor.equalsIgnoreCase(removerAcentos("Processo coronóide")));
                break;
            case "CANAL DA MANDÍBULA":
                retorno = (est_infor.equalsIgnoreCase("Canal mandibular"));
                break;   
        }
        
        return retorno;
    }
    
    public static String removerAcentos(String str) {
        return Normalizer.normalize(str, Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "");
    }

    
    //efeito para caso as respostas estejam certas ou erradas, 1=certa e 0=errada
    private void EfeitoVerificar(Text texto, ImageView img, int tipo){
        //Cria efeito
        DropShadow ds = new DropShadow();
        ds.setWidth(40.83);
        ds.setHeight(40.83);
        ds.setRadius(25.42);
        //caso esteje certo
        if(tipo==1){
            texto.setFill(Color.web("#07ff00"));
            ds.setColor(Color.web("#07ff00"));
        }else{
            ds.setColor(Color.web("#ff0000"));
            texto.setFill(Color.web("#ff0000"));
          
            //efeito de fazer o texto piscar, chamando a atênção do usuário
            final Timeline timeline = new Timeline();
            timeline.setCycleCount(6);
            timeline.setAutoReverse(true);
            final KeyValue kv = new KeyValue(texto.opacityProperty(), 0);
            final KeyFrame kf = new KeyFrame(Duration.millis(600), kv);
 
            timeline.getKeyFrames().add(kf);
            timeline.play();
    
        }
        img.setEffect(ds);
    }
    
    //Para esconder a caixa quando clicar em algum lugar da janela
    public void ClicarJanela(){
        EsconderCaixa();
    }
    
    //Para passar a imagem
    public void Proxima(){
        EsconderCaixa();
        tela++;
        verificar.setDisable(false);
        NovaIamgem(tela);
        proximo.setDisable(true);
        btrespostas.setDisable(true);
        EsconderC();
    }
    
    //metodo para mostrar a janela de ajuda
    public void Ajuda(){
        try {
            new Ajudar().start(new Stage());
        } catch (Exception ex) {
            Logger.getLogger(PrincipalControle.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void NovaIamgem(int valor){
        if(valor<13){
            Panei.getChildren().clear();
            FXMLLoader lo = new FXMLLoader(getClass().getResource("/Telas/T"+valor+".fxml"));
            try {
                Node  proj = (Node) lo.load();
                Panei.getChildren().add(proj);
            } catch (IOException ex) {
                Logger.getLogger(PrincipalControle.class.getName()).log(Level.SEVERE, null, ex);
            }
        }else{
            proximo.setDisable(true);
            btrespostas.setDisable(true);
            verificar.setDisable(true);
            //Terminou, mostra o resultado
            String msgf = "";
            for(int i=0; i<13; i++){
                msgf = msgf +"Questão "+(i+1)+" = N° de marcações: "+resc[i].length+" Acertos: "+totalacertos[i]+"§";
            }
            
            FinalizarControle.msg = msgf;
            try {
                Finalizar f = new Finalizar();
                FinalizarControle.acertos = acertos;
                FinalizarControle.erros = erros;
                f.start(new Stage());
                
            } catch (Exception ex) {
                Logger.getLogger(PrincipalControle.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    //metodo para insereir as imagens de informação de marcação e labels
    public void SetElementos(Text l1, Text l2, Text l3, Text l4, Text l5, Text l6, ImageView i1, ImageView i2, ImageView i3, ImageView i4, ImageView i5, ImageView i6){
        //envia os componentes do outro controle para os desse
        this.l1 = l1;
        this.l2 = l2;
        this.l3 = l3;
        this.l4 = l4;
        this.l5 = l5;
        this.l6 = l6;
        this.i1 = i1;
        this.i2 = i2;
        this.i3 = i3;
        this.i4 = i4;
        this.i5 = i5;
        this.i6 = i6;
        
        //preenche vetor das imagens
        imgs[0] = this.i1;
        imgs[1] = this.i2;
        imgs[2] = this.i3;
        imgs[3] = this.i4;
        imgs[4] = this.i5;
        imgs[5] = this.i6;
        //preenche vetor dos labels
        labels[0] = this.l1;
        labels[1] = this.l2;
        labels[2] = this.l3;
        labels[3] = this.l4;
        labels[4] = this.l5;
        labels[5] = this.l6;
        
        //deixa os elementos inviviveis e adiciona os eventos de passar o mauser sobre as imagens
        if(l1!=null){
            l1.setVisible(false);
            this.i1.setOnMouseEntered(new EventHandler<MouseEvent>() {

                @Override
                public void handle(MouseEvent event) {
                   Passar1();
                }
            });
        }
        if(l2!=null){
            l2.setVisible(false);
            this.i2.setOnMouseEntered(new EventHandler<MouseEvent>() {

                @Override
                public void handle(MouseEvent event) {
                   Passar2();
                }
            });
        }
        if(l3!=null){
            l3.setVisible(false);
            this.i3.setOnMouseEntered(new EventHandler<MouseEvent>() {

                @Override
                public void handle(MouseEvent event) {
                   Passar3();
                }
            });
        }
        if(l4!=null){
            l4.setVisible(false);
            this.i4.setOnMouseEntered(new EventHandler<MouseEvent>() {

                @Override
                public void handle(MouseEvent event) {
                   Passar4();
                }
            });
        }
        if(l5!=null){
            l5.setVisible(false);
            this.i5.setOnMouseEntered(new EventHandler<MouseEvent>() {

                @Override
                public void handle(MouseEvent event) {
                   Passar5();
                }
            });
        }
        if(l6!=null){
            l6.setVisible(false);
            this.i6.setOnMouseEntered(new EventHandler<MouseEvent>() {

                @Override
                public void handle(MouseEvent event) {
                   Passar6();
                }
            });
        }
    }
    
    //metodo para inserir
    
}
