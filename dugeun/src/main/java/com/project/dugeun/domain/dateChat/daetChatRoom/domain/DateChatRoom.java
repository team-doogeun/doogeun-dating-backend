package com.project.dugeun.domain.dateChat.daetChatRoom.domain;

import com.project.dugeun.domain.base.baseEntity.BaseEntity;
import com.project.dugeun.domain.blindDate.domain.Match;
import com.project.dugeun.domain.dateChat.dateChatMember.domain.DateChatMember;
import com.project.dugeun.domain.finalMatch.domain.FinalMatch;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.util.HashSet;
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
    private Set<DateChatMember> chatMembers = new HashSet<>();

    public static DateChatRoom create(FinalMatch finalMatch){

        return DateChatRoom.builder()
                .finalMatch(finalMatch)
                .build();
    }


}
