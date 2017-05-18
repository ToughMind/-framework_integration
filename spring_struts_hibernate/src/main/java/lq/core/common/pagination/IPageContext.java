package lq.core.common.pagination;

/**
 * 分页上下文环境。用于计算Page。
 * 
 * @author 刘泉
 * @date 2016年11月8日 下午6:53:17
 */
public interface IPageContext<E> {

	/**
	 * 默认设定每页显示记录数为10
	 */
	public static final int DEFAULT_PAGE_SIZE = 10;

	/**
	 * 计算总页数.
	 */
	public int getPageCount();

	/**
	 * 返回 Page 对象
	 */
	public Page<E> getPage(int index);

	/**
	 * 每页显示的记录数量
	 */
	public int getPageSize();

	/**
	 * 计算总记录数
	 */
	public int getTotal();

}