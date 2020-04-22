package com.lyj.admin.system.vo;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 路由meta
 * @author lyj
 */
@Data
@AllArgsConstructor
public class MetaVo {
    /**
     * 设置该路由在侧边栏和面包屑中展示的名字
     */
    private String title;

    /**
     * 设置该路由的图标，对应路径src/icons/svg
     */
    private String icon;

}
