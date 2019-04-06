package com.xmlmg.wechat.service.ita;

import com.xmlmg.wechat.common.service.impl.BaseServiceImpl;
import com.xmlmg.wechat.entity.ita.OperationSetting;
import com.xmlmg.wechat.mapper.ita.OperationSettingMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 公众号交互服务
 */
@Service
public class OperationOnlineService extends BaseServiceImpl<OperationSetting, String, OperationSettingMapper> {
    @Autowired
    private OperationSettingMapper operationSettingMapper;

    public List<OperationSetting> getEntranceList() {
        return operationSettingMapper.queryEntranceOperationList();
    }

    public List<OperationSetting> queryChildOperationList(String parentId, String author) {
        return operationSettingMapper.queryChildOperationList(parentId, author);
    }

    public OperationSetting getOperationSettingById(String id) {
        return operationSettingMapper.selectByPrimaryKey(id);
    }

    public String executeTargetOperation(OperationSetting operationSetting) {
        return "API，待开发...";
    }
}
