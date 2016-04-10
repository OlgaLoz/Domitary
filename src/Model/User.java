package Model;

public class User {

	private String login;
	private int password;
	private Role role;
	private int userId;

	public User(){}

	public User(String login, int password, Role role){
		this.login = login;
		this.password = password;
		this.role = role;
	}

	public String getLogin(){
		return login;
	}

	public int getPassword(){
		return password;
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

	public void setPassword(int password){
		this.password = password;
	}

	public void setUserId(int userId){
		this.userId = userId;
	}

	public void setRole(Role role){
		this.role = role;
	}
}