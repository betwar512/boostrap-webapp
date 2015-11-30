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

import customHandlers.LogCustomHelper;
import customHandlers.SumCustomHandler;
import customHandlers.TypeConvertor;
import utils.CustomItem;
import utils.GroupTotal;


/**
 * Servlet implementation class FposController
 */
public class FposController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FposController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
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
		   	 	
		  	
		  	
		  	
		  	
	        ArrayList<CustomItem> itemsTime=  SumCustomHandler.afterPassedDate(itemsAll,selectedDate,maxSelectedDate);
		   	
		  
		   	Collections.sort(itemsTime,new CustomItem());
		    Float total=customHandlers.SumCustomHandler.getTotal(map);	
		    
		    
		    Float totalBySelecteddate=SumCustomHandler.getTotal(itemsTime);
		    
		    ArrayList<GroupTotal> groupTotals=customHandlers.SumCustomHandler.getGroupTotal(map);//total for all the transaction in log file 
		

		    
		//set variables in request object 
		 request.setAttribute("total", totalBySelecteddate);
		 request.setAttribute("items", itemsTime);
		 request.setAttribute("cards",groupTotals);
		 
		String url="Table.jsp"; //relative url for display jsp page
		
		//HttpSession session =request.getSession();
		 request.getRequestDispatcher(url).forward(request,response);
		
	}

}
