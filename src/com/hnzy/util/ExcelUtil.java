package com.hnzy.util;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletOutputStream;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.util.CellRangeAddress;

import com.hnzy.znInfo.pojo.MyInfo;

public class ExcelUtil {
	public static void exportExcel(List<MyInfo> myInfoList,ServletOutputStream outputStream) throws IOException{
		//定义显示日期的公共格式
    SimpleDateFormat simpleDateFormat =new SimpleDateFormat("yyy-MM-dd:HH:mm:ss");
    String newdate=simpleDateFormat.format(new Date());
		//创建工作薄
		HSSFWorkbook workbook = new HSSFWorkbook();
		//创建合并单元格对象
		CellRangeAddress cellRangeAddress=new CellRangeAddress(0,0,0,0);
		//头标题样式
		HSSFCellStyle style1 = creatCellStyle(workbook, (short) 14);
		//列标题样式
		HSSFCellStyle style2 = creatCellStyle(workbook, (short) 11);
		//创建工作表
		HSSFSheet sheet = workbook.createSheet("my信息列表");
		//加载合并单元格对象
		sheet.addMergedRegion(cellRangeAddress);
		//设置默认列宽
		sheet.setDefaultColumnWidth(15);
		//创建行
		//创建头标题行，并设置头标题
		HSSFRow row1 = sheet.createRow(0);
		HSSFCell cell1 = row1.createCell(0);
		//创建题行，并设置头标题
		HSSFRow row2 = sheet.createRow(1);
		String[] titles =
		{ "名字", "性别", "学校", "地址" };
		for (int i = 0; i < titles.length; i++)
		{
			HSSFCell cell2 = row2.createCell(i);
			//加载单元格样式
			cell2.setCellStyle(style1);
			cell2.setCellValue(titles[i]);
		}
		//操作单元格，将用户写Execl
		if(myInfoList!=null){
			for(int j=0;j<myInfoList.size();j++){
			HSSFRow row =sheet .createRow(j+2);
			HSSFCell c1=row.createCell(0);
			c1.setCellValue(myInfoList.get(j).getName());
			HSSFCell c2=row.createCell(1);
			c2.setCellValue(myInfoList.get(j).getAge());
			HSSFCell c3=row.createCell(2);
			c3.setCellValue(myInfoList.get(j).getSchool());
			HSSFCell c4=row.createCell(3);
			c4.setCellValue(myInfoList.get(j).getAddress());
			}
		}
		workbook.write(outputStream);
		workbook.close();
	}
	private static HSSFCellStyle creatCellStyle(HSSFWorkbook workbook, short fontSize)
	{
		HSSFCellStyle style=workbook.createCellStyle();
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER);//水平居中
		style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);//垂直居中
		//创建字体
		HSSFFont font=workbook.createFont();
		font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);//加粗字体
		font.setFontHeightInPoints(fontSize);
		//加载字体
		style.setFont(font);
		
		return style;
	}

}
