package com.atguigu.springcloud.alibaba.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.sun.deploy.security.BlockedException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

/**
 * @author Administrator
 * @Data 2020/7/1 15:04
 *
 *
 * 测试流量控制规则:
 * 测试降级规则
 * 测试热点规则
 * 测试系统规则
 * 测试授权规则
 */
@RestController
@Slf4j
public class FlowLimitController {

    @GetMapping("/testA")
    public String testA(){
        return "----------testA";
    }

    @GetMapping("/testB")
    public String testB(){
        return "----------testB";
    }

    @GetMapping("/testD")
    public String testD(){
        try{
            TimeUnit.SECONDS.sleep(1);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        log.info("testD 测试");
        return "----------testD";
    }

    @GetMapping("/testE")
    public String testE(){
        log.info("testE 测试异常数");
        int age=10/0;
        return "----------testE";
    }

    @GetMapping("/testHotKey")
    @SentinelResource(value = "testHotKey", blockHandler = "deal_testHotKey")
    public String testHotKey(@RequestParam(value = "p1",required = false) String p1,
                             @RequestParam(value = "p2",required = false) String p2)
    {
        return "----------testHotKey";
    }

    public String deal_testHotKey(String p1, String p2, BlockException exception){
        return "----------deal_testHotKey";
    }
}
