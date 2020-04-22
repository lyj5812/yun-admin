package com.lyj.wxmp.config;

import org.springframework.data.redis.core.RedisTemplate;

import java.util.concurrent.TimeUnit;

import me.chanjar.weixin.mp.config.impl.WxMpDefaultConfigImpl;
import me.chanjar.weixin.mp.enums.TicketType;

/**
 * 基于Redis的微信配置provider.
 *
 * <pre>
 *    使用说明：本实现仅供参考，并不完整，
 *    比如为减少项目依赖，未加入redis分布式锁的实现，如有需要请自行实现。
 * </pre>
 *
 * @author lyj
 */
@SuppressWarnings("hiding")
public class YunWxMpRedisConfigImpl extends WxMpDefaultConfigImpl {
    private static final String ACCESS_TOKEN_KEY = "wx:access_token:";

    /**
     * 使用连接池保证线程安全.
     */
    private final RedisTemplate<String, String> redisTemplate;

    private String accessTokenKey;

    public YunWxMpRedisConfigImpl(RedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    /**
     * 每个公众号生成独有的存储key.
     */
    @Override
    public void setAppId(String appId) {
        super.setAppId(appId);
        this.accessTokenKey = ACCESS_TOKEN_KEY.concat(appId);
    }

    private String getTicketRedisKey(TicketType type) {
        return String.format("wx:ticket:key:%s:%s", this.appId, type.getCode());
    }

    @Override
    public String getAccessToken() {
        return redisTemplate.opsForValue().get(this.accessTokenKey);
    }

    @Override
    public boolean isAccessTokenExpired() {
        return redisTemplate.getExpire(accessTokenKey) < 2;
    }

    @Override
    public synchronized void updateAccessToken(String accessToken, int expiresInSeconds) {
        redisTemplate.opsForValue().set(this.accessTokenKey, accessToken, expiresInSeconds - 200, TimeUnit.SECONDS);
    }

    @Override
    public void expireAccessToken() {
        redisTemplate.expire(this.accessTokenKey, 0, TimeUnit.SECONDS);
    }

    @Override
    public String getTicket(TicketType type) {
        return redisTemplate.opsForValue().get(this.getTicketRedisKey(type));
    }

    @Override
    public boolean isTicketExpired(TicketType type) {
        return redisTemplate.getExpire(this.getTicketRedisKey(type)) < 2;
    }

    @Override
    public synchronized void updateTicket(TicketType type, String jsapiTicket, int expiresInSeconds) {
        redisTemplate.opsForValue().set(this.getTicketRedisKey(type), jsapiTicket, expiresInSeconds - 200, TimeUnit.SECONDS);
    }

    @Override
    public void expireTicket(TicketType type) {
        redisTemplate.expire(this.getTicketRedisKey(type), 0, TimeUnit.SECONDS);
    }

}
