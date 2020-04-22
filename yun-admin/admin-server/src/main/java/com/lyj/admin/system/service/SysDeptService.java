package com.lyj.admin.system.service;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lyj.admin.system.domain.SysDept;

import java.util.List;

/**
 * Description:
 * User: lyj
 * Date: 2019-04-30
 */
public interface SysDeptService extends IService<SysDept> {

    List<SysDept> selectDeptListTree();

    boolean delDept(SysDept dept);

    SysDept getDeptByUserId(Long userId);
}
