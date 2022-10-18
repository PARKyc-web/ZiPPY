package com.yedam.zippy.chat;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;


@CrossOrigin
@Configuration
@EnableWebSocketMessageBroker
public class ChatConfig implements WebSocketMessageBrokerConfigurer{  
  
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
//      registry.addEndpoint("/ws/chat").setAllowedOrigins("*").withSockJS();
      registry.addEndpoint("/ws/chat").setAllowedOriginPatterns("*").withSockJS();
    }
    
    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
      registry.enableSimpleBroker("/queue", "/topic");
      registry.setApplicationDestinationPrefixes("/app");
    }

  
}
