package Model;

import java.sql.Date;

/**
 * @version 1.0
 * @created 04-фев-2016 14:59:56
 */
public class Settler {

	private boolean bodyCheck;
	private String contract;
	private Date DateOfBirth;
	private Date DateOfSettlement;
	private String firstName;
	private String groupNumber;
	private String lastName;
	private String midName;
	private String order;
	public Place m_Place;

	public Settler(){

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

}