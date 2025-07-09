package com.jumedev.sialdo.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class AdminController {
    @FXML
    private Button btnCerrarSesion;


    @FXML
    private void handleLogout() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/jumedev/sialdo/login.fxml"));
            Scene scene = new Scene(loader.load());
            Stage stage = new Stage();
            stage.setTitle("Login");
            stage.setScene(scene);
            stage.show();
            ((Stage) btnCerrarSesion.getScene().getWindow()).close(); // Cierra ventana actual
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
