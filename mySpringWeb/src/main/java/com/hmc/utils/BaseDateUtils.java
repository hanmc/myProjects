package com.hmc.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * 日期工具类<p>
 * @author hanmc
 *
 */
@Component
public class BaseDateUtils {
	
	private  Logger ilog = LoggerFactory.getLogger(getClass().getSimpleName());
	
	
	/**
	 * 获取默认的当前日期字符串
	 * 默认格式为：yyyy-MM-dd hh:mm:s
	 * @return
	 */
	public String getDate(){
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		return dateFormat.format(new Date());
	}
	
	/**
	 * 获取指定格式的的当前日期的字符串
	 * 
	 * @return
	 */
	public String getDate(String format){
		SimpleDateFormat dateFormat = new SimpleDateFormat(format);
		return dateFormat.format(new Date());
	}
	
	/**
	 * 根据传入的指定格式和日期 返回字符串日期
	 * @param date
	 * @param format
	 * @return
	 */
	public String getDate(Date date,String format){
		SimpleDateFormat dateFormat = new SimpleDateFormat(format);
		return dateFormat.format(date);
	}

	/**
	 *  根据传入字符串日期和字符串对应的格式,返回Date类型日期
	 * @param date
	 * @param format
	 * @return
	 */
	public  Date getDate(String date,String format){
		SimpleDateFormat dateFormat = new SimpleDateFormat(format);
		Date  date2 = null;
		try {
			 date2 = dateFormat.parse(date);
		} catch (ParseException e) {
			ilog.info("日期转换异常：" + e.getMessage());
		}
		return date2;
	}
	
	/**
	 * 字符串日期的格式的转化方法
	 * 根据传入的字符串格式原日期和原日期的格式  返回新的字符串格式日期
	 * @param date 原字符串日期
	 * @param sourceFormat 原字符串日期格式
	 * @param targetFormat 要转换的字符串日期格式
	 * @return 要转换的字符串日期
	 */
	public  String getDate(String date,String sourceFormat,String targetFormat){
		
		return getDate(getDate(date, sourceFormat), targetFormat);
	}

}
