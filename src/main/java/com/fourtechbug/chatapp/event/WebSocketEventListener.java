package com.fourtechbug.chatapp.event;

import com.fourtechbug.chatapp.model.ChatMessage;
import com.fourtechbug.chatapp.service.ChatService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionConnectedEvent;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

@Component
public class WebSocketEventListener {

    private static final Logger logger = LoggerFactory.getLogger(WebSocketEventListener.class);

    @Autowired
    private SimpMessageSendingOperations messagingTemplate;
    
    @Autowired
    private ChatService chatService;

    /**
     * Handle WebSocket connection events
     */
    @EventListener
    public void handleWebSocketConnectListener(SessionConnectedEvent event) {
        logger.info("Received a new web socket connection");
    }

    /**
     * Handle WebSocket disconnection events
     */
    @EventListener
    public void handleWebSocketDisconnectListener(SessionDisconnectEvent event) {
        StompHeaderAccessor headerAccessor = StompHeaderAccessor.wrap(event.getMessage());

        String username = (String) headerAccessor.getSessionAttributes().get("username");
        if (username != null) {
            logger.info("User Disconnected : " + username);
            
            // Inform others about the disconnection
            ChatMessage chatMessage = chatService.userLeft(username);
            messagingTemplate.convertAndSend("/topic/public", chatMessage);
        }
    }
}