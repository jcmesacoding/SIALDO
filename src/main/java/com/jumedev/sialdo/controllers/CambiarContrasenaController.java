package com.jumedev.sialdo.controllers;

import com.jumedev.sialdo.db.DBConnection;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.stage.Stage;
import org.mindrot.jbcrypt.BCrypt;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CambiarContrasenaController {

    @FXML
    private PasswordField txtActual;
    @FXML
    private PasswordField txtNueva;
    @FXML
    private PasswordField txtConfirmar;

    private int usuarioId;

    public void setUsuarioId(int usuarioId) {
        this.usuarioId = usuarioId;
    }

    @FXML
    private void guardar() {
        String actual = txtActual.getText();
        String nueva = txtNueva.getText();
        String confirmar = txtConfirmar.getText();

        if (actual.isEmpty() || nueva.isEmpty() || confirmar.isEmpty()) {
            mostrarAlerta("Error", "Todos los campos son obligatorios.", Alert.AlertType.ERROR);
            return;
        }

        try (Connection conn = DBConnection.getConnection()) {
            // 1. Obtener el hash de la BD
            String sqlCheck = "SELECT password_hash FROM usuarios WHERE id = ?";
            String hashBD = null;

            try (PreparedStatement stmt = conn.prepareStatement(sqlCheck)) {
                stmt.setInt(1, usuarioId);
                try (ResultSet rs = stmt.executeQuery()) {
                    if (rs.next()) {
                        hashBD = rs.getString("password_hash");
                    } else {
                        mostrarAlerta("Error", "Usuario no encontrado.", Alert.AlertType.ERROR);
                        return;
                    }
                }
            }

            // 2. Validar contraseña actual con BCrypt
            if (!BCrypt.checkpw(actual, hashBD)) {
                mostrarAlerta("Error", "La contraseña actual no es correcta.", Alert.AlertType.ERROR);
                return;
            }

            // 3. Verificar que las nuevas coincidan
            if (!nueva.equals(confirmar)) {
                mostrarAlerta("Error", "Las contraseñas nuevas no coinciden.", Alert.AlertType.ERROR);
                return;
            }

            // 4. Hashear la nueva contraseña
            String nuevoHash = BCrypt.hashpw(nueva, BCrypt.gensalt());

            // 5. Actualizar en BD
            String sqlUpdate = "UPDATE usuarios SET password_hash = ? WHERE id = ?";
            try (PreparedStatement stmt = conn.prepareStatement(sqlUpdate)) {
                stmt.setString(1, nuevoHash);
                stmt.setInt(2, usuarioId);
                int filas = stmt.executeUpdate();

                if (filas > 0) {
                    mostrarAlerta("Éxito", "Contraseña actualizada correctamente.", Alert.AlertType.INFORMATION);
                    ((Stage) txtActual.getScene().getWindow()).close();
                } else {
                    mostrarAlerta("Error", "No se pudo actualizar la contraseña.", Alert.AlertType.ERROR);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
            mostrarAlerta("Error", "Error de conexión con la base de datos.", Alert.AlertType.ERROR);
        }
    }

    @FXML
    private void cancelar() {
        ((Stage) txtActual.getScene().getWindow()).close();
    }

    private void mostrarAlerta(String titulo, String mensaje, Alert.AlertType tipo) {
        Alert alert = new Alert(tipo);
        alert.setTitle(titulo);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}
