package com.reservasApi.packages.controller.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.reservasApi.packages.controller.models.common.StructIdName;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public class Booking {
    private long id;
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    @JsonProperty("date")
    private LocalDateTime dateStart;
    @JsonProperty(value = "lab")
    @NotNull(message = "Select a lab!")
    private StructIdName lab;
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private String username;

    public Booking(){

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public LocalDateTime getDateStart() {
        return dateStart;
    }

    public void setDateStart(LocalDateTime dateStart) {
        this.dateStart = dateStart;
    }

    public StructIdName getLab() {
        return lab;
    }

    public void setLab(StructIdName lab) {
        this.lab = lab;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
