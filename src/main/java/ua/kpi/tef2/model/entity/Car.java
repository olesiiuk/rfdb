package ua.kpi.tef2.model.entity;

public class Car {

    private int id;

    private String model;

    private String brand;

    private String type;

    private boolean isAvailable;

    public Car() {
    }

    public Car(int id, String model, String brand, String type, boolean isAvailable) {
        this.id = id;
        this.model = model;
        this.brand = brand;
        this.type = type;
        this.isAvailable = isAvailable;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }
}
