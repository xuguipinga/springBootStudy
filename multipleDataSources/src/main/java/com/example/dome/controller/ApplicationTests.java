package com.example.dome.controller;

import com.example.dome.common.annotion.RedisSource;
import com.example.dome.config.redis.RedisSelectSupport;
import com.example.dome.utils.RedisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Time 2019/8/29 13:47
 * @Author GuiPing.Xu
 * @Version 1.0.0
 * @Explain
 */
@RestController
@RequestMapping("/redis/*")
public class ApplicationTests {

    @Autowired
    private StringRedisTemplate redisTemplate;
    @Autowired
    RedisUtils redisUtils;

    @RequestMapping("/one")
    @RedisSource(4)         //选择db1库
    public String selectOne(){
        RedisSelectSupport.select(1);
        String one = (String) redisUtils.get("001ChangeMould");
        System.out.println(one);
        RedisSelectSupport.select(4);
        String tow = (String) redisUtils.get("001lastSensor");
        return one+"------------"+tow;
    }

    @RequestMapping("/two")
//    @RedisSource(5)         //选择db2库
    public String selectTwo(){
        redisUtils.set("three","12w121212122121");
        String two = redisUtils.get("three");
        return two;
    }

    /**
     * 同一个方法中切换不同的redis库
     * @return
     */
    @RequestMapping("/three")
    @RedisSource(4)         //选择db2库
    public String selectThree(){
        String two = redisUtils.get("two");
        System.out.println(two);
        //此处切换到db3库
        RedisSelectSupport.select(5);
        String three = redisTemplate.opsForValue().get("three");
        System.out.println(three);
        return three;
    }

}
