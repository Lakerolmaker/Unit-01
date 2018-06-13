package JackeLibrary;

import java.awt.Desktop;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.Arrays;

import com.sun.org.apache.xml.internal.resolver.helpers.FileURL;


public class fileClass {

	public String CurrentDir = System.getProperty("user.dir");
	//public String rootDir = $.computer.getDiskPath(0);
	public String desktop = System.getProperty("user.home") + File.separator + "Desktop";
	public String savePath =  System.getProperty("user.home") + File.separator + "lakerolmaker_programs";
	
	public void createTextFile(String fileName , String path) {
		
			String fname= path + File.separator + fileName +".txt";
		    File f = new File(path);
		    File f1 = new File(fname);

		    f.mkdirs();
		    try {
		        f1.createNewFile();
		    } catch (IOException e) {}
	}
	
	public void writeToTextFile(String fileName , String addedText , String path) {
		
		BufferedWriter bw = null;
		FileWriter fw = null;
		
		//: Gets the path
		String fname = path + File.separator + fileName +".txt";

		try {
				
			//: Writes to the file
			fw = new FileWriter(fname);
			bw = new BufferedWriter(fw);
			bw.write(addedText);

		} catch (IOException e) {
			console.log(e);
		} finally {

			try {
				// closes the writers
				if (bw != null)
					bw.close();

				if (fw != null)
					fw.close();

			} catch (IOException ex) {}

		}	
		
	}
	
	public void writeToExistingFile(String fileName , String addedText , String path) {
		
		BufferedWriter bw = null;
		FileWriter fw = null;
		
		//: Gets the path
		String fname = path + File.separator + fileName +".txt";
		
		String existingText = "";

		if(exists(fileName, path )) {
			existingText = readFromTextFile(fileName , path);
		}
		
		try {
				
			//: Writes to the file
			fw = new FileWriter(fname);
			bw = new BufferedWriter(fw);
			bw.write(existingText + addedText);

		} catch (IOException e) {} finally {

			try {
				// closes the writers
				if (bw != null)
					bw.close();

				if (fw != null)
					fw.close();

			} catch (IOException ex) {}

		}	
		
		
	}
	
	public String readFromTextFile(String fileName , String path) {
	
		BufferedReader br = null;
		FileReader fr = null;

		//: Gets the path
		String fname = path + File.separator + fileName +".txt";
		
		String output = null;
		
		try {
	
			fr = new FileReader(fname);
			br = new BufferedReader(fr);

			String sCurrentLine;
			output = "";
			while ((sCurrentLine = br.readLine()) != null) {
				output += sCurrentLine + "\n" ;
			}

		} catch (IOException e) {} finally {

			try {

				if (br != null)
					br.close();

				if (fr != null)
					fr.close();

			} catch (IOException ex) {}

		}
		
		return output;
	}
	
	public String[] readFromTextFile_Arr(String fileName , String path) {
		
		BufferedReader br = null;
		FileReader fr = null;

		//: Gets the path
		String fname = path + File.separator + fileName +".txt";
		
		
		ArrayList<String> temp = new ArrayList<String>();
		
		try {
	
			fr = new FileReader(fname);
			br = new BufferedReader(fr);
			
			String sCurrentLine;
			while ((sCurrentLine = br.readLine()) != null) {
				temp.add(sCurrentLine) ;
			}
			
		} catch (IOException e) {
			console.log(e);
		} finally {

			try {

				if (br != null)
					br.close();

				if (fr != null)
					fr.close();

			} catch (IOException ex) {}

		}
		
		return temp.toArray(new String[0]);
	}
	
	public String readFromFile(String fileName , String path) throws Exception {
		
		BufferedReader br = null;
		FileReader fr = null;

		//: Gets the path
		String fname = path + File.separator + fileName;
		
		String output = null;
		
		try {
	
			fr = new FileReader(fname);
			br = new BufferedReader(fr);

			String sCurrentLine;
			output = "";
			while ((sCurrentLine = br.readLine()) != null) {
				output += sCurrentLine + "\n" ;
			}

		} catch (IOException e) {
			throw new Exception("error");
		} finally {

			try {

				if (br != null)
					br.close();

				if (fr != null)
					fr.close();

			} catch (IOException ex) {}

		}
		
		return output;
	}
	
	public void dublicateFile(String fileName ,String path) throws IOException {
		
		String fname1 = path + File.separator + fileName;
		String fname2 = path + File.separator + "(copy)" + fileName;
		
		
		File orginal = new File(fname1);
		File copy = new File(fname2);
		
		copyFile(orginal, copy);
		
	}
	
	public void copyFile(File source, File dest) throws IOException {
	    FileChannel sourceChannel = null;
	    FileChannel destChannel = null;
	    try {
	        sourceChannel = new FileInputStream(source).getChannel();
	        destChannel = new FileOutputStream(dest).getChannel();
	        destChannel.transferFrom(sourceChannel, 0, sourceChannel.size());
	       }finally{
	           sourceChannel.close();
	           destChannel.close();
	       }
	}
	
	public boolean exists(String fileName , String path) {
		return new File(path + File.separator + fileName +".txt").exists();
	}
	
	public void createFolder(String folderName , String path) {
		try {
			new File(path + File.separator + folderName).mkdir();
		} catch (Exception e) {
			console.log(e);
		}
	}
	
	public void openExplorerWindow(String path) {
		try {
			Desktop.getDesktop().open(new File(path));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void ListDirectory(String path) {
		listDIR(path , 0);
	}
	
	private void listDIR(String path , int indentation) {
		
		File folder = new File(path);
		File[] listOfFiles = folder.listFiles();
		
		try {
		
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
		        System.out.print(indent + file.getName() + " | ");
		        System.out.println(getSizeEnding(file.length()));
		       
		    }
			
			if ((file.isDirectory()) && (file.isHidden() != true)){
				System.out.println(" ");
		        System.out.println(indent + File.separator + file.getName());
		        listDIR(file.getAbsolutePath() ,indentation + 1 );
		    }
			
		}
		} catch (Exception e) {}
	}
	
	public void createSavefolder() {
		$.file.createFolder("lakerolmaker_programs", System.getProperty("user.home"));	
	}
	
	public static String getSizeEnding(long size) {
		
		if(size < 1024) {
			return size + " Bytes";
		}else if(size < 1048576) {
			return size / 1024 + " Kilobytes";
		}else if(size < 1073741824) {
			return size / 1048576 +" Megabytes";
		}else if(size > 1073741824) {
			return size / 1073741824 + " Gigabytes";
		}
		return null;
	}

	public long megabytesToBytes(long bytes) {
		return bytes * 1048576;
	}
	
	public fileDeleter delete = new fileDeleter();
	
}
	
