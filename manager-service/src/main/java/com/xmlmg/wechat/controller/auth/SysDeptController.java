package com.xmlmg.wechat.controller.auth;


import com.xmlmg.wechat.common.controller.BaseController;
import com.xmlmg.wechat.entity.auth.SysDept;
import com.xmlmg.wechat.service.ISysDeptService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * [权限管理] 部门表 前端控制器
 * </p>
 *
 */

@Slf4j
@Api(tags = "部门")
@RestController
@RequestMapping("/dept")
public class SysDeptController extends BaseController<SysDept, Integer, ISysDeptService> {

}
