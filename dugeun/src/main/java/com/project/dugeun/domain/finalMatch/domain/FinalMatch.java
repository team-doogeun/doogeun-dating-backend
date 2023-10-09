package com.project.dugeun.domain.finalMatch.domain;

import com.project.dugeun.domain.base.baseEntity.BaseEntity;
import com.project.dugeun.domain.chat.domain.Room;
import com.project.dugeun.domain.user.domain.User;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

@Table(name="final_match")
@Getter
@Setter
@Entity
@EntityListeners(AuditingEntityListener.class)
public class FinalMatch extends BaseEntity {
    @ManyToOne(cascade = CascadeType.ALL)
    private User user1;

    @ManyToOne(cascade = CascadeType.ALL)
    private User user2;

    @CreatedDate
    private LocalDateTime createDate;

    @OneToOne(mappedBy = "finalMatch")
    private Room room;

    public void setUser(User user1,User user2){
        this.user1 = user1;
        this.user2 = user2;

    }
}
