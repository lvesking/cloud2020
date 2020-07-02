package com.atguigu.springcloud.alibaba.controller;

import com.atguigu.springcloud.entites.CommonResult;
import com.atguigu.springcloud.entites.Payment;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

/**
 * @author Administrator
 * @Data 2020/7/2 10:18
 */
@RestController
public class PaymentController {

    @Value("${server.port}")
    private String serverPort;

    public static HashMap<Long, Payment> hashMap=new HashMap<>();

    static {
        hashMap.put(1L,new Payment(1L,"28a11d5656516516"));
        hashMap.put(2L,new Payment(2L,"511215445q4dw45"));
        hashMap.put(3L,new Payment(3L,"4511e21wq1e445"));
    }
    @GetMapping("/paymentSQL/{id}")
    public CommonResult<Payment> paymentSQL(@PathVariable("id") Long id){
        Payment payment=hashMap.get(id);
        CommonResult<Payment> result=new CommonResult<>(200,"form mysql serverPort: "+serverPort,payment);
        return result;
    }
}
