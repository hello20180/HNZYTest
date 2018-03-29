package com.hnzy.util;

import java.io.Serializable;
import java.util.List;
/**
 * 分页工具类
 * @author skp
 * 2016-12-23
 *
 * @param <T>
 */
public class Pagination<T> implements Serializable {

	private static final long serialVersionUID = 20161221155202L;
	private int total;//总记录数
	private int pageIndex;//当前页
	private int pageSize;//一页显示多少条
	private List<T> items;//数据
	private int offset;//从第几条开始取，不包含当前数
	private int totalPage;//总页数
	public Pagination(){}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public int getPageIndex() {
		return pageIndex;
	}
	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public List<T> getItems() {
		return items;
	}
	public void setItems(List<T> items) {
		this.items = items;
	}
	public int getOffset() {
		return offset;
	}
	public void setOffset(int offset) {
		this.offset = offset;
	}
	public int getTotalPage() {
		return totalPage;
	}
	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}
	
}
