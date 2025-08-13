package com.jumedev.sialdo.controllers;

import com.jumedev.sialdo.models.Cliente;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AdminController {

    @FXML
    private Button btnCerrarSesion;

    @FXML
    private Button btnGestionUsuarios;

    @FXML
    private VBox sideMenu;

    @FXML
    private AnchorPane mainContent; // Este es el panel central dinámico

//    @FXML
//    private TableView<Cliente> tablaClientes;
//
//    @FXML
//    private TableColumn<Cliente, String> colNombre;
//
//    @FXML
//    private TableColumn<Cliente, String> colCiudad;
//
//    @FXML
//    private TableColumn<Cliente, Integer> colEdad;

    @FXML
    private void handleLogout() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/jumedev/sialdo/login.fxml"));
            Scene scene = new Scene(loader.load());
            Stage stage = new Stage();
            stage.setTitle("Login");
            stage.setScene(scene);
            stage.show();
            ((Stage) btnCerrarSesion.getScene().getWindow()).close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void loadUsuarios() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/jumedev/sialdo/gestionUsuarios.fxml"));
            Parent view = loader.load();
            mainContent.getChildren().setAll(view);

            // Oculta el panel izquierdo (los botones)
            sideMenu.setVisible(false);

            // También puedes usar esto si quieres que desaparezca totalmente:
            // ((BorderPane) mainContent.getParent()).setLeft(null);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
