package com.jumedev.sialdo.controllers;

import com.jumedev.sialdo.db.DBConnection;
import com.jumedev.sialdo.models.SessionManager;
import com.jumedev.sialdo.models.Usuario;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.mindrot.jbcrypt.BCrypt;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginController {

    @FXML private TextField usernameField; // en la UI puede llamarse usernameField pero contendrá el email
    @FXML private PasswordField passwordField;
    @FXML private Label errorLabel;

    @FXML
    public void handleLogin() {
        String userInput = usernameField.getText().trim(); // email
        String passwordInput = passwordField.getText().trim();

        if (userInput.isEmpty() || passwordInput.isEmpty()) {
            showError("Ingresa usuario y contraseña");
            return;
        }

        // Consulta: usamos 'email' porque tu tabla tiene email y password_hash
        String sql = "SELECT id, email, username, password_hash, rol"
                + " FROM usuarios"
                + " WHERE email = ?"
                + " LIMIT 1";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pst = conn.prepareStatement(sql)) {

            pst.setString(1, userInput);

            try (ResultSet rs = pst.executeQuery()) {
                if (rs.next()) {
                    int id = rs.getInt("id");
                    String email = null;
                    try { email = rs.getString("email"); } catch (Exception ignore) {}
                    String hash = null;
                    try { hash = rs.getString("password_hash"); } catch (Exception ignore) {}
                    String rol = rs.getString("rol");
                    String username = rs.getString("username");

                    if (hash != null && BCrypt.checkpw(passwordInput, hash)) {
                        // Login correcto: almacenar en sesión (rol no obligatorio)
                        Usuario usuario = new Usuario(id, email, username,  null, rol);
                        SessionManager.setCurrentUser(usuario);

                        // Abrir la pantalla principal (adapta la ruta a tu proyecto si es necesario)
                        try {
                            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/jumedev/sialdo/inicio.fxml"));
                            javafx.scene.Parent root = loader.load();
                            Stage stage = new Stage();
                            stage.setTitle("Sialdo - Principal");
                            stage.setScene(new javafx.scene.Scene(root));
                            stage.show();

                            // Cerrar ventana de login
                            Stage thisStage = (Stage) usernameField.getScene().getWindow();
                            thisStage.close();
                        } catch (Exception e) {
                            e.printStackTrace();
                            showError("No se pudo abrir la ventana principal.");
                        }
                        return;
                    }
                }
            }

            // Si llega aquí: usuario no encontrado o contraseña inválida
            showError("Usuario o contraseña incorrectos.");

        } catch (SQLException ex) {
            ex.printStackTrace();
            showAlert("Error", "Error al conectar con la base de datos: " + ex.getMessage(), Alert.AlertType.ERROR);
        }
    }

    @FXML
    private void abrirRegistro() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/jumedev/sialdo/registrarUsuario.fxml"));
            Parent root = loader.load();

            Stage stage = new Stage();
            stage.setTitle("Registrar Usuario");
            stage.setScene(new Scene(root));
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.showAndWait();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void showError(String message) {
        if (errorLabel != null) errorLabel.setText(message);
    }

    private void showAlert(String title, String msg, Alert.AlertType type) {
        Alert a = new Alert(type);
        a.setTitle(title);
        a.setContentText(msg);
        a.showAndWait();
    }

}

