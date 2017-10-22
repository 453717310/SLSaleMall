package org.slsale.pojo;

import java.io.Serializable;

/**
 * 基类
 */

public class Base implements Serializable,Cloneable{
	private Integer id;//id
	private Integer pageIndex;//当前页面

	public Integer getPageIndex() {
		return pageIndex;
	}

	public void setPageIndex(Integer pageIndex) {
		this.pageIndex = pageIndex;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

}
