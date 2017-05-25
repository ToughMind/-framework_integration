/**
 * @(#)BaseDao.JAVA, 2017年05月25日.
 *
 * Copyright 2017 Netease, Inc. All rights reserved.
 * NETEASE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package lq.core.dao;

import lq.core.domain.po.BasePO;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * 公用DAO，提供一些通用的、统一的方法。
 *
 * @author 刘泉 2017年05月25日 17:10
 */
public interface BaseDao<T extends BasePO> {

    /**
     * 新增实体
     */
    public void addEntity(final T entity);
    
    /**
     * 根据Id查询实体
     */
    public T getEntityById(final Class<T> cls, final Long id);

    /**
     * 更新实体
     */
    public void updateEntity(final T entity);

    /**
     * 根据Id删除实体
     */
    public void deleteEntityById(final Class<T> cls, final Long id);

    /**
     * 查询全部
     */
    public List<T> selectAll(Class<T> cls);

    /**
     * 单表模糊查询
     */
    //public List<T> findByLike(Example example);

    /**
     * 根据模糊分页查询
     */
    //public List<T> findByPage(Example example, FlexiPageDto flexiPageDto);

    /**
     * 单表模糊查询总记录数
     */
    //public int findRowCount(Example example);

}
