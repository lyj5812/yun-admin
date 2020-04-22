package com.lyj.gateway.filter;

import com.alibaba.fastjson.JSONObject;
import com.lyj.constants.SecurityConstants;
import com.lyj.pojo.R;

import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import java.nio.charset.StandardCharsets;

import reactor.core.publisher.Mono;

@Component
public class AuthFilter implements GlobalFilter {
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        //防止外部注入签名
        exchange.getRequest().mutate()
                .headers(httpHeaders -> httpHeaders.remove(SecurityConstants.AUTH_INNER_KEY))
                .build();
        if (exchange.getRequest().getMethod().equals(HttpMethod.GET) || exchange.getRequest().getMethod().equals(HttpMethod.POST)) {
            //成功
            return chain.filter(exchange);
        }
        //失败
        ServerHttpResponse response = exchange.getResponse();
        byte[] datas = JSONObject.toJSONString(R.error(510, "演示环境，不能操作")).getBytes(StandardCharsets.UTF_8);
        DataBuffer buffer = response.bufferFactory().wrap(datas);
        response.setStatusCode(HttpStatus.NOT_EXTENDED);
        response.getHeaders().add("Content-Type", "application/json;charset=UTF-8");
        return response.writeWith(Mono.just(buffer));
    }
}
