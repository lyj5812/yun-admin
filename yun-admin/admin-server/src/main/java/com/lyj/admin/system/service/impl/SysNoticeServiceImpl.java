package com.lyj.admin.system.service.impl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lyj.admin.system.mapper.SysNoticeMapper;
import com.lyj.admin.system.domain.SysNotice;
import com.lyj.admin.system.service.SysNoticeService;

/**
 * 通知公告表 ServiceImpl
 * 
 * @author ${table.author}
 * @date 2020-04-18
 */
@Service
public class SysNoticeServiceImpl extends ServiceImpl<SysNoticeMapper, SysNotice> implements SysNoticeService {

    @Autowired
    private SysNoticeMapper noticeMapper;

    /**
     * 查询分页列表
     * @param page 分页参数
     * @param notice
     * @return 分页数据
     */
    @Override
    public IPage<SysNotice> selectNoticeListPage(Page page, SysNotice notice) {
        return noticeMapper.selectNoticeListPage(page,notice);
    }

}
