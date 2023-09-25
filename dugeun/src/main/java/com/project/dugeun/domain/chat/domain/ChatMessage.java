package com.project.dugeun.domain.chat.domain;

import com.project.dugeun.domain.base.baseEntity.BaseEntity;
import com.project.dugeun.domain.user.domain.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ChatMessage extends BaseEntity {

    @Column(nullable = false)
    private String message;

    @ManyToOne
    @JoinColumn(name = "chat_room_id")
    private ChatRoom chatRoom;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User writer;

    public ChatMessage(String message, LocalDateTime now, ChatRoom chatRoom, User userByUserId) {

    }
}
