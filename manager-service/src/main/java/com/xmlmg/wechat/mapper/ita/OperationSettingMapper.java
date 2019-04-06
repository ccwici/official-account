package com.xmlmg.wechat.mapper.ita;

import com.xmlmg.wechat.common.mapper.BaseMapper;
import com.xmlmg.wechat.entity.ita.OperationSetting;

import java.util.List;

public interface OperationSettingMapper extends BaseMapper<OperationSetting, String> {

    public List<OperationSetting> queryEntranceOperationList();

    public List<OperationSetting> queryChildOperationList(String parentId, String author);

}