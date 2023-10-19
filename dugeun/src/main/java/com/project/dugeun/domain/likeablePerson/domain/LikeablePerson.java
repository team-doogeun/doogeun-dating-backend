package com.project.dugeun.domain.likeablePerson.domain;


import com.project.dugeun.domain.base.baseEntity.BaseEntity;
import com.project.dugeun.domain.user.domain.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Getter
@EntityListeners(AuditingEntityListener.class)
public class LikeablePerson extends BaseEntity {

    @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @Setter
    private User fromUser; // 호감을 표시한 사람

    @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @Setter
    private User toUser; // 호감을 받은 사람

    @CreatedDate
    private LocalDateTime createDate;


}
