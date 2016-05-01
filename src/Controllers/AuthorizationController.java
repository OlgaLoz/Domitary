package Controllers;

import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.ServletActionContext;

public class AuthorizationController extends ActionSupport{
	private String name;

	public String run() throws Exception {
		ServletActionContext.getRequest();
		return "success";
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}