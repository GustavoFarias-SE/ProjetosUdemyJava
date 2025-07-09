package com.example.projetoeditordetexto;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {

        TextArea areaEditavel = new TextArea();

        //Botao para salvar o texto
        Button botao = new Button("Salvar");
        botao.setOnAction(e -> salvarTexto(areaEditavel));

        //Barra de ferramentas
        ToolBar barraDeFerramentas = new ToolBar(botao);

        //Configuração layout
        BorderPane borderPane = new BorderPane();
        borderPane.setTop(barraDeFerramentas);
        borderPane.setCenter(areaEditavel);

        Scene scene = new Scene(borderPane, 800, 600);
        stage.setTitle("Editor de Texto!");
        stage.setScene(scene);
        stage.show();
    }

    private void salvarTexto(TextArea textArea){
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Salvar meu arquivo de texto");
        File file = fileChooser.showSaveDialog(null);
        if (file != null){
            try (PrintWriter printWriter = new PrintWriter(file)){
                printWriter.println(textArea.getText());
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }

    }
    public static void main(String[] args) {
        launch();
    }
}