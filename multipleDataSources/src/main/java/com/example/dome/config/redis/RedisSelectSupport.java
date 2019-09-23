package com.example.dome.config.redis;

/**
 * @Time 2019/9/4 15:35
 * @Author GuiPing.Xu
 * @Version 1.0.0
 * @Explain
 */
public class RedisSelectSupport {
    private static final ThreadLocal<Integer> SELECT_CONTEXT = new ThreadLocal<>();

    public static void select(int db){
        SELECT_CONTEXT.set(db);
    }

    public static Integer getSelect(){
        return SELECT_CONTEXT.get();
    }
}
