package com.lyj.admin.system.controller;
import com.lyj.admin.system.domain.SysDept;
import com.lyj.admin.system.service.SysDeptService;
import com.lyj.pojo.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * Date: 2019-04-30
 * @author lyj
 */
@Api(tags = "部门模块")
@RestController
@RequestMapping("/dept")
public class SysDeptController {

    @Autowired
    private SysDeptService deptService;

    /**
     * 加载部门列表树
     */
    @ApiOperation(value = "查询部门列表树")
    @GetMapping("/deptTreeList")
    public R<List<SysDept>> deptTreeList() {
        return R.ok(deptService.selectDeptListTree());
    }

    /**
     * 添加或修改
     */
    @ApiOperation(value = "添加或修改")
    @PutMapping("/deptAddOrEdit")
    public R deptAddOrEdit(@RequestBody SysDept dept){
        if (dept.getParentId().equals(0L)){
            dept.setAncestors("0");
        }else {
            SysDept parentDept = deptService.getById(dept.getParentId());
            dept.setAncestors(parentDept.getAncestors()+","+parentDept.getDeptId());
        }
        return R.ok(deptService.saveOrUpdate(dept));
    }

    /**
     * 删除
     */
    @ApiOperation(value = "删除")
    @DeleteMapping("/delDept")
    public R delDept(@RequestBody SysDept dept){
        return R.ok(deptService.delDept(dept));
    }
}
