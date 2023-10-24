package com.project.dugeun.domain.blindDate.domain;

import com.project.dugeun.domain.base.baseEntity.BaseEntity;
import com.project.dugeun.domain.user.domain.User;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Table(name = "blind_date")
@Getter
@Setter
@Entity
public class Match extends BaseEntity {

    @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private User user1;

    @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private User user2;

    @Column(name="score")
    private int compatibilityScore;

    @Builder.Default
    @Column(name="result")
    private Boolean matched = false;

    @Column(name = "introduced", columnDefinition = "TINYINT(1) default 0")
    private Boolean introduced ;


    public boolean isMatched() {
        return matched;
    }

    public boolean isIntroduced() {
        return introduced;
    }

}
