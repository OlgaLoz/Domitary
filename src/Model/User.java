package Model;


/**
 * @version 1.0
 * @created 04-фев-2016 14:59:56
 */
public class User {

	private String login;
	private int password;
	private Role role;
	private int userId;
	public Role m_Role;

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

}