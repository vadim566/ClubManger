import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.*;
/**
* This Class implement the person uses in herarchy ,Person use JTextField for id , name ,surname and tel.
*in this version of app it has 2 extantion {@link Student} and {@link Soldier}  
*the Person is extand of {@link ClubAbstractEntity} .
*@author David Musaiv and Yakir Maimon
*/

public class Person extends ClubAbstractEntity
{
/**
* variables
* String id - ID number of the person
* String name - Namae of the person
* String surename - Last name of the person
* String tel- Phone number of the person
*/
private String id ;
private String name;
private String surename;
private String tel;

/**
*Arrays variables in the panel
* String[] labels - Handle array of lables for GUI
* JTextField[] textField - Handle array of text fields to store the data.
* JLabel[] wrongLabel - Handle array of JLabels to store star sign for errors.
*/
protected String[] labels = { "ID: ", "Name: ", "Surname: ", "Tel: ",};//labels on the gui
protected JTextField[] textField=new JTextField[5];//text field to handle and place in the gui
protected JLabel[] wrongLabel=new JLabel[5];//label componnent for gui


	/**
	*Set Functions
	*/
	//Set person id
	public void setId(String id)
	{
		this.id=id;
	}
	//Set person name
	public void setName(String name)
	{
		this.name=name;
	}
	//Set person last name
	public void setSureName(String surename)
	{
		this.surename=surename;
	}
	//Set person telephone  number
	public void setTel(String tel)
	{
		this.tel=tel;
	}

	/**
	*Get Functions
	*/
	//Get person id
	public String getId()
	{
		return this.id;
	}
	//Get person name
	public String getName()
	{
		return this.name;
	}
	//Get person last Name
	public String getSureName()
	{
		return this.surename;
	}
	//Get person telephone number
	public String getTel()
	{
		return this.tel;
	}
	
	//Get info in text box
	public String getTextbox(int i)
	{
		return textField[i].getText();
	}
	//Set info in text box
	public void setTexBox(int i,String text)
	{
		textField[i].setText(text);
	}
	
	/**
	*Constructor.
	*Person constructor	with 4 arguments id , name , surename tel.
	*@param id  id of the person
	*@param name name of the person
	*@param surename of the person
	*@param tel telphone of the person
	*/
public Person(String id,String name,String surename,String tel )//4 string constructor
{
	super();//Use constructor of the abstract class
	super.frame.setSize(new Dimension(450,220));//Set and define windows size
	super.cenPanel.setSize(new Dimension(400,200));
	//super.frame.add(buttonsHandler);
	super.cenPanel.setLayout(new GridLayout(4,3,4,20));//Set Panel layout 
	super.frame.setTitle("Person Clubber's Data");//Set frame's title
	
	//Set id,name,surname,tel
	setId(id);
	setName(name);
	setSureName(surename);
	setTel(tel);
	
	for (int i = 0; i < labels.length; i++) {
	//First column
      JLabel PersonLabel = new JLabel(labels[i], JLabel.TRAILING);//Add left column of labels
	  PersonLabel.setSize(30,70); //Set size of label
      addToCenter(PersonLabel);
	  
	  //Add center column of textField
	  switch (i)
	  {
	  case 0:
	    textField[i] = new JTextField(getId());
	  break;
	  case 1:
	    textField[i] = new JTextField(getName());
	  break;
	  case 2:
	    textField[i] = new JTextField(getSureName());
	  break;
	  case 3:
	   textField[i] = new JTextField(getTel());
	  break;
	  default:
	  break;
	  }
	  //Second column
	  //Create the label and send it as componnent
     PersonLabel.setLabelFor(textField[i]);
     addToCenter(textField[i]);
	 //Third column
	 //Add space for red star ,Red star sign a bad input.it will appear on the right side of the text field.
	 wrongLabel[i] = new JLabel(" ", JLabel.LEADING);//Add right column of labels
   	 wrongLabel[i].setForeground(Color.red);
	 addToCenter(wrongLabel[i]);
	 
	 
    }
	
}//End of Person constructor
/**Choosing the visability of frame */

public void setVisible(boolean vis)
	{
		super.frame.setVisible(vis);
	}//End of setVisible
	
@Override
protected void addToCenter(Component guiComponent)
{
	super.addToCenter(guiComponent);//Set to center panel and choose the relevant GUI Component
	
}//End of addToCenter

/**
*Match function will check if key is equal to id in db
*@param  key - Holding a string that will be tested in this function, has to be id.
*@return TRUE- Will return true if key is matching to id field in this object 
*@return FALSE- Will return false if key is NOT matching to id field in this object 
*/
public boolean match(String key)
{

	if(key.equals(getId()))
	return true;
	return false;
}//End of match 

/**
ValidateDate function return true if the input of the id , name , surname ,tel is valid as pattern requirements . 
* String[] patterns - Handle array of uniqe pattern for the 4 different text fields : id , name , surname ,tel.
* index - index for each text field pattern
*@return matchFound - For cases that there is a pattern match -> true else false
*/
protected boolean validateData()
{
	 boolean matchFound=true;
	 int index=-1;
	 String [] patterns={"[0-9][-][0-9][0-9][0-9][0-9][0-9][0-9][0-9][||][1-9]","[A-Z][a-z]+","[A-Z][a-z]*[']?[-]?+","[+][(][[[1-9]?[1-9]?[1-9]?][)][[1-9]?[1-9]?[1-9]?][-][0-9][0-9][0-9][0-9][0-9][0-9][0-9]]"};
	
	//Check all the field of textbox if its regex
	while(matchFound == true && index<3)
	{
	//Id pattern check
	index++;
	Pattern patternList = Pattern.compile(patterns[index]);
    Matcher matcher = patternList.matcher(getTextbox(index));
    matchFound = matcher.find();
	
	//If its not matching put red star on the right sed of the relevant text field
	if(matchFound==false)
	{
		wrongLabel[index].setText("*");//Set red mark near the bad fields
		 wrongLabel[index].setForeground(Color.red);
	}
	else{
		wrongLabel[index].setText("");
	}
	
	
	
	}
		
	
	return matchFound;
	 
	 
	
}//End of ValidateData

/**
*Set the values into the memory
*/
protected void commit()
{
	if(validateData())
	{
	setId(getTextbox(0));
	setName(getTextbox(1));
	setSureName(getTextbox(2));
	setTel(getTextbox(3));
	frame.setVisible(false);
	}
	
}//End of commit
/**
*Reset the values from the memory to the textField
*/
 protected void rollBack()
	{
	setTexBox(0,getId());
	setTexBox(1,getName());
	setTexBox(2,getSureName());
	setTexBox(3,getTel());

	}//End of rollBack
 }//End of Person Class

	
 


