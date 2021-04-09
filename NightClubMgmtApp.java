import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.ArrayList; 
import java.io.*;

/**
*NightClubMgmtApp is the aplication - user interface
*@author David Musaiv and Yakir Maimon
*/

public class NightClubMgmtApp
{
	/**
	*Night-Club Regular Customers Repository
	*it will hold tha main data structure of the app it based on{@link ClubAbstractEntity}
	*/
	private ArrayList<ClubAbstractEntity> clubbers;//Create arraylist with ClubAbstractEntity
	
	/**
	*Constractor 
	*Create the application
	*1. it will create the main DB of the app - genric ArrayList
	*2.after that it will load the DB - {@link #loadClubbersDBFromFile}
	*3.and it the finale it will create the main gui  and will set it visible-{@link mainGUI}
	*/
	public NightClubMgmtApp()
		{
		
		clubbers = new ArrayList<>();
		loadClubbersDBFromFile();// Load the data
		
		mainGUI mainScreen= new mainGUI();
		mainScreen.setVisible(true);

		}//End of constructor
		
		/**
		*loadClubbersDBFromFile function is Read data from file, create the corresponding objects and put them
		*into clubbers ArrayList
		*it will handle the scenrio that there is no any file in the repsedory and will create it with{@link writeClubbersDBtoFile}
		*/
		private void loadClubbersDBFromFile()
		{
			try(
		FileInputStream fis = new FileInputStream("BKCustomers.dat");//Open new file stream
		ObjectInputStream ois = new ObjectInputStream(fis);//Attached it to object stream
		){
			
		clubbers= ((ArrayList<ClubAbstractEntity>)ois.readObject());//Copy the objects in the file into the array list
		ois.close();//Close the object stream
		}
		catch (FileNotFoundException e) {
							  //If the file not exist creaate a new one
							  writeClubbersDBtoFile();
							  loadClubbersDBFromFile();
							}
		catch (Exception e) {
			
							 
							 System.out.println(e.getMessage());
							 e.printStackTrace();
							}	
		}//End loadClubbersDBFromFile
		

		/**
		*writeClubbersDBtoFile function write all the objects
		*data in clubbers ArrayList into the file
		*/
	private void writeClubbersDBtoFile()
		{
		try(
	 FileOutputStream fos = new FileOutputStream("BKCustomers.dat");
    ObjectOutputStream oos = new ObjectOutputStream(fos);
	){
      oos.writeObject(clubbers);
		oos.flush();
		oos.close();
	}
			catch (Exception e) {
         System.out.println(" couldnt write the file");
         System.out.println(e.getMessage());
	}


		}//End writeClubbersDBtoFile
		
	
		
		/**
		*manipulateDB function is running information dialog to find the relevant contact
		*it will go threw the head of the ArrayList clubbers and then will use the {@link Person#match} 
		*or {@link Student#match} or {@link Soldier#match}
		*/
	private void manipulateDB()
	{
		boolean found = false;
		
		JFrame frame = new JFrame("Search Dialog");
		String searchText = JOptionPane.showInputDialog(
        frame, 
        "Enter the ID number of the member:", 
        "Search for a member", 
        JOptionPane.DEFAULT_OPTION);
				
	
			for(ClubAbstractEntity clubber : clubbers)
			if(clubber.match(searchText))
			{
			found = true;
			clubber.setVisible(true); 
			break;
			}
		if(!found)
		
		 JOptionPane.showMessageDialog(null, String.format("Clubber with key %s does not exist%n", searchText), "alert", JOptionPane.INFORMATION_MESSAGE);
		else found = !found;
		
		
	}// End of method manipulateDB
	
	
	
