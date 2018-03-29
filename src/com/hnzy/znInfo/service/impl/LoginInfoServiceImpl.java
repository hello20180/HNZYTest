package com.hnzy.znInfo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hnzy.util.Pagination;
import com.hnzy.znInfo.dao.LoginInfoDao;
import com.hnzy.znInfo.pojo.LoginInfo;
import com.hnzy.znInfo.pojo.MyInfo;
import com.hnzy.znInfo.service.LoginInfoService;

@Service
public class LoginInfoServiceImpl implements LoginInfoService
{

	@Autowired
	private LoginInfoDao loginInfoDao;
	@Override
	public LoginInfo find(String name,String pwd)
	{
		return loginInfoDao.find(name,pwd);
	}
	@Override
	public LoginInfo findNamePwd(int id)
	{
		return loginInfoDao.findNamePwd(id);
	}
	@Override
	public void updapwd(int id, String passnew)
	{
               loginInfoDao .updapwd(id, passnew);		
	}
	//分页并显示
	@Override
	public Pagination<LoginInfo> findAll(String pageNum, String limit)
	{
		
		//把分页数据转换为int类型
		int pageIndex=Integer.parseInt(pageNum==null?"1":pageNum);
		int pageSize=Integer.parseInt(limit==null?"5":limit);
		Pagination<LoginInfo>page=new Pagination<LoginInfo>();
		//从数据库查询一共有多少条数据
		page.setTotal(loginInfoDao.getTotalNum());
		//计算处一共有多少页
		int totalPage=(page.getTotal()%pageSize==0?(page.getTotal()/pageSize):(page.getTotal()/pageSize+1));
		//计算出从哪一条数据开始查询
		int offset=(pageIndex-1)*pageSize;
		page.setPageIndex(pageIndex);
		page.setPageSize(pageSize);
		page.setOffset(offset);
		page.setTotalPage(totalPage);
		page.setItems(loginInfoDao.findAll(offset, pageSize));
		return page;
		
	}
	//搜索并分页
	@Override
	public Pagination<LoginInfo> findAllSea(String pageNum, String limit, String name)
	{
		//把分页数据转换为int类型
		  int pageIndex=Integer.parseInt(pageNum==null?"1":pageNum);
		  int pageSize=Integer.parseInt(limit==null?"5":limit);
		  Pagination<LoginInfo> page=new Pagination<>();
		  //从数据库查询出一共有多少条数据
		  page.setTotal(loginInfoDao.getTotalNumSea(name));
		  //计算出一共有多少页
			int totalPage=(page.getTotal()%pageSize)==0?(page.getTotal()/pageSize):(page.getTotal()/pageSize+1);
			//计算出从哪一条数据开始查询
				int offset=(pageIndex-1)*pageSize;
				page.setPageIndex(pageIndex);
				page.setPageSize(pageSize);
				page.setOffset(offset);
				page.setTotalPage(totalPage);
				page.setItems(loginInfoDao.findAllSea(page.getOffset(),page.getPageSize(), name));
				return page;
	}
	@Override
	public void upState(int id, String states)
	{
		loginInfoDao.upState(id, states);
	}
	@Override
	public void toInsert(LoginInfo loginInfo)
	{
		loginInfoDao.Insert(loginInfo);
	}
	@Override
	public LoginInfo findById(int id)
	{
		return loginInfoDao.findById(id);
	}
	@Override
	public void update(LoginInfo loginInfo)
	{
		 loginInfoDao.update(loginInfo);
	}
	@Override
	public void delete(String id)
	{
		String ids[]=id.split(",");
		for(int i=0;i<ids.length;i++){
			int Id=Integer.parseInt(ids[i]);
			loginInfoDao.delete(Id);
		}
      		
	}
	@Override
	public LoginInfo findName(String name)
	{
		return loginInfoDao.findName(name);
	}
  
}
