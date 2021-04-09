import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.*;

	/**
	*Student is extand of Person , Student add a @param StudentID to the panel
	*its extand {@link Person} 
	*instance variables
	*  studentLabel hold the label for student as extends the array label
	*  studentID  is the ID of the student.
	*  textFieldStudent  textField for input of student
	*  wrongLabelStudent student error star sign to an error for student class
	*@author David Musaiv and Yakir Maimon
	*/
public class Student extends Person
{

	protected String studentLabelString = "StudentID";//the label on the jframe
	private String studentID;//the string that will handle the studentId 
	protected JTextField textFieldStudent=new JTextField("");
	protected JLabel wrongLabelStudent=new JLabel();
	
	
	/**
	*Set and Get Functions
	*/
	//Set student id
	public void setStudentID(String studentID)
	{
	this.studentID=studentID  ;
	}
	
	//Get student id
	public String getStudentID()
	{
		return studentID;
	}
	//Get text to field 
	public String getTextFieldStudent()
	{
		return textFieldStudent.getText();
	}
	
	/**
	*Constructor.
	*Student frame constructor get 4 arguments - Create frame of student
	*@param id the id of the student
	*@param name of the student
	*@param surname of the student
	*@param tel telphone of the student
	*@param studentID the number student of the the student
	*/
	
	public Student(String id,String name,String surname,String tel,String studentID)
	{
		
	//Create the base
	super(id,name,surname,tel);//Constructor of person
	
	setStudentID(studentID);
	//Adopt new changes in the frame and extend the panel layout
	super.frame.setSize(new Dimension(450,220));//Set windows size
	super.cenPanel.setLayout(new GridLayout(5,3,4,10));//Set layout 
	super.frame.setTitle("Student Clubber's Data");;//Set frame title
	
	//First column
	JLabel studentLabel = new JLabel(studentLabelString, JLabel.TRAILING);//Add left column of labels
	studentLabel.setSize(30,70);
	super.addToCenter(studentLabel);
	
	//Second column
	textFieldStudent = new JTextField(getStudentID());
	studentLabel.setLabelFor(textFieldStudent);
	super.addToCenter(textFieldStudent);
	//Third column
	wrongLabelStudent = new JLabel("", JLabel.LEADING);//Add right column of labels
   	wrongLabelStudent.setForeground(Color.red);
	wrongLabelStudent.setLabelFor(textFieldStudent);
	super.addToCenter(wrongLabelStudent);

	}//End of Student
	
	/**
	*Match function will check if key is equal to student id in db
	*@return TRUE- Will return true if key is matching to student id field in this object 
	*@return FALSE- Will return false if key is NOT matching to student id field in this object 
	*/
	@Override
	public boolean match(String key)
	{
		
		if(super.match(key) || (getStudentID().length()==9 && key.equals(getStudentID().substring(4))))
		{
			return true;
		}
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
		boolean matchFound;
		super.validateData();
		
		//If the validate person is flase the break the checking process
		if(super.validateData()==false)
		return false;
		
		String regex="[A-Z][A-Z][A-Z][\\/][1-9][0-9][0-9][0-9][0-9]$";
		Pattern student = Pattern.compile(regex);//pattern: Captial Letter*3 , digits*5
		Matcher matcher = student.matcher(getTextFieldStudent());
		matchFound = matcher.find();
		
		if(matchFound==false)
		{
			wrongLabelStudent.setText("*");//Set red mark near the bad field
			return matchFound;	
		}
		else{
			wrongLabelStudent.setText("");
		}
		
		
		return matchFound;
		
	}//End of validateData
		
	/**
	*Set studentID into the memory
	*/	
	protected void commit()
	{
		
		
		validateData();
		if(validateData()==true)
		{
		super.commit();
		setStudentID(getTextFieldStudent());
		frame.setVisible(false);
		}
		
		
	}//End of commit
	
/**
*Reset the values from the memory to the textField
*/
 protected void rollBack()
 {
	
	super.rollBack();
	
	
	 textFieldStudent.setText(getStudentID());
		 
 }//End of rollBack
	
	
	
}//End of student class