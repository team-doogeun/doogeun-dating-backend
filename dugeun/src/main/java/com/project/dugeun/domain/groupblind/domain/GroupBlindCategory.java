package com.project.dugeun.domain.groupblind.domain;

public enum GroupBlindCategory {

    TWOTOTWO("2:2"),
    THREETOTHREE("3:3"),
    FOURTOFOUR("4:4");

    private String krName;

    GroupBlindCategory(String krName) {
        this.krName = krName;
    }

    public String getKrName() {
        return krName;
    }
}
