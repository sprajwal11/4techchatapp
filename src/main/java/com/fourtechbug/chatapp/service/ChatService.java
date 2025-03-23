package com.fourtechbug.chatapp.service;

import com.fourtechbug.chatapp.model.ChatMessage;

public interface ChatService {
    /**
     * Process and send a chat message
     * @param chatMessage The message to process
     * @return The processed message
     */
    ChatMessage processMessage(ChatMessage chatMessage);
    
    /**
     * Handle user join event
     * @param username The username of the joining user
     * @return The created join message
     */
    ChatMessage userJoined(String username);
    
    /**
     * Handle user leave event
     * @param username The username of the leaving user
     * @return The created leave message
     */
    ChatMessage userLeft(String username);
}