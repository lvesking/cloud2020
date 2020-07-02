package com.atguigu.springcloud.alibaba.myhandler;

import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.atguigu.springcloud.entites.CommonResult;
import com.atguigu.springcloud.entites.Payment;

/**
 * @author Administrator
 * @Data 2020/7/2 9:56
 */
public class CustomerBlockHandler {
    public static CommonResult handlerException(BlockException exception){
        return new CommonResult(4444,"按客户自定义");
    }
    public static CommonResult handlerException2(BlockException exception){
        return new CommonResult(4444,"按客户自定义2");
    }
}
