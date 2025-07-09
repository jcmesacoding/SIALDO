package com.jumedev.sialdo.controllers;

import com.jumedev.sialdo.models.SessionManager;
import com.jumedev.sialdo.utils.SceneUtils;
import com.jumedev.sialdo.models.Usuario;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;

import java.io.IOException;
import java.time.LocalDate;

public class ClienteController {

    @FXML private StackPane mainContent;
    @FXML private Label lblUsuario;
    @FXML private Label lblFecha;

    @FXML
    public void initialize() {
        Usuario user = SessionManager.getCurrentUser();
        lblUsuario.setText("Usuario: " + user.getUsername());
        lblFecha.setText("Fecha: " + LocalDate.now());

        loadContent("cliente_content.fxml"); // Cargar vista por defecto
    }

    @FXML
    public void handleLogout() {
        SessionManager.clearSession();
        SceneUtils.loadScene("login.fxml", "Login");
    }

    @FXML
    public void loadInicio() {
        loadContent("cliente_content.fxml");
    }

    @FXML
    public void loadMisDatos() {
        loadContent("misdatos.fxml");
    }

    @FXML
    public void loadPagos() {
        loadContent("pagos.fxml");
    }

    @FXML
    public void loadResumen() {
        loadContent("resumen.fxml");
    }

    private void loadContent(String fxml) {
        try {
            Node content = FXMLLoader.load(getClass().getResource("/com/jumedev/sialdo/" + fxml));
            mainContent.getChildren().setAll(content);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
