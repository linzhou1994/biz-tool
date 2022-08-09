package com.springboot.rocketmq;

import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;

/**
 * CopyRight : <company domain>
 * Project :  biz-tool
 * Comments : <对此类的描述，可以引用系统设计中的描述>
 * JDK version : JDK1.8
 * Create Date : 2022-08-09 16:05
 *
 * @author : linzhou
 * @version : 1.0
 * @since : 1.0
 */
@Slf4j
@Service
@RocketMQMessageListener(topic = "topic_wpr", selectorExpression = "tag_1", consumerGroup = "QueueHandler")
public class QueueListener implements RocketMQListener<MessageExt> {
    @Override
    public void onMessage(MessageExt messageExt) {
        String body = new String(messageExt.getBody(), StandardCharsets.UTF_8);
        System.out.println("消息内容:"+ body);
        System.out.println("消息ID:"+messageExt.getMsgId());
    }
}
