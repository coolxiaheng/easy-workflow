package com.winsse.sc.wf;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @Author xiaheng
 * @Description :
 * @Date 16:39 2021/6/4
 */
@EnableFeignClients
@EnableDiscoveryClient
@SpringBootApplication
@MapperScan({"com.winsse.sc.**.mapper"})
public class WFApplication {
    public static void main(String[] args) {
        SpringApplication.run(WFApplication.class, args);
    }
}
