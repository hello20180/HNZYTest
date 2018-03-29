package com.hnzy.znInfo.controller;

import static org.hamcrest.CoreMatchers.nullValue;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.PseudoColumnUsage;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.ibatis.annotations.Param;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;
import com.hnzy.util.Pagination;
import com.hnzy.znInfo.pojo.MyInfo;
import com.hnzy.znInfo.service.MyInfoService;
import com.mysql.jdbc.StringUtils;

@Controller
@RequestMapping("myInfoCon")
public class MyInfoController
{
	
 @Autowired
 private MyInfoService myInfoService;
 private  List<MyInfo> myinfoList;
 
 

 @RequestMapping("findMyInfo")
 public ModelAndView findmyInfo(HttpServletRequest request,String pageNum,String limit){
	 ModelAndView modelAndView=new ModelAndView();
	 Pagination<MyInfo> page=myInfoService.findAll(pageNum, limit);
	 modelAndView.setViewName("MyInfo");
	 modelAndView.addObject("page",page);
	return modelAndView;
	 
 }
@RequestMapping("searchInfo")
 @ResponseBody
 public JSONObject searchInfo(String pageNum,String limit ,String name) throws UnsupportedEncodingException{
	 name=new String(name.getBytes("ISO-8859-1"),"utf-8")+"";
	 JSONObject  jsonObject=new JSONObject();
	 Pagination<MyInfo> page=myInfoService.findAllSea(pageNum, limit, "%"+name+"%");
	 jsonObject.put("page",page);
	return jsonObject;
 }
 
@RequestMapping("toInsert")
 public String toInsert(){
	return "toInsert";
	 
 }

@RequestMapping("Insert")
public String Insets(MyInfo myInfo) throws UnsupportedEncodingException{
	if(myInfo!=null ){
		myInfo.setAddress(new String(myInfo.getAddress().getBytes("ISO-8859-1"),"utf-8")+"");
		myInfo.setName(new String(myInfo.getName().getBytes("ISO-8859-1"),"utf-8")+"");
		myInfo.setSchool(new String(myInfo.getSchool().getBytes("ISO-8859-1"),"utf-8")+"");
		myInfoService.Insert(myInfo);	
	}

	return"myInfosuccess";
}
 
@RequestMapping("toUpdate")
public String toUpdate(HttpServletRequest request,int id,MyInfo myInfo){

	myInfo=myInfoService.findById(id);
	request.setAttribute("myIf",myInfo);
	return"toUpdate";
}


//导出
	@RequestMapping("MyInfodoExportExcel")
	public void  MyInfodoExportExcel(MyInfo myInfo,HttpSession session,HttpServletResponse response,@Param("name")String name) throws IOException{
		name=new String(name.getBytes("ISO-8859-1"),"utf-8")+"";
		//告诉浏览器要弹出的文档类型
		response.setContentType("application/x-execl");
		//告诉浏览器这个文档作为附件给别人下载（放置浏览器不兼容，文件要编码）
		response.setHeader("Content-Disposition", "attachment;filename="+new String("my信息列表.xls".getBytes(),"ISO-8859-1"));
		//获取输出流
		ServletOutputStream outputStream=response.getOutputStream();
		myInfoService.exportExcel(myInfoService.MyInfodoExportExcel("%"+name+"%"), outputStream);
		if(outputStream!=null){
			outputStream.close();
		}
		
	}
	
//导入 
	public void inportExcel(MultipartFile file ,HttpServletRequest request,HttpServletResponse  response){
	
	
	}


 @RequestMapping("login")
public String login(){
	return"index";
}
 @RequestMapping("delete")
 public String delete(String id){
	 myInfoService.delete(id);
	return "redirect:findMyInfo.action";
	 
 }
 @RequestMapping("update")
 public String update(MyInfo myInfo) throws UnsupportedEncodingException{
	myInfo.setAddress(new String(myInfo.getAddress().getBytes("ISO-8859-1"),"utf-8")+"");
	myInfo.setName(new String(myInfo.getName().getBytes("ISO-8859-1"),"utf-8")+"");
	myInfo.setSchool(new String(myInfo.getSchool().getBytes("ISO-8859-1"),"utf-8")+"");
	 myInfoService.update(myInfo);
	 return "myInfosuccess";
 }
public List<MyInfo> getMyinfoList()
{
	return myinfoList;
}

public void setMyinfoList(List<MyInfo> myinfoList)
{
	this.myinfoList = myinfoList;
}

}
