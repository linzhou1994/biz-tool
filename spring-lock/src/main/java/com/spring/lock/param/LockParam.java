package com.spring.lock.param;

/**
 * @date : 2022/5/20 11:37
 * @author: linzhou
 * @description : LockParam
 */
public class LockParam {
  /**
   * 分布式锁key
   */
  private String distributedLockKey;
  /**
    * 是否可重入
     */
  private boolean isReentrant;

  /**
   * 加锁等待时间
   */
  private long expireSeconds;

  public LockParam(String distributedLockKey, boolean isReentrant) {
    this.distributedLockKey = distributedLockKey;
    this.isReentrant = isReentrant;
    this.expireSeconds = 2L;
  }
  public LockParam(String distributedLockKey) {
    this.distributedLockKey = distributedLockKey;
    this.isReentrant = false;
    this.expireSeconds = 2L;
  }

  public LockParam(String distributedLockKey, boolean isReentrant, long expireSeconds) {
    this.distributedLockKey = distributedLockKey;
    this.isReentrant = isReentrant;
    this.expireSeconds = expireSeconds;
  }

  public String getDistributedLockKey() {
    return distributedLockKey;
  }

  public void setDistributedLockKey(String distributedLockKey) {
    this.distributedLockKey = distributedLockKey;
  }

  public boolean isReentrant() {
    return isReentrant;
  }

  public void setReentrant(boolean reentrant) {
    isReentrant = reentrant;
  }

  public long getExpireSeconds() {
    return expireSeconds;
  }

  public void setExpireSeconds(long expireSeconds) {
    this.expireSeconds = expireSeconds;
  }
}
