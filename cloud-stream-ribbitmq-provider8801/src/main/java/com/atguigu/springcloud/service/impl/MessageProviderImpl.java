package com.atguigu.springcloud.service.impl;

import com.atguigu.springcloud.service.IMessageProvider;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;

import javax.annotation.Resource;
import java.util.UUID;

/**
 * @author Administrator
 * @Data 2020/6/23 9:55
 */
@EnableBinding(Source.class)   //定义消息推送的管道
public class MessageProviderImpl implements IMessageProvider
{

    //消息发送管道
    @Resource
    private MessageChannel output;


    @Override
    public String send() {
        String serial= UUID.randomUUID().toString();
        output.send(MessageBuilder.withPayload(serial).build());
        System.out.println("**********serial:   "+serial);
        return null;
    }
}
