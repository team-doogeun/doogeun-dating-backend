package com.project.dugeun.domain.blindDate.domain;

import com.project.dugeun.domain.user.domain.User;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Table(name = "match")
@Getter
@Setter
@Entity
public class Match {

    @Id
    @Column(name="id",unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private User user1;


    @ManyToOne
    private User user2;

    @Column(name="score")
    private int compatibilityScore;
}
