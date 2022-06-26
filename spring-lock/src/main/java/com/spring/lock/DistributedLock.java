package com.spring.lock;

import com.spring.lock.param.LockParam;
import com.spring.lock.result.BaseLockResult;

/**
 * @date : 2022/5/20 11:27
 * @author: linzhou
 * @description : 分布式锁接口类
 */
public interface DistributedLock<T extends BaseLockResult>{

  /**
   * 分布式锁加锁
   *
   * @param lockParam
   * @return 加锁失败返回null，加锁成功返回成功对象用于后续解锁
   */
  T lock(LockParam lockParam);

  /**
   * 分布式锁解锁
   *
   * @param lockResult
   */
  void unLock(T lockResult);
}
