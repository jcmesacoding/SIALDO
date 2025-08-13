package com.jumedev.sialdo.services;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jumedev.sialdo.models.Cliente;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

public class ClienteService {

    private final String baseUrl = "http://localhost:8080/api/clientes";
    private final ObjectMapper mapper = new ObjectMapper();

    public List<Cliente> getClientes(String criterio) throws Exception {
        URL url = new URL(baseUrl + "?q=" + criterio);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");

        try (InputStream input = conn.getInputStream()) {
            return mapper.readValue(input, new TypeReference<List<Cliente>>() {});
        }
    }

    public void guardarCliente(Cliente cliente) throws Exception {
        URL url = new URL(baseUrl);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("POST");
        conn.setDoOutput(true);
        conn.setRequestProperty("Content-Type", "application/json");

        mapper.writeValue(conn.getOutputStream(), cliente);

        int responseCode = conn.getResponseCode();
        if (responseCode != 201 && responseCode != 200) {
            throw new RuntimeException("Error al guardar cliente. Código: " + responseCode);
        }
    }
}
