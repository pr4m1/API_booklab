package com.reservasApi.packages.repository.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDateTime;

@Entity
@Table(name="booking")
public class BookingE {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private LocalDateTime dateStart;
    private LocalDateTime dateFinish;
    @ManyToOne
    @JoinColumn(name="idLab")
    private LabE lab;

    @ManyToOne
    @JoinColumn(name="idUser")
    private UserE user;

    public BookingE() {
    }

    public BookingE(LocalDateTime dateStart, LabE lab) {
        this.dateStart = dateStart;
        this.lab = lab;
    }

    public long getId() {
        return id;
    }
    public void setId(long id){this.id=id;}

    public LocalDateTime getDateStart() {
        return dateStart;
    }

    public void setDateStart(LocalDateTime dateStart) {
        this.dateStart = dateStart;
    }
    public LocalDateTime getDateFinish() {
        return dateFinish;
    }

    public void setDateFinish(LocalDateTime dateFinish) {
        this.dateFinish = dateFinish;
    }

    public LabE getLab() {
        return lab;
    }

    public void setLab(LabE lab) {
        this.lab = lab;
    }

    public UserE getUser() {
        return user;
    }

    public void setUser(UserE user) {
        this.user = user;
    }
}
