package com.fourtechbug.chatapp.controller;

import com.fourtechbug.chatapp.model.ChatMessage;
import com.fourtechbug.chatapp.service.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;

@Controller
public class ChatController {

    @Autowired
    private ChatService chatService;

    /**
     * Handle chat messages sent from clients
     */

    @MessageMapping("/chat.sendMessage")
    @SendTo("/topic/public")
    public ChatMessage sendMessage(@Payload ChatMessage chatMessage) {
        return chatService.processMessage(chatMessage);
    }

    /**
     * Handle user join events
     */
    @MessageMapping("/chat.addUser")
    @SendTo("/topic/public")
    public ChatMessage addUser(@Payload ChatMessage chatMessage, 
                               SimpMessageHeaderAccessor headerAccessor) {
        // Add username to WebSocket session attributes
        headerAccessor.getSessionAttributes().put("username", chatMessage.getSender());
        return chatService.userJoined(chatMessage.getSender());
    }
}