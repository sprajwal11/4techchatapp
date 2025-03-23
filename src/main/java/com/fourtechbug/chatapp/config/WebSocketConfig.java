package com.fourtechbug.chatapp.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        // Simple in-memory message broker
        registry.enableSimpleBroker("/topic");
        registry.setApplicationDestinationPrefixes("/app");

        // For production with RabbitMQ (uncomment when needed)
        /*
        registry.enableStompBrokerRelay("/topic")
            .setRelayHost("localhost")
            .setRelayPort(61613)
            .setClientLogin("guest")
            .setClientPasscode("guest");
        */
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/ws")
                // Replace wildcard with specific origins or use allowedOriginPatterns
                .setAllowedOriginPatterns("*")  // Instead of setAllowedOrigins("*")
                .withSockJS()
                .setDisconnectDelay(30 * 1000)
                .setHeartbeatTime(25000)
                .setClientLibraryUrl("https://cdn.jsdelivr.net/npm/sockjs-client@1/dist/sockjs.min.js");
    }
}