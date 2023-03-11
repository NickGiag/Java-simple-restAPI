package studentCRUD.dao.dbutil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {
	private static Connection conn;
	
	private DBUtil() {}
	
	public static Connection openConnection() throws SQLException {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			String url = "jdbc:mysql://localhost:3306/studentdb?serverTimezone=UTC";
			String username = "user123";
			String password = "123";
			
			conn = DriverManager.getConnection(url, username, password);
			return conn;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static void closeConnection() throws SQLException {
		conn.close();
	}
}
