package com.jumedev.sialdo.controllers;

import com.jumedev.sialdo.db.DBConnection;
import com.jumedev.sialdo.models.SessionManager;
import com.jumedev.sialdo.models.Usuario;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class MisDatosController {

    @FXML private TextField txtNombre;
    @FXML private TextField txtCorreo;
    @FXML private TextField txtCorreo1; // teléfono, si lo tienes en la DB

    private Usuario currentUser;

    @FXML
    public void initialize() {
        // Obtener usuario en sesión
        currentUser = SessionManager.getCurrentUser();
        if (currentUser != null) {
            txtNombre.setText(currentUser.getUsername());
            txtCorreo.setText(currentUser.getEmail());
//             txtCorreo1.setText(currentUser.getTelefono());
        }
    }

    @FXML
    private void guardarCambios() {
        if (currentUser == null) return;

        String nuevoNombre = txtNombre.getText().trim();
        String nuevoCorreo = txtCorreo.getText().trim();
        // String nuevoTelefono = txtCorreo1.getText().trim();

        if (nuevoNombre.isEmpty() || nuevoCorreo.isEmpty()) {
            mostrarAlerta("Error", "El nombre y correo no pueden estar vacíos", Alert.AlertType.ERROR);
            return;
        }

        String sql = "UPDATE usuarios SET username = ?, email = ? WHERE id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pst = conn.prepareStatement(sql)) {

            pst.setString(1, nuevoNombre);
            pst.setString(2, nuevoCorreo);
            pst.setInt(3, currentUser.getId());

            int filas = pst.executeUpdate();
            if (filas > 0) {
                mostrarAlerta("Éxito", "Datos actualizados correctamente", Alert.AlertType.INFORMATION);

                // Actualizar la sesión con los nuevos datos
                currentUser.setUsername(nuevoNombre);
                currentUser.setEmail(nuevoCorreo);
            } else {
                mostrarAlerta("Error", "No se pudieron actualizar los datos", Alert.AlertType.ERROR);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            mostrarAlerta("Error", "Error al actualizar datos: " + e.getMessage(), Alert.AlertType.ERROR);
        }
    }

    @FXML
    private void cambiarContrasena() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/jumedev/sialdo/cambiarContrasena.fxml"));
            AnchorPane root = loader.load();

            // Obtener el controlador y pasarle el ID del usuario en sesión
            CambiarContrasenaController controller = loader.getController();
            controller.setUsuarioId(currentUser.getId());

            Stage stage = new Stage();
            stage.setTitle("Cambiar Contraseña");
            stage.setScene(new Scene(root));
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.showAndWait();

        } catch (IOException e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("No se pudo abrir la ventana de cambiar contraseña: " + e.getMessage());
            alert.showAndWait();
        }
    }

    private void mostrarAlerta(String titulo, String mensaje, Alert.AlertType tipo) {
        Alert alert = new Alert(tipo);
        alert.setTitle(titulo);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}
