import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

/**
*ClubAbstractEntity is a Abstract class that create the Panel and Frame for his heirs (Person ,studnet, soldier).
*ClubAbstractEntity is extand of JFrame .
* frame  Create frame.
*  botPanel Creater bottom panel.
*  buttonsHandler  Handler for bottom.
*  cenPanel  Create center panel.
*@author David Musaiv and Yakir Maimon
*/
abstract public class ClubAbstractEntity extends JFrame
{
	protected JFrame frame;
	protected JPanel botPanel;
	protected ButtonsHandler buttonsHandler;
	protected JPanel cenPanel;
	boolean firstIn=false;
	
	/**
	*Constructors with none parametrs
	*/
	public ClubAbstractEntity()
	{
		
		//Initializtion and creatinon of MUST objects
		frame = new JFrame("H.B Club");// Create a frame with title H.B Club
		botPanel= new JPanel();//Jpanel for holding the botton panel
		buttonsHandler= new ButtonsHandler(botPanel);//Creation if button panel
		cenPanel=  new JPanel(); //Jpanel for holding the center panel
		cenPanel.setSize(new Dimension(400,200));//Set size of the panel
		
		//Frame creation and placment
		frame.setSize(500, 500);//Set size of the screen @not common
		frame.setLocationRelativeTo( null );//Put in the center of screen @common
		frame.setResizable(false);//Disable resize of the window @common
		frame.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);//Disable closing window @common
		
		//Put button in bottom of the screen @common
		frame.add(cenPanel,BorderLayout.SOUTH);//Put the main JPanel with the info of types of members in the center
		frame.setVisible(false);//Show it to the user @not common
		frame.add(buttonsHandler,BorderLayout.SOUTH);//Add the status bar at the bottom
		
	}//End of ClubAbstractEntity Constructor


/**
*addToCenter function will put in the center of the GUI the polymorfic window that will be showen to the user
*@param  guiComponent - a component of GUI that will be transferd into this area
*/
	protected void addToCenter(Component guiComponent)
	{
	this.cenPanel.add(guiComponent);//set center panel with relavant GUI Component
	this.frame.add(cenPanel,BorderLayout.CENTER);////Add the status bar at the center
	}
	
	/**
	*Abstract function for its heirs
	*/
	public abstract boolean match(String key);/**will find match at the db*/
	protected  abstract void commit();/**will commit the data to db*/
	protected  abstract void rollBack();/**will rollback the info in the gui like in the db*/
	protected abstract boolean validateData();/**will check if the data in the gui is in the pattern*/
	

	/**
	*Inner class that handling button events
	*/
	private  class ButtonsHandler extends JPanel implements ActionListener
	{
		/**
		*Function that create a button by its text
		* String text - which text is going to be showen on the JButton
		* JButton object with none background and with text
		*/
		JPanel botPanel= new JPanel();//create an temp  JPanel to place the buttons
		 private JButton buttonOk = new JButton("Ok");
		  private JButton buttonCancel = new JButton("Cancel");
		/**
		*Class constructor 
		*@param botPanel - will create a panel with buttons 'ok' and 'cancel' buttons will placed on JPanel
		*/
		public ButtonsHandler(JPanel botPanel)
		{
			this.botPanel = botPanel;
			
			add(createButton(buttonOk) );
			add(createButton(buttonCancel));
			buttonCancel.setEnabled(false);
					
		}
		
		
		/**
		*Function that create a button by its text
		*@param  button - the button we want to implement an action listener on it
		*@return JButton object with none background and with text
		*/
		private JButton createButton(JButton button)
		{
			
			button.setBackground( null );
			button.addActionListener( this );//TODO CHECK WHAT IS THIS

			return button;
		}
		
		/**
		*Action handler of buttons
		*@param  e -  action listener item 
		*/
		public void actionPerformed(ActionEvent e)
		{
			JButton button = (JButton)e.getSource();

			//Ok button event
			if ("Ok".equals(e.getActionCommand()))
			{
				if(firstIn==false)
				{
					buttonCancel.setEnabled(true);
					firstIn=true;
				}
				commit();
			}			
			//Cancel button event
			if ("Cancel".equals(e.getActionCommand()))
			{

				rollBack();//RollBack the info
			}
			
		}//End of actionPerformed
		
		
	}//End of ButtonsHandler class


}//End of ClubAbstractEntity Class