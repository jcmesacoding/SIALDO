package com.jumedev.sialdo.controllers;

import com.jumedev.sialdo.models.SessionManager;
import com.jumedev.sialdo.models.Usuario;
import com.jumedev.sialdo.utils.SceneUtils;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;

import java.io.IOException;
import java.time.LocalDate;

public class InicioController {
    @FXML private StackPane mainContent;
    @FXML private Label lblUsuario;
    @FXML private Label lblFecha;

    @FXML private Button btnAlquiler;
    @FXML private Button btnResumen;

    @FXML
    public void initialize() {
        Usuario user = SessionManager.getCurrentUser();
        lblUsuario.setText("Usuario: " + user.getUsername());
        lblFecha.setText("Fecha: " + LocalDate.now());

        // Control de acceso por rol
        if (user.getRol().equals("CLIENTE")) {
            btnAlquiler.setVisible(false);
            btnResumen.setVisible(false);
        }

        // Carga inicial
        switch (user.getRol()) {
            case "ADMIN" -> loadContent("admin_content.fxml");
            case "SUPERVISOR" -> loadContent("supervisor_content.fxml");
            case "CLIENTE" -> loadContent("cliente_content.fxml");
        }
    }

    @FXML
    public void loadInicio() {
        loadContent("inicio_content.fxml");
    }

    @FXML
    public void loadMisDatos() {
        loadContent("misdatos.fxml");
    }

    @FXML
    public void handleLogout() {
        SessionManager.clearSession();
        SceneUtils.loadScene("login.fxml", "Login");
    }

    private void loadContent(String fxml) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/jumedev/sialdo/" + fxml));
            Node content = loader.load();
            mainContent.getChildren().setAll(content);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML public void loadConfiguracion() { loadContent("configuracion.fxml"); }
    @FXML public void loadAlquiler() { loadContent("alquiler.fxml"); }
    @FXML public void loadPagos() { loadContent("pagos.fxml"); }
    @FXML public void loadResumen() { loadContent("resumen.fxml"); }
    @FXML public void loadAcerca() { loadContent("acerca.fxml"); }
}
