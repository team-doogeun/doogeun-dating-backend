package com.project.dugeun.domain.blindDate.domain;

import com.project.dugeun.domain.user.domain.User;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;

@Table(name = "blind_date")
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

    @Builder.Default
    @Column(name="result")
    private Boolean matched = false;

    // 초기화하고자 하는 칼럼



    public void setUser(User user1,User user2){
        this.user1 = user1;
        this.user2 = user2;

    }

}
