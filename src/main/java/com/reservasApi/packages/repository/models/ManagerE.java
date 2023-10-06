package com.reservasApi.packages.repository.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.List;

@Entity
@Table(name="manager")
public class ManagerE {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @NotBlank
    private String name;
    @NotNull(message = "Please enter duration")
    private int duration;
    @NotNull(message = "Please enter number of time slots per day")
    private int numberTimeSlotsDay;
    @NotNull(message = "Please enter number of time slots per week")
    private int numberTimeSlotsWeek;
    @NotNull(message = "Please enter number of total time slots")
    private int numberTimeSlotsTotal;
    @OneToMany(mappedBy = "manager", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<LabE> labs;

    /* - - CONSTRUCTORS - - */

    public ManagerE() {
    }

    public ManagerE(String name, int duration) {
        this.name = name;
        this.duration = duration;
    }

    /* - - GETTERS && SETTERS - - */

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

    public List<LabE> getLabs() {
        return labs;
    }

    public void setLabs(List<LabE> labES) {
        this.labs = labES;
    }
}
