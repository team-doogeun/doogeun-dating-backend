//package com.project.dugeun.domain.groupblind.api;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.project.dugeun.domain.groupblind.application.GroupBlindService;
//import com.project.dugeun.domain.groupblind.domain.GroupBlindRoom;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//import org.springframework.web.socket.CloseStatus;
//import org.springframework.web.socket.TextMessage;
//import org.springframework.web.socket.WebSocketSession;
//import org.springframework.web.socket.handler.TextWebSocketHandler;
//
//import java.util.HashMap;
//import java.util.HashSet;
//import java.util.Map;
//import java.util.Set;
//
//@Component
//public class MyHandler extends TextWebSocketHandler {
//    private final GroupBlindService groupBlindService;
//
//    private final Map<Long, Set<WebSocketSession>> sessionMap = new HashMap<>();
//
//    public MyHandler(GroupBlindService groupBlindService) {
//        this.groupBlindService = groupBlindService;
//    }
//
//    @Override
//    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
//        Long meetingRoomId = (Long) session.getAttributes().get("meetingRoomId");
//        if (meetingRoomId != null) {
//            sessionMap.computeIfAbsent(meetingRoomId, k -> new HashSet<>()).add(session);
//        }
//    }
//
//    @Override
//    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
//        Long meetingRoomId = (Long) session.getAttributes().get("meetingRoomId");
//        if (meetingRoomId != null) {
//            sessionMap.get(meetingRoomId).remove(session);
//        }
//    }
//
//    @Override
//    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
//        ObjectMapper mapper = new ObjectMapper();
//        Message msg = mapper.readValue(message.getPayload(), Message.class);
//        if (msg.getType() == MessageType.JOIN) {
//            Long meetingRoomId = msg.getMeetingRoomId();
//            if (meetingRoomId != null) {
//                GroupBlindRoom groupBlindRoom = groupBlindService.getMeetingRoomById(meetingRoomId);
//                if (groupBlindRoom != null) {
//                    msg.setUsername((String) session.getAttributes().get("username"));
//                    sendMessageToMeetingRoom(meetingRoom, msg);
//                }
//            }
//        }
//    }
//
//    private void sendMessageToMeetingRoom(GroupBlindRoom groupBlindRoom, Message message) throws IOException {
//        Set<WebSocketSession> sessions = sessionMap.get(groupBlindRoom.getId());
//        if (sessions != null) {
//            for (WebSocketSession session : sessions) {
//                session.sendMessage(new TextMessage(new ObjectMapper().writeValueAsString(message)));
//            }
//        }
//    }
//}
