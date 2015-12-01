package customHandlers;
import java.util.ArrayList;
import java.util.HashMap;
import utils.CustomItem;
import utils.GroupTotal;

/*
 * 
 * */
public class SumCustomHandler {

	
	/*
	 * Coagulate total for the map 
	 * */
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
	/*
	 * GetTotal by ArrayList 
	 * 
	 * */
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
	 * Calculate total for Map by key groups  
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
	
	
	
	
	
}
