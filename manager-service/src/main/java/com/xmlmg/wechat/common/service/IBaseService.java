package com.xmlmg.wechat.common.service;

import com.github.pagehelper.PageInfo;
import com.xmlmg.wechat.vo.MyPage;


/**
 * @describe：  通用service 接口
 * @version: 1.0
 */
public interface IBaseService<T, ID> { // NOSONAR

    int deleteByPrimaryKey(ID id);

    int insert(T record);

    int insertSelective(T record);

    T selectByPrimaryKey(ID id);

    PageInfo<T> selectList(MyPage page);

    int updateByPrimaryKeySelective(T record);

    int updateByPrimaryKey(T record);

}
