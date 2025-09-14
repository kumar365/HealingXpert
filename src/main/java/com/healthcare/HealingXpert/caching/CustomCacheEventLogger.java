package com.healthcare.HealingXpert.caching;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.listener.KeyspaceEventMessageListener;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.stereotype.Component;

@Component
public class CustomCacheEventLogger extends KeyspaceEventMessageListener {
    private static final Logger LOG = LoggerFactory.getLogger(CustomCacheEventLogger.class);

    public CustomCacheEventLogger(RedisMessageListenerContainer listenerContainer) {
        super(listenerContainer);
    }

    @Override
    protected void doHandleMessage(Message message) {
        String channel = new String(message.getChannel());
        String key = new String(message.getBody());
        LOG.info("Redis cache event - Channel: '{}', Key: '{}'", channel, key);
    }
}