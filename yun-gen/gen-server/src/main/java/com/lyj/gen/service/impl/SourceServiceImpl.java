package com.lyj.gen.service.impl;
import com.lyj.common.redis.RedisService;
import com.lyj.common.utils.UserUtils;
import com.lyj.gen.dto.DataSourceDto;
import com.lyj.gen.service.SourceService;
import com.lyj.gen.utils.GenConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class SourceServiceImpl implements SourceService {

    @Autowired
    private RedisService redisService;

    @Override
    public Boolean add(DataSourceDto sourceDto) {
        if (StrUtil.isNotBlank(sourceDto.getSourceId())) {
            redisService.hPut(UserUtils.getUserId() + GenConstants.USER_DBS_KEY, sourceDto.getSourceId(), sourceDto);
        } else {
            sourceDto.setSourceId(IdUtil.simpleUUID());
            redisService.hPut(UserUtils.getUserId() + GenConstants.USER_DBS_KEY, sourceDto.getSourceId(), sourceDto);
        }
        return true;
    }

    @Override
    public List<Object> getSourceList() {
        return redisService.hValues(UserUtils.getUserId() + GenConstants.USER_DBS_KEY);
    }
}
