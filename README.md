# ClubManger

## Porpuse
managing clients and pricing of members 

## classes and interfaces

### classes
#### *ClubAbstractEntity is a Abstract class that create the Panel and Frame for his heirs (Person ,studnet, soldier).
ClubAbstractEntity is extand of JFrame .

#### Person
Class implement the person uses in herarchy ,Person use JTextField for id , name ,surname and tel.
in this version of app it has 2 extantion {@link Student} and {@link Soldier}  
the Person is extand of {@link ClubAbstractEntity} .

#### Soldier
Soldier extand of Person , Soldier add a @param personalNum to the panel

#### Student
*Student is extand of Person , Student add a @param StudentID to the panel
	*its extand {@link Person} 

#### User interfaces
*NightClubMgmtApp is the aplication - user interface



## DB
All the data will be writed into local file that will use the app as a DB
