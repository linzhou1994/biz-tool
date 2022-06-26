package com.spring.lock.result;


import com.spring.lock.param.LockParam;

import java.io.Closeable;
import java.io.IOException;

/**
 *
 * @date : 2022/5/20 11:30
 * @author: linzhou
 * @description : BaseLockBo
 */
public class BaseLockResult implements Closeable {

  private LockParam lockParam;

  private Long threadId;

  private String threadName;

  public LockParam getLockParam() {
    return lockParam;
  }

  public void setLockParam(LockParam lockParam) {
    this.lockParam = lockParam;
  }

  public Long getThreadId() {
    return threadId;
  }

  public void setThreadId(Long threadId) {
    this.threadId = threadId;
  }

  public String getThreadName() {
    return threadName;
  }

  public void setThreadName(String threadName) {
    this.threadName = threadName;
  }

  @Override
  public void close() throws IOException {
    throw new UnsupportedOperationException();
  }
}
