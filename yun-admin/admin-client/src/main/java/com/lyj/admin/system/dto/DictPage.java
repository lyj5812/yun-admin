package com.lyj.admin.system.dto;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import lombok.Data;

@Data
public class DictPage extends Page {
    private String dictName;

    private String dictType;

    private String status;
}
