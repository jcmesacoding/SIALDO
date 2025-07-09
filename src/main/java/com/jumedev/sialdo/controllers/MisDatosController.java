package com.jumedev.sialdo.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class MisDatosController {

    @FXML private TextField txtNombre;
    @FXML private TextField txtCorreo;

    @FXML
    public void initialize() {
        // Carga simulada
        txtNombre.setText("Juan Carlos");
        txtCorreo.setText("juan@email.com");
    }

    @FXML
    public void guardarCambios() {
        System.out.println("Nombre: " + txtNombre.getText());
        System.out.println("Correo: " + txtCorreo.getText());
    }
}
