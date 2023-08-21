package com.project.dugeun.domain.dateChat.dateChatMember.domain;

import com.project.dugeun.domain.base.baseEntity.BaseEntity;
import com.project.dugeun.domain.dateChat.daetChatRoom.domain.DateChatRoom;
import com.project.dugeun.domain.dateChat.dateChatMessage.domain.DateChatMessage;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import javax.persistence.*;
import java.lang.reflect.Member;
import java.util.ArrayList;
import java.util.List;

import static lombok.AccessLevel.PROTECTED;


@Getter
@Entity
@NoArgsConstructor(access = PROTECTED)
@SuperBuilder
public class DateChatMember extends BaseEntity
{

    @ManyToOne(fetch = FetchType.LAZY)
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    private DateChatRoom dateChatRoom;

    @OneToMany(mappedBy = "sender", cascade = CascadeType.PERSIST, orphanRemoval = true)
    private List<DateChatMessage> dateChatMessages = new ArrayList<>();

    @Builder
    public DateChatMember(Member member, DateChatRoom dateChatRoom){
        this.member = member;
        this.dateChatRoom = dateChatRoom;
    }

}
