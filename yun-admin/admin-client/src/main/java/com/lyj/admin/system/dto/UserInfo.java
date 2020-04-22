package com.lyj.admin.system.dto;
import com.lyj.admin.system.domain.SysUser;
import lombok.Data;

@Data
public class UserInfo {

    private SysUser sysUser;

    private String [] perms;

    private String [] roleKeys;
}
