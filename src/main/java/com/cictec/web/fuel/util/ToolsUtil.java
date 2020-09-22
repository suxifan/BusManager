package com.cictec.web.fuel.util;

import java.io.PrintWriter;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

/**
 * 
 * @author hehui
 *
 */
public class ToolsUtil {

	private static final Logger LOG = Logger.getLogger(ToolsUtil.class);
	
	/**
	 * 日期转换成字符串
	 * 
	 * @param date
	 * @return str
	 */
	public static String DateToStr(DateFormat format, Date date) {

		String str = format.format(date);
		return str;
	}

	/**
	 * 字符串转换成日期
	 * 
	 * @param str
	 * @return date
	 */
	public static Date StrToDate(DateFormat format, String str) {

		Date date = null;
		try {
			date = format.parse(str);
		} catch (ParseException e) {
			LOG.error(str + "转换为日期失败！" + e.toString());
		}
		return date;
	}
	
	/**
	 * 字符串转换成日期(格式：2015-07-21)
	 * @param str
	 * @return date
	 */
	public static Date StrToDate(String yearMonthDay) {
		String year,month,day;
		if (yearMonthDay == null || yearMonthDay.length() == 0) {
			return null;
		}
		String []yearMonthDayTmp = yearMonthDay.split("-");
		year = yearMonthDayTmp[0];
		month = yearMonthDayTmp[1];
		day = yearMonthDayTmp[2];
		Calendar cal = Calendar.getInstance();
		cal.set(new Integer(year), new Integer(month)-1, new Integer(day));
		return cal.getTime();
	}

	/**
	 * 字符串转换成日期
	 * 
	 * @param str as:2015-07-01T00:00:0
	 * @return date
	 * @throws ParseException 
	 */
	public static Date StrTimeToDate(String str) throws ParseException {

		Date date = null;
		String strymd = str.substring(0,10);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		//try {
			date = sdf.parse(strymd);
		//} catch (ParseException e) {
		//	LOG.error(str + "转换为日期失败！" + e.toString());
		//}
		return date;
	}
	
	public static Date StrTimeToDateEnd(String str) throws ParseException {

		Date date = null;
		String strymd = str.substring(0,10);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		//try {
			date = sdf.parse(strymd);
		//} catch (ParseException e) {
		//	LOG.error(str + "转换为日期失败！" + e.toString());
		//}
			date.setHours(23);
			date.setMinutes(59);
			date.setSeconds(59);
		return date;
	}
	/**
	 * 把当前日期转换成日期串
	 */
	public static String getDate(Date date){
		if (date == null) {
			return null;
		}
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
		String dateTmp = format.format(date);
		return dateTmp;
	}
	
	
	/**
	 * JSON传输的公用方法
	 * @param response
	 */
	public static void writerJson( HttpServletResponse response ,String jsonStr) {
		PrintWriter printWriter;
		
		try {			
			printWriter = response.getWriter();			
			printWriter.write(jsonStr);
			printWriter.flush();
			printWriter.close();
		
		}catch(Exception e){
			LOG.error("FuelBusinessController->writerJson方法执行失败！" + e.toString());
		}
		
	}
	
	/**
	 * 十进制大小端转换
	 * @param response
	 */
	public static String switchDecimalEndian(String input)
	{
 	 	/*ByteBuffer buffer= ByteBuffer.allocate(20);
        buffer.order(ByteOrder.LITTLE_ENDIAN);
        //buffer.asIntBuffer().put(Long.parseLong(input));
        buffer.asLongBuffer().put(Long.parseLong(input));
        buffer.order(ByteOrder.BIG_ENDIAN);    
        //String ret = Integer.toString(buffer.getInt());
        String ret = Long.toString(buffer.getLong());*/
		String ret = input;
        if(ret.length() < 10){
        	int zeroNums = 10 - ret.length();
        	for(int i = 0; i < zeroNums; i ++){
        		ret = "0"+ret;
        	}
        }
        return ret;
	}
	/**
	 * 十进制大小端转换
	 * @param response
	 */
	public static String switchHexToDecimalEndian(String input)
	{      
        String revInp = "";
        int start = input.length()-2;
        while(start >= 0){
        	revInp += input.substring(start, start+2);
        	start -= 2;
        }
        String ret = Long.toString(Long.parseLong(revInp, 16));
        if(ret.length() < 10){
        	int zeroNums = 10 - ret.length();
        	for(int i = 0; i < zeroNums; i ++){
        		ret = "0"+ret;
        	}
        }
        return ret;
	}
	public static String switchHexToDecimal(String input)
	{
		//String ret = Integer.toString(Integer.parseInt(input, 16));
		String ret = Long.toString(Long.parseLong(input, 16));
        if(ret.length() < 10){
        	int zeroNums = 10 - ret.length();
        	for(int i = 0; i < zeroNums; i ++){
        		ret = "0"+ret;
        	}
        }
        return ret;
	}
	
	public static Date getCurrTime(){
		Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis(System.currentTimeMillis());
		return cal.getTime();
	}
}
