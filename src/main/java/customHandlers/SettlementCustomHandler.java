package customHandlers;

import java.util.ArrayList;

import utils.Settlement;

public class SettlementCustomHandler {

	
	public static ArrayList<Settlement>  settleMents(ArrayList<ArrayList<String>> retMap){
		
		ArrayList<Settlement> list=new ArrayList<>();
		for (ArrayList<String> t : retMap){
		
		
		//int PRE SETTLEMENT counter
	//	int preSettlementCounter=0;
		boolean settlement=false;
		boolean terminalBool=false;

		 Settlement settl=new Settlement();
		
		for (String r : t) {//String inside Array String x
			String[] stSplit=r.split(" "); //split String by space to detriment what's in our String  
			String stS=stSplit[0];
		  //  preSettlementCounter++;
		    if(r.contains("PRE SETTLEMENT")){
					settlement=true;
	    }
		    
			if(terminalBool){ //terminal code comes 1 iteration after Terminal keyWord 	
				settl.terminalId=stSplit[0];
				terminalBool=false;
			        }
		
	if(settlement){
			if(stS.contains("Merchant"))settl.merchantId=stSplit[6];	     
			else
				if(stS.contains("Terminal")) terminalBool=true;    //nextLine 
				else
					if(stS.contains("SETTLEMENT"))settl.date=stSplit[6];
						else
							if(r.contains("Purch Amount"))settl.purchAmount=stSplit[6];
		 					else
		 						if(r.contains("Purch Count"))settl.purchCount=stSplit[12];

	        }
		}
		
		
		if(settl.isValid())
		list.add(settl);
		settlement=false;
		
	 }
		return list;
   }	
}
