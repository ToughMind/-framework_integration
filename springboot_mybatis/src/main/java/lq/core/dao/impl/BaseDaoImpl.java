/**
 * @(#)BaseDaoImpl.JAVA, 2017年05月25日.
 *
 * Copyright 2017 Netease, Inc. All rights reserved.
 * NETEASE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package lq.core.dao.impl;

import lq.core.dao.BaseDao;
import lq.core.domain.po.BasePO;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.List;

/**
 * 公用DAO实现类。
 *
 * @author 刘泉 2017年05月25日 17:49
 */
@Repository("baseDao")
public class BaseDaoImpl<T extends BasePO> implements BaseDao<T> {

    @Resource
    @Qualifier("sessionFactory")
    private SqlSessionFactory sqlSessionFactory;

    private SqlSession sqlSession;

    @SuppressWarnings("rawtypes")
    public <M extends Mapper<T>> M getMapper(Class cls) {
        //MapperClass mapperClass = (MapperClass) cls.getAnnotation(MapperClass.class);
        //if(null == mapperClass){
        //    throw new RuntimeException("没有注解MapperClass");
        //}
        return (M) getSqlSession().getMapper(cls);
    }

    public SqlSession getSqlSession() {
        if (null == sqlSession) {
            synchronized (BaseDaoImpl.class) {
                this.sqlSession = new SqlSessionTemplate(sqlSessionFactory);
            }
        }
        return this.sqlSession;
    }

    @Override
    public T getEntityById(Class<T> cls, Long id) {
        return this.getMapper(cls).selectByPrimaryKey(id);
    }

    @Override
    public void addEntity(T entity) {
        this.getMapper(entity.getClass()).insert(entity);
    }

    @Override
    public void updateEntity(T entity) {
        this.getMapper(entity.getClass()).updateByPrimaryKey(entity);
    }

    @Override
    public void deleteEntityById(Class<T> cls, Long id) {
        this.getMapper(cls).deleteByPrimaryKey(id);
    }

    @Override
    public List<T> selectAll(Class<T> cls) {
        return this.getMapper(cls).selectAll();
    }

    //@Override
    //public List<T> findByLike(Example example) {
    //    return this.getMapper(example.getEntityClass())
    //        .selectByExample(example);
    //}
    //
    //@Override
    //public List<T> findByPage(Example example, FlexiPageDto flexiPageDto) {
    //
    //    RowBounds rowBounds = new RowBounds(flexiPageDto.getOffset(),
    //        flexiPageDto.getRp());
    //    return this.getMapper(example.getEntityClass())
    //        .selectByExampleAndRowBounds(example, rowBounds);
    //}
    //
    //@Override
    //public int findRowCount(Example example) {
    //    return this.getMapper(example.getEntityClass())
    //        .selectCountByExample(example);
    //}

}
