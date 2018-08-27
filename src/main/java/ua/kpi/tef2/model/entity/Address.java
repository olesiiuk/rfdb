package ua.kpi.tef2.model.entity;

public class Address {

    private int id;

    private String street;

    private int number;

    public Address() {
    }

    public Address(String street, int distance) {
        this.street = street;
        this.number = distance;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }
}
