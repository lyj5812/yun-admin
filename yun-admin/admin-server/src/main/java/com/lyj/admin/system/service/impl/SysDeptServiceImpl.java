package com.lyj.admin.system.service.impl;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lyj.admin.system.mapper.SysDeptMapper;
import com.lyj.admin.system.domain.SysDept;
import com.lyj.admin.system.service.SysDeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Description:
 * Date: 2019-04-30
 * @author lyj
 */
@Service
public class SysDeptServiceImpl extends ServiceImpl<SysDeptMapper, SysDept> implements SysDeptService {

    @Autowired
    private SysDeptMapper deptMapper;

    /**
     * 查询部门树
     * @return
     */
    @Override
    public List<SysDept> selectDeptListTree(){
        //查询所有部门
        List<SysDept> depts = deptMapper.selectList(new QueryWrapper<SysDept>().orderByAsc("order_num"));
        return getChildPerms(depts, 0);
    }

    @Override
    public boolean delDept(SysDept dept) {
        List<SysDept> list = new ArrayList<>();
        recursionGetList(dept,list);
        return deptMapper.deleteBatchIds(list.stream().map(SysDept::getDeptId).collect(Collectors.toList()))>0;
    }

    @Override
    public SysDept getDeptByUserId(Long userId) {
        return deptMapper.getDeptByUserId(userId);
    }


    /**
     * 根据父节点的ID获取所有子节点
     * @param list 分类表
     * @param parentId 传入的父节点ID
     * @return String
     */
    public List<SysDept> getChildPerms(List<SysDept> list, int parentId) {
        List<SysDept> returnList = new ArrayList<>();
        for (Iterator<SysDept> iterator = list.iterator(); iterator.hasNext();) {
            SysDept t = iterator.next();
            // 一、根据传入的某个父节点ID,遍历该父节点的所有子节点
            if (t.getParentId() == parentId) {
                recursionFn(list, t);
                returnList.add(t);
            }
        }
        return returnList;
    }

    /**
     * 递归列表
     * @param list
     * @param t
     */
    private void recursionFn(List<SysDept> list, SysDept t) {
        // 得到子节点列表
        List<SysDept> childList = getChildList(list, t);
        t.setChildren(childList);
        for (SysDept tChild : childList) {
            if (hasChild(list, tChild)) {
                // 判断是否有子节点
                Iterator<SysDept> it = childList.iterator();
                while (it.hasNext()) {
                    SysDept n = it.next();
                    recursionFn(list, n);
                }
            }
        }
    }

    /**
     * 递归 获得列表
     * @param dept
     */
    private void recursionGetList(SysDept dept,List<SysDept> list) {
        list.add(dept);
        if (!dept.getChildren().isEmpty()){
            for (SysDept child : dept.getChildren()) {
                recursionGetList(child,list);
            }
        }
    }

    /**
     * 得到子节点列表
     */
    private List<SysDept> getChildList(List<SysDept> list, SysDept t) {
        List<SysDept> tlist = new ArrayList<>();
        Iterator<SysDept> it = list.iterator();
        while (it.hasNext()) {
            SysDept n = it.next();
            if (n.getParentId().longValue() == t.getDeptId().longValue()) {
                tlist.add(n);
            }
        }
        return tlist;
    }

    /**
     * 判断是否有子节点
     */
    private boolean hasChild(List<SysDept> list, SysDept t) {
        return getChildList(list, t).size() > 0 ? true : false;
    }

}
