package com.xmlmg.wechat.mapper.ita;

import com.xmlmg.wechat.Application;
import com.xmlmg.wechat.entity.ita.OperationSetting;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class InteractionSettingMapperTest {
    @Autowired
    OperationSettingMapper operationSettingMapper;

    @Test
    public void queryEntranceOperationList() {
        List<OperationSetting> result = operationSettingMapper.queryEntranceOperationList();
        Assert.assertTrue(result.size() > 0);
    }

}
