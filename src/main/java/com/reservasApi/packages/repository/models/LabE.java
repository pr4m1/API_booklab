package com.reservasApi.packages.repository.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import java.util.List;

@Entity
@Table(name="lab")
public class LabE {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @NotBlank
    private String name;
    @ManyToOne
    @JoinColumn(name="idManager")
    private ManagerE manager;
    @OneToMany(mappedBy = "lab", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<BookingE> bookings;

    /* - - CONSTRUCTORS - - */

    public LabE() {
    }

    public LabE(String name, ManagerE manager) {
        this.name = name;
        this.manager = manager;
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
    @JsonIgnore
    public ManagerE getManager() {
        return manager;
    }

    public void setManager(ManagerE manager) {
        this.manager = manager;
    }

    public List<BookingE> getBookings() {
        return bookings;
    }

    public void setBookings(List<BookingE> bookings) {
        this.bookings = bookings;
    }
}
