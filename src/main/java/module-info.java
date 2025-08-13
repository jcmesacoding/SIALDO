module com.jumedev.sialdo {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires java.sql;
    requires java.desktop;
    requires java.net.http;
    requires com.fasterxml.jackson.databind;
    requires jbcrypt;

    opens com.jumedev.sialdo to javafx.fxml;
    exports com.jumedev.sialdo;
    exports com.jumedev.sialdo.controllers;
    opens com.jumedev.sialdo.controllers to javafx.fxml;
}