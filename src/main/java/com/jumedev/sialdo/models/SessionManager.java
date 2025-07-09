package com.jumedev.sialdo.models;


public class SessionManager {
    private static Usuario currentUser;

    public static void setCurrentUser(Usuario user) {
        currentUser = user;
    }

    public static Usuario getCurrentUser() {
        return currentUser;
    }

    public static void clearSession() {
        currentUser = null;
    }

    public static void setUsuarioActual(Usuario usuario) {

    }
}
