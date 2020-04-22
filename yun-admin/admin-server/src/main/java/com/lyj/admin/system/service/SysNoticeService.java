package com.lyj.admin.system.service;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lyj.admin.system.domain.SysNotice;
import java.util.List;

/**
 * SysNotice Service
 * 
 * @author ${table.author}
 * @date 2020-04-18
 */
public interface SysNoticeService extends IService<SysNotice>{

    /**
     * 查询分页列表
     * 
     * @param page 分页参数
     * @param sysNotice
     * @return 分页数据
     */
    IPage<SysNotice> selectNoticeListPage(Page page, SysNotice sysNotice);

}
