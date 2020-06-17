package com.atguigu.springcloud.service;

import org.springframework.stereotype.Component;

/**
 * @author Administrator
 * @Data 2020/6/15 10:13
 */
@Component
public class PaymentFallbackService implements PaymentHystrixService {
    @Override
    public String paymentInfo_OK(Integer id) {
        return "---PaymentFallbackService fallback paymentInfo_OK ";
    }

    @Override
    public String paymentInfo_TimeOut(Integer id) {
        return "---PaymentFallbackService fallback paymentInfo_TimeOut ";
    }
}
