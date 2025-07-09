package com.jumedev.sialdo.controllers;

import com.jumedev.sialdo.models.SessionManager;
import com.jumedev.sialdo.models.Usuario;
import com.jumedev.sialdo.utils.SceneUtils;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.fxml.FXMLLoader;

import java.util.List;
import java.util.Optional;

public class LoginController {

    @FXML private TextField usernameField;
    @FXML private PasswordField passwordField;
    @FXML private Label errorLabel;

    // Usuarios simulados (en producción vendrían de una base de datos)
    private final List<Usuario> usuarios = List.of(
            new Usuario("admin", "123", "ADMIN"),
            new Usuario("supervisor", "456", "SUPERVISOR"),
            new Usuario("cliente", "789", "CLIENTE")
    );

    @FXML
    public void handleLogin() {
        String user = usernameField.getText().trim();
        String pass = passwordField.getText().trim();

        Optional<Usuario> usuarioOpt = usuarios.stream()
                .filter(u -> u.getUsername().equals(user) && u.getPassword().equals(pass))
                .findFirst();

        if (usuarioOpt.isPresent()) {
            Usuario usuario = usuarioOpt.get();

            // Guardar usuario actual en sesión
            SessionManager.setCurrentUser(usuario);

            // Cargar layout común
            SceneUtils.loadScene("inicio.fxml", "Panel de " + usuario.getRol());

        } else {
            showError("Usuario o contraseña incorrectos.");
        }
    }

    private void showError(String message) {
        errorLabel.setText(message);
    }
}

