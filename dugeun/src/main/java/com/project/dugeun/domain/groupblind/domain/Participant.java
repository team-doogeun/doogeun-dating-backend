package com.project.dugeun.domain.groupblind.domain;


import com.project.dugeun.domain.user.domain.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
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


    private GroupBlindRole Role; // 역할 ( 방장, 참가자 )


}
