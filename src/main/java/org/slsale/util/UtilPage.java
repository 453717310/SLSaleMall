package org.slsale.util;

/**
 * 分页工具类
 * @author dll
 *
 */
public class UtilPage {
	private Integer pageIndex = 1;// 当前页
	private Integer pageSize = 1;// 页大小
	private Integer pageCount = 1;// 总页数
	private Integer totalCount = 0;// 总记录数

	/**
	 * 
	 */
	public UtilPage() {
		super();
	}

	/**
	 * @param pageIndex
	 * @param pageSize
	 * @param pageCount
	 * @param totalCount
	 */
	public UtilPage(Integer pageIndex, Integer pageSize, Integer pageCount,
			Integer totalCount) {
		super();
		this.pageIndex = pageIndex;
		this.pageSize = pageSize;
		this.pageCount = pageCount;
		this.totalCount = totalCount;
	}

	/**
	 * @return the pageIndex
	 */
	public Integer getPageIndex() {
		return pageIndex;
	}

	/**
	 * @param pageIndex the pageIndex to set
	 */
	public void setPageIndex(Integer pageIndex) {
		this.pageIndex = pageIndex;
	}

	/**
	 * @return the pageSize
	 */
	public Integer getPageSize() {
		return pageSize;
	}

	/**
	 * @param pageSize the pageSize to set
	 */
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	/**
	 * @return the pageCount
	 */
	public Integer getPageCount() {
		return pageCount;
	}

	/**
	 * @param pageCount the pageCount to set
	 */
	public void setPageCount(Integer pageCount) {
		this.pageCount = pageCount;
	}

	/**
	 * @return the totalCount
	 */
	public Integer getTotalCount() {
		return totalCount;
	}

	/**
	 * @param totalCount the totalCount to set
	 */
	public void setTotalCount(Integer totalCount) {
		if (totalCount > 0) {
			this.totalCount = totalCount;
			this.pageCount = (totalCount % pageSize == 0) ? (totalCount / pageSize)
					: totalCount / pageSize + 1;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "UtilPage [pageIndex=" + pageIndex + ", pageSize=" + pageSize
				+ ", pageCount=" + pageCount + ", totalCount=" + totalCount
				+ "]";
	}

}
