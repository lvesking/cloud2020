package com.atguigu.springcloud.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

/**
 * @author Administrator
 * @Data 2020/6/11 16:58
 */
@RestController
@Slf4j
public class OrderController {

    public static final String CONSUL_URL="http://consul-provider-payment";

    @Resource
    private RestTemplate restTemplate;


    @GetMapping(value = "/consumer/payment/consul")
    public String get(){
        return restTemplate.getForObject(CONSUL_URL+"/payment/consul",String.class);
    }


}
