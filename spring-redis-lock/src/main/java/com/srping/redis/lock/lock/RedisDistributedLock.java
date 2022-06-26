package com.srping.redis.lock.lock;

import com.srping.redis.lock.result.RedisLockResult;
import com.wwl.zouwu.oms.framework.lock.DistributedLock;
import com.wwl.zouwu.oms.framework.lock.param.LockParam;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * @date : 2022/5/26 16:55
 * @author: linzhou
 * @description : RedisDistributedLock
 */
@Component
public class RedisDistributedLock implements DistributedLock<RedisLockResult> {

  @Autowired
  private RedissonClient redissonClient;

  public RedisDistributedLock(RedissonClient redissonClient) {
    this.redissonClient = redissonClient;
  }

  @Override
  public RedisLockResult lock(LockParam lockParam) {
    try {
      RLock lock = redissonClient.getLock(lockParam.getDistributedLockKey());
      if (lockParam.getExpireSeconds() == 0) {
        lock.lock();
      } else {
        if (!lock.tryLock(lockParam.getExpireSeconds(), 60 * 60L, TimeUnit.SECONDS)){
          return null;
        }
      }
      return buildRedisLockResult(lockParam, lock);
    } catch (Exception e) {
      return null;
    }
  }

  /**
   * 构建分布式锁返回类
   *
   * @param lockParam
   * @param lock
   * @return
   */
  private RedisLockResult buildRedisLockResult(LockParam lockParam, RLock lock) {
    RedisLockResult redisLockResult = new RedisLockResult();
    redisLockResult.setLockParam(lockParam);
    Thread thread = Thread.currentThread();
    redisLockResult.setThreadId(thread.getId());
    redisLockResult.setThreadName(thread.getName());
    redisLockResult.setLock(lock);
    return redisLockResult;
  }

  @Override
  public void unLock(RedisLockResult lockResult) {
    if (Objects.isNull(lockResult) || Objects.isNull(lockResult.getLock())||
      !(lockResult.getLock().isLocked() && lockResult.getLock().isHeldByCurrentThread())) {
      return;
    }
    lockResult.getLock().unlock();
  }
}
