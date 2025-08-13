package com.jumedev.sialdo;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class SialdoApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(SialdoApplication.class.getResource("login.fxml"));
        Scene scene = new Scene(fxmlLoader.load());

        Image icon = new Image(SialdoApplication.class.getResource("/images/image-sialdo.png").toExternalForm());
        stage.getIcons().add(icon);
        stage.setTitle("Sistema de Alquiler SIALDO");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}   