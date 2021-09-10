package conexaojdbc;

import java.sql.Connection;
import java.sql.DriverManager;

public class SingleConnect {
	private static String url = "jdbc:postgresql://localhost:5432/webfullstack";
	private static String user = "postgres";
	private static String password = "root123";
	private static Connection connection = null;
	
	public SingleConnect() {
		conectar();
	}
	
	static {
		conectar();
	}
	
	private static void conectar() {
		try {
			if(connection == null) {
				Class.forName("org.postgresql.Driver");
				connection = DriverManager.getConnection(url, user, password);
				connection.setAutoCommit(false);
				System.out.println("Conectado minino");
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static Connection getConnection() {
		return connection;
	}
	
}
