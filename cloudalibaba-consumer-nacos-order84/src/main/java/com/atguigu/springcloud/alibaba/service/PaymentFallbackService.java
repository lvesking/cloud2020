package com.atguigu.springcloud.alibaba.service;

import com.atguigu.springcloud.entites.CommonResult;
import com.atguigu.springcloud.entites.Payment;
import org.springframework.stereotype.Component;

/**
 * @author Administrator
 * @Data 2020/7/2 15:55
 */
@Component
public class PaymentFallbackService implements PaymentService
{
    @Override
    public CommonResult<Payment> paymentSQL(Long id) {
        return new CommonResult<>(444,"服务降级---PaymentFallbackService",new Payment(id,"errorSerial"));
    }
}
