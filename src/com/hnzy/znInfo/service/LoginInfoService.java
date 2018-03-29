package com.hnzy.znInfo.service;

import com.hnzy.util.Pagination;
import com.hnzy.znInfo.pojo.LoginInfo;

public interface LoginInfoService
{
   public LoginInfo find(String name,String pwd);
   public LoginInfo findNamePwd(int id);
   public void updapwd(int id,String passnew);
    //查询所有并分页
 	 public Pagination<LoginInfo> findAll(String pageNum,String limit);
 	 //根据条件搜索并分页
 	 public Pagination<LoginInfo>findAllSea(String pageNum,String limit,String name);
 	//更新用户状态
 	public void upState(int id,String states);
 	//插入信息
 	public void toInsert(LoginInfo loginInfo);
 	//根据ID查找信息
 	public LoginInfo findById(int id);
 	//插入
 	public void update(LoginInfo loginInfo);
 	//删除
 	public void delete(String id);
 	//查找名字是否重复
 	public LoginInfo findName(String name);
}
