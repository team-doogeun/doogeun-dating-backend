package com.project.dugeun.domain.chat;

import com.project.dugeun.domain.finalMatch.dao.FinalMatchRepository;
import com.project.dugeun.domain.finalMatch.domain.FinalMatch;
import com.project.dugeun.domain.user.dao.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ChatService {
    private final RoomRepository roomRepository;
    private final ChatRepository chatRepository;
    private final FinalMatchRepository finalMatchRepository;
    private final UserRepository userRepository;

    /**
     * 모든 채팅방 찾기
     */
    public List<Room> findAllRoom() {
        return roomRepository.findAll();
    }

    /**
     * 특정 채팅방 찾기
     * @param id room_id
     */
    public Room findRoomById(Long id) {
        return roomRepository.findById(id).orElseThrow();
    }

    /**
     * 채팅방 만들기
     * FinalMatch에 해당하는 채팅방 만듣고 해당 채팅방 아이디 return
     */
//    public Room createRoom(String name) {
//        return roomRepository.save(Room.createRoom(name));
//    }

    /////////////////

    /**
     * 채팅 생성
     * @param roomId 채팅방 id
     * @param sender 보낸이
     * @param message 내용
     */
    public Chat createChat(Long roomId, String sender, String message) {
        Room room = roomRepository.findById(roomId).orElseThrow();  //방 찾기 -> 없는 방일 경우 여기서 예외처리
        return chatRepository.save(Chat.createChat(room, sender, message));
    }

    /**
     * 채팅방 채팅내용 불러오기
     * @param roomId 채팅방 id
     */
    public List<Chat> findAllChatByRoomId(Long roomId) {
        return chatRepository.findAllByRoomId(roomId);
    }

    public Long findAndMakeChatRoom(String userId, String anotherUserId){
        FinalMatch finalMatch = finalMatchRepository.findByUser1AndUser2(userRepository.findByUserId(userId),userRepository.findByUserId(anotherUserId));
        if (finalMatch != null)
        {
            Room room = new Room();
            roomRepository.save(room);
            return room.getId();
        }
        return null;
}
}