package programs;
import JackeLibrary.*;

import java.awt.Color;
import java.awt.Font;
import java.io.File;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;

import com.sun.java.swing.plaf.gtk.GTKConstants.Orientation;
import com.sun.javafx.tk.FontMetrics;

import UILibrary.UI;
import UILibrary.border;
import UILibrary.gridSelect;

public class SystemMonitor {

	public static UI frame1;
	
	public static void start() {
		
		
		// TODO : Add drive capacity both , left and total
		// TODO : Graphics card -- Not possible
		// TODO : optical drive
		
		frame1 = new UI("System Monitor" , "System Monitor", 430 , 300 ,false , false, true);
		

		String[] columnNames1 = {
				"Computer :",
				"Operating System",
               	};
		
		String computerName = $.computer.getComputerName();
		Object[][] data1 = {
			    {"Computer :" , computerName},
			    {"Operating System" , $.computer.getOSname()},
			    {"Operating Version" , $.computer.getOSversion()},
			    {"Operating Arch" , $.computer.getOsArch()},
			    {"Atached Monitors" , $.computer.getNumberOfDisplays()},  
			};
		
		frame1.addGridTable("Computer Info", 150, 10, 250 , 80,  data1 , columnNames1);
		
		
		String[] columnNames2 = {
				"1",
				"2",
               	};
		Object[][] data2;
		
		if($.network.netIsAvailable()) {
			Object[][] predata = {
				    {"Ip-adress" , 	$.network.getPrivateIpAdress() },
				    {"Pulic adress" ,$.network.getpublicIpAdres() },
				    {"Local Hostname" , $.network.getLocalHostname()},
				    {"Public Hostname" , $.network.getPublicHostname()},  
				    {"Internet provider" , $.network.getInternetProvider()},  
				};
			data2 = predata;
		}else {
			Object[][] predata = {
				    {"Ip-adress" , 	"No internet" },
				    {"Pulic adress" , "No internet" },
				    {"Local Hostname" ,"No internet"},
				    {"Public Hostname" , "No internet"},  
				    {"Internet provider" ,"No internet"},  
				};
			data2 = predata;
		}
		
		frame1.addGridTable("network Info", 150, 100, 250 , 80 , data2 , columnNames2);
		
		
		String[] columnNames3 = {
				"Computer :",
				"Operating System",
               	};
		
		Object[][] data3 = {
			    {"Country" , $.user.getCountry()},
			    {"Region" , $.user.getRegion()},
			    {"City" , $.user.getCity()},
			    {"Postal Code" , $.user.getPostalCode()},
			};
		
		frame1.addGridTable("user Info", 150, 200, 250 , 80, data3 , columnNames3);
		
		frame1.gridTable.setSelectModel("user Info", gridSelect.CellSelect);
		frame1.update();
		
	
		//: Ram
		frame1.addLabel("ram label", "Ram", 40 , 200, 50, 50);
		frame1.addPogressbar("RamUsage", 45, 10, 15, 200, false, 0, 100);
		frame1.progressbar.setOrientation("RamUsage", 1);
		
		String ramAmout = Long.toString($.computer.getTotallRam()) + " GB";
		frame1.addLabel("ramAmout", ramAmout , 9 , 116, 50, 10);
		
		
		//: CPU
		frame1.addLabel("CPU label", "CPU", 97, 200, 50, 50);
		frame1.addPogressbar("cpuLoad", 102, 10, 15, 200, false, 0, 100);
		frame1.progressbar.setOrientation("cpuLoad", 1);
			
		createDiskData();
		
		//: updates the ram usage every second
		$.time.setInterval("system monitor_meme" , 0, 1000, ()->{
			int usedMem = (int) $.computer.getUsedMem();
			int totallMem = (int) $.computer.getVMTotalMem();
			int memProcentage = totallMem/usedMem;
			frame1.updateProgressbar("RamUsage", memProcentage );
		});
		
		//: updates the CPU usage every second
		$.time.setInterval("system monitor_cpu",0, 1000, ()->{
			int cpuLoad = (int)($.computer.cpuLoad() * 100);
			frame1.updateProgressbar("cpuLoad", cpuLoad);
		});
		
		
		
		showAll();
		
	}
	
	private static ArrayList<UI> diskFrames = new ArrayList<UI>();
	
	private static void createDiskData() {
		
		int lenght = 0;
		
		for(int i = 0 ; i < $.computer.getAmountOfDrives(); i++) {
		
			if($.computer.getNormalDisk(i) == true) {
			
			UI createdUI = new UI("DriveFrame" + i , "", lenght , 0, 80 , 300 , false , false ,false);
			
			createdUI.addPogressbar("progress", 30, 55, 15, 200, false, 0, 100);
			createdUI.progressbar.setOrientation("progress", 1);
			createdUI.updateProgressbar("progress", $.computer.getDiskProcentage(i));
			
			String name1 = "label1" + i;
			createdUI.addLabel(	name1, "Disk : " + $.computer.getDiskname(i) , 10, 10, 60, 20);
			createdUI.label.setborder(name1 , border.loweredbevel);
			createdUI.label.setFontFamliy(name1 , Font.SANS_SERIF);
			
			String name2 = "label2" + i;
			String text = Integer.toString($.computer.getDiskProcentage(i));
			createdUI.addLabel( name2 , text , 10, 150, 20, 20);
			
			diskFrames.add(createdUI);
	
			}else {
				
				UI createdUI = new UI("DriveFrame" + i , "", lenght , 0, 80 , 300 , false , false ,false);
							
				String name1 = "label1" + i;
				createdUI.addLabel(	name1, "Optical" , 10, 10, 60, 20);
				createdUI.label.setborder(name1 , border.loweredbevel);
				createdUI.label.setFontFamliy(name1 , Font.SANS_SERIF);
				
				String name2 = "label2" + i;
				createdUI.addLabel(	name2, "Drive" , 10, 35, 60, 20);
				createdUI.label.setborder(name2 , border.loweredbevel);
				createdUI.label.setFontFamliy(name2 , Font.SANS_SERIF);
				
				String name3 = "label3" + i;
				createdUI.addLabel(	name3, $.computer.getDiskname(i) , 10, 55, 60, 20);
				createdUI.label.setborder(name3 , border.loweredbevel);
				createdUI.label.setFontFamliy(name3 , Font.SANS_SERIF);
				
				diskFrames.add(createdUI);
							
			}
			
			lenght += 80;
		}
	}
	
	public static void showAll(){
		frame1.show();
		for (UI ui : diskFrames) {
			ui.show();
		}
	}
	
}
