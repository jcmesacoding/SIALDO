package com.jumedev.sialdo.controllers;

import com.jumedev.sialdo.db.DBConnection;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.io.FileWriter;
import java.io.IOException;
import java.sql.*;

public class ResumenController {

    @FXML
    private Button btnExportar;

    @FXML
    private Label lblEstadoExportacion;

    @FXML
    public void initialize() {
        btnExportar.setOnAction(e -> exportarDatos());
    }

    private void exportarDatos() {
        String sql = "SELECT id, nombre, ciudad, edad FROM clientes";

        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql);
             FileWriter writer = new FileWriter("clientes_exportados.csv")) {

            writer.write("ID,Nombre,Ciudad,Edad\n");

            while (rs.next()) {
                writer.write(rs.getInt("id") + "," +
                        rs.getString("nombre") + "," +
                        rs.getString("ciudad") + "," +
                        rs.getString("edad") + "\n");
            }

            lblEstadoExportacion.setText("✅ Exportación realizada con éxito.");

        } catch (SQLException | IOException ex) {
            ex.printStackTrace();
            lblEstadoExportacion.setText("❌ Error: " + ex.getMessage());
        }
    }
}
