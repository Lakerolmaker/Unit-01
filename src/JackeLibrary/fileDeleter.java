package JackeLibrary;
import java.io.File;
import java.io.FileFilter;
import java.util.Arrays;

import sun.applet.Main;

public class fileDeleter {
	
	public static String CurrentDir = System.getProperty("user.dir");
	String path = "";
	
	private long deleteLimit = 0;
	private long totallFiles = 0;
	private int filesDeleted = 0;
	private int filesFailed = 0;
	
	//: Deletes files in a directory under a specific megabyte
	public void FilesLowerThan(String path , long bytes) {
	
		deleteLimit = bytes * 1048576;

		console.external.openStandalone(()->{
			
		console.external.print("File deleter V 1.0 ");
		console.external.printLine();
		console.external.print("Log : ");
		
		deleteFiles(path, 0);
		
		console.external.print("");
		console.external.print("Totall files : " + totallFiles );
		console.external.print("Files deleted : " + filesDeleted );
		console.external.print("Files Failed : " + filesFailed );
		});
	}
	
	private void deleteFiles(String path, int indentation) {
		
		File folder = new File(path);
		File[] listOfFiles = folder.listFiles();
		
	      Arrays.sort(listOfFiles, (f1, f2) -> {
	          if (f1.isDirectory() && !f2.isDirectory()) {
	             return 1;
	          } else if (!f1.isDirectory() && f2.isDirectory()) {
	             return -1;
	          } else {
	             return f1.compareTo(f2);
	          }
	       });

		String indent = "";
		
		for(int i = 0 ; i < indentation; i++) {
			indent += "\t";
		}
		
		
		for (File file : listOfFiles) {
		
		    if ((file.isFile()) && (	file.isHidden() != true)) {
		        
		    		totallFiles++;
		        long fileSize = file.length();
		        
		        if(fileSize > deleteLimit) {
		        		if(file.delete() == true) {
		        			console.log("file deleted : ");
		        			console.logg(file.getName());
		        			filesDeleted++;
		        		}else {
		        			console.log("file FAILED : ");
		        			console.logg(file.getName());
		        			filesFailed++;
			        }
		        } 
		    }
			
			if ((file.isDirectory()) && (file.isHidden() != true)){
		        deleteFiles(file.getAbsolutePath() ,indentation + 1 );
		    }
			
		}
		
		
	}


}
