package com.xmlmg.wechat.controller.oponline;

import com.xmlmg.wechat.common.jwt.JwtUser;
import com.xmlmg.wechat.common.util.StringUtils;
import com.xmlmg.wechat.entity.ita.OperationSetting;
import com.xmlmg.wechat.service.ita.OperationOnlineService;
import com.xmlmg.wechat.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/oponline/shortcutOp/")
public class ShortcutOpController {

    @Autowired
    OperationOnlineService operationOnlineService;

    @GetMapping("/entranceList/")
    public Result queryEntranceList() {
        return Result.success(operationOnlineService.getEntranceList());
    }

    @GetMapping("/more/{parentId}")
    public Result queryChildOperationList(@PathVariable String parentId) {
        OperationSetting operationSetting = operationOnlineService.getOperationSettingById(parentId);
        if (operationSetting == null) {
            return Result.error500(null, "target op not exists");
        }

        if (StringUtils.isEmpty(operationSetting.getApi())) {
            UsernamePasswordAuthenticationToken userToken = (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
            return Result.success(operationOnlineService.queryChildOperationList(parentId, ((JwtUser) userToken.getPrincipal()).getUsername()));
        }
        return Result.success("", operationOnlineService.executeTargetOperation(operationSetting));
    }
}
