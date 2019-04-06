package com.xmlmg.wechat.service;


import com.xmlmg.wechat.entity.auth.SysRole;
import com.xmlmg.wechat.common.service.IBaseService;

import java.util.List;
import java.util.Set;

/**
 * <p>
 * [权限管理] 角色表 服务类
 * </p>
 */
public interface ISysRoleService extends IBaseService<SysRole, Integer> {

    Set<SysRole> selectByPrimaryKeyCollection(List<Integer> ids);

    Set<SysRole> selectByUserName(String username);

}
