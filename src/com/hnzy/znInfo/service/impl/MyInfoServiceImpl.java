package com.hnzy.znInfo.service.impl;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletOutputStream;

import org.apache.poi.ss.usermodel.PrintCellComments;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.hnzy.util.ExcelUtil;
import com.hnzy.util.Pagination;
import com.hnzy.znInfo.dao.MyInfoDao;
import com.hnzy.znInfo.pojo.MyInfo;
import com.hnzy.znInfo.service.MyInfoService;


@Service
public class MyInfoServiceImpl implements MyInfoService
{
	@Autowired
	private MyInfoDao myinfoDao;

	@Override
	public Pagination<MyInfo> findAll(String pageNum, String limit)
	{
		
		//把分页数据转换为int类型
		int pageIndex=Integer.parseInt(pageNum==null?"1":pageNum);
		int pageSize=Integer.parseInt(limit==null?"5":limit);
		Pagination<MyInfo>page=new Pagination<MyInfo>();
		//从数据库查询一共有多少条数据
		page.setTotal(myinfoDao.getTotalNum());
		//计算处一共有多少页
		int totalPage=(page.getTotal()%pageSize==0?(page.getTotal()/pageSize):(page.getTotal()/pageSize+1));
		//计算出从哪一条数据开始查询
		int offset=(pageIndex-1)*pageSize;
		page.setPageIndex(pageIndex);
		page.setPageSize(pageSize);
		page.setOffset(offset);
		page.setTotalPage(totalPage);
		page.setItems(myinfoDao.findAll(offset, pageSize));
		return page;
	
	}

	//按条件查询并分页
	@Override
	public Pagination<MyInfo> findAllSea(String pageNum, String limit, String name)
	{
		return null;
	
	}

	@Override
	public void Insert(MyInfo myInfo)
	{
		myinfoDao.Insert(myInfo);
	}

	@Override
	public MyInfo findById(int id)
	{
		return myinfoDao.findById(id);
	}

	@Override
	public void delete(String id)
	{
		String [] Id=id.split(",");
		for(int i=0;i<Id.length;i++){
			myinfoDao.delete(Integer.parseInt(Id[i]));
		}
	}

	@Override
	public void update(MyInfo myInfo)
	{
			myinfoDao.update(myInfo);
	}

	@Override
	public List<MyInfo> MyInfodoExportExcel(String name)
	{
		return myinfoDao.MyInfodoExportExcel(name);
	}

	@Override
	public void exportExcel(List<MyInfo> yhInfosList, ServletOutputStream outputStream) throws IOException
	{
		ExcelUtil.exportExcel(yhInfosList, outputStream);
	}
}
