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

import customHandlers.ElixerLogHandler;
import customHandlers.GroupCustomHandler;
import customHandlers.LogCustomHelper;
import customHandlers.ReceiptCustomHandler;
import customHandlers.SettlementCustomHandler;
import customHandlers.SumCustomHandler;
import customHandlers.TypeConvertor;
import utils.*;


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

		request.getRequestDispatcher("tables.xhtml").forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 * 
	 * 
	 * Servlet Handle calls to Other classes to create 
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	//	ReceiptCustomHandler rc=new ReceiptCustomHandler();
	//   LogCustomHelper lcg=new LogCustomHelper();//logger class with property HashMap list of all the transactions 
	   ElixerLogHandler eLh=new ElixerLogHandler();
	  
	  	
			//JQuery passed data 
			String minTime=request.getParameter("mindatetime");
			String maxTime=request.getParameter("maxdatetime");
			System.out.println(minTime);	
			Date selectedDate=TypeConvertor.jqueryStringToDate(minTime); //Min time input
			Date maxSelectedDate=TypeConvertor.jqueryStringToDate(maxTime);//Max time input   
	     
	        // HashMap<String,ArrayList<CustomItem>> map =rc.map; //lcg.objectArray;
		  	ArrayList<CustomItem> itemsAll=SumCustomHandler.mapToList(eLh.objectArray);
		  	//Settlements 
		    ArrayList<Settlement> sett=SettlementCustomHandler.settlements(eLh.stringMap);
		  	// create list of items in specified proximity 
	        ArrayList<CustomItem> itemsTime=  GroupCustomHandler.afterPassedDate(itemsAll,selectedDate,maxSelectedDate);  
		   	Collections.sort(itemsTime,new CustomItem());//Sort collection interface 
		    Float totalBySelecteddate=SumCustomHandler.getTotal(itemsTime);    
		    HashMap<String,ArrayList<CustomItem>> timeSortedMap=GroupCustomHandler.createMap(itemsTime);//Sort by card type     
		    ArrayList<GroupTotal> groupTotals=customHandlers.SumCustomHandler.getGroupTotal(timeSortedMap);//total for all the transaction in log file 
	

		    //  HashMap<String,ArrayList<CustomItem>> terminalMap = rc.map;
		        ArrayList<CustomItem> terminalItemsAll=SumCustomHandler.mapToList(eLh.objectArray);        
		        HashMap<String,ArrayList<CustomItem>> mapTerminal=GroupCustomHandler.createTerminalGroup(terminalItemsAll);
		        ArrayList<TotalTerminalCard> terminalGroupTotals=customHandlers.SumCustomHandler.getTerminalCardTotal(mapTerminal);
		        Float total=0f;
		        for(TotalTerminalCard it:terminalGroupTotals)
		        total+=it.total;
		    ///////
	
		 request.setAttribute("terminaltotal", total);   
	     request.setAttribute("terminalgroups", terminalGroupTotals);
		    
		//set variables in request object 
		 request.setAttribute("settlement", sett);
		 request.setAttribute("total", totalBySelecteddate);
		 request.setAttribute("items", itemsTime);
		 request.setAttribute("cards",groupTotals);
		  
		 doGet(request,response);	 
	}

}
