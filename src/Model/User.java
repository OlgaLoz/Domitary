package Model;


/**
 * @version 1.0
 * @created 10-Apr-2016 00:12:00
 */
public class User {

	private String login;
	private int password;
	private Role role;
	private int userId;

	public User(){

	}

	public void finalize() throws Throwable {

	}
	public String getLogin(){
		return "";
	}

	public int getPassword(){
		return 0;
	}

	public Role getRole(){
		return null;
	}

	public int getUserId(){
		return 0;
	}

	/**
	 * 
	 * @param login
	 */
	public void setLogin(String login){

	}

	/**
	 * 
	 * @param password
	 */
	public void setPassword(int password){

	}

	/**
	 * 
	 * @param userId
	 */
	public void setUserId(int userId){

	}
}//end User