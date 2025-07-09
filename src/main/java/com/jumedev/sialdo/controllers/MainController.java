package com.jumedev.sialdo.controllers;

import com.jumedev.sialdo.db.DBConnection;
import com.jumedev.sialdo.models.Alquiler;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.sql.*;

public class MainController {

    @FXML private TextField clienteField, itemField, precioField;
    @FXML private TableView<Alquiler> tablaAlquileres;
    @FXML private TableColumn<Alquiler, Integer> colId;
    @FXML private TableColumn<Alquiler, String> colCliente, colItem;
    @FXML private TableColumn<Alquiler, Double> colPrecio;

    private ObservableList<Alquiler> listaAlquileres = FXCollections.observableArrayList();
    private Alquiler alquilerSeleccionado = null;

    @FXML
    public void initialize() {
        colId.setCellValueFactory(cellData -> cellData.getValue().idProperty().asObject());
        colCliente.setCellValueFactory(cellData -> cellData.getValue().clienteProperty());
        colItem.setCellValueFactory(cellData -> cellData.getValue().itemProperty());
        colPrecio.setCellValueFactory(cellData -> cellData.getValue().precioProperty().asObject());

        tablaAlquileres.setItems(listaAlquileres);
        cargarAlquileres();

        tablaAlquileres.setOnMouseClicked(event -> {
            alquilerSeleccionado = tablaAlquileres.getSelectionModel().getSelectedItem();
            if (alquilerSeleccionado != null) {
                clienteField.setText(alquilerSeleccionado.getCliente());
                itemField.setText(alquilerSeleccionado.getItem());
                precioField.setText(String.valueOf(alquilerSeleccionado.getPrecio()));
            }
        });
    }

    public void cargarAlquileres() {
        listaAlquileres.clear();
        try (Connection conn = DBConnection.getConnection()) {
            String sql = "SELECT * FROM alquileres";
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                listaAlquileres.add(new Alquiler(
                        rs.getInt("id"),
                        rs.getString("cliente"),
                        rs.getString("item"),
                        rs.getDouble("precio")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void agregarAlquiler() {
        try (Connection conn = DBConnection.getConnection()) {
            String sql = "INSERT INTO alquileres (cliente, item, precio) VALUES (?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, clienteField.getText());
            stmt.setString(2, itemField.getText());
            stmt.setDouble(3, Double.parseDouble(precioField.getText()));
            stmt.executeUpdate();
            cargarAlquileres();
            limpiarCampos();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void eliminarAlquiler() {
        if (alquilerSeleccionado == null) return;

        try (Connection conn = DBConnection.getConnection()) {
            String sql = "DELETE FROM alquileres WHERE id = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, alquilerSeleccionado.getId());
            stmt.executeUpdate();
            cargarAlquileres();
            limpiarCampos();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void editarAlquiler() {
        if (alquilerSeleccionado == null) return;

        try (Connection conn = DBConnection.getConnection()) {
            String sql = "UPDATE alquileres SET cliente=?, item=?, precio=? WHERE id=?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, clienteField.getText());
            stmt.setString(2, itemField.getText());
            stmt.setDouble(3, Double.parseDouble(precioField.getText()));
            stmt.setInt(4, alquilerSeleccionado.getId());
            stmt.executeUpdate();
            cargarAlquileres();
            limpiarCampos();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void limpiarCampos() {
        clienteField.clear();
        itemField.clear();
        precioField.clear();
        alquilerSeleccionado = null;
    }
}
