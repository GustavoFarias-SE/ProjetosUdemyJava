package com.example.projetorelogiodigital;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class HelloApplication extends Application {

    final DateTimeFormatter formatador = DateTimeFormatter.ofPattern("HH:mm:ss");

    @Override
    public void start(Stage stage) throws IOException {

        Label rotuloTempo = new Label();
        rotuloTempo.setStyle("-fx-font-size: 24pt; -fx-text-fill: blue;");

        //Criação do KeyFrame

        KeyFrame keyFrameAtualizar = new KeyFrame(Duration.ZERO, e -> {
            rotuloTempo.setText(LocalDateTime.now().format(formatador));
        });

        //Outro KeyFrame
        KeyFrame keyFrameintervalo = new KeyFrame(Duration.seconds(1));

        //Criação da timeLine e adição dos keyFrames
        Timeline relogio = new Timeline();
        relogio.getKeyFrames().addAll(keyFrameAtualizar, keyFrameintervalo);

        relogio.setCycleCount(Animation.INDEFINITE);
        relogio.play(); //Inciar a Timeline

        VBox layout = new VBox(rotuloTempo);
        layout.setAlignment(Pos.CENTER);
        layout.setStyle("-fx-background-color: black;");

        Scene cena = new Scene(layout, 210, 100);

        stage.setTitle("Relógio Digital");
        stage.setScene(cena);
        stage.show();

    }

    public static void main(String[] args) {
        launch();
    }
}