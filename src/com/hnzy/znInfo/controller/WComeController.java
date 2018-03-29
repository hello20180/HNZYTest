package com.hnzy.znInfo.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("wcome")
@Controller
public class WComeController
{
	
 @RequestMapping("welcome")
  public String welcome(HttpServletRequest  request){
	 
	  return "welcome";
  }
}
