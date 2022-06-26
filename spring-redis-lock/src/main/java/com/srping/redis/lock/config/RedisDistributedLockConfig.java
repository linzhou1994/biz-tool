package com.srping.redis.lock.config;


import com.srping.redis.lock.lock.RedisDistributedLock;
import org.redisson.api.RedissonClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * CopyRight : <company domain>
 * Project :  zouwu-oms-framework
 * Comments : <对此类的描述，可以引用系统设计中的描述>
 * JDK version : JDK1.8
 * Create Date : 2022-05-30 14:48
 *
 * @author : linzhou
 * @version : 1.0
 * @since : 1.0
 */
@Configuration
public class RedisDistributedLockConfig {

  @Bean
  public RedisDistributedLock getRedisDistributedLock(RedissonClient redissonClient){
    return new RedisDistributedLock(redissonClient);
  }
}
