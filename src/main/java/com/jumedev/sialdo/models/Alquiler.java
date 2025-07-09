package com.jumedev.sialdo.models;

import javafx.beans.property.*;

public class Alquiler {
    private final IntegerProperty id;
    private final StringProperty cliente;
    private final StringProperty item;
    private final DoubleProperty precio;

    public Alquiler(int id, String cliente, String item, double precio) {
        this.id = new SimpleIntegerProperty(id);
        this.cliente = new SimpleStringProperty(cliente);
        this.item = new SimpleStringProperty(item);
        this.precio = new SimpleDoubleProperty(precio);
    }

    public int getId() { return id.get(); }
    public String getCliente() { return cliente.get(); }
    public String getItem() { return item.get(); }
    public double getPrecio() { return precio.get(); }

    public IntegerProperty idProperty() { return id; }
    public StringProperty clienteProperty() { return cliente; }
    public StringProperty itemProperty() { return item; }
    public DoubleProperty precioProperty() { return precio; }
}
