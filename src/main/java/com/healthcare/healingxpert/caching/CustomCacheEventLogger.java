package com.healthcare.healingxpert.caching;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.listener.KeyspaceEventMessageListener;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.stereotype.Component;

@Component
@ConditionalOnClass(RedisConnectionFactory.class)
@ConditionalOnBean(RedisMessageListenerContainer.class)
@ConditionalOnProperty(name = "spring.redis.enabled", havingValue = "true", matchIfMissing = true)
public class CustomCacheEventLogger extends KeyspaceEventMessageListener {
    private static final Logger LOG = LoggerFactory.getLogger(CustomCacheEventLogger.class);

    public CustomCacheEventLogger(RedisMessageListenerContainer listenerContainer) {
        super(listenerContainer);
    }

    @Override
    protected void doHandleMessage(Message message) {
        try {
            String channel = new String(message.getChannel());
            String key = new String(message.getBody());
            LOG.info("Redis cache event - Channel: '{}', Key: '{}'", channel, key);
        } catch (Exception e) {
            LOG.error("Error processing Redis cache event: {}", e.getMessage());
        }
    }
}