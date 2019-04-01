package com.xmlmg.wechat.service.impl;

import com.xmlmg.wechat.entity.auth.SysRole;
import com.xmlmg.wechat.mapper.SysRoleMapper;
import com.xmlmg.wechat.service.ISysRoleService;
import com.xmlmg.wechat.common.service.impl.BaseServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

/**
 * <p>
 * [权限管理] 角色表 服务实现类
 * </p>
 *
 * @author wang chen chen
 * @since 2018-10-23
 */

@Slf4j
@Service
public class SysRoleServiceImpl extends BaseServiceImpl<SysRole, Integer, SysRoleMapper> implements ISysRoleService {

    @Override
    public Set<SysRole> selectByPrimaryKeyCollection(List<Integer> ids) {
        return null;
    }

    @Override
    public Set<SysRole> selectByUserName(String username) {
        return baseMapper.selectByUserName(username);
    }

}
