package com.test1;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class Test{

	
	public static List<Date> getDatesBetweenTwoDate(Date startDate, Date endDate) {
        List<Date> list = new ArrayList<Date>();
        list.add(startDate);// 把开始时间加入集合
        
        if(endDate == null || startDate.equals(endDate)){
            return list;
        }
        Date tmp = null;
        if(endDate.before(startDate)){
            tmp = startDate;
            startDate = endDate;
            endDate = tmp;
        }
        
        Calendar cal = Calendar.getInstance();
        // 使用给定的 Date 设置此 Calendar 的时间
        cal.setTime(startDate);
        boolean bContinue = true;
        while (bContinue) {
            // 根据日历的规则，为给定的日历字段添加或减去指定的时间量
            cal.add(Calendar.DAY_OF_MONTH, 1);
            // 测试此日期是否在指定日期之后
            if (endDate.after(cal.getTime())) {
                list.add(cal.getTime());
            } else {
                break;
            }
        }
        list.add(endDate);// 把结束时间加入集合
        return list;
    }
	
	/**  
	 * <p>Title: main </P> 
	 * <p>Description: TODO </P> 
	 * @param args 
	 * return void    返回类型  
	 * throws  
	 * date 2014-11-24 上午09:11:47 
	 */  
	public static void main(String[] args) {  
	    try {  
	          
	       
	         DateFormat df = new SimpleDateFormat("yyyy-MM-dd");  
	        Calendar ca = Calendar.getInstance();  
	        Date d = df.parse("2014-11-04");  
	        ca.setTime(d);//设置当前时间  
	          
//	        CalendarUtil ct = new CalendarUtil();  
//	          
//	        String c = ct.addDateByWorkDay(ca,5);  
//	        System.out.println(c);  
	           
	    } catch ( Exception e) {  
	        // TODO: handle exception  
	       
	        e.printStackTrace();  
	    }  
	      
	}  
	  
	 private static List<Calendar> holidayList = new ArrayList<Calendar>();  //节假日列表  
	   
	 /** 
	  *  
	  * <p>Title: addDateByWorkDay </P> 
	  * <p>Description: TODO  计算相加day天，并且排除节假日和周末后的日期</P> 
	  * @param calendar  当前的日期 
	  * @param day  相加天数 
	  * @return    
	  * return Calendar    返回类型   返回相加day天，并且排除节假日和周末后的日期 
	  * throws  
	  * date 2014-11-24 上午10:32:55 
	  */  
	 public static String addDateByWorkDay(Calendar calendar,int day){  
	       
	     try {  
	        for (int i = 0; i < day; i++) {  
	              
	             calendar.add(Calendar.DAY_OF_MONTH, 1);  
	               
	             if(checkHoliday(calendar)){  
	                 i--;  
	             }  
	        }  
	    } catch (Exception e) {  
	        e.printStackTrace();  
	    }  
	     DateFormat df = new SimpleDateFormat("yyyy-MM-dd");  
	    return  df.format(calendar.getTime());  
	 }  
	   
	 /** 
	  *  
	  * <p>Title: checkHoliday </P> 
	  * <p>Description: TODO 验证日期是否是节假日</P> 
	  * @param calendar  传入需要验证的日期 
	  * @return  
	  * return boolean    返回类型  返回true是节假日，返回false不是节假日 
	  * throws  
	  * date 2014-11-24 上午10:13:07 
	  */  
	 public static boolean checkHoliday(Calendar calendar) throws Exception{  
	       
	     //判断日期是否是周六周日  
	     if(calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY ||   
	             calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY){  
	         return true;  
	     }
	     //判断日期是否是节假日  
	     for (Calendar cal : holidayList) {  
	        if(cal.get(Calendar.MONTH) == calendar.get(Calendar.MONTH) &&  
	        		cal.get(Calendar.DAY_OF_MONTH) == calendar.get(Calendar.DAY_OF_MONTH)&&  
	        				cal.get(Calendar.YEAR) == calendar.get(Calendar.YEAR)){  
	            return true;  
	        }  
	    }  
	        
	     return false;  
	 }  
	   
	 /** 
	  *  
	  * <p>Title: initHolidayList </P> 
	  * <p>Description: TODO  把所有节假日放入list，验证前要先执行这个方法</P> 
	  * @param date  从数据库查 查出来的格式2014-05-09 
	  * return void    返回类型  
	  * throws  
	  * date 2014-11-24 上午10:11:35 
	  */  
	public static  void initHolidayList(List<String> date) {  
	  
	    for (String string : date) {  
	  
	        String[] da = string.split("-");  
	  
	        Calendar calendar = Calendar.getInstance();  
	        calendar.set(Calendar.YEAR, Integer.valueOf(da[0]));  
	        calendar.set(Calendar.MONTH, Integer.valueOf(da[1]) - 1);// 月份比正常小1,0代表一月  
	        calendar.set(Calendar.DAY_OF_MONTH, Integer.valueOf(da[2]));  
	        holidayList.add(calendar);  
	    }  
	  
	}  
	
}