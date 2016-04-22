package Model;

public class User {

	private String login;
	private byte[] password;
	private byte[] salt;
	private Role role;
	private int userId;

	public User(){}

	public User(String login, byte[] password, Role role){
		this.login = login;
		this.password = password;
		this.role = role;
	}

	public String getLogin(){
		return login;
	}

	public byte[] getPassword(){
		return password;
	}

	public byte[] getSalt(){
		return salt;
	}

	public Role getRole(){
		return role;
	}

	public int getUserId(){
		return userId;
	}

	public void setLogin(String login){
		this.login = login;
	}

	public void setPassword(byte[] password){
		this.password = password;
	}

	public void setUserId(int userId){
		this.userId = userId;
	}

	public void setRole(Role role){
		this.role = role;
	}

	public void setSalt(byte[] salt){
		this.salt = salt;
	}
}