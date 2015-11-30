package customHandlers;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import utils.CustomItem;
import utils.GroupTotal;

/*
 * 
 * */
public class SumCustomHandler {

	
	//Get total of the passed log file 
	public static Float getTotal(HashMap<String,ArrayList<CustomItem>> map ){
		
	
		Float total=0.0f;
		//Map iterator values 
		for (ArrayList<CustomItem> items : map.values()) {
			//total all 
			for (CustomItem customItem : items) {	
		
				if(customItem.totalAmount!=null)
	                 total+=customItem.totalAmount;			
			}
			
		}
		return total;
		
	}
	
	public static Float getTotal(ArrayList<CustomItem> list ){
		
		
		Float total=0.0f;
		//Map iterator values 
		for (CustomItem item :list) {
			//total all 
				if(item.totalAmount!=null)
	                 total+=item.totalAmount;			
		}
		return total;
		
	}
	
	
	
	
	/*
	 * Convert mapped items to ArrayList 
	 */
	
	public static ArrayList<CustomItem>  mapToList(HashMap<String,ArrayList<CustomItem>> map ){
		
		ArrayList<CustomItem> itemsAll=new ArrayList<>();
		//Map iterator values 
		for (ArrayList<CustomItem> items : map.values()) {
			for (CustomItem customItem : items) 
				itemsAll.add(customItem);		
		}
		return itemsAll;
				
	}
	
	
	
	
	/*
	 * Calculate total for each group of cardTypes 
	 * Return array of type GroupTotal 
	 * */
	public static ArrayList<GroupTotal> getGroupTotal(HashMap<String,ArrayList<CustomItem>> map){
		
		
		ArrayList<GroupTotal> groupTotals=new ArrayList<>(); //pass as Attribute 
		//iterat with key 
		for (String key : map.keySet()) {
		 GroupTotal gp=new GroupTotal();
		 gp.key=key;
			ArrayList<CustomItem> keyValues=map.get(key);
			float groupTotal=0;
			    for(CustomItem it:keyValues){
			    groupTotal+=it.totalAmount;		    	
			    }
			gp.total=groupTotal; //get total 
				groupTotals.add(gp);
		}
		
		return groupTotals;
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
