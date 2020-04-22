package com.lyj.gateway;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 *  网关服务启动类
 * @author lyj
 * @date 2019/01/21
 */
@SpringBootApplication
@EnableDiscoveryClient
public class GatewayServerApplication {

  public static void main(String[] args) {
    SpringApplication.run(GatewayServerApplication.class, args);
  }
}
