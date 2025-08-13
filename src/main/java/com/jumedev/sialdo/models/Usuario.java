package com.jumedev.sialdo.models;

public class    Usuario {
    private int id;
    private String email;
    private String username; // en tu caso puede ser el email
    private String password; // normalmente null después del login por seguridad
    private String rol;
//    private String telefono;

    // Constructor con id (recomendado para sesión real)
    public Usuario(int id, String email, String username, String password, String rol) {
        this.id = id;
        this.email = email;
        this.username = username;
        this.password = password;
        this.rol = rol;
    }

    // Constructor antiguo para compatibilidad con mocks (sin id)
    public Usuario(String username, String password, String rol) {
        this.id = -1;
        this.username = username;
        this.password = password;
        this.rol = rol;
    }

    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }


}

