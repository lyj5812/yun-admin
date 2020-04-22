package com.lyj.admin.system.dto;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import lombok.Data;

@Data
public class PostPage extends Page {
    private String postCode;

    private String PostName;

    private String status;
}
