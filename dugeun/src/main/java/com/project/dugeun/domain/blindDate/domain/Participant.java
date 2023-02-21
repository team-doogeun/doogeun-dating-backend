package com.project.dugeun.domain.blindDate.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.project.dugeun.domain.user.domain.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Entity
public class Participant extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToOne(fetch = FetchType.EAGER)
    @JsonIgnore
    private User user;

    private LocalDate nextMatchingDate;



}
