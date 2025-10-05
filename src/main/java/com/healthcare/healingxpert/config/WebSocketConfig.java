package com.healthcare.healingxpert.config;

import com.healthcare.healingxpert.handler.VideoCallHandler;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.*;

@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(new VideoCallHandler(), "/video-call")
                .setAllowedOrigins("*");
    }
}