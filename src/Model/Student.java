package Model;

import java.sql.Date;

public class Student {

	private int studentId;
	private String firstName;
	private String midName;
	private String lastName;
	private Date dateOfBirth;
	private String groupNumber;
	private String statement;
	private Date dateOfSettlement;
	private String order;
	private String contract;
	private int roomId;
	private StudentStatus studentStatus;
	private int userID;

	public Student(){  }

	public int getStudentId() { return studentId; }

	public int getUserId() { return userID; }

	public String getFirstName() { return firstName; }

	public String getMidName(){
		return midName;
	}

	public String getLastName(){
		return lastName;
	}

	public Date getDateOfBirth(){
		return dateOfBirth;
	}

	public String getGroupNumber(){
		return groupNumber;
	}

	public String getStatement() { return statement; }

	public Date getDateOfSettlement() { return dateOfSettlement; }

	public String getOrder(){
		return order;
	}

	public String getContract() { return contract; }

	public int getRoomId(){
		return roomId;
	}

	public StudentStatus getStudentStatus() { return studentStatus; }


	public void setStudentId(int studentId){
		this.studentId = studentId;
	}

	public void setUserId(int userId){
		this.userID = userId;
	}

	public void setFirstName(String firstName){
		this.firstName = firstName;
	}

	public void setMidName(String midName){
		this.midName = midName;
	}

	public void setLastName(String lastName){
		this.lastName = lastName;
	}

	public void setDateOfBirth(Date dateOfBirth){
		this.dateOfBirth = dateOfBirth;
	}

	public void setGroupNumber(String groupNumber){
		this.groupNumber = groupNumber;
	}

	public void setStatement(String statement){
		this.statement = statement;
	}

	public void setDateOfSettlement(Date dateOfSettlement){
		this.dateOfSettlement = dateOfSettlement;
	}

	public void setOrder(String order){
		this.order = order;
	}

	public void setContract(String contract){
		this.contract = contract;
	}

	public void setRoomId(int roomId){
		this.roomId = roomId;
	}

	public void setStudentStatus(StudentStatus studentStatus){
		this.studentStatus = studentStatus;
	}

}