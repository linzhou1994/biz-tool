package com.springboot.rocketmq.test;

import com.springboot.rocketmq.BaseTest;
import com.springboot.rocketmq.User;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;


/**
 * CopyRight : <company domain>
 * Project :  biz-tool
 * Comments : <对此类的描述，可以引用系统设计中的描述>
 * JDK version : JDK1.8
 * Create Date : 2022-08-09 15:21
 *
 * @author : linzhou
 * @version : 1.0
 * @since : 1.0
 */
public class MqSendTest  extends BaseTest {
    @Autowired
    private RocketMQTemplate rocketMQTemplate;

    @Test
    public void contextLoads() {
        User user = new User();
        user.setName("linzhou");
        user.setAge(100);
        rocketMQTemplate.syncSend("lz" + ":" + "test",user);
    }
}
