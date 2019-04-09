package com.xmlmg.wechat.common.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xmlmg.wechat.common.mapper.BaseMapper;
import com.xmlmg.wechat.common.service.IBaseService;
import com.xmlmg.wechat.vo.MyPage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;


/**
 * T：VO对象类型，ID：ID的类型，M：Mapper类
 * @describe： 通用service实现类
 * @version: 1.0
 */

public abstract class BaseServiceImpl<T, ID, M extends BaseMapper> implements IBaseService<T, ID> { // NOSONAR
    protected Logger logger = LoggerFactory.getLogger(this.getClass().getName());

    @Autowired
    protected M baseMapper;

    @Transactional(rollbackFor = RuntimeException.class)
    @Override
    public int deleteByPrimaryKey(ID id) {
        return baseMapper.deleteByPrimaryKey(id);
    }

    @Transactional(rollbackFor = RuntimeException.class)
    @Override
    public int insert(T record) {
        return baseMapper.insert(record);
    }

    @Transactional(rollbackFor = RuntimeException.class)
    @Override
    public int insertSelective(T record) {
        return baseMapper.insertSelective(record);
    }

    @Override
    public T selectByPrimaryKey(ID id) {
        return (T) baseMapper.selectByPrimaryKey(id);
    }

    @Override
    public PageInfo<T> selectList(MyPage page) {
        logger.info("selectList....... PageNum: {}  PageSize:{}", page.getPageNum(), page.getPageSize());
        return PageHelper.startPage(page.getPageNum(), page.getPageSize()).doSelectPageInfo(() -> baseMapper.selectList());
    }

    @Transactional(rollbackFor = RuntimeException.class)
    @Override
    public int updateByPrimaryKeySelective(T record) {
        return baseMapper.updateByPrimaryKeySelective(record);
    }

    @Transactional(rollbackFor = RuntimeException.class)
    @Override
    public int updateByPrimaryKey(T record) {
        return baseMapper.updateByPrimaryKey(record);
    }

}
