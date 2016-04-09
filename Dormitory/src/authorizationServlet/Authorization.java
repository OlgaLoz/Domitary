package authorizationServlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import main.DatabaseUtils;

import javax.naming.NamingException;
import java.sql.*;


/**
 * Servlet implementation class Authorization
 */
@WebServlet("/Authorization")
public class Authorization extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Authorization() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	//	response.getWriter().append("Served at: ").append(request.getContextPath());
		Connection cc = null; 
		Statement s = null;
		
		try {
			cc = DatabaseUtils.getConnection();
		s = cc.createStatement();
	      // SQL код:
		s.executeQuery("SET CHARACTER SET UTF8");
		s.executeQuery("SET CHARSET UTF8");
		s.executeQuery("SET NAMES UTF8");
	      ResultSet r = s.executeQuery("SELECT * FROM Role");
	      while (r.next()) {
	         // Регистр не имеет значения:
	    	  response.getWriter().println(r.getString("ID_Role") + ": "
	               + r.getString("RoleName"));
	      }
	      
		}
		catch (NamingException ex) {  }
		catch (SQLException ex) {  }
		finally {
	      DatabaseUtils.closeStatement(s);
	      DatabaseUtils.closeConnection(cc);
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
