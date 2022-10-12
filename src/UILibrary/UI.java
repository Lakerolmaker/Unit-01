package UILibrary;

import static LakerLibrary.Input.*;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.GridBagConstraints;
import java.awt.LayoutManager;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.SpinnerDateModel;
import javax.swing.SpinnerListModel;
import javax.swing.SpinnerModel;
import javax.swing.SwingUtilities;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.TableModel;

import com.sun.prism.paint.Color;


import LakerLibrary.console;
import UILibrary.*;
import javafx.geometry.Insets;
import javafx.scene.image.Image;

public class UI {
	
	public  JFrame frame; 
	public String framename;
	private  ArrayList<JTextField> activeTextFields = new ArrayList<JTextField>(); 
	private  ArrayList<JButton> activebuttons = new ArrayList<JButton>(); 
	private  ArrayList<JLabel> activelabels = new ArrayList<JLabel>(); 
	private  ArrayList<JProgressBar> activeProgressBar = new ArrayList<JProgressBar>(); 
	private  ArrayList<JSlider> activeSliders = new ArrayList<JSlider>(); 
	private  ArrayList<JComboBox> activeDropdown = new ArrayList<JComboBox>(); 
	private  ArrayList<JPasswordField> activePasswordFields = new ArrayList<JPasswordField>(); 
	private  ArrayList<JSpinner> activeSpinners = new ArrayList<JSpinner>(); 
	private  ArrayList<JTable> activeGridTable = new ArrayList<JTable>(); 
	
	
	public UI(String frameID, String title , int width , int height ,  Boolean fullScreen ,  Boolean togglefullscreen, Boolean mainWindow) {
		
		framename = frameID;
		
		//: Creates the frame object
		JFrame createdFrame = new JFrame(frameID); 
		//: Sets the dimensions the the frame
		createdFrame.setSize(width, height);
		//: Defines that the window will close when the user presses the close button if it is the main window
		if(mainWindow) {
			createdFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		}else {
			createdFrame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		}
		//: Sets the name of frame so it canbe acced later
		createdFrame.setName(frameID);
		
		//: Sets the title of the window
		createdFrame.setTitle(title);
		
		//: Defines fullscreen mode if it is specified
		if(fullScreen)
			createdFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		
		//: Defines if you can resize the window
		createdFrame.setResizable(togglefullscreen);
		
	
		//: Get´s the dimension of the window
		GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
		int ScreenWidth = gd.getDisplayMode().getWidth();
		int ScreenHeight = gd.getDisplayMode().getHeight();
			
		//: Calculates the center of the screen and removes the the size of the frame.
		int x = ScreenWidth/2 - (int)createdFrame.getSize().getWidth()/2;
		int y = ScreenHeight/2 - (int)createdFrame.getSize().getHeight()/2;
		//: Sets the coordinates of the frame in the center of the screen
		createdFrame.setLocation(x,y);
				
		//: Shows the frame
		createdFrame.setLocationRelativeTo(null);
		
		createdFrame.setLayout(null);
		
		//:Adds the frame to a list so it can be accessed
		frame = createdFrame;
		
		//: Adds the events to the frame
		this.addEvents();
	}

	public UI(String frameNameInput, String title , int x , int y , int width , int height ,  Boolean fullScreen ,  Boolean togglefullscreen , Boolean mainWindow) {
		
		framename = frameNameInput;
		
		//: Creates the frame object
		JFrame createdFrame = new JFrame(frameNameInput); 
		//: Sets the dimensions the the frame
		createdFrame.setSize(width, height);
		
		//: Defines that the window will close when the user presses the close button if it is the main window
		if(mainWindow) {
			createdFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		}else {
			createdFrame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		}

		//: Sets the name of frame so it canbe acced later
		createdFrame.setName(frameNameInput);
		
		//: Sets the title of the window
		createdFrame.setTitle(title);
		
		//: Defines fullscreen mode if it is specified
		if(fullScreen)
			createdFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		
		//: Defines if you can resize the window
		createdFrame.setResizable(togglefullscreen);

		//: Sets coordinates
		createdFrame.setLocation(x,y);
				
		//: Sets the layout
		createdFrame.setLayout(null);
		
		//:Adds the frame to a list so it can be accessed
		frame = createdFrame;
		
		//: Adds the events to the frame
		this.addEvents();
	}
	
