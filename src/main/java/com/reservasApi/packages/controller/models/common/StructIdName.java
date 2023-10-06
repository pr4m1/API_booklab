package com.reservasApi.packages.controller.models.common;

import jakarta.validation.constraints.NotNull;

public class StructIdName {
    @NotNull
    private long id;
    private String name;

    public StructIdName(){

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
}
