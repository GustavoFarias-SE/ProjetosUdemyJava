package com.example.projetocalculadoraimc;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
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
        //Etiquetas para os campos de entrada
        Label etiquetaPeso = new Label("Peso");
        Label etiquetaAltura = new Label("Altura");

        //Campos de texto para os dados
        TextField campoPeso = new TextField();
        campoPeso.setPromptText("Peso em Kg");

        TextField campoAltura = new TextField();
        campoAltura.setPromptText("Altura em metros");

        //Mostrar o resultado do IMC
        Label etiquetaResult = new Label();

        //Botão para calcular o IMC
        Button botaoCalcular = new Button("Calcular IMC");
        botaoCalcular.setOnAction(e -> {
            try {
                double peso = Double.parseDouble(campoPeso.getText().replace(',', '.'));
                double altura = Double.parseDouble(campoAltura.getText().replace(',', '.'));

                double imc = peso / (altura * altura);
                etiquetaResult.setText(String.format("Seu imc é de: %.2f", imc));
            } catch (NumberFormatException ex){
                etiquetaResult.setText("Por favor insira números válidos para peso e altura");
            }
        });

        //Layout Vertical
        VBox layout = new VBox(10, etiquetaPeso, campoPeso, etiquetaAltura, campoAltura, botaoCalcular, etiquetaResult);
        layout.setPadding(new Insets(10));
        layout.setAlignment(Pos.CENTER);

        //Cena e stage
        Scene cena = new Scene(layout, 300, 500);
        stage.setTitle("Calculadora IMC");
        stage.setScene(cena);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}