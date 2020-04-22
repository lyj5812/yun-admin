package com.lyj.admin.system.mapper;
import com.lyj.admin.system.domain.SysNotice;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

/**
 * 通知公告表 Mapper
 * 
 * @author ${table.author}
 * @date 2020-04-18
 */
public interface SysNoticeMapper extends BaseMapper<SysNotice>{

    /**
     * 查询分页列表
     * @param page 分页参数
     * @param notice
     * @return 分页数据
     */
    IPage<SysNotice> selectNoticeListPage(Page page, @Param("notice") SysNotice notice);
}
