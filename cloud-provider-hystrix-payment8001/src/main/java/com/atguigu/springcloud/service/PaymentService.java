package com.atguigu.springcloud.service;

import cn.hutool.core.util.IdUtil;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.cloud.commons.util.IdUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.concurrent.TimeUnit;

/**
 * @author Administrator
 * @Data 2020/6/12 15:44
 */
@Service
public class PaymentService {

    /**
     * 正常访问肯定OK的方法
     * @param id
     * @return
     */
    public String paymentInfo_OK(Integer id){
        return "线程池:   "+Thread.currentThread().getName()+"  paymentInfo_OK,id:  "+id+"\t"+"哈哈哈哈哈";
    }


    @HystrixCommand(fallbackMethod = "paymentInfo_TimeOutHandler",commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "3000")
    })
    public String paymentInfo_TimeOut(Integer id){
        int timeout=5;
        try{
            TimeUnit.SECONDS.sleep(timeout);}catch (InterruptedException e){e.printStackTrace();}
        return "线程池:   "+Thread.currentThread().getName()+"  paymentInfo_TimeOut,id:  "+id+"\t"+"哈哈哈哈哈"+"    耗时"+timeout+"秒";
    }

    @HystrixCommand(fallbackMethod = "paymentInfo_TimeOutHandler")
    public String paymentInfo_TimeOutHandler(Integer id){
        int timeout=3;
        try{
            TimeUnit.SECONDS.sleep(timeout);}catch (InterruptedException e){e.printStackTrace();}
        return "线程池:   "+Thread.currentThread().getName()+"  系统繁忙或运行报错,请稍后再试,id:  "+id+"\t"+"哭哭哭哭哭哭";
    }


    //----------服务熔断
    @HystrixCommand(fallbackMethod = "paymentCircuitBreaker_fallback",commandProperties = {
            @HystrixProperty(name = "circuitBreaker.enabled",value = "true"),//是否开启断路器
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold",value = "10"),//请求次数
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds",value = "10000"),//失败后重试睡眠多久
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage",value = "60"),//失败率达到多少后跳闸
    })

    public String paymentCircuitBreaker(@PathVariable("id") Integer id){
        if(id<0){
            throw new RuntimeException("*****id 不能为负数");
        }
        String serialNumber= IdUtil.simpleUUID();
        return Thread.currentThread().getName()+"\t"+"调用成功,流水号"+serialNumber;
    }
    public  String paymentCircuitBreaker_fallback(@PathVariable("id") Integer id){
        return "id 不能负数,请稍后再试   哈哈";
    }




}
