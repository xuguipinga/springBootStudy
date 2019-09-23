package com.example.dome.common.aop;

import com.example.dome.common.annotion.RedisSource;
import com.example.dome.config.redis.RedisSelectSupport;
import com.example.dome.config.redis.SelectableRedisTemplate;
import com.example.dome.utils.RRException;
import com.example.dome.utils.RedisUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.context.annotation.Configuration;

import java.lang.reflect.Method;

/**
 * @Time 2019/9/4 15:59
 * @Author GuiPing.Xu
 * @Version 1.0.0
 * @Explain
 */
@Aspect
@Configuration
public class RedisAspect {

    private Logger logger = LoggerFactory.getLogger(getClass());
    /**
     *是否开启redis缓存  true开启   false关闭
     */
    @Value("${example.redis-multi-open: false}")
    private boolean open;

    @Autowired
    private RedisUtils redisUtils;

    @Value("${spring.redis.database:0}")
    private int defaultDataBase;

    @Around("execution(* com.example.dome.utils.RedisUtils.*(..))")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        Object result = null;
        if(open){
            try{
                result = point.proceed();
            }catch (Exception e){
                logger.error("redis error", e);
                throw new RRException("Redis服务异常");
            }
        }
        return result;
    }

    @Around("@annotation(com.example.dome.common.annotion.RedisSource)")
    @ConditionalOnBean(SelectableRedisTemplate.class)
    public Object configRedis(ProceedingJoinPoint point) throws Throwable{
        int db = defaultDataBase;
        try {
            MethodSignature signature = (MethodSignature) point.getSignature();
            Method method = signature.getMethod();

            RedisSource config = method.getAnnotation(RedisSource.class);
            if(config != null){
                db = config.value();
            }
            RedisSelectSupport.select(db);
            return point.proceed();
        } finally {
            RedisSelectSupport.select(defaultDataBase);
            logger.debug("redis reset {} to {}", db, defaultDataBase);
        }
    }
}
