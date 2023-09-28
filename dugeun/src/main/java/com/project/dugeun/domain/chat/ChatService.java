package com.project.dugeun.domain.chat;

import com.project.dugeun.domain.finalMatch.dao.FinalMatchRepository;
import com.project.dugeun.domain.finalMatch.domain.FinalMatch;
import com.project.dugeun.domain.user.dao.UserRepository;
import com.project.dugeun.domain.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
     *
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
     *
     * @param roomId  채팅방 id
     * @param sender  보낸이
     * @param message 내용
     */
    public Chat createChat(Long roomId, String sender, String message) {
        Room room = roomRepository.findById(roomId).orElseThrow();  //방 찾기 -> 없는 방일 경우 여기서 예외처리
        return chatRepository.save(Chat.createChat(room, sender, message));
    }

    /**
     * 채팅방 채팅내용 불러오기
     *
     * @param roomId 채팅방 id
     */
    public List<Chat> findAllChatByRoomId(Long roomId) {
        return chatRepository.findAllByRoomId(roomId);
    }

    @Transactional
    public Long findAndMakeChatRoom(String userId, String anotherUserId) {
        User user1 = userRepository.findByUserId(userId);
        User user2 = userRepository.findByUserId(anotherUserId);

        FinalMatch finalMatch = finalMatchRepository.findByUser1AndUser2(user1, user2);
        FinalMatch finalMatch2 = finalMatchRepository.findByUser1AndUser2(user2, user1);
        if (finalMatch != null) {
            if (finalMatch.getRoom() != null) {
                return finalMatch.getRoom().getId();
            }
            else if(finalMatch.getRoom() == null){
                Room newRoom = new Room();
                newRoom.setFinalMatch(finalMatch);
                roomRepository.save(newRoom);
                finalMatch.setRoom(newRoom);
                finalMatchRepository.save(finalMatch);
                return newRoom.getId();
            }

        }
        if (finalMatch2 != null) {
            if (finalMatch2.getRoom() != null) {
                return finalMatch2.getRoom().getId();
            }
            else if(finalMatch2.getRoom() == null){
                Room newRoom = new Room();
                newRoom.setFinalMatch(finalMatch2);
                roomRepository.save(newRoom);
                finalMatch2.setRoom(newRoom);
                finalMatchRepository.save(finalMatch2);
                return newRoom.getId();
            }
        }
        return null;
    }
}