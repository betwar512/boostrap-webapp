package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import customHandlers.GroupCustomHandler;
import customHandlers.LogCustomHelper;
import customHandlers.SumCustomHandler;
import customHandlers.TypeConvertor;
import utils.CustomItem;
import utils.GroupTotal;

/**
 * Servlet implementation class TableController
 */
public class TableController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TableController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		CustomItem item=new CustomItem();
		item.cardNumber="124353545";
		CustomItem item1=new CustomItem();
		item1.cardNumber="564796";
		CustomItem item2=new CustomItem();
		item2.cardNumber="987456";
		CustomItem item3=new CustomItem();
		item3.cardNumber="987987";
		CustomItem item4=new CustomItem();
		item4.cardNumber="23189";
		
		ArrayList<CustomItem> items=new ArrayList<CustomItem>();
		items.add(item);
		items.add(item1);
		items.add(item2);
		items.add(item3);
		items.add(item4);
		
		
		
		request.setAttribute("items", items);
		request.getRequestDispatcher("tables.xhtml").forward(request, response);
	
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		
		String minTime=request.getParameter("mindatetime");
		String maxTime=request.getParameter("maxdatetime");
		System.out.println(minTime);
		
	     Date selectedDate=TypeConvertor.jqueryStringToDate(minTime); //Min time index
	     Date maxSelectedDate=TypeConvertor.jqueryStringToDate(maxTime);//Max time index 

		
		
	        LogCustomHelper lcg=new LogCustomHelper();//logger class with property HashMap list of all the transactions 
		
	        HashMap<String,ArrayList<CustomItem>> map = lcg.objectArray;
		  	ArrayList<CustomItem> itemsAll=SumCustomHandler.mapToList(map);
		   	 	
		  	
		  	
		  	
		  	
	        ArrayList<CustomItem> itemsTime=  GroupCustomHandler.afterPassedDate(itemsAll,selectedDate,maxSelectedDate);
		   	Collections.sort(itemsTime,new CustomItem());
		    Float total=customHandlers.SumCustomHandler.getTotal(map);	  
		    Float totalBySelecteddate=SumCustomHandler.getTotal(itemsTime);   
		    ArrayList<GroupTotal> groupTotals=customHandlers.SumCustomHandler.getGroupTotal(map);//total for all the transaction in log file 
		

		    
		//set variables in request object 
		 request.setAttribute("total", totalBySelecteddate);
		 request.setAttribute("items", itemsTime);
		 request.setAttribute("cards",groupTotals);
 	 
		// doGet(request,response);
		 
		 
		 

	
		
		
	}

}
