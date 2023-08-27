package com.project.dugeun.domain.dateChat.dateChatMessage.domain;

import com.project.dugeun.domain.base.baseEntity.BaseEntity;
import com.project.dugeun.domain.dateChat.daetChatRoom.domain.DateChatRoom;
import com.project.dugeun.domain.dateChat.dateChatMember.domain.DateChatMember;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

import static lombok.AccessLevel.PROTECTED;

@Getter
@NoArgsConstructor(access = PROTECTED)
@Entity
@SuperBuilder
public class DateChatMessage extends BaseEntity {

    private String content; // 내용

    @ManyToOne(fetch = FetchType.LAZY)
    private DateChatMember sender; // 작성자

    @ManyToOne(fetch = FetchType.LAZY)
    private DateChatRoom dateChatRoom; // 해당 채팅방 룸

    @Builder
    public DateChatMessage(String content, DateChatMember sender, DateChatRoom dateChatRoom)
    {
        this.content = content;
        this.sender = sender;
        this.dateChatRoom = dateChatRoom;
    }

    public static DateChatMessage create(String content, DateChatMember dateChatMember, DateChatRoom dateChatRoom)
    {

        return DateChatMessage.builder()
                .content(content)
                .sender(dateChatMember)
                .dateChatRoom(dateChatRoom)
                .build();
    }


}
