package com.example.food.common;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * 通信工具
 * 场景：java后端需要向fabric sdk后端发送数据，那发送数据用到的发送工具就是RestTemplate
 */
@Configuration
public class RestTemplateConfig {

    //@Bean return restTemplate的restTemplate实例交给springboot管理
    @Bean
    public RestTemplate getResttemplate(){
        RestTemplate restTemplate=new RestTemplate();
        return restTemplate;
    }
}
