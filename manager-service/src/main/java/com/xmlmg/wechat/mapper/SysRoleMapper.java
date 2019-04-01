package com.xmlmg.wechat.mapper;

import com.xmlmg.wechat.entity.auth.SysRole;
import com.xmlmg.wechat.common.mapper.BaseMapper;

import java.util.Set;

public interface SysRoleMapper extends BaseMapper<SysRole, Integer> {

    Set<SysRole> selectByUserName(String username);

}