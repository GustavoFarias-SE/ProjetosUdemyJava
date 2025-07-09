package com.example.projetolistadecompras;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ProjetoListaDeCompras extends Application {

    //Criação das variaveis de lista
    private ArrayList<String> listaDeCompras = new ArrayList<>();
    private ListView<String> listaVisualizavel = new ListView<>();

    @Override
    public void start(Stage stage) throws IOException {
        stage.setTitle("Lista De Compras");

        TextField textFieldDescricaoItem = new TextField();
        Button botaoAdicionar = new Button("Adicionar");
        Button botaoExportar = new Button("Exportar lista");

        Label labelAdicionar = new Label("Digite o item que deseja adicionar:");
        Label labelListaDeCompras = new Label("Lista de compras:");

        //Criação da ObservableList a partir da lista de compras
        ObservableList<String> observableListaDeCompras = FXCollections.observableArrayList(listaDeCompras);
        listaVisualizavel.setItems(observableListaDeCompras);

        VBox vBox = new VBox();
        vBox.getChildren().addAll(labelAdicionar, textFieldDescricaoItem, botaoAdicionar);
        vBox.getChildren().addAll(labelListaDeCompras, listaVisualizavel, botaoExportar);
        vBox.setSpacing(10);
        vBox.setPadding(new Insets(10)); //margens internas

        botaoAdicionar.setOnAction(e -> {
            String item = textFieldDescricaoItem.getText();
            if (!item.isEmpty()){
                listaDeCompras.add(item);
                listaVisualizavel.getItems().add(item);
                textFieldDescricaoItem.clear(); //Campo de texto é limpo
            }
        });

        botaoExportar.setOnAction(e -> {
            try {
                File arquivo = new File("listaDeCompras.txt");
                PrintWriter writer = new PrintWriter(arquivo);
                for (String item : listaDeCompras){
                    writer.println(item);
                }
                writer.close();
            } catch (Exception ex) {
                ex.getMessage();
            }
        });

        Scene scene = new Scene(vBox, 350, 300);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}