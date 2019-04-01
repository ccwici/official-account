package com.xmlmg.wechat.service.impl;

import com.xmlmg.wechat.entity.auth.SysRole;
import com.xmlmg.wechat.entity.auth.SysUser;
import com.xmlmg.wechat.mapper.SysUserMapper;
import com.xmlmg.wechat.service.ISysRoleService;
import com.xmlmg.wechat.service.ISysUserService;
import com.xmlmg.wechat.vo.ButtonVo;
import com.xmlmg.wechat.vo.MenuVo;
import com.xmlmg.wechat.vo.SysUserVo;
import com.xmlmg.wechat.common.exception.UserExistsException;
import com.xmlmg.wechat.common.jwt.JwtTokenUtil;
import com.xmlmg.wechat.common.service.impl.BaseServiceImpl;
import com.xmlmg.wechat.common.util.StringUtils;
import com.xmlmg.wechat.common.util.TreeBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * <p>
 * [权限管理] 用户表 服务实现类
 * </p>
 *
 * @author wang chen chen
 * @since 2018-10-23
 */

@Service
public class SysUserServiceImpl extends BaseServiceImpl<SysUser, String, SysUserMapper> implements ISysUserService {

    @Autowired
    private ISysRoleService sysRoleService;

    @Override
    public SysUser findByUsername(String username) throws UsernameNotFoundException {
        if (StringUtils.isEmpty(username)) {
            throw new UsernameNotFoundException("用户名不可以为空!");
        }
        SysUser sysUser = baseMapper.selectByUserName(username);
        if (sysUser == null || StringUtils.isEmpty(sysUser.getUid()) || StringUtils.isEmpty(sysUser.getUsername())) {
            throw new UsernameNotFoundException("用户名不存在!");
        }
        logger.info("SysUserServiceImpl......... {}", sysUser);
        return sysUser;
    }

    @Override
    public SysUserVo findUserInfo(String username) {
        SysUser sysUser = findByUsername(username);
        Set<SysRole> sysRoles = sysRoleService.selectByUserName(username);
        Set<ButtonVo> buttonVos = new HashSet<>();
        Set<MenuVo> menuVos = new HashSet<>();

        sysRoles.forEach(role -> {
            logger.info("role: {}", role.getDescribe());
            role.getPermissions().forEach(permission -> {
                if (permission.getType().toLowerCase().equals("button")) {
                    /*
                    * 如果权限是按钮，就添加到按钮里面
                    * */
                    buttonVos.add(new ButtonVo(permission.getPid(), permission.getResources(), permission.getTitle()));
                }
                if (permission.getType().toLowerCase().equals("menu")) {
                    /*
                    * 如果权限是菜单，就添加到菜单里面
                    * */
                    menuVos.add(new MenuVo(permission.getPid(), permission.getFather(), permission.getIcon(), permission.getResources(), permission.getTitle()));
                }
            });
        });

        SysUserVo sysUserVo =
                new SysUserVo(sysUser.getUid(), sysUser.getAvatar(),
                        sysUser.getNickname(), sysUser.getUsername(),
                        sysUser.getMail(), sysUser.getAddTime(),
                        sysUser.getRoles(), buttonVos, TreeBuilder.findRoots(menuVos));
        return sysUserVo;
    }

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public String login(String username, String password) {
        UsernamePasswordAuthenticationToken upToken = new UsernamePasswordAuthenticationToken(username, password);
        Authentication authentication = authenticationManager.authenticate(upToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        return jwtTokenUtil.generateToken(userDetails);
    }

    @Override
    public Integer register(SysUser sysUser) throws UserExistsException {
        String username = sysUser.getUsername();
        if (findByUsername(username) != null) {
            throw new UserExistsException(String.format("'%s' 这个用用户已经存在了", username));
        }
        String rawPassword = sysUser.getPassword();
        sysUser.setPassword(passwordEncoder.encode(rawPassword));
        sysUser.setUpTime(new Date());
        sysUser.setAddTime(new Date());
        return baseMapper.insertSelective(sysUser);
    }

    @Override
    public String refreshToken(String oldToken) {
        if (!jwtTokenUtil.isTokenExpired(oldToken)) {
            return jwtTokenUtil.refreshToken(oldToken);
        }
        return "error";
    }

}
