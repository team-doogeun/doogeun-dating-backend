package com.project.dugeun.domain.groupblind.domain;

import com.project.dugeun.domain.user.domain.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@SuperBuilder
public class Participant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @CreatedDate
    private LocalDateTime createdDate;

    @LastModifiedDate
    private LocalDateTime modifyDate;

    @ManyToOne
    private User user;

    @ManyToOne
    private GroupBlindRoom groupBlindRoom;

    @Enumerated(EnumType.STRING)
    private GroupBlindRole groupBlindRole;// 역할 ( 방장, 참가자 )
}