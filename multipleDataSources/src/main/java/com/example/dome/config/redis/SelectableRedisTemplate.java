package com.example.dome.config.redis;

import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.StringRedisTemplate;

/**
 * @Time 2019/9/4 15:30
 * @Author GuiPing.Xu
 * @Version 1.0.0
 * @Explain
 */
public class SelectableRedisTemplate  extends StringRedisTemplate {
    @Override
    protected RedisConnection createRedisConnectionProxy(RedisConnection pm) {
        return super.createRedisConnectionProxy(pm);
    }

    @Override
    protected RedisConnection preProcessConnection(RedisConnection connection, boolean existingConnection) {
        Integer db;
        if((db = RedisSelectSupport.getSelect()) != null){
            connection.select(db);
        }
//        else {
//            connection.select(0);
//        }
        return super.preProcessConnection(connection, existingConnection);
    }
}
