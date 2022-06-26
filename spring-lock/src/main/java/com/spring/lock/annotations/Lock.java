package com.spring.lock.annotations;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author linzhou
 * @ClassName Lock.java
 * @createTime 2021年12月17日 15:23:00
 * @Description
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Lock {
  /**
   * 分布式锁的key
   *
   * @return
   */
  String key() default "";

  /**
   * 是否可重入
   *
   * @return
   */
  boolean isReentrant() default false;

  /**
   * 分布式锁等待时间
   *
   * @return
   */
  long expireSeconds() default 2;

}
