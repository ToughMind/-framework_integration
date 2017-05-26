/**
 * @(#)BaseDao.JAVA, 2017年05月26日.
 *
 * Copyright 2017 Netease, Inc. All rights reserved.
 * NETEASE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package lq.core.dao;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.List;

/**
 * 公共DAO抽象类，提供一些统一的方法。
 *
 * @author 刘泉 2017年05月26日 15:06
 */
public abstract class AbstactBaseDao<T> {

    @Resource
    @Qualifier("sessionFactory")
    private SqlSessionFactory sqlSessionFactory;

    @Autowired
    private Mapper<T> mapper;

    private SqlSession sqlSession;

    public SqlSession getSqlSession() {
        if (null == sqlSession) {
            synchronized (AbstactBaseDao.class) {
                this.sqlSession = new SqlSessionTemplate(sqlSessionFactory);
            }
        }
        return this.sqlSession;
    }

    public int addEntity(T entity) {
        return this.mapper.insert(entity);
    }

    public int deleteEntityById(Object id) {
        return this.mapper.deleteByPrimaryKey(id);
    }

    public int updateEntity(T entity) {
        return this.mapper.updateByPrimaryKey(entity);
    }

    public T getEntityById(Object id) {
        return this.mapper.selectByPrimaryKey(id);
    }

    public List<T> selectAll() {
        return this.mapper.selectAll();
    }

}
