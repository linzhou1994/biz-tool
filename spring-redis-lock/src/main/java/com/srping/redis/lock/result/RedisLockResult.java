package com.srping.redis.lock.result;

import com.wwl.zouwu.oms.framework.lock.result.BaseLockResult;
import org.redisson.api.RLock;

import java.io.IOException;
import java.util.Objects;

/**
 * @date : 2022/5/26 17:02
 * @author: linzhou
 * @description : RedisLockResult
 */
public class RedisLockResult extends BaseLockResult {

  private RLock lock;


  public RLock getLock() {
    return lock;
  }

  public void setLock(RLock lock) {
    this.lock = lock;
  }


  @Override
  public void close() throws IOException {
    if (Objects.nonNull(lock)){
      lock.unlock();
    }
  }
}
