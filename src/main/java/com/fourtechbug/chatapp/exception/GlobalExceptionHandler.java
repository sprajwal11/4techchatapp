package com.fourtechbug.chatapp.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ControllerAdvice;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ChatException.class)
    public ResponseEntity<String> handleChatException(ChatException ex) {
        return ResponseEntity.badRequest().body(ex.getMessage());
    }
}
