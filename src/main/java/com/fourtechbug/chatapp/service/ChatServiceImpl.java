package com.fourtechbug.chatapp.service;

import com.fourtechbug.chatapp.model.ChatMessage;
import com.fourtechbug.chatapp.util.MessageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Service;

@Service
public class ChatServiceImpl implements ChatService {

    @Autowired
    private SimpMessageSendingOperations messagingTemplate;
    
    @Autowired
    private MessageUtils messageUtils;

    @Override
    public ChatMessage processMessage(ChatMessage chatMessage) {
        ChatMessage enrichedMessage = null;
        if (chatMessage.getSender().equals("1")){
            // Add any message processing logic here (filtering, formatting, etc.)
            enrichedMessage = messageUtils.enrichMessage(chatMessage);
            // Send to all subscribed WebSocket clients on the /topic/public destination
            messagingTemplate.convertAndSend("/topic/public", enrichedMessage); 
        }
        else {
            new IllegalArgumentException("Sender does not match");
        }
        return enrichedMessage;
    }


    @Override
    public ChatMessage userJoined(String username) {
        ChatMessage message = new ChatMessage();
        message.setType(ChatMessage.MessageType.JOIN);
        message.setSender(username);
        message.setContent(username + " joined the chat!");
        return messageUtils.enrichMessage(message);
    }

    @Override
    public ChatMessage userLeft(String username) {
        ChatMessage message = new ChatMessage();
        message.setType(ChatMessage.MessageType.LEAVE);
        message.setSender(username);
        message.setContent(username + " left the chat!");
        return messageUtils.enrichMessage(message);
    }
}