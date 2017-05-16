package lq.common.service.impl;

import java.util.List;

import lq.common.dao.IBaseDao;
import lq.common.pagination.Page;
import lq.common.pagination.PageUtil;
import lq.common.service.IBaseService;

/**
 * 通用接口实现类：业务层。
 * 由于用了VO-BO-PO架构，BO是core模块和web模块的转接点，此通用类无法用到。
 * 
 * @author 刘泉
 * @date 2016年11月8日 下午7:17:47
 */
@Deprecated
public abstract class BaseServiceImpl<M extends java.io.Serializable, PK extends java.io.Serializable>
		implements IBaseService<M, PK> {

	protected IBaseDao<M, PK> dao;

	public void setDao(IBaseDao<M, PK> dao) {
		this.dao = dao;
	}

	public IBaseDao<M, PK> getDao() {
		return this.dao;
	}

	@Override
	public M save(M model) {
		getDao().save(model);
		return model;
	}

	@Override
	public void merge(M model) {
		getDao().merge(model);
	}

	@Override
	public void saveOrUpdate(M model) {
		getDao().saveOrUpdate(model);
	}

	@Override
	public void update(M model) {
		getDao().update(model);
	}

	@Override
	public void delete(PK id) {
		getDao().delete(id);
	}

	// @Override
	// public void deleteObject(M model) {
	// getDao().deleteObject(model);
	// }

	@Override
	public M get(PK id) {
		return getDao().get(id);
	}

	@Override
	public int countAll() {
		return getDao().countAll();
	}

	@Override
	public List<M> listAll() {
		return getDao().listAll();
	}

	@Override
	public Page<M> listAll(int pn) {
		return this.listAll(pn, 10);
	}

	@Override
	public Page<M> listAll(int pn, int pageSize) {
		Integer count = countAll();
		List<M> items = getDao().listAll(pn, pageSize);
		return PageUtil.getPage(count, pn, items, pageSize);
	}

}
