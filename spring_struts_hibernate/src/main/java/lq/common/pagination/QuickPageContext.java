package lq.common.pagination;

import java.util.List;

/**
 * 动态分页实现.每次查询返回一页记录的情况下使用。
 * 
 * @author 刘泉
 * @date 2016年11月8日 下午6:55:18
 */
public class QuickPageContext<E> implements IPageContext<E> {

	private List<E> items;

	/**
	 * 总记录数
	 */
	private int totalCount;

	/**
	 * 每页显示记录数
	 */
	private int pageSize;

	public QuickPageContext(int totalCount, int pageSize, List<E> items) {
		this.totalCount = totalCount;
		this.pageSize = pageSize == 0 ? 10 : pageSize;
		if (items != null) {
			this.items = items;
		}
	}

	public Page<E> getPage(int index) {
		Page<E> page = new Page<E>();
		// 为了简化测试，我们把不需要的注释掉，需要时去掉注释
		// page.setContext(this);
		int index2 = index > getPageCount() ? 1 : index;
		page.setHasNext(index2 < getPageCount());
		page.setHasPre(index2 > 1);
		page.setIndex(index2);
		page.setItems(items);
		return page;
	}

	/**
	 * 计算总页数.
	 */
	public int getPageCount() {
		int div = totalCount / pageSize;
		int result = (totalCount % pageSize == 0) ? div : div + 1;

		return result;
	}

	public int getTotal() {
		return this.totalCount;
	}

	public int getPageSize() {
		return this.pageSize;
	}
}