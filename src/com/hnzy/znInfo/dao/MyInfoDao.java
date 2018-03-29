package com.hnzy.znInfo.dao;


import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.hnzy.util.BaseDao;
import com.hnzy.znInfo.pojo.MyInfo;

public interface MyInfoDao extends BaseDao<MyInfo>
{
//   public List<MyInfo> findMyInfo();
	
	/**
	 * 分页查询
	 * @param offset
	 * @param limit
	 * @return
	 */
	public List<MyInfo> findAllSea(@Param("offset")int offset ,@Param("limit")int limit,@Param("name")String name);
	/**
	 * 得到总数
	 * @return
	 */
	public int getTotalNumSea(@Param("name")String name);
	//搜索导出
    public List<MyInfo> MyInfodoExportExcel(@Param("name")String name);
}
