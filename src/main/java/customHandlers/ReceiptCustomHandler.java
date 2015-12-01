package customHandlers;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class ReceiptCustomHandler {

	
	/*
	 * 
	 * 	final File folder = new File("/home/you/Desktop");
                	listFilesForFolder(folder);
	
	  */
	private static List<File> listFilesForFolder(final File folder) {
	  List<File> files=new ArrayList<>();
	  
		for (final File fileEntry : folder.listFiles())   		
			files.add(fileEntry);      
	    return files;
	}

	
	/*
	 * Read all the files 
	 * */
	public static void createMap()
	{
		
		ArrayList<ArrayList<String>> map=new ArrayList<ArrayList<String>>();//add all to map 
		//int mapIndex=0;
		
		final String folderPath = "/Users/betwar/Desktop/workSpace/boostrap-webapp/src/main/java/receipt";
		final File folder = new File(folderPath);
		List<File> files= listFilesForFolder(folder);
		
		for(File file:files){
		try{
	
			   FileInputStream fstream = new FileInputStream(file);
			   BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
			   String strLine;
			   /* read the log File line by line */
			   while ((strLine = br.readLine()) != null)   {
				      
				   System.out.println(strLine);
				 
			      }
			   } catch (Exception e) {
				     System.err.println("Error: " + e.getMessage());
			}
		  }
		}
	
	
	
	
}
