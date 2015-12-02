package customHandlers;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import utils.CustomItem;

public class ReceiptCustomHandler {

	public HashMap<String,ArrayList<CustomItem>> map;
	public ArrayList<ArrayList<String>>  stringMap;
	
	
	public ReceiptCustomHandler(){
		
		
		 stringMap=createReceiptMap();
		 map=createCustomMap();
		
	}
	
	
	/*
	 * 
	 * 	final File folder = new File("/home/you/Desktop");
                	listFilesForFolder(folder);
	
	  */
	private static List<File> listFilesForFolder(final File folder) {
	  List<File> files=new ArrayList<>();
	  
		for (File fileEntry : folder.listFiles())  { 	//fpos Root folders 	
			//fileEntry.add(fileEntry);   
			
			if(fileEntry.isDirectory()){
			for(File secFolder:fileEntry.listFiles()){
			if (secFolder.isDirectory()){
				if(secFolder.getName().equals("Receipt")) {
			     for(final File file: secFolder.listFiles()){
			    	 files.add(file);
			        }
			      }
				}
			  }
			}
		}  
	    return files;
	}

	
	/*
	 * Read all the files 
	 * */
	public ArrayList<ArrayList<String>> createReceiptMap()
	{
		
		ArrayList<ArrayList<String>> map=new ArrayList<ArrayList<String>>();//add all to map 
		//int mapIndex=0;
		
		final String folderPath = "/Users/betwar/Desktop/PC_EFT";
		final File folder = new File(folderPath);
		List<File> files= listFilesForFolder(folder);
		
		
		for(File file:files){
			
	     	try{
	
			   FileInputStream fstream = new FileInputStream(file);
			   BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
			   String strLine;
			   boolean bool=false;
			   int lineCounter=0; //if is line counter 2 that make ziro and add to array 
			   /* read the log File line by line */
			   ArrayList<String> array=new ArrayList<String>();
			   while ((strLine = br.readLine()) != null)   {
				  
		
				  //  CUSTOMER COPY
				    
				    
				    if(strLine.trim().equals("CUSTOMER COPY"))
				    {
				    	bool=true;
				    }else
				    	if(strLine.equals("------------------------")){	    	
				    	bool=false;
				    	lineCounter++;
				    	} 
				    
				    
				    	if(!bool && lineCounter==2){
				    		
				    		
				    		@SuppressWarnings("unchecked")
							ArrayList<String> cloneArray=(ArrayList<String>) array.clone();
				    		if(cloneArray.size()>5)
				    		map.add(cloneArray);
					    	array.clear();
					    	lineCounter=0;
				    	}
				    
				    	
				    	
				    
				    if(bool && strLine.trim().length()>1){				    	
				    	array.add(strLine.trim());	
				    }

				    	

				   System.out.println(strLine);
			      }
			br.close(); 
	     	} catch (Exception e) {
				     System.err.println("Error: " + e.getMessage());
			}
		  }
		
		
		return map;
		}
	

	
	/*
	 * CreaTE CUSTOMITEM Map with key CardTYPE
	 * 
	 * */
	
	private HashMap<String,ArrayList<CustomItem>> createCustomMap(){
		
		ArrayList<ArrayList<String>> array=stringMap;
		
		HashMap<String,ArrayList<CustomItem>> map=new HashMap<String,ArrayList<CustomItem>>();		
			
		for(ArrayList<String> list:array){
			
			
			CustomItem item=new CustomItem();
			
			item.merchantId=list.get(2).split(" ")[6];//
			item.cardType=list.get(7);	//
			//item.status=list.get(12);//
			item.terminalId=list.get(3).split(" ")[8];
			
		boolean statusBool=false;
		int statusCounter=0;
		
			
			
			for(String r : list){
				
				
				if(statusBool) {
					if(statusCounter<1)
						statusCounter++;
					else 
						if(statusCounter==1)
					{	
						statusCounter=0;
						item.status=r;
						statusBool=false;	
					}
				}
				
				
				if(r.startsWith("#")) {//check if it's card number 
					item.cardNumber=r.split(" ")[0].substring(12); //last 4 digit 
					statusBool=true;		
					}
				    else
				    	if(r.contains("Date/Time")){
					
					 Date d = TypeConvertor.stringToDate(r.substring(9).trim());
					item.dateTime=d;
				}
				else 
					if(r.startsWith("PURCHASE")){
						
						String[] s=r.split(" ");
						item.purchaseAmount=s[s.length-1].substring(1);
						}
				else 
					if(r.contains("TOTAL AUD")){
						String[] t=r.split(" ");
						t[t.length-1].substring(1);
						
						item.totalAmount=Float.valueOf(t[t.length-1].substring(1));
						}
			      }	
				


			
			if(!map.containsKey(item.cardType)){	
				ArrayList<CustomItem> c=new ArrayList<>();
				c.add(item);
			map.put(item.cardType,c);
			}else{
				
			ArrayList<CustomItem> c=map.get(item.cardType);
			c.add(item);
				
			}//else
			
		}//foreach

		return map;

		
	}
	
	
	
	
	
	
}
