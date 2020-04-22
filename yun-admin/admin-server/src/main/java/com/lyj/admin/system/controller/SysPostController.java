package com.lyj.admin.system.controller;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lyj.admin.system.domain.SysPost;
import com.lyj.admin.system.service.SysPostService;
import com.lyj.pojo.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import java.util.stream.Collectors;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * @date: 2019-05-05
 * @author lyj
 */
@Api(tags = "岗位模块")
@RestController
@RequestMapping("/post")
public class SysPostController {
    @Autowired
    private SysPostService postService;

    /**
     * 查询所有岗位
     */
    @ApiOperation("查询所有岗位")
    @GetMapping("/getPostList")
    public R<List<SysPost>> getPostList() {
        return R.ok(postService.list(new QueryWrapper<SysPost>().eq("status","0").orderByAsc("post_sort")));
    }

    /**
     * 岗位分页查询
     *
     * @param post
     * @return
     */
    @ApiOperation("岗位分页查询")
    @GetMapping("/postListPage")
    public R<IPage> postListPage(Page page,SysPost post) {
        return R.ok(postService.selectPostListPage(page,post));
    }

    @ApiOperation("添加或者修改")
    @PutMapping("/addOrEdit")
    public R addOrEdit(@RequestBody SysPost post){
        return postService.saveOrUpdate(post)?R.ok():R.error();
    }

    @ApiOperation("删除")
    @DeleteMapping("/deleteList")
    public R deleteList(@RequestBody List<SysPost> posts){
        boolean flag = postService.removeByIds(posts.stream().map(SysPost::getPostId).collect(Collectors.toList()));
        return flag?R.ok():R.error();
    }
}
