package com.atguigu.myrule;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Administrator
 * @Data 2020/6/12 10:16
 */
@Configuration
public class MySelfRule {

    @Bean
    public IRule myRule(){
        //随机   默认是轮询
        return new RandomRule();
    }

}
