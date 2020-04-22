package com.lyj.admin.system.dto;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import lombok.Data;

@Data
public class RolePage extends Page {
    private String roleName;

    private String roleKey;

    private String status;
}
