package com.example.projetomininavegadorweb;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        TextField campoUrl = new TextField();
        WebView navegador = new WebView();
        WebEngine motor = navegador.getEngine();

        //Carregar uma página na web quando o usuário apertar ENTER
        campoUrl.setOnAction(e -> motor.load(formatarUrl(campoUrl.getText())));

        VBox vBox = new VBox();
        vBox.getChildren().addAll(campoUrl, navegador);
        Scene scene = new Scene(vBox);

        stage.setTitle("Navegador Tavos");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }


    public String formatarUrl(String url) {
        if (!url.startsWith("http://") && !url.startsWith("https://")){
            url = "http://" + url;
        }
        return url;
    }
}

