package com.jumedev.sialdo.controllers;

import com.jumedev.sialdo.models.Cliente;
import com.jumedev.sialdo.services.ClienteService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import javax.swing.*;

public class GestionUsuariosController{

    @FXML private TableView<Cliente> tablaClientes;
    @FXML private TableColumn<Cliente, Long> colId;
    @FXML private TableColumn<Cliente, String> colNombre;
    @FXML private TableColumn<Cliente, String> colCiudad;
    @FXML private TableColumn<Cliente, String> colEdad;

    @FXML private TextField txtBuscar;
    @FXML private TextField txtNombre;
    @FXML private TextField txtCiudad;
    @FXML private Button btnBuscar;
    @FXML private Button btnGuardar;

    private final ClienteService clienteService = new ClienteService();

    @FXML
    public void initialize() {
        colId.setCellValueFactory(cell -> new javafx.beans.property.SimpleLongProperty(cell.getValue().getId()).asObject());
        colNombre.setCellValueFactory(cell -> new javafx.beans.property.SimpleStringProperty(cell.getValue().getNombre()));
        colCiudad.setCellValueFactory(cell -> new javafx.beans.property.SimpleStringProperty(cell.getValue().getCiudad()));
    }

    @FXML
    public void buscarClientes() {
        try {
            String criterio = txtBuscar.getText();
            var lista = clienteService.getClientes(criterio);
            ObservableList<Cliente> datos = FXCollections.observableArrayList(lista);
            tablaClientes.setItems(datos);
        } catch (Exception e) {
            e.printStackTrace();
            mostrarAlerta("Error al buscar clientes.");
        }
    }

    @FXML
    public void guardarCliente() {
        try {
            Cliente nuevo = new Cliente();
            nuevo.setNombre(txtNombre.getText());
            nuevo.setCiudad(txtCiudad.getText());
            clienteService.guardarCliente(nuevo);
            mostrarAlerta("Cliente guardado exitosamente.");
            buscarClientes(); // refrescar
        } catch (Exception e) {
            e.printStackTrace();
            mostrarAlerta("Error al guardar cliente.");
        }
    }

    private void mostrarAlerta(String mensaje) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}