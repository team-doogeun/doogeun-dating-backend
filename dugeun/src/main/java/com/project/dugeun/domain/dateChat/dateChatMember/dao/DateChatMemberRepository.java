package com.project.dugeun.domain.dateChat.dateChatMember.dao;

import com.project.dugeun.domain.dateChat.dateChatMember.domain.DateChatMember;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DateChatMemberRepository extends JpaRepository<DateChatMember,Long> {

    List<DateChatMember> findByDateChatRoomId(Long dateChatRoomId);

}
