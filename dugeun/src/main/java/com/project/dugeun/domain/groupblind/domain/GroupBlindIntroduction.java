package com.project.dugeun.domain.groupblind.domain;

import lombok.Getter;

import javax.persistence.Embeddable;
import javax.persistence.Entity;

@Embeddable
@Getter
public class GroupBlindIntroduction {

    private String introduction;

    public GroupBlindIntroduction() {
    }

    public GroupBlindIntroduction(String introduction) {
        this.introduction = introduction;
    }
}