package customHandlers;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import utils.CustomItem;

public class GroupCustomHandler {


	
	/*
	 * Create Map out of passed ArrayList 
	 * Key = CardType 
	 * 
	 * */
	public static HashMap<String,ArrayList<CustomItem>> createMap(ArrayList<CustomItem> items){
		
		HashMap<String,ArrayList<CustomItem>> map=new HashMap<>();
		
		
		for(CustomItem item:items){
		
		if(!map.containsKey(item.cardType)){	
			ArrayList<CustomItem> c=new ArrayList<>();
			c.add(item);
		map.put(item.cardType,c);
		}else{
			
		ArrayList<CustomItem> c=map.get(item.cardType);
		c.add(item);
			
				}//else
			}
		return map;
		}
	
	/*
	 * Grouping method by TerminalId 
	 * 
	 * */
	public static HashMap<String,ArrayList<CustomItem>> createTerminalGroup(ArrayList<CustomItem> items){
		
				HashMap<String,ArrayList<CustomItem>> map=new HashMap<>();
		
		
		for(CustomItem item:items){
		
			if(!map.containsKey(item.cardType)){	
				ArrayList<CustomItem> c=new ArrayList<>();
					c.add(item);
						map.put(item.terminalId,c);
					}else{
			
						ArrayList<CustomItem> c=map.get(item.terminalId);
								c.add(item);
				}//else
			}	
		return map;	
	}
	

	/*
	 * Time comparison 
	 * 
	 * */
	public static ArrayList<CustomItem> afterPassedDate(ArrayList<CustomItem> list,Date minDate,Date maxDate){
		
		ArrayList<CustomItem> itemsAll=new ArrayList<>();
			for (CustomItem customItem : list) {
				if(customItem.dateTime.after(minDate)&& customItem.dateTime.before(maxDate))
					itemsAll.add(customItem);	
						
		}
		return itemsAll;
	}
	
	
	
}
