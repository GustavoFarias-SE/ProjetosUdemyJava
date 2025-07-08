package com.example.geradordesenhasgui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        stage.setTitle("Gerado de senha");

        Label labelTamanhoSenha = new Label("Tamanho da senha");
        TextField campoTamanhoSenha = new TextField();
        campoTamanhoSenha.setText("8"); // Sugestão para o tamanho da senha

        Label labelSenhaGerada = new Label("Senha gerada");
        TextField campoSenhaGerada = new TextField();
        campoSenhaGerada.setEditable(false);
        campoSenhaGerada.setStyle("-fx-text-fill: cyan; -fx-background-color: black");

        Button botaoGerarSenha = new Button("Gerar Senha");

        botaoGerarSenha.setOnAction(e -> {
            int tamanhoSenha = Integer.parseInt(campoTamanhoSenha.getText()); //Resgata o tamanho desejado
            String senha = GeradorDeSenhas.gerarSenha(tamanhoSenha); //Gera a senha
            campoSenhaGerada.setText(senha); //Mostra a senha gerada para o usuário

        });

        VBox vBox = new VBox(labelTamanhoSenha, campoTamanhoSenha, botaoGerarSenha, labelSenhaGerada, campoSenhaGerada);
        vBox.setSpacing(10);
        vBox.setPadding(new Insets(10));

        Scene cena = new Scene(vBox, 300, 200);
        stage.setScene(cena);
        stage.show();

    }

    public static void main(String[] args) {
        launch();
    }


}