package com.hnzy.znInfo.service;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletOutputStream;

import org.apache.ibatis.annotations.Param;

import com.hnzy.util.Pagination;
import com.hnzy.znInfo.pojo.MyInfo;

public interface MyInfoService
{
	//查询所有并分页
	 public Pagination<MyInfo> findAll(String pageNum,String limit);
	 //根据条件搜索并分页
	 public Pagination<MyInfo>findAllSea(String pageNum,String limit,String name);
	 //插入
	 public void Insert(MyInfo myInfo);
	 //更新
	 public MyInfo findById(int id);
	 //删除
	 public void delete(String id);
	 //更新
	 public void update(MyInfo myInfo);
	//搜索导出
	 public List<MyInfo> MyInfodoExportExcel(@Param("name")String name);
	 //导出
	 public void exportExcel(List<MyInfo>yhInfosList,ServletOutputStream outputStream) throws IOException;
}
