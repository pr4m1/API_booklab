package com.reservasApi.packages.controller.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.reservasApi.packages.controller.models.common.StructIdName;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class Lab {
    private long id;
    @JsonProperty("name")
    @NotBlank
    private String name;
    @JsonProperty(value = "manager")
    @NotNull(message = "Select a manager!")
    private StructIdName manager;

    /* - - CONSTRUCTORS - - */

    public Lab() {
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
    public StructIdName getManager() {
        return manager;
    }

    public void setManager(StructIdName manager) {
        this.manager = manager;
    }

}
