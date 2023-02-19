package com.project.dugeun.model.domain.blindDate;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.project.dugeun.model.domain.BaseTimeEntity;
import com.project.dugeun.model.domain.user.User;
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
