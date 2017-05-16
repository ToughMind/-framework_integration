package lq.common.pagination;

import java.util.List;

/**
 * 公用类：分页工具类。
 * 
 * @author 刘泉
 * @date 2016年11月8日 下午6:51:37
 */
public class PageUtil {

	/**
	 * 根据页码和每页大小得到起始记录位置
	 * 
	 * @param pageNumber
	 *            页码
	 * @param pageSize
	 *            每页大小
	 * @return 起始记录位置
	 */
	public static int getPageStart(int pageNumber, int pageSize) {
		return getPageStart(Integer.MAX_VALUE, pageNumber, pageSize);
	}

	/**
	 * 根据页码和每页大小得到起始记录位置（起始位置不会大于总记录数）
	 * 
	 * @param totalCount
	 *            总记录数
	 * @param pageNumber
	 *            页码
	 * @param pageSize
	 *            每页大小
	 * @return 起始记录位置
	 */
	public static int getPageStart(int totalCount, int pageNumber, int pageSize) {
		int start = (pageNumber - 1) * pageSize;
		if (start >= totalCount) {
			start = 0;
		}

		return start;
	}

	/**
	 * 构造分页对象
	 * 
	 * @param totalCount
	 *            满足条件的所有记录总和
	 * @param pageNumber
	 *            本次分页的页码
	 */
	public static <E> Page<E> getPage(int totalCount, int pageNumber, List<E> items, int pageSize) {
		IPageContext<E> pageContext = new QuickPageContext<E>(totalCount, pageSize, items);
		return pageContext.getPage(pageNumber);
	}
}
