import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
/**
	*Soldier extand of Person , Soldier add a @param personalNum to the panel
	*its extand {@link Person} 
	
	*instance variables
	*  soldierLabel  Hold the label for soldier as extends the array label
	*  personalNum  ID of the soldier.
	* textFieldsoldier  TextField for input of soldier
	*  wrongLabelsoldier  Soldier error star sign to an error for soldier class
	*@author David Musaiv and Yakir Maimon
*/

public class Soldier extends Person
{
	protected String soldierLabelString = "Personal Num";
	private String personalNum;
	protected JTextField textFieldSoldier;
	protected JLabel wrongLabelSoldier;
	
	
	/**
	*Set and Get Functions
	*/
	public void setPersonalNum(String personalNum)
	{
	 this.personalNum=personalNum;
	}
	
	public String getPersonalNum()
	{
		return personalNum;
	}
	public String getTextFieldSoldier()
	{
		return textFieldSoldier.getText();
	}

	
	/**
	*Constructor.
	*soldierFrame constructor - create Frame of soldier
	*/
	
	
	public Soldier(String id,String name,String surname,String tel,String personalNum)
	{
	//Create the base
	super(id,name,surname,tel);//constructor of person
	setPersonalNum(personalNum);
	//Adopt new changes in the frame and extend the panel layout
	super.frame.setSize(new Dimension(450,220));//Set windows size
	super.cenPanel.setLayout(new GridLayout(5,3,4,10));//Set layout 
	super.frame.setTitle("Soldier Clubber's Data");;//Set frame title
	
	//First column
	JLabel soldierLabel = new JLabel(soldierLabelString, JLabel.TRAILING);//Add left column of labels
	soldierLabel.setSize(30,70);
	super.addToCenter(soldierLabel);
	
	//Second column
	textFieldSoldier = new JTextField(getPersonalNum());
	soldierLabel.setLabelFor(textFieldSoldier);
	super.addToCenter(textFieldSoldier);
	//Third column
	wrongLabelSoldier = new JLabel("", JLabel.LEADING);//Add right column of labels
   	wrongLabelSoldier.setForeground(Color.red);
	//WrongLabelSoldier.setLabelFor(textFieldSoldier);
	super.addToCenter(wrongLabelSoldier);
	
	}
	
	
	/**
	*Match function will check if key is equal to personal number in db
	*@return matchFound- Will return true if key is matching to personal number field in this object else false
		*/
	@Override
	public boolean match(String key)
	{
		
		if(super.match(key)|| key.equals(getPersonalNum()))
		return true;
		
		//else
		return false;
		
	}//End of match
	
	/**
	*ValidateDate function return true if the input of the id , name , surname ,tel is valid as pattern requirements . 
	*@return matchFound - will be true if match and if not false
	*/
	@Override
	protected boolean validateData()
	{
	boolean matchFound=true;
	super.validateData();
	
		//If the validate person is flase the break the checking process
	if(super.validateData()==false)
	return false;

	Pattern soldier = Pattern.compile("[R|O|C][\\/][1-9][0-9][0-9][0-9][0-9][0-9][0-9]");//pattern: R or O or C and /  , 7 digits non zero
    Matcher matcher = soldier.matcher(getTextFieldSoldier());
    matchFound = matcher.find();
	
	if(matchFound==false)
	{
		wrongLabelSoldier.setText("*");//Set red mark near the bad field
		return matchFound;	
	}
	else{
		wrongLabelSoldier.setText("");
	}
	
	return matchFound;
	
	}//End of ValidateData
	
	/**
	*Set PersonalNum into the memory
	*/	
	//commit 
	protected void commit()
	{
		validateData();
		
		if(validateData()==true)
		{
		super.commit();
		setPersonalNum(getTextFieldSoldier());
		frame.setVisible(false);
		}
		
	}//End of commit
/**
*Reset the values from the memory to the textField
*/
@Override
 protected void rollBack()
 {
	
	super.rollBack();
	
	textFieldSoldier.setText(getPersonalNum());
	 
		 
 }//End of rollBack 
	
	
	
}//End of Soldier Class