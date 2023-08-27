package com.project.dugeun.domain.dateChat.daetChatRoom.domain;

import com.project.dugeun.domain.base.baseEntity.BaseEntity;
import com.project.dugeun.domain.blindDate.domain.Match;
import com.project.dugeun.domain.dateChat.dateChatMember.domain.DateChatMember;
import com.project.dugeun.domain.dateChat.dateChatMessage.domain.DateChatMessage;
import com.project.dugeun.domain.finalMatch.domain.FinalMatch;
import com.project.dugeun.domain.user.domain.User;
import com.project.dugeun.domain.user.domain.profile.category.CharacterType;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@RequiredArgsConstructor
@Entity
@SuperBuilder
public class DateChatRoom extends BaseEntity {

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST, orphanRemoval = true)
    private FinalMatch finalMatch;

    @OneToMany(mappedBy = "dateChatRoom", cascade = CascadeType.PERSIST, orphanRemoval = true)
    @Builder.Default
    private Set<DateChatMember> dateChatMembers = new HashSet<>();

    @OneToMany(mappedBy = "dateChatRoom", cascade = CascadeType.PERSIST, orphanRemoval = true)
    private List<DateChatMessage> dateChatMessages = new ArrayList<>();


    public static DateChatRoom create(FinalMatch finalMatch){

        return DateChatRoom.builder()
                .finalMatch(finalMatch)
                .id(finalMatch.getId()) // 해당 Finalmatch와 DatChatRoom의 id같도록 설정
                .build();
    }

    public void addChatUser(User user)
    {
        DateChatMember dateChatMember = DateChatMember.builder()
                .user(user)
                .dateChatRoom(this)
                .build();

        dateChatMembers.add(dateChatMember);
    }

    public boolean containsUser(String userId) {
        for (DateChatMember member : dateChatMembers) {
            if (member.getUser().getUserId().equals(userId)) {
                return true;
            }
        }
        return false;
    }


}
