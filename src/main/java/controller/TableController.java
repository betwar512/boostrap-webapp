package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import customHandlers.GroupCustomHandler;
import customHandlers.LogCustomHelper;
import customHandlers.SumCustomHandler;
import utils.*;

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

		    LogCustomHelper lcg=new LogCustomHelper();//logger class with property HashMap list of all the transactions 
		    
	        HashMap<String,ArrayList<CustomItem>> map = lcg.objectArray;
	        ArrayList<CustomItem> itemsAll=SumCustomHandler.mapToList(map);        
	        HashMap<String,ArrayList<CustomItem>> mapTerminal=GroupCustomHandler.createTerminalGroup(itemsAll);
	        ArrayList<TotalTerminalCard> groupTotals=customHandlers.SumCustomHandler.getTerminalCardTotal(mapTerminal);
	        Float total=0f;
	        for(TotalTerminalCard it:groupTotals)
	        total+=it.total;
		    request.setAttribute("total", total);   
		    request.setAttribute("groups", groupTotals);
			request.getRequestDispatcher("fullreport.xhtml").forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// doGet(request,response);	
	}

}
