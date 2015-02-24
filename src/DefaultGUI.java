import java.awt.*;

import javax.swing.*;

import java.awt.event.*;

import javax.swing.JOptionPane;

public class DefaultGUI {
	
	DefaultGUI(){
		
	}

	
	public static void dropdownselect(){
		String[] searchtypes = {"getmatchhistory","getmatchdetails","getheroes","getplayersummaries"};
		
		String input = (String) JOptionPane.showInputDialog(null, "Choose now...",
		        "Search Options", JOptionPane.QUESTION_MESSAGE, null, searchtypes, searchtypes[1]); // Initial choice
		    System.out.println(input);
		    
		inputManagement.searchType = input;
	}

}
