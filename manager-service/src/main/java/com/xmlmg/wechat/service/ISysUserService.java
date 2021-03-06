package com.xmlmg.wechat.service;

import com.xmlmg.wechat.common.service.IBaseService;
import com.xmlmg.wechat.entity.auth.SysUser;
import com.xmlmg.wechat.vo.SysUserVo;

/**
 * <p>
 * [权限管理] 用户表 服务类
 * </p>
 *
 */
public interface ISysUserService extends IBaseService<SysUser, String> {

    SysUser findByUsername(String username);


    /**
     * 获取用户详细信息
     * @param username
     * @return 操作结果
     */
    SysUserVo findUserInfo(String username);

    /**
     * 用户登录
     *
     * @param username 用户名
     * @param password 密码
     * @return 操作结果
     */
    String login(String username, String password);

    /**
     * 用户注册
     *
     * @param sysUser 用户信息
     * @return 操作结果
     */
    Integer register(SysUser sysUser);

    /**
     * 刷新密钥
     *
     * @param oldToken 原密钥
     * @return 新密钥
     */
    String refreshToken(String oldToken);

}
