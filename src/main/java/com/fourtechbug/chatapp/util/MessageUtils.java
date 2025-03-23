package com.fourtechbug.chatapp.util;

import com.fourtechbug.chatapp.model.ChatMessage;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.UUID;

@Component
public class MessageUtils {

    /**
     * Enrich a message with additional information (ID, timestamp)
     * @param message The message to enrich
     * @return The enriched message
     */
    public ChatMessage enrichMessage(ChatMessage message) {
        if (message.getId() == null) {
            message.setId(UUID.randomUUID().toString());
        }
        
        if (message.getTimestamp() == null) {
            message.setTimestamp(LocalDateTime.now());
        }
        
        return message;
    }
    
    /**
     * Sanitize message content to prevent XSS attacks
     * @param content The content to sanitize
     * @return Sanitized content
     */
    public String sanitizeContent(String content) {
        if (content == null) {
            return null;
        }
        
        // Simple sanitization - replace HTML tags
        return content.replaceAll("<", "&lt;").replaceAll(">", "&gt;");
    }
}