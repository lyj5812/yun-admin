package com.lyj.admin.system.dto;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import lombok.Data;

@Data
public class UserPage extends Page {
    private String loginName;

    private String phonenumber;

    private Long deptId;

    private String status;
}