		/**
		*mainGUI class extand  {@link javax.swing.JFrame} ,
		* and create the main GUI with all the relavant components
		*to handle all the object in the app threw the variant of
		*member types in the app{@link Person},{@link Student},{@link Soldier} 
		*that based on {@link ClubAbstractEntity}
		*the main gui is divided by a grid of 2 lines that has 4 buttons
		*/
		private class mainGUI extends JFrame 
		{
     
	
		//Buttons 
		private JButton buttonSearch = new mainButton("Search");
		private JButton buttonCreatePerson = new mainButton("Create Person");
		private JButton buttonCreateStudent = new mainButton("Create Student");
		private JButton buttonCreateSoldier = new mainButton("Create Soldier");
     
	 
	 /*main constructor*/
		public mainGUI() 
			{
				super("H.B Club");//Title
				super.setResizable(false);//Disable resize of the window 
				super.setDefaultCloseOperation(EXIT_ON_CLOSE);//Enable closing window 
				// Create a new panel with GridBagLayout manager
				JPanel mainPanel = new JPanel(new GridBagLayout());
				 
				GridBagConstraints constraints = new GridBagConstraints();//constraints to fulfil on the panel
				constraints.anchor = GridBagConstraints.WEST;
				constraints.insets = new Insets(10, 10, 10, 10);
				 
				// Add components to the panel
				
				//First line Search button
				constraints.gridx = 1;
				constraints.gridy = 0;     
				constraints.anchor = GridBagConstraints.CENTER;		
				mainPanel.add(buttonSearch, constraints);
				
				
				//new line 
				constraints.gridwidth = 1;
			
				constraints.gridx = 0;
				constraints.gridy = 3;     
				mainPanel.add(buttonCreatePerson, constraints);
				 
				constraints.gridx = 1;
				mainPanel.add(buttonCreateStudent, constraints);
				
				constraints.gridx = 2;
				mainPanel.add(buttonCreateSoldier, constraints);
				

				// Set border for the panel
				mainPanel.setBorder(BorderFactory.createTitledBorder(
						BorderFactory.createEtchedBorder(), "Main menu"));
				 
				// Add the panel to this frame
				add(mainPanel);
				 
				pack();
				setLocationRelativeTo(null);
				
				
			}//End constructor
			

			
				
		}//End mainGUI class
			
			
		/**
		*mainButton class extends JButton and make the buttons able to respond after clicking on it
		*there are 4 buttons:
		*1.search - {@link #manipulateDB}- will search threw the arraylistt 
		*2.create person -{@link Person#Person}- will create an empty person
		*3.create student- {@link Student#Student}- will create an empty Student
		*4.create soldier- {@link Soldier#Soldier}- will create an empty Soldier
		*after clicking on one of the creating buttons object it will put invoke it,
		*and set the jframe of the object to visible{@link #editNewMember}		
		*/	
		private  class mainButton extends JButton implements ActionListener
		{
		ClubAbstractEntity clubber;
		
		//mainButton constructor create the main button
		public mainButton(String text)
		{
		super(text);
		createButton(text);
		}//End of mainButton constructor
		
		private void createButton(String text)
		{
			
			this.setBackground( null );
			this.addActionListener( this );//conect it to the action listener

			 
		}
		/**
		*Action handler of buttons
		*@param  e -  action listener item 
		*/
		public void actionPerformed(ActionEvent e)
		{
			writeClubbersDBtoFile();
			JButton button = (JButton)e.getSource();

			//search button event
			if ("Search".equals(e.getActionCommand()))
			{
				
				
				manipulateDB();
				
			}
				
				
			//Create button events 
			//Person cretate
			if ("Create Person".equals(e.getActionCommand()))
			{
			clubbers.add(new Person("", "", "",""));
				editNewMember();
				
			}
			//Student
			if ("Create Student".equals(e.getActionCommand()))
			
			{
				clubbers.add(new Student("", "", "","", ""));
				editNewMember();
				
				
			}
			//Soldier
			if ("Create Soldier".equals(e.getActionCommand()))
			{
					clubbers.add(new Soldier("", "", "","", ""));
					editNewMember();
					
			}
			
						
		}//End of actionPerformed
		
		
		/**
		*editNewMember function is editing a member of clubber
		*/
		private void editNewMember()
		{
			
			clubber=clubbers.get(clubbers.size()-1);
			
			
			clubber.setVisible(true);
		
		}//End editNewMember class

	}//End mainButton class


		/**
		Main will create the application
		*/
		public static void main(String[] args)
		{
			NightClubMgmtApp appliction = new NightClubMgmtApp(); //Create the appliction
			
			
		}//End of Main
		
}//End of NightClubMgmtApp
	
		
		

