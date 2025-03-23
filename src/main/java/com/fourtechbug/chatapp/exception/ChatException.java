package com.fourtechbug.chatapp.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ChatException extends RuntimeException {


//    private final int errorCode;

    public ChatException(String message) {
        super(message);
    }

    public ChatException(String message, Throwable cause) {
        super(message, cause);
    }

//    public ChatException(String message, int errorCode) {
//        super(message);
//        this.errorCode = errorCode;
//    }
//
//    public int getErrorCode() {
//        return errorCode;
//    }

}
