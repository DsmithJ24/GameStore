import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

//testing the git clone
//This one is based off the files Logan shared in Discord. Work on this one

public class DB_Controller {
	Connection connection;
	String sql;
	String DB_PATH = DB_Controller.class.getResource("store_inventory.sqlite").getFile();
	
	//used for testing
	//String DB_PATH = DB_Controller.class.getResource("Chinook_Sqlite_AutoIncrementPKs.sqlite").getFile();
	
	
	public DB_Controller() throws ClassNotFoundException, SQLException {
		//load the sqlite-JDBC driver
		Class.forName("org.sqlite.JDBC");
		
		//protocl (jdbc): subprotocol (sqlite):databaseName (store_inventory.sqlite)
		connection = DriverManager.getConnection("jdbc:sqlite:" + DB_PATH);
	}
	
	public void run() throws SQLException {
		Scanner input = new Scanner(System.in);
		
		//now get the inputs
		System.out.print("Enter an artist (type n/a for none): ");
		String param = input.next();
		
		//generate parameterized sql
		if (param.equalsIgnoreCase("n/a")) {
			//this will fetch the Console, price, and #in stock for entered title
			//sql = "stuff";
		/*
			//testing
			sql = "SELECT art.Name AS art_name, alb.Title AS alb_title" +
			" FROM album alb INNER JOIN artist art USING (ArtistId)" +
			" ORDER BY art_name, alb_title";
		*/
		
			
		} else {
			//probably just have this print a message or whole inventory
			//sql = "other stuff";
			
		/*	
			//testing
			sql = "SELECT art.Name AS art_name, alb.Title AS alb_title" +
			" FROM artist art INNER JOIN album alb USING (ArtistId)" +
			" WHERE art.Name LIKE ?" +
			" ORDER BY art_name, alb_title";
		*/
			
		}
		
		System.out.println("\nSQL: " + sql + "\n");
		
		/*
		 * prepare statement (never use string concatention to build query)
		 * to avoid SQL injection
		 * never do "Where art.Name LIKE" + param + "ORDER BY..."
		 */
		
		PreparedStatement stmt = connection.prepareStatement(sql);
		
		/*
		 * bind parameter(s)
		 * In SQLite "?" is a placeholder (acts like variable)
		 * 1 is the first place holder, 2 is second, etc.
		 */
		
		if (!param.equalsIgnoreCase("n/a")) {
			stmt.setString(1, param); //this is a dummy, replace based on database
			
		}
		
		//get results
		ResultSet res = stmt.executeQuery();
		while(res.next()) {
			System.out.println("<" + res.getString("art_name") + ">" + res.getString("alb_title")); //again will change later
			
		}
	}
	
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		DB_Controller controller = new DB_Controller();
		controller.run();
	}
}
