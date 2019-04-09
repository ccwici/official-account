package com.xmlmg.wechat.common.mapper;

import java.util.List;

/**
 * @describe： 通用  Mapper 接口
 * @version: 1.0
 */
public interface BaseMapper<T, ID> { // NOSONAR

    int deleteByPrimaryKey(ID id);

    int insert(T record);

    int insertSelective(T record);

    T selectByPrimaryKey(ID id);

    List<T> selectList();

    int updateByPrimaryKeySelective(T record);

    int updateByPrimaryKey(T record);

}
