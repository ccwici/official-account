package com.xmlmg.wechat.controller.auth;

import com.xmlmg.wechat.common.controller.BaseController;
import com.xmlmg.wechat.entity.auth.SysPermission;
import com.xmlmg.wechat.service.ISysPermissionService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * [权限管理] 权限表 前端控制器
 * </p>
 *
 */

@Slf4j
@Api(tags = "权限")
@RestController
@RequestMapping("/permission")
public class SysPermissionController extends BaseController<SysPermission, Integer, ISysPermissionService> {

}
