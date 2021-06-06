package project;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class Main extends Application{

    @FXML
    private ComboBox ComboBox1;
    @FXML
    private TextField ResultText, CiagText, SeedText, WielomianText;

    private int param;
    private String seeds, polynominals, texts, finalresult;

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("main.fxml"));
        primaryStage.setTitle("Crypto_BSK_2");
        primaryStage.setScene(new Scene(root, 600, 400));
        primaryStage.show();
    }

    @FXML
    public void onStart(){
        String options[] =
                { "B1. Synchronous Stream Cipher", "B2. Ciphertext Autokey" };
        ComboBox1.setItems(FXCollections.observableArrayList(options));
    }

    @FXML
    public void codeAction(){
        texts = CiagText.getText();
        seeds = SeedText.getText();
        polynominals = WielomianText.getText();
        int seed[] = new int[seeds.length()];
        int polynominal[] = new int[polynominals.length()];
        int text[] = new int [texts.length()];
        char seedc[], polynominalc[], textc[];
        int result[] = new int[texts.length()];
        textc = texts.toCharArray();
        polynominalc = polynominals.toCharArray();
        seedc = seeds.toCharArray();

        for(int i=0;i<seeds.length();i++){
            seed[i]=Integer.parseInt(String.valueOf(seedc[i]));
            polynominal[i]=Integer.parseInt(String.valueOf(polynominalc[i]));
        }
        for(int i=0;i<texts.length();i++){
            text[i]=Integer.parseInt(String.valueOf(textc[i]));
        }
        Generator generator = new Generator();

        switch(param){
            case 0:
                result = generator.run(texts.length(),seed,polynominal);
                finalresult="";
                for(int i=0;i<texts.length();i++){
                    finalresult += ((result[i]+text[i])%2);
                }
                ResultText.setText(finalresult);
                break;
            case 1:
                result = generator.run(text,seed,polynominal, true);
                finalresult="";
                for(int i=0;i<texts.length();i++){
                    finalresult += result[i];
                }
                ResultText.setText(finalresult);
                break;
        }
    }

    @FXML
    public void decodeAction(){
        texts = CiagText.getText();
        seeds = SeedText.getText();
        polynominals = WielomianText.getText();
        int seed[] = new int[seeds.length()];
        int polynominal[] = new int[polynominals.length()];
        int text[] = new int [texts.length()];
        char seedc[], polynominalc[], textc[];
        int result[] = new int[texts.length()];
        textc = texts.toCharArray();
        polynominalc = polynominals.toCharArray();
        seedc = seeds.toCharArray();

        for(int i=0;i<seeds.length();i++){
            seed[i]=Integer.parseInt(String.valueOf(seedc[i]));
            polynominal[i]=Integer.parseInt(String.valueOf(polynominalc[i]));
        }
        for(int i=0;i<texts.length();i++){
            text[i]=Integer.parseInt(String.valueOf(textc[i]));
        }
        Generator generator = new Generator();

        switch(param){
            case 0:
                result = generator.run(texts.length(),seed,polynominal);
                finalresult="";
                for(int i=0;i<texts.length();i++){
                    finalresult += ((result[i]+text[i])%2);
                }
                ResultText.setText(finalresult);
                break;
            case 1:
                result = generator.run(text,seed,polynominal, false);
                finalresult="";
                for(int i=0;i<texts.length();i++){
                    finalresult += result[i];
                }
                ResultText.setText(finalresult);
                break;
        }
    }

    @FXML
    public void onChange(){
        Object actually = ComboBox1.getValue();
        if(actually == "B1. Synchronous Stream Cipher"){
            param = 0;
        }
        if(actually == "B2. Ciphertext Autokey"){
            param = 1;
        }
    }

    public static void main(String[] args)
    {
        launch(args);
    }
}