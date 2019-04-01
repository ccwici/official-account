package com.xmlmg.wechat.mapper;

import com.xmlmg.wechat.common.mapper.BaseMapper;
import com.xmlmg.wechat.entity.auth.SysUser;

public interface SysUserMapper extends BaseMapper<SysUser, String> {

    SysUser selectByUserName(String username);

}