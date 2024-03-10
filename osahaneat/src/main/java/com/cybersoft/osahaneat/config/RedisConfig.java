package com.cybersoft.osahaneat.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnection;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

@Configuration
public class RedisConfig {
    @Bean
    public LettuceConnectionFactory redisConnection(){
        RedisStandaloneConfiguration redisConfiguration = new RedisStandaloneConfiguration();
        redisConfiguration.setHostName("localhost");
        redisConfiguration.setPort(6378);
//        redisConfiguration.setDatabase(0);
//        redisConfiguration.setUsername();
//        redisConfiguration.setPassword();
        return new LettuceConnectionFactory(redisConfiguration);
    }
    @Bean
    public RedisTemplate<String, Object> redisTemplate(LettuceConnectionFactory redisConnection){
        RedisTemplate<String,Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(redisConnection);
        return redisTemplate;
    }
}
