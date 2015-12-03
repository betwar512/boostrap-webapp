package customHandlers;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import utils.CustomItem;


/*
 * Handling serialization for WFPOS Elixer roofTop backlog 
 * */
public class ElixerLogHandler {

	
	
	
			public HashMap<String,ArrayList<CustomItem>> objectArray;  //CustomItemList 
			public ArrayList<ArrayList<String>>  stringMap; //Map Strings
			
			public ElixerLogHandler(){
				
				objectArray=createCustomItems();
				stringMap=createMap();
				
			}
			
			
			/*
			 * arg Folder Path as a file type 
			 * Capture all the files with sufix : .LOG 
			 */
			private static List<File> listFilesForFolder(final File folder) {
				  List<File> files=new ArrayList<>();
				  
					for (File fileEntry : folder.listFiles())  { 	//fpos Root folders 	
						//fileEntry.add(fileEntry);   
						
						if(fileEntry.isDirectory()){
						for(File secFolder:fileEntry.listFiles()){
						if (secFolder.getName().endsWith("LOG")){
						
						    	 files.add(secFolder);
						        
						 
							}
						  }
						}
					}  
				    return files;
				}

			/*
			 * Generate ArrayList out of LOG file 
			 * Map file into Array 
			 */
			public  ArrayList<ArrayList<String>> createMap()
			{

				
				
				
				
				ArrayList<ArrayList<String>> map=new ArrayList<ArrayList<String>>();//add all to map 
				//int mapIndex=0;
				
				final String folderPath = "C:\\PC_EFT";
				final File folder = new File(folderPath);
				List<File> files= listFilesForFolder(folder);
				
				for(File file:files){
				try{
			
					   FileInputStream fstream = new FileInputStream(file);
					   BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
					   String strLine;
					   /* read the log File line by line */
					   while ((strLine = br.readLine()) != null)   {
					  //doing the tag catching here 
			   
						boolean bool=false; //checker for B 
				   
						   //check for a tag 
						   if(strLine.contains("B     CUSTOMER COPY      B"))
						    bool=true;	 
						   
						   //end of the data 
					     if(strLine.contains("<?xml version")){
					    	 bool=false;     
					     }
						   
						   if(bool){	   
							  String[] strArray=strLine.split("B");//split by B	  
						      ArrayList<String> strList=new ArrayList<String>();
						  for (String s : strArray) {
							//  strList=new ArrayList<String>();//List for Strings	  
							  if(!s.contains("\\0d\\0a") && s.trim().length()>0){ // if not contains \0d\0a
							if( !s.trim().isEmpty()){
							strList.add(s.trim());
								System.out.println(s.trim());
							}			 
						}	  
					}
						map.add(strList);
				}
			}
					  br.close();
					} catch (Exception e) {
					     System.err.println("Error: " + e.getMessage());
				}
			}//top forEach 

				System.out.println("Done");

				return map;
				
			}
			
			
			
			/*
			 * Test level 3
			 * Version 2.0
			 * Map Array String to CustomItems with cardType as key 
			 * 
			 * */
			public  HashMap<String,ArrayList<CustomItem>> createCustomItems(){
				
		
				
				ArrayList<CustomItem> ci=new ArrayList<>();
				//get Map 
			ArrayList<ArrayList<String>> retMap=createMap();		
			HashMap<String,ArrayList<CustomItem>> map=new HashMap<>();

				for (ArrayList<String> t : retMap) {
		
					//Entry<Integer, ArrayList<String>> t = entries.next();	
					CustomItem item=new CustomItem(); //Pass an Item 
					
					/*bool flags to validate the pattern 
					 * NOT 100% but works in most of a time  
					 *
					 */
					boolean bool=false;
					boolean statBool=false;
					boolean auth=false;
				    int cardCounter=0; //2 line after date/time
				  //  int statusCounter=0;
	
					for (String r : t) {//String inside Array String 		
						//Try Items
						String[] stSplit=r.split(" "); //split String by space to detriment what's in our String  
					
						
						//cleanUp array 
						List<String> cleanSplit=new ArrayList<String>(Arrays.asList(stSplit));
					 cleanSplit.removeAll(Arrays.asList(""," ",null));
					 String stS=cleanSplit.get(0);
					 
					
					 //Card Type 
					 if(bool&& cardCounter>=1 && r.length()>3){ 
						 	item.cardType=r.toLowerCase();
						 	bool=false;	}
					 if(bool)
						 cardCounter++;
					 
					 
					 	if(statBool &&  auth){
					 		item.status=r;
					 		auth=false;
					 		statBool=false;
					 	}	 
					 if(statBool&& !auth)
					 {
						 if(r.contains("AUTH"))
							 auth=true;
						 else{
							item.status=r;
							statBool=false;	
						 }
					 }
					 
				
					 
					 		
					 		
					 if(r.startsWith("......")) {//check if it's card number 
							item.cardNumber=cleanSplit.get(0).substring(6);
							
							}else
									if(stS.contains("TERMINAL")) 
										item.terminalId=cleanSplit.get(2);	
									else
										if(stS.contains("DATE/TIME")){	
											String date=cleanSplit.get(1).substring(2, 5) +" "+cleanSplit.get(1).substring(0, 2) +" 20"+ cleanSplit.get(1).substring(5, 7)+" "+ cleanSplit.get(2);
											
										 Date d = TypeConvertor.convertStringToDateElixer(date);
										 item.dateTime=d;
										 bool=true;
						}
						else
							if(stS.contains("AMOUNT")) 
							item.purchaseAmount= cleanSplit.get(1);
						else 
							if(stS.contains("TOTAL")){
								item.totalAmount=TypeConvertor.stringTofloat(cleanSplit.get(2).substring(0));
								statBool=true;
							}else
							if(stS.contains("INV/ROC"))
								item.merchantId=cleanSplit.get(2);
					}	
					
					if(item.isValid()){//check if item is null or not 
				//	ci.add(item);
				
			
						
						
					if(!map.containsKey(item.cardType)){	
						ArrayList<CustomItem> c=new ArrayList<>();
						c.add(item);
					map.put(item.cardType,c);
					}else{
						
					ArrayList<CustomItem> c=map.get(item.cardType);
					c.add(item);
						
					}//else
				}//if valid 
			
			}
		
				return map;
				
			}
			
		
			
	
			

	

}
