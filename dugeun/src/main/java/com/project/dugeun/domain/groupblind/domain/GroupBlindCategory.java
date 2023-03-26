package com.project.dugeun.domain.groupblind.domain;

public enum GroupBlindCategory {

    TWOTOTWO("2:2 미팅방"),
    THREETOTHREE("3:3 미팅방"),
    FOURTOFOUR("4:4 미팅방");

    private String krName;

    GroupBlindCategory(String krName) {
        this.krName = krName;
    }

    public String getKrName() {
        return krName;
    }
}
