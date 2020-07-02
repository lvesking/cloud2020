package com.atguigu.springcloud.alibaba.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.atguigu.springcloud.alibaba.service.PaymentService;
import com.atguigu.springcloud.entites.CommonResult;
import com.atguigu.springcloud.entites.Payment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import javax.annotation.Resources;

/**
 * @author Administrator
 * @Data 2020/7/2 11:14
 */
@RestController
@Slf4j
public class CircleBreakerController {

    public static final String SERVICE_URL="http://nacos-payment-provider";

    @Resource
    private RestTemplate restTemplate;

    @RequestMapping("/consumer/fallback/{id}")
   // @SentinelResource(value = "fallback")  没有配置
   // @SentinelResource(value = "fallback",fallback = "handlerFallback")   //fallback只负责业务异常
   // @SentinelResource(value = "fallback",blockHandler = "blockHandler")  //blockHandler只负责sentinel控制台配置违规
    @SentinelResource(value = "fallback",blockHandler = "blockHandler",fallback = "handlerFallback")
    public CommonResult<Payment> fallback(@PathVariable("id") Long id) throws IllegalAccessException {
        CommonResult<Payment> result=restTemplate.getForObject(SERVICE_URL+"/paymentSQL/"+id,CommonResult.class,id);
        if(id==4){
            throw new IllegalAccessException("非法参数异常");
        }else if(result.getData()==null){
            throw new NullPointerException("没有此条记录");
        }
        return result;
    }

    public CommonResult blockHandler(@PathVariable("id")Long id, BlockException e){
        Payment payment=new Payment(id,"null");
        return new CommonResult(455,"sentinel配置异常处理"+e.getMessage(),payment);
    }

    public CommonResult handlerFallback(@PathVariable("id")Long id,Throwable e){
        Payment payment=new Payment(id,"null");
        return new CommonResult(444,"兜底异常内容"+e.getMessage(),payment);
    }




    //-----------------openfeign

    @Resource
    private PaymentService paymentService;

    @GetMapping("/consumer/paymentSQL/{id}")
    public CommonResult<Payment> paymentSQL(@PathVariable("id") Long id){
        return paymentService.paymentSQL(id);
    }



}
