package com.hnzy.znInfo.controller;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;
import com.hnzy.util.MD5Util;
import com.hnzy.util.Pagination;
import com.hnzy.util.PropertyUtil;
import com.hnzy.util.StringUtil;
import com.hnzy.znInfo.pojo.LoginInfo;
import com.hnzy.znInfo.service.LoginInfoService;

@Controller
@RequestMapping("loginCon")
public class LoginInfoController
{
	@Autowired
	private LoginInfoService loginInfoService ;
	private List<LoginInfo> loginList;
	public String msg;
	
	@RequestMapping("toLogin")
	public String toLogin(){
		return "login";
	}
	//更新密码
	@RequestMapping("updapwd")
	public String updapwd(int id,String pwdnew ){
		pwdnew=(MD5Util.string2MD5(pwdnew));
		loginInfoService.updapwd(id, pwdnew);
		return "login";
	}
	//修改密码
	@RequestMapping("changepwd")
	public String changepwd(HttpServletRequest request,int id,LoginInfo loginInfo){
		loginInfo=loginInfoService.findNamePwd(id);
		loginInfo.setPwd(MD5Util.string2MD5(loginInfo.getPwd()));
		request.setAttribute("changepwd",loginInfo);
		return "editpwd";
		
		
	}
	//登录
	@RequestMapping(value="loginInfo")
	public String login(HttpSession session,HttpServletRequest request,LoginInfo loginInfo){
		loginInfo.setPwd(MD5Util.string2MD5(loginInfo.getPwd()));
		if(StringUtil.isNotEmpty(loginInfo.getName())&&StringUtil.isNotEmpty(loginInfo.getPwd())){
			loginInfo=loginInfoService.find(loginInfo.getName(),loginInfo.getPwd());
			if(loginInfo!=null){
				//判断状态是否启用
				if(loginInfo.getState()==0){
					session.setAttribute("admins",loginInfo);
					session.setAttribute("role",loginInfo.getRole());
					return "index";
				}else{
					msg= PropertyUtil.Accountdisabled;
				}
			}else{
				msg= PropertyUtil.Informationerror;
			}
			
		}else{
			msg= PropertyUtil.Informationempty;
		}
		request.setAttribute("msg",msg);
		return "login";
		
	}
	//查找所有信息
	 @RequestMapping("loginIf")
	 public ModelAndView findmyInfo(HttpServletRequest request,String pageNum,String limit){
		 ModelAndView modelAndView=new ModelAndView();
		 Pagination<LoginInfo> page=loginInfoService.findAll(pageNum, limit);
		 modelAndView.setViewName("loginInfo");
		 modelAndView.addObject("page",page);
		return modelAndView;
		 
	 }
	 //更新状态
	 @RequestMapping("upState")
	public String upState(int id,String states){
		loginInfoService.upState(id, states);
		return "redirect:loginIf.action";
		
	}
	 //搜索
	 @RequestMapping("searchInfo")
	 @ResponseBody
	 public JSONObject searchInfo(String pageNum,String limit ,String name) throws UnsupportedEncodingException{
		 name=new String(name.getBytes("ISO-8859-1"),"utf-8")+"";
		 JSONObject  jsonObject=new JSONObject();
		 Pagination<LoginInfo> page=loginInfoService.findAllSea(pageNum, limit, "%"+name+"%");
		 jsonObject.put("page",page);
		return jsonObject;
	 }
	 //添加
	 @RequestMapping("Insert")
	 public String Insert(LoginInfo loginInfo) throws UnsupportedEncodingException 
	 {
		 //密码加密
		 loginInfo.setPwd(MD5Util.string2MD5(loginInfo.getPwd()));
		 loginInfo.setName( new String(loginInfo.getName().getBytes("ISO-8859-1"),"utf-8")+"");
		 loginInfo.setEndTime(new Date());
		 loginInfo.setStatTime(new Date());
		 loginInfoService.toInsert(loginInfo);
		return "loginInfosuccess";
		 
	 }
	 //跳转到插入页面
	 @RequestMapping("toInsert")
	 public String toInsert(){
		return "toInsertLogin";
		 
	 }
	 //跳转到更新页面
	 @RequestMapping("toUpdate")
	 public String toUpdate(HttpServletRequest request,LoginInfo loginInfo,int id){
		 //根据ID查找对象
		 loginInfo=loginInfoService.findById(id);
		 request.setAttribute("Info", loginInfo);
		return "toUpdateLogin";
		 
	 }
	 //更新信息
	 @RequestMapping("update")
	 public String update(LoginInfo loginInfo) throws UnsupportedEncodingException{
		 loginInfo.setPwd(MD5Util.string2MD5(loginInfo.getPwd()));
		 loginInfo.setName( new String(loginInfo.getName().getBytes("ISO-8859-1"),"utf-8")+"");
		 loginInfo.setStatTime(new Date());
		 loginInfo.setEndTime(new Date());
		 loginInfoService.update(loginInfo);
		return "loginInfosuccess";
		 
	 }
	 //删除
	 @RequestMapping("delete")
	 public String delete(String id){
		 loginInfoService.delete(id);
		 return "redirect:loginIf.action";
	 }
	 //查找用户名称是否重复
	 @RequestMapping("findName")
	 @ResponseBody
	 public JSONObject findName(LoginInfo loginInfo,String name) throws UnsupportedEncodingException{
		 name= new String(name.getBytes("ISO-8859-1"),"utf-8");
		 JSONObject jsonObject=new JSONObject();
		 loginInfo=loginInfoService.findName(name);
		 if(loginInfo!=null){
			if(loginInfo.getName().equals(name))
			{
				jsonObject.put("json",1);
			}
		 }else{
				jsonObject.put("json",0);
			 }
		return jsonObject;	
	 }
	public List<LoginInfo> getLoginList()
	{
		return loginList;
	}

	public void setLoginList(List<LoginInfo> loginList)
	{
		this.loginList = loginList;
	}

}
