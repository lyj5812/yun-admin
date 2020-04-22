package com.lyj.admin.system.controller;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import com.lyj.pojo.R;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import com.lyj.admin.system.domain.SysNotice;
import com.lyj.admin.system.service.SysNoticeService;

/**
 *
 * 通知公告表 Controller
 * @author ${table.author}
 * @date 2020-04-18
 */
@Api(tags = "通知公告表模块")
@RestController
@RequestMapping("/notice")
public class SysNoticeController {

    @Autowired
    private SysNoticeService noticeService;

    /**
     * 查询分页列表
     */
    @ApiOperation("分页查询")
    @GetMapping("/listPage")
    public R<IPage<SysNotice>> listPage(Page page,SysNotice notice) {
        return R.ok(noticeService.selectNoticeListPage(page, notice));
    }

    /**
     * 新增修改
     */
    @ApiOperation("新增修改")
    @PutMapping("/addOrEdit")
    public R addOrEdit(@RequestBody SysNotice notice) {
        return R.ok(noticeService.saveOrUpdate(notice));
    }

    /**
     * 删除
     */
    @ApiOperation("删除")
    @DeleteMapping( "/delByIds")
    public R delete(@RequestBody List<Long> ids) {
        return R.ok(noticeService.removeByIds(ids));
    }
}