package com.fourtechbug.chatapp.controller;

import com.fourtechbug.chatapp.model.ChatMessage;
import com.fourtechbug.chatapp.service.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/messages")
public class MessageRestController {

    @Autowired
    private ChatService chatService;

    /**
     * Endpoint to send a message via HTTP POST
     * @param chatMessage The chat message to be sent
     * @return The processed chat message
     */
    @PostMapping("/send")
    public ChatMessage sendMessage(@RequestBody ChatMessage chatMessage) {
        return chatService.processMessage(chatMessage);
    }

    /**
     * Endpoint to handle user joining the chat via HTTP POST
     * @param username The username of the joining user
     * @return The message indicating the user joined
     */
    @PostMapping("/join")
    public ChatMessage addUser(@RequestParam String username) {
        return chatService.userJoined(username);
    }

    /**
     * Endpoint to handle user leaving the chat via HTTP POST
     * @param username The username of the leaving user
     * @return The message indicating the user left
     */
    @PostMapping("/leave")
    public ChatMessage leaveUser(@RequestParam String username) {
        return chatService.userLeft(username);
    }
}
