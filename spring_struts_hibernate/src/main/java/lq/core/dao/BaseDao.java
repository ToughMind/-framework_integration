package lq.core.dao;

import java.io.Serializable;
import java.util.List;

/**
 * 公用接口：通用DAO接口。
 * 
 * @author 刘泉
 * @date 2016年11月8日 下午5:43:51
 */
public interface BaseDao<M extends Serializable, PK extends Serializable> {

	/**
	 * 保存模型对象
	 */
	public void save(M model);

	/**
	 * 保存或更新模型对象
	 */
	public void saveOrUpdate(M model);

	/**
	 * 更新模型对象
	 */
	public void update(M model);

	/**
	 * 合并模型对象状态到底层会话
	 */
	public void merge(M model);

	/**
	 * 删除模型对象
	 */
	public void delete(PK id);

	/**
	 * 根据主键获取模型对象
	 */
	public M get(PK id);

	/**
	 * 统计模型对象对应数据库表中的记录数
	 */
	public int countAll();

	/**
	 * 返回所有模型对象
	 */
	public List<M> listAll();

	/**
	 * 分页获取所有模型对象
	 */
	public List<M> listAll(int pn, int pageSize);

	/**
	 * 刷新底层session对象（可能对部分实现无效如JDBC）
	 */
	public void flush();

	/**
	 * 执行底层session对象的clear操作（可能对部分实现无效如JDBC）
	 */
	public void clear();

}
