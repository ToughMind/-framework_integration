package lq.core.dao.impl;

import lq.core.common.pagination.PageUtil;
import lq.core.dao.BaseDao;
import org.hibernate.*;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.type.Type;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.orm.hibernate5.HibernateCallback;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import javax.annotation.PostConstruct;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/**
 * 公用类：DAO层基本操作类。
 * 
 * @author 刘泉
 * @date 2016年11月8日 下午6:32:12
 */
public abstract class BaseDaoImpl<M extends Serializable, PK extends Serializable>
    extends HibernateDaoSupport implements BaseDao<M, PK> {

    protected final Logger logger;

    public BaseDaoImpl() {
        logger = LoggerFactory.getLogger(getClass());
    }

    @Autowired
    @Required
    public void setSf(SessionFactory sf) {
        setSessionFactory(sf);
    }

    private Class<M> entityClass;

    private String HQL_LIST_ALL;

    private String HQL_COUNT_ALL;

    @PostConstruct
    @SuppressWarnings("unchecked")
    public void init() {
        // 1、通过反射获取注解“M”（即模型对象）的类类型
        this.entityClass = (Class<M>) ((ParameterizedType) getClass()
            .getGenericSuperclass()).getActualTypeArguments()[0];
        // 2、得到模型对象的实体名
        String entityName = getSessionFactory()
            .getClassMetadata(this.entityClass).getEntityName();
        // 3、根据实体名生成HQL
        HQL_LIST_ALL = "from " + entityName;
        HQL_COUNT_ALL = " select count(*) from " + entityName;
    }

    protected String getListAllHql() {
        return HQL_LIST_ALL;
    }

    protected String getCountAllHql() {
        return HQL_COUNT_ALL;
    }

    @Override
    public void save(M model) {
        getHibernateTemplate().save(model);
    }

    @Override
    public void saveOrUpdate(M model) {
        getHibernateTemplate().saveOrUpdate(model);
    }

    @Override
    public void update(M model) {
        getHibernateTemplate().update(model);

    }

    @Override
    public void merge(M model) {
        getHibernateTemplate().merge(model);
    }

    @Override
    public void delete(PK id) {
        getHibernateTemplate().delete(this.get(id));

    }

    @Override
    public M get(PK id) {
        return getHibernateTemplate().get(this.entityClass, id);
    }

    @Override
    public int countAll() {
        Number total = unique(getCountAllHql());
        return total.intValue();
    }

    @Override
    public void flush() {
        getHibernateTemplate().flush();
        getHibernateTemplate().clear();
    }

    @Override
    public void clear() {
        getHibernateTemplate().clear();
    }

    @Override
    public List<M> listAll() {
        return list(getListAllHql(), -1, -1);
    }

    @Override
    public List<M> listAll(int pn, int pageSize) {
        return list(getListAllHql(), pn, pageSize);
    }

    /**
     * 通用列表查询,当pn<=-1 且 pageSize<=-1表示查询所有记录
     * 
     * @param <T>
     *            模型类型
     * @param hql
     *            Hibernate查询语句
     * @param pn
     *            页码 从1开始，
     * @param pageSize
     *            每页记录数
     * @param paramlist
     *            可变参数列表
     * @return 模型对象列表
     */
    @SuppressWarnings("unchecked")
    protected <T> List<T> list(final String hql, final int pn,
        final int pageSize, final Object... paramlist) {
        return getHibernateTemplate().execute(new HibernateCallback<List<T>>() {

            @Override
            public List<T> doInHibernate(Session session)
                throws HibernateException {
                Query query = session.createQuery(hql);
                if (paramlist != null) {
                    for (int i = 0; i < paramlist.length; i++) {
                        query.setParameter(i, paramlist[i]);// 设置占位符参数
                    }
                }
                if (pn > -1 && pageSize > -1) {// 分页处理
                    query.setMaxResults(pageSize);// 设置将获取的最大记录数
                    int start = PageUtil.getPageStart(pn, pageSize);
                    if (start != 0) {
                        query.setFirstResult(start);// 设置记录开始位置
                    }
                }
                return query.list();
            }
        });

    }

    /**
     * 根据查询条件返回唯一一条记录
     * 
     * @param <T>
     *            返回类型
     * @param hql
     *            Hibernate查询语句
     * @param paramlist
     *            参数列表
     * @return
     */
    @SuppressWarnings("unchecked")
    protected <T> T unique(final String hql, final Object... paramlist) {
        return getHibernateTemplate().execute(new HibernateCallback<T>() {

            public T doInHibernate(Session session) throws HibernateException {
                Query query = session.createQuery(hql);
                if (paramlist != null) {
                    for (int i = 0; i < paramlist.length; i++) {
                        query.setParameter(i, paramlist[i]);
                    }
                }
                return (T) query.setMaxResults(1).uniqueResult();
            }
        });
    }

    /**
     * 列表查询
     * 
     * @param <T>
     *            模型类型
     * @param hql
     *            Hibernate查询语句
     * @param pn
     *            页码 从1开始
     * @param pageSize
     *            每页记录数
     * @param map
     *            命名参数Map
     * @return 模型列表
     */
    @SuppressWarnings("unchecked")
    protected <T> List<T> list(final String hql, final int pn,
        final int pageSize, final Map<String, Collection<?>> map) {
        List<T> resultList = getHibernateTemplate()
            .execute(new HibernateCallback<List<T>>() {

                public List<T> doInHibernate(Session session)
                    throws HibernateException {
                    Query query = session.createQuery(hql);
                    for (Entry<String, Collection<?>> e: map.entrySet()) {
                        query.setParameterList(e.getKey(), e.getValue());
                    }
                    if (pn > -1 && pageSize > -1) {
                        query.setMaxResults(pageSize);
                        int start = PageUtil.getPageStart(pn, pageSize);
                        if (start != 0) {
                            query.setFirstResult(start);
                        }
                    }
                    if (pn < 0) {
                        query.setFirstResult(0);
                    }
                    List<T> results = query.list();
                    return results;
                }
            });
        return resultList;
    }

    @SuppressWarnings("unchecked")
    public <T> List<T> list(Criteria criteria) {
        return criteria.list();
    }

    public <T> List<T> list(DetachedCriteria criteria) {
        return list(criteria.getExecutableCriteria(currentSession()));
    }

    /**
     * 根据查询条件返回唯一一条记录，支持
     * 
     * @param <T>
     *            返回类型
     * @param hql
     *            Hibernate查询语句
     * @param map
     *            命名参数Map
     * @return
     */
    @SuppressWarnings("unchecked")
    protected <T> T unique(final String hql,
        final Map<String, Collection<?>> map) {
        return (T) getHibernateTemplate()
            .execute(new HibernateCallback<Object>() {

                public Object doInHibernate(Session session)
                    throws HibernateException {
                    Query query = session.createQuery(hql);
                    for (Entry<String, Collection<?>> e: map.entrySet()) {
                        query.setParameterList(e.getKey(), e.getValue());
                    }
                    return query.uniqueResult();
                }
            });
    }

    @SuppressWarnings("unchecked")
    public <T> T unique(DetachedCriteria criteria) {
        return (T) unique(criteria.getExecutableCriteria(currentSession()));
    }

    @SuppressWarnings("unchecked")
    public <T> T unique(Criteria criteria) {
        return (T) criteria.uniqueResult();
    }

    /**
     * 执行如insert, update, delete 等.
     * 
     * @param hql
     *            HQL
     * @param paramlist
     *            参数列表
     * @return 更新记录数
     */
    protected int execte(final String hql, final Object... paramlist) {
        Object result = getHibernateTemplate()
            .execute(new HibernateCallback<Integer>() {

                public Integer doInHibernate(Session session)
                    throws HibernateException {
                    Query query = session.createQuery(hql);
                    if (paramlist != null) {
                        for (int i = 0; i < paramlist.length; i++) {
                            query.setParameter(i, paramlist[i]);
                        }
                    }
                    return query.executeUpdate();
                }
            });

        return result == null ? 0 : ((Integer) result).intValue();
    }

    /**
     * 执行本地SQL，如insert, update, delete
     * 
     * @param natvieSQL
     *            JDBC SQL
     * @param paramlist
     *            参数列表
     * @return
     */
    protected int execteByNative(final String natvieSQL,
        final Object... paramlist) {
        Object result = getHibernateTemplate()
            .execute(new HibernateCallback<Integer>() {

                public Integer doInHibernate(Session session)
                    throws HibernateException {
                    Query query = session.createSQLQuery(natvieSQL);
                    if (paramlist != null) {
                        for (int i = 0; i < paramlist.length; i++) {
                            query.setParameter(i, paramlist[i]);
                        }
                    }
                    return query.executeUpdate();
                }
            });

        return result == null ? 0 : ((Integer) result).intValue();
    }

    @SuppressWarnings("unchecked")
    public <T> List<T> listByNative(final String nativeSQL, final int pn,
        final int pageSize, final List<Entry<String, Class<?>>> entityList,
        final List<Entry<String, Type>> scalarList, final Object... paramlist) {

        List<T> result = getHibernateTemplate()
            .execute(new HibernateCallback<List<T>>() {

                public List<T> doInHibernate(Session session)
                    throws HibernateException {
                    SQLQuery query = session.createSQLQuery(nativeSQL);
                    if (entityList != null) {
                        for (Entry<String, Class<?>> entity: entityList) {
                            query.addEntity(entity.getKey(), entity.getValue());
                        }
                    }
                    if (scalarList != null) {
                        for (Entry<String, Type> entity: scalarList) {
                            query.addScalar(entity.getKey(), entity.getValue());
                        }
                    }
                    if (paramlist != null) {
                        for (int i = 0; i < paramlist.length; i++) {
                            query.setParameter(i, paramlist[i]);
                        }
                    }
                    if (pn > -1 && pageSize > -1) {
                        query.setMaxResults(pageSize);
                        int start = PageUtil.getPageStart(pn, pageSize);
                        if (start != 0) {
                            query.setFirstResult(start);
                        }
                    }
                    List<T> result = query.list();
                    return result;
                }
            });
        return result;
    }

    @SuppressWarnings("unchecked")
    protected <T> T uniqueByNative(final String natvieSQL,
        final List<Entry<String, Type>> scalarList, final Object... paramlist) {
        return (T) getHibernateTemplate().execute(new HibernateCallback<T>() {

            public T doInHibernate(Session session) throws HibernateException {
                SQLQuery query = session.createSQLQuery(natvieSQL);
                if (scalarList != null) {
                    for (Entry<String, Type> entity: scalarList) {
                        query.addScalar(entity.getKey(), entity.getValue());
                    }
                }
                if (paramlist != null) {
                    for (int i = 0; i < paramlist.length; i++) {
                        query.setParameter(i, paramlist[i]);
                    }
                }
                Object result = query.uniqueResult();
                return (T) result;
            }
        });
    }

}
