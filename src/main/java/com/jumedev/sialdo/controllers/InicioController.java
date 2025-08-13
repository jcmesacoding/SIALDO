package com.jumedev.sialdo.controllers;

import com.jumedev.sialdo.models.SessionManager;
import com.jumedev.sialdo.models.Usuario;
import com.jumedev.sialdo.utils.SceneUtils;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;

public class InicioController {
    @FXML private StackPane mainContent;
    @FXML private Label lblUsuario;
    @FXML private Label lblFecha;
    @FXML private Button btnCerrarSesion;

    @FXML private MenuButton btnAlquiler;
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
        Usuario user = SessionManager.getCurrentUser();
        switch (user.getRol()) {
            case "ADMIN" -> loadContent("admin_content.fxml");
            case "SUPERVISOR" -> loadContent("supervisor_content.fxml");
            case "CLIENTE" -> loadContent("cliente_content.fxml");
        }
    }

    @FXML
    public void loadMisDatos() {
        loadContent("misdatos.fxml");
    }

//    @FXML
//    public void handleLogout() {
//        SessionManager.clearSession();
//        SceneUtils.loadScene("login.fxml", "Login");
//    }
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
        } catch (Exception e) {
            e.printStackTrace();
        }
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
    @FXML public void loadInmueble() { loadContent("inmueble.fxml"); }
    @FXML public void loadTipoAlquiler() { loadContent("alquiler.fxml"); }
    @FXML public void loadTipoMoneda() { loadContent("tipoDeMoneda.fxml"); }

    @FXML public void loadAlquiler() { loadContent("alquiler.fxml"); }
    @FXML public void loadHistorialAlquiler() { loadContent("historialAlquiler.fxml"); }
    @FXML public void loadRegistrar() { loadContent("contratoAlquiler.fxml"); }

    @FXML public void loadPagos() { loadContent("pagos.fxml"); }
    @FXML public void loadPagoAlquiler() { loadContent("pagoAlquiler.fxml"); }
    @FXML public void loadHistorialPagos() { loadContent("historialPagos.fxml"); }


    @FXML public void loadResumen() { loadContent("resumen.fxml"); }
    @FXML public void loadAcerca() { loadContent("acerca.fxml"); }
}
