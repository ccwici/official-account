package com.xmlmg.wechat.controller.auth;


import com.xmlmg.wechat.entity.auth.SysRole;
import com.xmlmg.wechat.service.ISysRoleService;
import com.xmlmg.wechat.common.controller.BaseController;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * [权限管理] 角色表 前端控制器
 * </p>
 *
 */

@Slf4j
@Api(tags = "角色")
@RestController
@RequestMapping("/role")
public class SysRoleController extends BaseController<SysRole, Integer, ISysRoleService> {

}
