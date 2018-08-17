package ua.kpi.tef2.model.entity;

import java.time.LocalDate;

public class Trip {

    private int id;

    private int userId;

    private int carId;

    private LocalDate date;

    private int cost;

    private String from;

    private String to;

    public Trip() {
    }

    public Trip(int id, int userId, int carId, LocalDate date, int cost, String from, String to) {
        this.id = id;
        this.userId = userId;
        this.carId = carId;
        this.date = date;
        this.cost = cost;
        this.from = from;
        this.to = to;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getCarId() {
        return carId;
    }

    public void setCarId(int carId) {
        this.carId = carId;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }
}
