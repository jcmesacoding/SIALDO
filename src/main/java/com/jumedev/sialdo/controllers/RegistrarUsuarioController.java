package com.jumedev.sialdo.controllers;

import com.jumedev.sialdo.db.DBConnection;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import org.mindrot.jbcrypt.BCrypt;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class RegistrarUsuarioController {

    @FXML private TextField emailField;
    @FXML private PasswordField passwordField;
    @FXML private PasswordField confirmPasswordField;
    @FXML private TextField usernameField;

    @FXML
    public void registrarUsuario() {
        String username = usernameField.getText().trim();
        String email = emailField.getText().trim();
        String pass = passwordField.getText().trim();
        String confirm = confirmPasswordField.getText().trim();

        if (username.isEmpty() || email.isEmpty() || pass.isEmpty()) {
            mostrarAlerta("Error", "Debe ingresar nombre, correo y contraseña", Alert.AlertType.ERROR);
            return;
        }

        if (!pass.equals(confirm)) {
            mostrarAlerta("Error", "Las contraseñas no coinciden", Alert.AlertType.ERROR);
            return;
        }

        // Asignar rol según email
        String rol;
        if (email.endsWith("@admin.com")) {
            rol = "ADMIN";
        } else if (email.endsWith("@supervisor.com")) {
            rol = "SUPERVISOR";
        } else {
            rol = "CLIENTE";
        }

        String hashed = BCrypt.hashpw(pass, BCrypt.gensalt());

        String sql = "INSERT INTO usuarios (username, email, password_hash, rol) VALUES (?, ?, ?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pst = conn.prepareStatement(sql)) {

            pst.setString(1, username);
            pst.setString(2, email);
            pst.setString(3, hashed);
            pst.setString(4, rol);

            int rows = pst.executeUpdate();
            if (rows > 0) {
                mostrarAlerta("Éxito", "Usuario registrado correctamente con rol: " + rol,
                        Alert.AlertType.INFORMATION);
            }

        } catch (SQLException e) {
            mostrarAlerta("Error", "No se pudo registrar: " + e.getMessage(), Alert.AlertType.ERROR);
            e.printStackTrace();
        }
    }

    private void mostrarAlerta(String titulo, String mensaje, Alert.AlertType tipo) {
        Alert alert = new Alert(tipo);
        alert.setTitle(titulo);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}