	private void addEvents() {
		
		frame.addWindowListener(new WindowListener() {

			@Override
			public void windowOpened(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowClosing(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowClosed(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowIconified(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowDeiconified(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowActivated(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowDeactivated(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
	    	
	    	
	    });
		
	}
	
	public void addButton(String ID, int x , int y , int width , int height , Runnable function) {

		JPanel panel = new JPanel();
		   
		panel.setLayout(null);
	   	panel.setSize(new Dimension(width, height));
	   	panel.setBounds(x, y, width, height);
	  

	   	JButton button = new JButton(ID);
	   	button.setName(ID);
	   	button.setBounds(0, 0, width, height);
		panel.add(button);
		
		frame.add(panel);
		
		activebuttons.add(button);
		
		button.addActionListener(new ActionListener()
		{
		  public void actionPerformed(ActionEvent e)
		  {
			  function.run();
		  }
		});


	}
	
	public void addLabel(String ID , String LabelText,  int x , int y , int width , int height) {
				
		JPanel panel = new JPanel();
		   
		panel.setLayout(null);
	   	panel.setSize(new Dimension(width, height));
	   	panel.setBounds(x, y, width, height);
	  
	   	JLabel label = new JLabel(LabelText);
	   	label.setName(ID);
	   	label.setBounds(0, 0, width, height);
		
	   	label.addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				console.log("hello");
			}
			
			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				console.log("hello");
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				console.log("hello");
			}
		});
	   	
	   	
	   	panel.add(label);
	   	
	
	   	
		frame.add(panel);
		
		activelabels.add(label);
	
	}
		
	public void addTextField(String ID,  String deafualtInput, int x , int y , int width , int height) {

		JPanel panel = new JPanel();
		   
		panel.setLayout(null);
	   	panel.setSize(new Dimension(width, height));
	   	panel.setBounds(x, y, width, height);
	  

	   	JTextField input = new JTextField(deafualtInput);
	   	input.setName(ID);
	   	input.setBounds(0, 0, width, height);
	   	
	   	panel.add(input);
		frame.add(panel);
		
		activeTextFields.add(input);
		
	}
		
	public void addPogressbar( String progressBarName  , int x , int y , int width , int height,  boolean showNumber , int min , int max) {
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
	   	panel.setSize(new Dimension(width, height));
	   	panel.setBounds(x, y, width, height);
	  

	   	JProgressBar progressBar = new JProgressBar(min, max);
	   	progressBar.setName(progressBarName);
	   	progressBar.setBounds(0, 0, width, height);
		progressBar.setStringPainted(showNumber);
		progressBar.setOrientation(0);
		panel.add(progressBar);
		
		frame.add(panel);
		
		activeProgressBar.add(progressBar);
		
	}
	
	public void updateProgressbar( String progressBarName , int procentage){
		
		JProgressBar progressBar = null;

		for(int i = 0 ; i < activeProgressBar.size(); i++) {

			if(activeProgressBar.get(i).getName() == progressBarName )
				progressBar = activeProgressBar.get(i);
		}
		
		
		progressBar.setValue(procentage);
	}

	public void addSlider(String sliderName, int x , int y , int width , int height, int min, int max,int startingPoint , boolean horizontal){
		
		JPanel panel = new JPanel();
		 
		panel.setLayout(null);
	   	panel.setSize(new Dimension(width, height));
	   	panel.setBounds(x, y, width, height);
	  
	    JSlider slider = null;
		
			if(horizontal == true) 
				 slider = new JSlider(JSlider.HORIZONTAL, min, max, startingPoint);
			else
				 slider = new JSlider(JSlider.VERTICAL,  min, max , startingPoint);

		slider.setName(sliderName);
		slider.setBounds(0, 0, width, height);
		slider.setMinorTickSpacing(2);
		slider.setMajorTickSpacing(10);
		slider.setPaintTicks(true);
		slider.setPaintLabels(true);
		slider.setLabelTable(slider.createStandardLabels(10));
		panel.add(slider);
		
		frame.add(panel);
		activeSliders.add(slider);
		    
	    
	    
	    		
	}
	
	public int getSliderValue(String sliderName){
		
		int Slidervalue = 0;
		

		for(int i = 0 ; i < activeSliders.size(); i++) {

			if(activeSliders.get(i).getName() == sliderName )
				Slidervalue = activeSliders.get(i).getValue();
			}	
		
		
		return Slidervalue;
		
	}
	
	public void addDropdown(String dropdownName, int x , int y , int width , int height , String[]Choices ) {
        
        JPanel panel = new JPanel();
 	   
		panel.setLayout(null);
	   	panel.setSize(new Dimension(width, height));
	   	panel.setBounds(x, y, width, height);
	  

        JComboBox<String> dropdown = new JComboBox<String>(Choices);
        dropdown.setName(dropdownName);
        dropdown.setBounds(0, 0, width, height);
		panel.add(dropdown);
		
		frame.add(panel);
		
		activeDropdown.add(dropdown);

		} 
	
	public String getDropdownItem(String dropdownName) {
		
		String dropdownItem = "";
	
		for(int i = 0 ; i < activeDropdown.size(); i++) {
			if(activeDropdown.get(i).getName() == dropdownName )
				dropdownItem = activeDropdown.get(i).getSelectedItem().toString();
			}	
		
		return dropdownItem;
	
	}
	
	public void addPasswordField(String fieldName, int x , int y , int width , int height) {
		
		JPanel panel = new JPanel();
	   
		panel.setLayout(null);
	   	panel.setSize(new Dimension(width, height));
	   	panel.setBounds(x, y, width, height);
	  

		JPasswordField passwordField = new JPasswordField(10);
		passwordField.setName(fieldName);
		passwordField.setBounds(0, 0, width, height);
		panel.add(passwordField);
		
		frame.add(panel);
		
	   
	}
	
	public void addSpinner(String dropdownName, int x , int y , int width , int height , boolean editable , String[]Choices ) {

		 	JPanel panel = new JPanel();
	 	   
			panel.setLayout(null);
		   	panel.setSize(new Dimension(width, height));
		   	panel.setBounds(x, y, width, height);
		  
		   	JSpinner spinner = new JSpinner();
		  
		   	
		   	SpinnerModel model = new SpinnerListModel(Arrays.asList(Choices));
		   	
		    spinner.setModel(model);
		    
		    if(editable)
		    		spinner.setEditor(new JSpinner.DefaultEditor(spinner));
		    
	        spinner.setBounds(0, 0, width, height);
			panel.add(spinner);
			
			frame.add(panel);
			
			activeSpinners.add(spinner);
	}
	
	public void addGridTable(String ID, int x , int y , int width ,int height , Object[][] rowData, Object[] columnLabelNames) {
		
		JPanel panel = new JPanel();
		   
		panel.setLayout(null);
	   	panel.setSize(new Dimension(width, height));
	   	panel.setBounds(x, y, width, height);
	  
	   	
	   	JTable Table = new JTable(rowData, columnLabelNames);

	   	Table.setName(ID);
	   	
	   	Table.setAutoCreateRowSorter(true);

	   	
//	   	Table.setGridColor(java.awt.Color.BLACK);
//	   	Table.setBorder(border.blackline);
	   	
	   	Table.setBackground(new java.awt.Color(230, 228, 228));
	   	Table.setShowGrid(false);
	   	Table.setOpaque(false);
	   	Table.setBounds(0, 0, width, height);
	   	Table.setAutoResizeMode(0);
	   	
		panel.add(Table);
		
		frame.add(panel);
		
		activeGridTable.add(Table);
		
		
	}
	
	public void addScrollBox(Component addedObejct , int width , int height) {
	
		int x = addedObejct.getParent().getX();
		int y = addedObejct.getParent().getY();
		
		console.logg(" x : " + addedObejct.getParent().getX());
		console.logg(" y : " + y);
		
		JPanel panel = new JPanel();
		   
		panel.setLayout(null);
	   	panel.setSize(new Dimension(height, width));
	   	panel.setBounds(x, y, height, width);
		
		JScrollPane scrollPane = new JScrollPane(addedObejct);
		scrollPane.setName(addedObejct.getName());
		scrollPane.setBounds(addedObejct.getX(), addedObejct.getY(), height, width);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
	//	scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		
		
		
		panel.add(scrollPane);
		
		frame.add(panel);
		
		
	}
	
	public void updateobject(String elementName, String fieldName , int xIncrament ,  int yIncrament) {
		
		if(elementName == "textfield") {
			JTextField selectedField = null;

			for(int i = 0 ; i < activeTextFields.size(); i++) {

				if(activeTextFields.get(i).getName() == fieldName )
					selectedField = activeTextFields.get(i);
				}	
			selectedField.setBounds(selectedField.getX() + xIncrament, selectedField.getY() + yIncrament, selectedField.getWidth(), selectedField.getHeight());
			selectedField.updateUI();
			
			console.log("X : " + selectedField.getX() + "\n");
			console.log("Y : " + selectedField.getY() + "\n\n");
			
		}else if(elementName == "button") {
			JButton selectedButton = null;

			for(int i = 0 ; i < activebuttons.size(); i++) {
				if(activebuttons.get(i).getText() == fieldName )
					selectedButton = activebuttons.get(i);
				}	
			selectedButton.setBounds(selectedButton.getX()  + xIncrament, selectedButton.getY() + yIncrament , selectedButton.getWidth() , selectedButton.getHeight() );
			selectedButton.updateUI();
			
			console.log("X : " + selectedButton.getX() + "\n");
			console.log("Y : " + selectedButton.getY() + "\n\n");

		}else if(elementName == "label") {
			JLabel selectedLabel = null;

			for(int i = 0 ; i < activelabels.size(); i++) {
				if(activelabels.get(i).getName() == fieldName )
		
					selectedLabel = activelabels.get(i);
				}	
			selectedLabel.getParent().setBounds(selectedLabel.getX()  + xIncrament, selectedLabel.getParent().getY() + yIncrament , selectedLabel.getParent().getWidth() , selectedLabel.getHeight() );
			selectedLabel.updateUI();
			
			console.log("X : " + selectedLabel.getParent().getX() + "\n");
			console.log("Y : " + selectedLabel.getParent().getY() + "\n\n");

		}else if(elementName == "progressBar") {
			JProgressBar progressBar = null;

			for(int i = 0 ; i < activeProgressBar.size(); i++) {

				if(activeProgressBar.get(i).getName() == fieldName )
					progressBar = activeProgressBar.get(i);
			}
			
			progressBar.setBounds(progressBar.getX()  , progressBar.getY()  , progressBar.getWidth() + xIncrament, progressBar.getHeight() + yIncrament);
			progressBar.updateUI();
			
			console.log("X : " + progressBar.getX() + "\n");
			console.log("Y : " + progressBar.getY() + "\n\n");

		}else if(elementName == "frame") {
			
			frame.setSize(frame.getWidth() + xIncrament, frame.getHeight() + yIncrament);
			
			console.log("X : " + frame.getWidth() + "\n");
			console.log("Y : " + frame.getHeight() + "\n\n");

		}
		
		
		
	
		
	}
	
	public void show() {
		
		frame.setVisible(true);
	}
	
	public void hide() {
		
		frame.setVisible(false);
	}
	
	public void close() {
		
		frame.dispose();
	}
	
	public void update() {
		frame.repaint();	
	}
	
	public void clear() {
		frame.getContentPane().removeAll();
		frame.getContentPane().repaint();
		
		activebuttons.clear();
		activeDropdown.clear();
		activeGridTable.clear();
		activelabels.clear();
		activePasswordFields.clear();
		activeProgressBar.clear();
		activeSliders.clear();
		activeSpinners.clear();
		activeTextFields.clear();
		
		
	}
	
	public GetterInterface getter = new GetterInterface() {

		@Override
		public JTextField getTextField(String ID) {
			
			for(int i = 0 ; i < activeTextFields.size(); i++) {
				if(activeTextFields.get(i).getName().equals(ID))
					return  activeTextFields.get(i);
			}
			
			return null;
		}

		@Override
		public JButton getButton(String ID) {
			
			for(int i = 0 ; i < activebuttons.size(); i++) {
				if(activebuttons.get(i).getName().equals(ID))
					return  activebuttons.get(i);
			}
			
			return null;
		}

		@Override
		public JLabel getLabel(String ID) {
			
			for(int i = 0 ; i < activelabels.size(); i++) {
				if(activelabels.get(i).getName().equals(ID))
					return  activelabels.get(i);
			}
			
			return null;
		}

		@Override
		public JProgressBar getProgressbar(String ID) {
			
			for(int i = 0 ; i < activeProgressBar.size(); i++) {
				if(activeProgressBar.get(i).getName().equals(ID))
					return  activeProgressBar.get(i);
			}
			
			return null;
		}

		@Override
		public JSlider getSlider(String ID) {
			
			for(int i = 0 ; i < activeSliders.size(); i++) {
				if(activeSliders.get(i).getName().equals(ID))
					return  activeSliders.get(i);
			}
			
			return null;
		}

		@Override
		public JComboBox getCombobox(String ID) {
			
			for(int i = 0 ; i < activeDropdown.size(); i++) {
				if(activeDropdown.get(i).getName().equals(ID))
					return  activeDropdown.get(i);
			}
			
			return null;
		}

		@Override
		public JPasswordField getPasswordfield(String ID) {
			
			for(int i = 0 ; i < activePasswordFields.size(); i++) {
				if(activePasswordFields.get(i).getName().equals(ID))
					return  activePasswordFields.get(i);
			}
			
			return null;
		}

		@Override
		public JSpinner getSpinner(String ID) {
			
			for(int i = 0 ; i < activeSpinners.size(); i++) {
				if(activeSpinners.get(i).getName().equals(ID))
					return  activeSpinners.get(i);
			}
			
			return null;
		}

		@Override
		public JTable getGridTable(String ID) {
	
			for(int i = 0 ; i < activeGridTable.size(); i++) {
				if(activeGridTable.get(i).getName().equals(ID))
					return  activeGridTable.get(i);
			}
			
			return null;
		}

	
	};
	
	public labelInterface label = new labelInterface() {
		
		public String getValue(String ID) {
			
			return getter.getLabel(ID).getText();
		}
		
		public void setValue(String ID , String value) {
			getter.getLabel(ID).setText(value);
		}
		
		
		String fontFamliy = "Serif";
		int fontType =  Font.PLAIN;
		int fontSize = 12;
		
		
		
		public void setborder(String ID , Border borderkind) {		
			JLabel selectedLabel = getter.getLabel(ID);
			
			if(selectedLabel != null)
				selectedLabel.setBorder(borderkind);
			
		}
		
		public void setFontFamliy(String ID , String afontFamliy) {
				JLabel selectedLabel = getter.getLabel(ID);
					
				if(selectedLabel != null)
					fontFamliy = afontFamliy;
					updateFont(ID);
		}
		
		public void setFontType(String ID , int afontType) {
				JLabel selectedLabel = getter.getLabel(ID);
					
				if(selectedLabel != null)
					fontType = afontType;
					updateFont(ID);
			
		}
		
		public void setFontSize(String ID ,int size) {
				JLabel selectedLabel = getter.getLabel(ID);
			
				if(selectedLabel != null)
					fontSize = size;
					updateFont(ID);
					
				
		}
		
		public void updateFont(String ID) {
			
			getter.getLabel(ID).setFont(new Font( fontFamliy, fontType, fontSize));
			
		}
		
		public void setAllingment(String ID , int choice) {
			
			getter.getLabel(ID).setHorizontalAlignment(choice);
		}
		
	
	};	
	
	public progressbarInterface progressbar = new progressbarInterface() {
			
		public void setOrientation(String elementName, int chosenOrientation) {
			
			JProgressBar progressBar = null;

			for(int i = 0 ; i < activeProgressBar.size(); i++) {

				if(activeProgressBar.get(i).getName() == elementName )
					progressBar = activeProgressBar.get(i);
			}
			
			progressBar.setOrientation(chosenOrientation);
		}
		
	};
	
	public eventInterface event = new eventInterface() {

	
		@Override
		public void addWindowOpened(Runnable function) {
			
			frame.addWindowListener(new WindowListener() {

				@Override
				public void windowOpened(WindowEvent e) {
					function.run();
					
				}

				@Override
				public void windowClosing(WindowEvent e) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void windowClosed(WindowEvent e) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void windowIconified(WindowEvent e) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void windowDeiconified(WindowEvent e) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void windowActivated(WindowEvent e) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void windowDeactivated(WindowEvent e) {
					// TODO Auto-generated method stub
					
				}
				
			});	
		}

		@Override
		public void addWindowClosing(Runnable function) {
		
			frame.addWindowListener(new WindowListener() {

				@Override
				public void windowOpened(WindowEvent e) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void windowClosing(WindowEvent e) {
					function.run();
					
				}

				@Override
				public void windowClosed(WindowEvent e) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void windowIconified(WindowEvent e) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void windowDeiconified(WindowEvent e) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void windowActivated(WindowEvent e) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void windowDeactivated(WindowEvent e) {
					// TODO Auto-generated method stub
					
				}
				
			});
			
		}

		@Override
		public void addWindowClosed(Runnable function) {

			frame.addWindowListener(new WindowListener() {

				@Override
				public void windowOpened(WindowEvent e) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void windowClosing(WindowEvent e) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void windowClosed(WindowEvent e) {
					function.run();
					
				}

				@Override
				public void windowIconified(WindowEvent e) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void windowDeiconified(WindowEvent e) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void windowActivated(WindowEvent e) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void windowDeactivated(WindowEvent e) {
					// TODO Auto-generated method stub
					
				}
				
			});
			
		}

		@Override
		public void addWindowIconified(Runnable function) {
			
			frame.addWindowListener(new WindowListener() {

				@Override
				public void windowOpened(WindowEvent e) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void windowClosing(WindowEvent e) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void windowClosed(WindowEvent e) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void windowIconified(WindowEvent e) {
					function.run();
					
				}

				@Override
				public void windowDeiconified(WindowEvent e) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void windowActivated(WindowEvent e) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void windowDeactivated(WindowEvent e) {
					// TODO Auto-generated method stub
					
				}
				
			});
			
		}

		@Override
		public void addWindowDeiconified(Runnable function) {
			
			frame.addWindowListener(new WindowListener() {

				@Override
				public void windowOpened(WindowEvent e) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void windowClosing(WindowEvent e) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void windowClosed(WindowEvent e) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void windowIconified(WindowEvent e) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void windowDeiconified(WindowEvent e) {
					function.run();
					
				}

				@Override
				public void windowActivated(WindowEvent e) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void windowDeactivated(WindowEvent e) {
					// TODO Auto-generated method stub
					
				}
				
			});
			
		}

		@Override
		public void addWindowActivated(Runnable function) {
			
			frame.addWindowListener(new WindowListener() {

				@Override
				public void windowOpened(WindowEvent e) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void windowClosing(WindowEvent e) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void windowClosed(WindowEvent e) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void windowIconified(WindowEvent e) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void windowDeiconified(WindowEvent e) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void windowActivated(WindowEvent e) {
					function.run();
					
				}

				@Override
				public void windowDeactivated(WindowEvent e) {
					// TODO Auto-generated method stub
					console.log("yo");
				}
				
			});
			
		}

		@Override
		public void addWindowDeactivated(Runnable function) {
			
			frame.addWindowListener(new WindowListener() {

				@Override
				public void windowOpened(WindowEvent e) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void windowClosing(WindowEvent e) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void windowClosed(WindowEvent e) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void windowIconified(WindowEvent e) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void windowDeiconified(WindowEvent e) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void windowActivated(WindowEvent e) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void windowDeactivated(WindowEvent e) {
					function.run();
					
				}
				
			});
			
		}

	
	};

	public gridInterfacee gridTable = new gridInterfacee() {
		
	 public void setGridColor(String ID , java.awt.Color SelectedColor) {
		 getter.getGridTable(ID).setShowGrid(true);
		 getter.getGridTable(ID).setBorder(border.color(SelectedColor));
		 getter.getGridTable(ID).setGridColor(SelectedColor);
	 }
		
	 public void setBackground(String ID , java.awt.Color SelectedColor) {
		 getter.getGridTable(ID).setBackground(SelectedColor);
	 }
	 
	 public void setSelectModel(String ID , int selectedGridFormats) {
		 
		 if(selectedGridFormats == gridSelect.CellSelect) {
			 getter.getGridTable(ID).setRowSelectionAllowed(true);
		 }else if(selectedGridFormats == gridSelect.ColumnSelect) {
			 getter.getGridTable(ID).setColumnSelectionAllowed(true);
		 }else if(selectedGridFormats == gridSelect.RowSelect) {
			 getter.getGridTable(ID).setRowSelectionAllowed(true);
		 }
		
		 
	 }
	
	 public GridReturn getValues(String ID) {
			int row = getter.getGridTable(ID).getSelectedRow();
			int column = getter.getGridTable(ID).getSelectedColumn();
			JTable hello =  getter.getGridTable(ID);
			System.out.println("hello");
			
			
			return new GridReturn(0 , 0);
		 }

	};
		
	public sliderInterface slider =  new sliderInterface() {

		public void addOnChange(String ID, Runnable callback) {
			
			getter.getSlider(ID).addChangeListener(new ChangeListener() {

				@Override
				public void stateChanged(ChangeEvent e) {
					callback.run();
				}
				
			});
			
		}
		
		public int getvalue(String ID) {
			
			return getter.getSlider(ID).getValue();
		
		}
		
		public int getMin(String ID) {
			
			return getter.getSlider(ID).getMinimum();
		
		}

		public int getMax(String ID) {
	
			return getter.getSlider(ID).getMaximum();

		}
		
		public int getProcentage(String ID) {
			double totall = (getMax(ID) - getMin(ID));
			double procentage = (getvalue(ID) / totall) * 100;
			
			return (int) procentage;

		}
		
	};
	
	public textfieldInterface textfield = new textfieldInterface(){
		
		public String getValue(String ID) {
			
			return getter.getTextField(ID).getText();
		}
		
		public void setValue(String ID , String value) {
			getter.getTextField(ID).setText(value);
		}
		
		String fontFamliy = "Serif";
		int fontType =  Font.PLAIN;
		int fontSize = 12;
		
		public void setborder(String ID , Border borderkind) {		
			JTextField selectedTextfield = getter.getTextField(ID);
			
			if(selectedTextfield != null)
				selectedTextfield.setBorder(borderkind);
			
		}
		
		public void setFontFamliy(String ID , String afontFamliy) {
			JTextField selectedTextfield = getter.getTextField(ID);
					
				if(selectedTextfield != null)
					fontFamliy = afontFamliy;
					updateFont(ID);
		}
		
		public void setFontType(String ID , int afontType) {
			JTextField selectedTextfield = getter.getTextField(ID);
					
				if(selectedTextfield != null)
					fontType = afontType;
					updateFont(ID);
			
		}
		
		public void setFontSize(String ID ,int size) {
			JTextField selectedTextfield = getter.getTextField(ID);
			
				if(selectedTextfield != null)
					fontSize = size;
					updateFont(ID);
				
		}
		
		public void updateFont(String ID) {
			
			getter.getTextField(ID).setFont(new Font( fontFamliy, fontType, fontSize));
		}
		
		public void setAllingment(String ID , int choice) {
			
			getter.getTextField(ID).setHorizontalAlignment(choice);
		}
		
		private int keypressed;
		public int getPressedKey(){
			return keypressed;
		}
		
		public void addKeypressEvent(String ID , Runnable callback) {
			
			JTextField selectedTextfield = getter.getTextField(ID);
			
			selectedTextfield.addKeyListener(new KeyListener() {
				
				@Override
				public void keyTyped(KeyEvent e) {
					// TODO Auto-generated method stub
					
				}
				
				@Override
				public void keyReleased(KeyEvent e) {
					// TODO Auto-generated method stub
					
				}
				
				@Override
				public void keyPressed(KeyEvent e) {
			
					keypressed = e.getKeyCode();
					callback.run();
				
				}
			});
			
		}
		
	};
	
	
}



