package com.lyj.admin.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.lyj.common.utils.UserUtils;

import org.apache.ibatis.reflection.MetaObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * Description:
 * Date: 2019-04-24
 *
 * @author lyj
 */
@Component
public class MyMetaObjectHandler implements MetaObjectHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(MyMetaObjectHandler.class);

    /**
     * 插入元对象字段填充（用于插入时对公共字段的填充）
     *
     * @param metaObject 元对象
     */
    @Override
    public void insertFill(MetaObject metaObject) {
        LOGGER.info("start insert fill ....");
        if (UserUtils.getUser() != null) {
            this.setFieldValByName("createBy", UserUtils.getUserId().toString(), metaObject);
            this.setFieldValByName("updateBy", UserUtils.getUserId().toString(), metaObject);
        }
        this.setFieldValByName("delFlag", "0", metaObject);
        this.setFieldValByName("createTime", new Date(), metaObject);
        this.setFieldValByName("updateTime", new Date(), metaObject);
    }

    /**
     * 更新元对象字段填充（用于更新时对公共字段的填充）
     *
     * @param metaObject 元对象
     */
    @Override
    public void updateFill(MetaObject metaObject) {
        LOGGER.info("start update fill ....");
        if (UserUtils.getUser() != null) {
            this.setFieldValByName("updateBy", UserUtils.getUserId().toString(), metaObject);
        }
        this.setFieldValByName("updateTime", new Date(), metaObject);
    }
}
