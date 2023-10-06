package com.reservasApi.packages.controller.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Access {
    @JsonProperty("access")
    private boolean access;

    public Access(boolean access) {
        this.access = access;
    }

    public boolean isAccess() {
        return access;
    }

    public void setAccess(boolean access) {
        this.access = access;
    }
}
