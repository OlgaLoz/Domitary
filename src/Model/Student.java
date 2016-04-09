package Model;


import java.sql.Date;

/**
 * @version 1.0
 * @created 10-Apr-2016 00:12:00
 */
public class Student {

	private boolean bodyCheck;
	private String contract;
	private Date dateOfBirth;
	private Date dateOfSettlement;
	private String firstName;
	private String groupNumber;
	private String lastName;
	private String midName;
	private String order;
	private int placeId;
	private int studentId;
	private Boolean isSettled;
	private String statement;

	public Student(){

	}

	public void finalize() throws Throwable {

	}
	public String getContract(){
		return "";
	}

	public Date getDateOfBirth(){
		return null;
	}

	public Date getDateOfSettlement(){
		return null;
	}

	public String getFirstName(){
		return "";
	}

	public String getGroupNumber(){
		return "";
	}

	public String getLastName(){
		return "";
	}

	public String getMidName(){
		return "";
	}

	public String getOrder(){
		return "";
	}

	public int getPlaceId(){
		return 0;
	}

	public int getStudentId(){
		return 0;
	}

	public Boolean getIsSettled() { return null; }

	public String getStatement() { return ""; }

	public boolean isBodyCheck(){
		return false;
	}

	/**
	 * 
	 * @param bodyCheck
	 */
	public void setBodyCheck(boolean bodyCheck){

	}

	/**
	 * 
	 * @param contract
	 */
	public void setContract(String contract){

	}

	/**
	 * 
	 * @param dateOfBirth
	 */
	public void setDateOfBirth(Date dateOfBirth){

	}

	/**
	 * 
	 * @param dateOfSettlement
	 */
	public void setDateOfSettlement(Date dateOfSettlement){

	}

	/**
	 * 
	 * @param firstName
	 */
	public void setFirstName(String firstName){

	}

	/**
	 * 
	 * @param groupNumber
	 */
	public void setGroupNumber(String groupNumber){

	}

	/**
	 * 
	 * @param lastName
	 */
	public void setLastName(String lastName){

	}

	/**
	 * 
	 * @param midName
	 */
	public void setMidName(String midName){

	}

	/**
	 * 
	 * @param order
	 */
	public void setOrder(String order){

	}

	/**
	 * 
	 * @param placeId
	 */
	public void setPlaceId(int placeId){

	}

	/**
	 * 
	 * @param studentId
	 */
	public void setStudentId(int studentId){

	}

	/**
	 *
	 * @param isSettled
	 */
	public void setIsSettled(Boolean isSettled){

	}

	/**
	 *
	 * @param statement
	 */
	public void setStatement(String statement){

	}
}//end Student