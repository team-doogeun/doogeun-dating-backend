package com.project.dugeun.domain.chat.application;

import com.project.dugeun.domain.chat.dao.ChatRoomRepository;
import com.project.dugeun.domain.chat.domain.ChatMessage;
import com.project.dugeun.domain.chat.domain.ChatRoom;
import com.project.dugeun.domain.chat.domain.ChatRoomJoin;
import com.project.dugeun.domain.chat.dto.ChatRoomForm;
import com.project.dugeun.domain.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class ChatRoomService {
    private final ChatRoomRepository chatRoomRepository;
    private final ChatRoomJoinService chatRoomJoinService;

    public Optional<ChatRoom> findById(Long id) {
        return chatRoomRepository.findById(id);
    }

    public List<ChatRoomForm> setting(List<ChatRoomJoin> chatRoomJoins, User user) {
        List<ChatRoomForm> chatRooms = new ArrayList<>();
        for(ChatRoomJoin tmp : chatRoomJoins){
            ChatRoomForm chatRoomForm = new ChatRoomForm();
            ChatRoom chatRoom = tmp.getChatRoom();
            chatRoomForm.setId(chatRoom.getId());
            if(chatRoom.getMessages().size() != 0) {
                Collections.sort(chatRoom.getMessages(), new Comparator<ChatMessage>() {
                    @Override
                    public int compare(ChatMessage c1, ChatMessage c2) {
                        if(c1.getCreateDate().isAfter(c2.getCreateDate())){
                            return -1;
                        }
                        else{
                            return 1;
                        }
                    }
                });
                ChatMessage lastMessage = chatRoom.getMessages().get(0);
                chatRoomForm.makeChatRoomForm(lastMessage.getMessage(),chatRoomJoinService.findAnotherUser(chatRoom, user.getName()),lastMessage.getCreateDate());
                chatRooms.add(chatRoomForm);
            }
            else{
                chatRoomJoinService.delete(tmp);
            }
        }
        Collections.sort(chatRooms, new Comparator<ChatRoomForm>() {
            @Override
            public int compare(ChatRoomForm c1, ChatRoomForm c2) {
                if(c1.getTime().isAfter(c2.getTime())){
                    return -1;
                }
                else{
                    return 1;
                }
            }
        });
        return chatRooms;
    }

    public Optional<ChatRoom> findChatRoomByTwoUser(User user,User anotherUser){
       return chatRoomRepository.findByChatRoomJoinUserInAndChatRoomJoinUserIn(user,anotherUser);
    }
}