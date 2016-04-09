package main;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.SQLException;

public class DatabaseUtils {
	
	public static Connection getConnection() throws SQLException, NamingException {
		 InitialContext initialContext = new InitialContext();
		 Context context = (Context) initialContext.lookup("java:comp/env");

		 String dataResourceName = "jdbc/dormitory_db";
		 DataSource dataSource = (DataSource) context.lookup(dataResourceName);
		 return dataSource.getConnection();
		 }


	public static void closeConnection(Connection connection) {
		 if (connection != null) {
		 try {
		 connection.close();
		 } catch (SQLException e) {
		// logger.warn("Can't close database connection.", e);
		 }
		 }
		 }

		 public static void closeStatement(Statement statement) {
		 if (statement != null) {
		 try {
		 statement.close();
		 } catch (SQLException e) {
	//	 logger.warn("Can't close statement", e);
		 }
		 }
		 }
	
}
