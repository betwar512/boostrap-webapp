package customHandlers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TypeConvertor {

	
	
	/*
	 * Convert String DateTime to java Type DateTime
	 * dateFormat="dd/MM/yy HH:mm"
	 */
	public static final Date stringToDate(String str){
	
	 SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yy HH:mm");
	 
	 Date date=null;
     try
     {
          date = simpleDateFormat.parse(str);
       //  System.out.println("date : "+simpleDateFormat.format(date));
     }
     catch (ParseException ex)
     {
         System.out.println("Exception "+ex);
     }
     
     return date;
	}

	
	
	public static final Date jqueryStringToDate(String str){
		
		 SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy hh:mm");
		 
		 Date date=null;
	     try
	     {
	          date = simpleDateFormat.parse(str);
	       //  System.out.println("date : "+simpleDateFormat.format(date));
	     }
	     catch (ParseException ex)
	     {
	         System.out.println("Exception "+ex);
	     }
	     
	     return date;
		}
	
	
	
	public static final Date convertStringToDateElixer(String str){
		
 SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MMM dd yyyy HH:mm");
		 
		 Date date=null;
	     try
	     {
	          date = simpleDateFormat.parse(str);
	        System.out.println("date : "+simpleDateFormat.format(date));
	     }
	     catch (ParseException ex)
	     {
	         System.out.println("Exception "+ex);
	     }
	     
	     return date;
	}
	
	/*
	 * Convert string to Float  
	 */
  public static final Float stringTofloat(String str){
	
	String number=str.substring(1);
	Float f=Float.parseFloat(number);
	
	return f;
    }
  
  public static void cardGroups(){
	    
  }
  
  
  /*
   * Time formatter to 24h from 12h
   * */
  public static String convertTime(String str){
		  SimpleDateFormat displayFormat = new SimpleDateFormat("HH:mm");
		  SimpleDateFormat parseFormat = new SimpleDateFormat("hh:mma");
		  String time = "";
		  Date date=new Date();
		try {
			date = parseFormat.parse(str);
			time = displayFormat.format(date);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		  return time;
     }
  
  
}
