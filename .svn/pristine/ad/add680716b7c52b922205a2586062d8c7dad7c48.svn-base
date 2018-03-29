package com.hnzy.util;

import java.io.Serializable;
import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface BaseDao <T extends Serializable>
{
	/**
	 * @author Administrator 查找信息
	 */
	public List<T> find();

	/**
	 * @author Administrator 插入信息
	 */
	public void Insert( T entity);

	/**
	 * @author Administrator 删除
	 */
	public int delete(int id);

	/**
	 * @author Administrator 根据id查找信息
	 */
	public T findById(Serializable id);

	/**
	 * @author Administrator 更新信息
	 */
	public void update(T  entity);
	/**
	 * 分页查询
	 * @param offset
	 * @param limit
	 * @return
	 */
	public List<T> findAll(@Param("offset")int offset ,@Param("limit")int limit);
	/**
	 * 得到总数
	 * @return
	 */
	public int getTotalNum();
}
