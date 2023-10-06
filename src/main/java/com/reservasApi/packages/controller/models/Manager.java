package com.reservasApi.packages.controller.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;

public class Manager {
    private long id;
    @JsonProperty("name")
    private String name;
    @JsonProperty("duration")
    @NotNull(message = "Please enter duration")
    private int duration;

    @JsonProperty("numberTimeSlotsDay")
    @NotNull(message = "Please enter number of time slots per day")
    private int numberTimeSlotsDay;

    @JsonProperty("numberTimeSlotsWeek")
    @NotNull(message = "Please enter number of time slots per week")
    private int numberTimeSlotsWeek;

    @JsonProperty("numberTimeSlotsTotal")
    @NotNull(message = "Please enter number of total time slots")
    private int numberTimeSlotsTotal;

    /* - - CONSTRUCTORS - - */

    public Manager() {
    }

    public Manager(String name, int duration) {
        this.name = name;
        this.duration = duration;
    }
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public int getNumberTimeSlotsDay() {
        return numberTimeSlotsDay;
    }

    public void setNumberTimeSlotsDay(int numberTimeSlotsDay) {
        this.numberTimeSlotsDay = numberTimeSlotsDay;
    }

    public int getNumberTimeSlotsWeek() {
        return numberTimeSlotsWeek;
    }

    public void setNumberTimeSlotsWeek(int numberTimeSlotsWeek) {
        this.numberTimeSlotsWeek = numberTimeSlotsWeek;
    }

    public int getNumberTimeSlotsTotal() {
        return numberTimeSlotsTotal;
    }

    public void setNumberTimeSlotsTotal(int numberTimeSlotsTotal) {
        this.numberTimeSlotsTotal = numberTimeSlotsTotal;
    }

}
